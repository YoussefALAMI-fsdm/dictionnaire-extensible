package ma.youssefproject.dictionnaire.App;

import ma.youssefproject.dictionnaire.config.DAOFactory;
import ma.youssefproject.dictionnaire.controller.DictionnaireController;
import ma.youssefproject.dictionnaire.view.cli.InterfaceCLI;

public class Main {

    public static void main(String[] args) {

        // Création de la factory
        DAOFactory factory = new DAOFactory();

        // Injection du DAO via le controller
        DictionnaireController controller = new DictionnaireController(factory.getMotDAO());

        // Injection du controller dans la vue
        InterfaceCLI cli = new InterfaceCLI(controller);

        cli.menuDemmarage();


        /*
         * ⚡ Importance de l'injection en architecture MVC :
         *
         * 1️⃣ Séparation claire des responsabilités :
         *    - La vue n'accède jamais directement au modèle.
         *    - Le controller orchestre les interactions entre la vue et le modèle.
         *
         * 2️⃣ Flexibilité et maintenance :
         *    - On peut facilement remplacer le DAO ou le controller par une autre
         *      implémentation sans modifier le reste de l'application.
         *
         * 3️⃣ Testabilité améliorée :
         *    - On peut injecter des objets factices (mock) pour les tests unitaires,
         *      sans avoir besoin d'une vraie base de données ou d'une interface utilisateur.
         *
         * 4️⃣ Réduction des dépendances fortes :
         *    - Main n'a plus besoin de connaître les détails de la création des objets
         *      (connexion DB, DAO, etc.), ce qui rend le code plus propre et modulable.
         *
         * 🔹 En résumé, l’injection permet de respecter les principes MVC et facilite
         *    l’évolution, le test et la maintenance du projet.
         */
    }
}
