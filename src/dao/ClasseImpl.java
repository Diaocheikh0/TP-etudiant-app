package dao;

import entity.Classe;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClasseImpl implements IClasse{

    private DB db = new DB();
    private ResultSet rs;
    private int ok;


    @Override
    public int add(Classe classe) {
        String sql = "INSERT INTO classe(id, nom) VALUES(DEFAULT,?)";
        try{
            db.initPrepar(sql);
            db.getPstm().setString(1, classe.getNom());
            ok = db.executeMaj();
            db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ok;
    }

    @Override
    public int update(Classe classe) {
        String sql = "UPDATE classe SET nom = ?, effectif = ? WHERE id = ?";
        try{
            db.initPrepar(sql);
            db.getPstm().setString(1, classe.getNom());
            db.getPstm().setInt(2, classe.getEffectif());
            db.getPstm().setInt(3, classe.getId());
            ok = db.executeMaj();
            db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ok;
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM classe WHERE id = ?";
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
    public List<Classe> list() {
        String sql = "SELECT * FROM classe ORDER BY nom ASC";
        List<Classe> classes = new ArrayList<>();
        try {
            db.initPrepar(sql);
            rs = db.executeSelect();
            while (rs.next()){
                Classe classe = new Classe();
                classe.setId(rs.getInt("id"));
                classe.setNom(rs.getString("nom"));
                classe.setEffectif(rs.getInt("effectif"));
                classes.add(classe);
            }
            db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }

    @Override
    public Classe get(int id) {
        String sql = "SELECT * FROM classe WHERE id = ?";
        Classe classe = null;
        try {
            db.initPrepar(sql);
            db.getPstm().setInt(1, id);
            rs = db.executeSelect();
            if (rs.next()){
                classe = new Classe();
                classe.setId(rs.getInt("id"));
                classe.setNom(rs.getString("nom"));
                classe.setEffectif(rs.getInt("effectif"));
            }
            db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classe;
    }
}