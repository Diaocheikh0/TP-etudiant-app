package dao;

import entity.Classe;
import entity.Etudiant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EtudiantImpl implements IEtudiant {

    private DB db = new DB();
    private ResultSet rs;
    private int ok;

    @Override
    public int add(Etudiant etudiant) {
        String sql = "INSERT INTO etudiant(id, matricule, nom, prenom, moyenne, classe_id) VALUES(DEFAULT,?,?,?,?,?)";
        try{
            db.initPrepar(sql);
            db.getPstm().setString(1, etudiant.getMatricule());
            db.getPstm().setString(2, etudiant.getNom());
            db.getPstm().setString(3, etudiant.getPrenom());
            db.getPstm().setDouble(4, etudiant.getMoyenne());
            db.getPstm().setInt(5, etudiant.getClasse().getId());
            ok = db.executeMaj();
            db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ok;
    }

    @Override
    public int update(Etudiant etudiant) {
        String sql = "UPDATE etudiant SET matricule = ?, nom = ?, prenom = ?, moyenne = ?, classe_id = ? WHERE id = ?";
        try{
            db.initPrepar(sql);
            db.getPstm().setString(1, etudiant.getMatricule());
            db.getPstm().setString(2, etudiant.getNom());
            db.getPstm().setString(3, etudiant.getPrenom());
            db.getPstm().setDouble(4, etudiant.getMoyenne());
            db.getPstm().setInt(5, etudiant.getClasse().getId());
            db.getPstm().setInt(6, etudiant.getId());
            ok = db.executeMaj();
            db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ok;
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM etudiant WHERE id = ?";
        try{
            db.initPrepar(sql);
            db.getPstm().setInt(1, id);
            ok = db.executeMaj();
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
        String sql = "SELECT * FROM etudiant WHERE classe_nom = ?";

        try {
            db.initPrepar(sql);
            db.getPstm().setString(1, classe);

            ResultSet rs = db.executeSelect();

            while (rs.next()) {
                Etudiant etudiant = new Etudiant();
                etudiant.setId(rs.getInt("id"));
                etudiant.setMatricule(rs.getString("matricule"));
                etudiant.setNom(rs.getString("nom"));
                etudiant.setPrenom(rs.getString("prenom"));
                etudiant.setMoyenne(rs.getDouble("moyenne"));

                Classe cl = new Classe();
                cl.setNom(rs.getString("classe_nom"));
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
