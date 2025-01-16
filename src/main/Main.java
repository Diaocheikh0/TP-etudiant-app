package main;
import dao.ClasseImpl;
import dao.DB;
import dao.IClasse;
import entity.Classe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IClasse dao = new ClasseImpl();
        /*Scanner sc = new Scanner(System.in);
        Classe classe = new Classe();
        System.out.println("Nom du classe");
        classe.setNom(sc.next());
        int ok = dao.add(classe);
        if (ok == 1) {
            System.out.println("Insertion succes");
        }else{
            System.out.println("Insertion failed");
        }*/

        /*for (Classe cl : dao.list()){
            System.out.println(cl);
        }*/

        /*Scanner scanner = new Scanner(System.in);
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
            System.out.println("Classe n'existe pas");
        }*/

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
    }

}