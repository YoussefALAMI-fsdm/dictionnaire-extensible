package ma.youssefproject.dictionnaire.view.cli;

import ma.youssefproject.dictionnaire.controller.DictionnaireController;
import ma.youssefproject.dictionnaire.model.Mot;
import ma.youssefproject.dictionnaire.utils.ConsoleUtils;

import java.util.Scanner;

public class InterfaceCLI {

    private static Scanner sc = new Scanner(System.in);
    private final DictionnaireController controller;  // instance passée à la vue

    // Constructeur avec injection du controller
    public InterfaceCLI(DictionnaireController controller) {
        this.controller = controller;
    }

    public void ajoutDef(String mot) {
        ConsoleUtils.clearScreen();
        System.out.print("\n\tVeuillez donner la définition du mot \"" + mot + "\" : ");
        String def = sc.nextLine();
        System.out.print("\n\tVeuillez donner la catégorie du mot \"" + mot + "\" : ");
        String cat = sc.nextLine();

        // Appel à l'instance du controller
        System.out.println("\n\t\t" + controller.ajouterDef(mot, def, cat));
    }

    public void afficherDef(Mot mot) {
        System.out.println("\n\n\tLa définition du mot " + mot.getMot() + " est : " + mot.getDef());
    }

    public void menuDemmarage() {
        int choix;
        do {
            ConsoleUtils.clearScreen();
            System.out.println("\n\n\t\t---------------- Bonjour dans votre dictionnaire CLI -------------------- ");
            System.out.println("\n\t1) Chercher une définition");
            System.out.println("\n \t2) Fermer le programme");
            System.out.print("\n\t\tTaper votre choix : ");
            choix = sc.nextInt();
            sc.nextLine(); // Vide le retour à la ligne restant

            if (choix == 1) {
                ConsoleUtils.clearScreen();
                System.out.print("\n\tDonner le mot à chercher : ");
                String mot = sc.nextLine();

                // Appel à l'instance du controller
                Mot m = controller.rechercherDef(mot);
                ConsoleUtils.clearScreen();

                if (m == null) {
                    System.out.print("\n\tLe mot n'est pas trouvé, voulez-vous ajouter une définition (yes/no) ? ");
                    String reponse = sc.nextLine();
                    if (reponse.equalsIgnoreCase("yes") || reponse.equalsIgnoreCase("y")) {
                        ajoutDef(mot);
                    }
                } else {
                    afficherDef(m);
                }

                System.out.print("\n\tVoulez-vous revenir au menu principal (y/n) ? : ");
                String reponse = sc.nextLine();
                ConsoleUtils.clearScreen();
                if (reponse.equalsIgnoreCase("yes") || reponse.equalsIgnoreCase("y")) {
                    continue;
                } else {
                    break;
                }
            }

        } while (choix != 2);
    }
}