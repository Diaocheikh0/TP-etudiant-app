package main;
import dao.*;
import entity.Classe;
import entity.Etudiant;
import java.util.List;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        IEtudiant dao = new EtudiantImpl();

        /*Scanner sc = new Scanner(System.in);*/

        /* Ajout Etudiant
        Etudiant etudiant = new Etudiant();

        // Saisie du nom de l'étudiant
        System.out.print("Nom de l'étudiant : ");
        etudiant.setNom(sc.nextLine());

        // Saisie du prénom de l'étudiant
        System.out.print("Prénom de l'étudiant : ");
        etudiant.setPrenom(sc.nextLine());

        // Saisie de la moyenne de l'étudiant
        System.out.print("Moyenne de l'étudiant : ");
        while (!sc.hasNextDouble()) {
            System.out.println("Veuillez entrer une valeur numérique valide.");
            sc.next();
        }
        etudiant.setMoyenne(sc.nextDouble());
        sc.nextLine();

        System.out.print("ID de la classe de l'étudiant : ");
        while (!sc.hasNextInt()) {
            System.out.println("Veuillez entrer un nombre entier valide.");
            sc.next();
        }
        int classeId = sc.nextInt();
        sc.nextLine();

        // Création de l'objet Classe et affectation à l'étudiant
        Classe classe = new Classe();
        classe.setId(classeId);
        etudiant.setClasse(classe);

        // Génération automatique du matricule basé sur l'ID de la classe
        etudiant.setMatricule(etudiant.generateMatricule(String.valueOf(classeId)));

        int ok = dao.add(etudiant);
        if (ok == 1) {
            System.out.println("Insertion success !");
        } else {
            System.out.println("Insertion failed !");
        }
        Fin ajout Etudiant*/

        /* Afficher la Liste des étudiant

        for (Etudiant etudiant : dao.list()) {
            System.out.println(etudiant);
        }

        Fin affichage des étudiant/*
         */

        /*Update Etudiant

        for (Etudiant etudiant : dao.list()) {
            System.out.println(etudiant);
        }

        Scanner scanner = new Scanner(System.in);
        int id;
        do {
            System.out.println("Donner l'id de l'étudiant : ");
            id = scanner.nextInt();
        } while (id < 0);

        Etudiant etudiant = dao.get(id);
        if (etudiant != null) {
            System.out.println("Donner le nom de l'étudiant : ");
            etudiant.setNom(scanner.next());

            System.out.println("Donner le prénom de l'étudiant : ");
            etudiant.setPrenom(scanner.next());


            double moyenne;
            do {
                System.out.println("Donner la moyenne de l'étudiant : ");
                while (!scanner.hasNextDouble()) {
                    System.out.println("Veuillez entrer une valeur numérique valide pour la moyenne !");
                    scanner.next();
                }
                moyenne = scanner.nextDouble();
            } while (moyenne < 0);
            etudiant.setMoyenne(moyenne);


            System.out.println("Donner l'ID de la classe de l'étudiant : ");
            int classeId = scanner.nextInt();
            Classe classe = new Classe(classeId);
            etudiant.setClasse(classe);


            int ok = dao.update(etudiant);
            if (ok == 1) {
                System.out.println("Modification réussie !");
            } else {
                System.out.println("Modification échouée.");
            }
        } else {
            System.out.println("L'étudiant n'existe pas.");
        }

        Fin Update Etudiant*/

        /* Début suppréssion étudiant

        Scanner scanner = new Scanner(System.in);
        int id;
        do {
            System.out.println("Donner l'id de l'étudiant à supprimer : ");
            id = scanner.nextInt();
        }while(id<0);
        int ok = dao.delete(id);
        if (ok == 1) {
            System.out.println("Suppression success");
        }else {
            System.out.println("Suppression failed");
        }

        FIN suppréssion étudiant */

        /*Début ajout Classe

        IClasse dao = new ClasseImpl();
        Scanner sc = new Scanner(System.in);
        Classe classe = new Classe();
        System.out.println("Nom du classe");
        classe.setNom(sc.next());
        int ok = dao.add(classe);
        if (ok == 1) {
            System.out.println("Insertion success");
        }else{
            System.out.println("Insertion failed");
        }

        Fin ajout Classe*/

        /* Affichage la lIaste des classes

        for (Classe cl : dao.list()){
            System.out.println(cl);
        }

        Fin affichage la liste de la classe*/

        /*Début Update Classe

        for (Classe cl : dao.list()){
            System.out.println(cl);
        }

        Scanner scanner = new Scanner(System.in);
        int id;
        do {
            System.out.println("Donner l'id de la classe : ");
            id = scanner.nextInt();
        }while(id<0);
        Classe classe = dao.get(id);
        if (classe != null) {
            System.out.println("Donner le nom de la classe : ");
            classe.setNom(scanner.next());
            System.out.println("Donner l'effectif de la classe : ");
            classe.setEffectif(scanner.nextInt());
            int ok = dao.update(classe);
            if (ok == 1) {
                System.out.println("Modification success");
            }else {
                System.out.println("Modification failed");
            }
        } else {
            System.out.println("La Classe n'existe pas");
        }

        Fin Update Classe*/

        /*Début suppression Classe

        Scanner scanner = new Scanner(System.in);
        int id;
        do {
            System.out.println("Donner l'id de la classe à supprimer : ");
            id = scanner.nextInt();
        }while(id<0);
        int ok = dao.delete(id);
        if (ok == 1) {
            System.out.println("Suppression success");
        }else {
            System.out.println("Suppression failed");
        }

        Fin Suppréssion Classe*/
    }

}