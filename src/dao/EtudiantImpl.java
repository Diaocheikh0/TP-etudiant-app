package dao;

import entity.Classe;
import entity.Etudiant;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EtudiantImpl implements IEtudiant {

    @Override
    public List<Etudiant> getEtudiantsByClasse(String classe) {
        return List.of();
    }

    @Override
    public int add(Etudiant etudiant) {
        return 0;
    }

    @Override
    public int update(Etudiant etudiant) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public List<Etudiant> list() {
        return List.of();
    }

    @Override
    public Etudiant get(int id) {
        return null;
    }
}
