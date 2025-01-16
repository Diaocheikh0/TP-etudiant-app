package dao;

import entity.Etudiant;

import java.rmi.Remote;
import java.util.List;

public interface IEtudiant extends Repository<Etudiant>{

   public List<Etudiant> getEtudiantsByClasse(String classe);
}
