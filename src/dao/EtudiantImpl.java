package dao;

import entity.Classe;
import entity.Etudiant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EtudiantImpl implements IEtudiant {

    private DB db = new DB();
    private ResultSet rs;
    private int ok;

    @Override
    public int add(Etudiant etudiant) {
        String sql = "INSERT INTO etudiant VALUES(DEFAULT,?,?,?,?,?)";
        try{
            int idClasse = etudiant.getClasse().getId();
            db.initPrepar(sql);
            db.getPstm().setString(1, etudiant.generateMatricule());
            db.getPstm().setString(2, etudiant.getNom());
            db.getPstm().setString(3, etudiant.getPrenom());
            db.getPstm().setDouble(4, etudiant.getMoyenne());
            db.getPstm().setInt(5, idClasse);
            ok = db.executeMaj();
            if(ok == 1){
                String raq = "UPDATE classe SET effectif = effectif + 1 WHERE id =?";
                db.initPrepar(raq);
                db.getPstm().setInt(1, idClasse);
                ok = db.executeMaj();
            }
            db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ok;
    }

    @Override
    public int update(Etudiant etudiant) {
        String selectClasseSql = "SELECT nom FROM classe WHERE id = ?"; // Nouvelle requête
        String selectSql = "SELECT classe_id, matricule FROM etudiant WHERE id = ?";
        String updateSql = "UPDATE etudiant SET matricule = ?, nom = ?, prenom = ?, moyenne = ?, classe_id = ? WHERE id = ?";
        String existingMatricule = null;
        int existingClasseId = -1;
        int ok = 0;

        try {
            // Étape 1 : Récupérer l'ancienne classe et le matricule
            db.initPrepar(selectSql);
            db.getPstm().setInt(1, etudiant.getId());
            ResultSet rs = db.executeSelect();

            if (rs.next()) {
                existingClasseId = rs.getInt("classe_id");
                existingMatricule = rs.getString("matricule");
            }
            rs.close();
            db.closeConnection();

            // Étape 2 : Si la classe a changé, récupérer le NOM de la nouvelle classe
            if (existingMatricule != null && existingClasseId != etudiant.getClasse().getId()) {
                // Récupérer le nom de la nouvelle classe depuis la DB
                db.initPrepar(selectClasseSql);
                db.getPstm().setInt(1, etudiant.getClasse().getId());
                ResultSet rsClasse = db.executeSelect();

                if (rsClasse.next()) {
                    String nomClasse = rsClasse.getString("nom");
                    // Extraire la date et construire le nouveau matricule
                    String regex = "ET@(\\d{14})(.*?)#";
                    Matcher matcher = Pattern.compile(regex).matcher(existingMatricule);
                    if (matcher.find()) {
                        String datePart = matcher.group(1);
                        String newMatricule = "ET@" + datePart + nomClasse + "#";
                        etudiant.setMatricule(newMatricule);
                    }
                }
                rsClasse.close();
                db.closeConnection();
            }

            // Étape 3 : Exécuter la mise à jour
            db.initPrepar(updateSql);
            db.getPstm().setString(1, etudiant.getMatricule());
            db.getPstm().setString(2, etudiant.getNom());
            db.getPstm().setString(3, etudiant.getPrenom());
            db.getPstm().setDouble(4, etudiant.getMoyenne());
            db.getPstm().setInt(5, etudiant.getClasse().getId());
            db.getPstm().setInt(6, etudiant.getId());
            ok = db.executeMaj();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.closeConnection();
        }
        return ok;
    }

    @Override
    public int delete(int id) {
        String sqlSelect = "SELECT classe_id FROM etudiant WHERE id = ?";
        String sqlDelete = "DELETE FROM etudiant WHERE id = ?";
        try {
            db.initPrepar(sqlSelect);
            db.getPstm().setInt(1, id);
            ResultSet rs = db.executeSelect();

            int idClasse = -1;
            if (rs.next()) {
                idClasse = rs.getInt("classe_id");
            }
            rs.close();

            db.initPrepar(sqlDelete);
            db.getPstm().setInt(1, id);
            ok = db.executeMaj();

            if (ok == 1 && idClasse != -1) {
                String sqlUpdate = "UPDATE classe SET effectif = effectif - 1 WHERE id = ?";
                db.initPrepar(sqlUpdate);
                db.getPstm().setInt(1, idClasse);
                ok = db.executeMaj();
            }
            db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ok;
    }


    @Override
    public List<Etudiant> list() {
        String sql = "SELECT * FROM etudiant ORDER BY nom ASC";
        List<Etudiant> etudiants = new ArrayList<>();
        try {
            db.initPrepar(sql);
            rs = db.executeSelect();
            while (rs.next()){
                Etudiant etudiant = new Etudiant();
                etudiant.setId(rs.getInt("id"));
                etudiant.setMatricule(rs.getString("matricule"));
                etudiant.setNom(rs.getString("nom"));
                etudiant.setPrenom(rs.getString("prenom"));
                etudiant.setMoyenne(rs.getDouble("moyenne"));
                etudiant.setClasse(new Classe(rs.getInt("classe_id")));
                etudiants.add(etudiant);
            }
            db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return etudiants;
    }

    @Override
    public Etudiant get(int id) {
        String sql = "SELECT * FROM etudiant WHERE id = ?";
        Etudiant etudiant = null;
        try {
            db.initPrepar(sql);
            db.getPstm().setInt(1, id);
            rs = db.executeSelect();
            if (rs.next()){
                etudiant = new Etudiant();
                etudiant.setId(rs.getInt("id"));
                etudiant.setMatricule(rs.getString("matricule"));
                etudiant.setNom(rs.getString("nom"));
                etudiant.setPrenom(rs.getString("prenom"));
                etudiant.setMoyenne(rs.getDouble("moyenne"));
                etudiant.setClasse(new Classe(rs.getInt("classe_id")));
            }
            db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return etudiant;
    }

    @Override
    public List<Etudiant> getEtudiantsByClasse(String classe) {
        List<Etudiant> etudiants = new ArrayList<>();
        String sql = "SELECT * FROM etudiant e, classe c WHERE e.classe_id = c.id AND c.nom LIKE ?";

        try {
            db.initPrepar(sql);
            db.getPstm().setString(1, "%" +classe+ "%");

            ResultSet rs = db.getPstm().executeQuery();

            while (rs.next()) {
                Etudiant etudiant = new Etudiant();
                etudiant.setId(rs.getInt("id"));
                etudiant.setMatricule(rs.getString("matricule"));
                etudiant.setNom(rs.getString("nom"));
                etudiant.setPrenom(rs.getString("prenom"));
                etudiant.setMoyenne(rs.getDouble("moyenne"));

                IClasse dao = new ClasseImpl();
                Classe cl = dao.get(rs.getInt("classe_id"));
                etudiant.setClasse(cl);

                etudiants.add(etudiant);
            }
            db.closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return etudiants;
    }
}
