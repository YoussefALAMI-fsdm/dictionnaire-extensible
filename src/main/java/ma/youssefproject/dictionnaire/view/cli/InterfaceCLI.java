package ma.youssefproject.dictionnaire.view.cli;

import ma.youssefproject.dictionnaire.controller.DictionnaireController;
import ma.youssefproject.dictionnaire.model.Mot;
import ma.youssefproject.dictionnaire.utils.ConsoleUtils;

import java.util.List;
import java.util.Scanner;

public class InterfaceCLI {

    private static Scanner sc = new Scanner(System.in);

    public void ajoutDef ( String mot ) {
       ConsoleUtils.clearScreen();
        System.out.print("\n\t Veuillez donnée la definition du mot \""+mot+"\" : ");
        String def = sc.nextLine() ;
        System.out.println("\n\t Veuillez donnée la catégorie du mot \""+mot+"\" : ");
        String cat = sc.nextLine() ;

        System.out.println("\n\t\t"+DictionnaireController.ajouterDef(mot,def,cat)) ;
    }

    public void afficherDef(Mot mot) {

        System.out.println("\n\n\t La defintion du mot "+mot.getMot()+" est : "+mot.getDef());
    }

        public void menuDemmarage () {
            int choix;
            do {
                ConsoleUtils.clearScreen();
                System.out.println("\n\n\t\t  ---------------- Bonjour dans votre dictionnaire CLI -------------------- ");
                System.out.println("\n\n\t 1) Chercher une definition");
                System.out.println("\n\t 2) Fermer le programme");
                System.out.print("\n\n\t\t\t\t Taper votre choix : ");
                choix = sc.nextInt();
                sc.nextLine(); // <-- Vide le retour à la ligne restant
                if (choix == 1) {
                    ConsoleUtils.clearScreen();  // Utilisation d'Utils
                    System.out.print("\n\n\t Donner le mot a chercher : ");
                    String mot = sc.nextLine();
                    Mot m = DictionnaireController.rechercherDef(mot);
                    ConsoleUtils.clearScreen();
                    if ( m == null ) {
                        System.out.print("\n\n\t\t Le mot n'est pas trouvé , voullez ajouter une def (yes/no) ? ");
                        String reponse = sc.nextLine() ;

                        if ( reponse.equalsIgnoreCase("yes") || reponse.equalsIgnoreCase("y"))
                            ajoutDef(mot);
                    }
                    else {
                        afficherDef(m);
                    }
                    System.out.print("\n\t\t Voulez vous revenir au menu principale (y/n) ? : ");
                    String reponse = sc.nextLine() ;
                    ConsoleUtils.clearScreen();
                    if ( reponse.equalsIgnoreCase("yes") || reponse.equalsIgnoreCase("y"))
                        continue;
                    else
                        break ;
                }
            } while (choix != 2);
        }
    }
