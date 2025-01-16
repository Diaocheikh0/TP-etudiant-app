package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Etudiant {

    private int id;
    private String matricule;
    private String nom;
    private String prenom;
    private Double moyenne;
    private Classe classe;

    public Etudiant() {
    }

    public Etudiant(String nom, String prenom, Double moyenne, Classe classe) {
        this.nom = nom;
        this.prenom = prenom;
        this.moyenne = moyenne;
        this.classe = classe;
        this.matricule = generateMatricule();
    }

    public int getId() {
        return id;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Double getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(Double moyenne) {
        this.moyenne = moyenne;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    private String generateMatricule() {
        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String dateTime = now.format(formatter);

        String mat = "ET@" + dateTime + classe + "#";
        return mat.toUpperCase();
    }
}
