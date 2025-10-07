package ma.youssefproject.dictionnaire.config;

import ma.youssefproject.dictionnaire.model.DataBase;
import ma.youssefproject.dictionnaire.model.MotDAO;

import java.sql.Connection;

public class DAOFactory {

    // ⚡ Connexion unique à la base de données
    // - On la garde ici pour que tous les DAO utilisent la même connexion.
    // - Évite de créer plusieurs connexions inutiles et de surcharger la DB.
    private final Connection connexion;

    // 🔹 Constructeur de la factory
    // - Crée la connexion à la base de données au moment où la factory est instanciée.
    // - Permet de centraliser la création de DAO et la gestion de la connexion.
    public DAOFactory() {
        // Instancie la classe DataBase qui gère la connexion
        DataBase db = new DataBase();
        // Récupère la connexion et la conserve dans un attribut final
        this.connexion = db.getConnexion();
    }

    // 🔹 Méthode pour obtenir un DAO spécifique
    // - Ici, on retourne un MotDAO déjà prêt à l'emploi.
    // - L'avantage : Main ou le controller n'ont pas besoin de savoir comment créer le DAO,
    //   ils reçoivent simplement l'objet déjà configuré.
    // - Permet aussi d'échanger facilement le DAO (ex : pour tests unitaires)
    public MotDAO getMotDAO() {
        return new MotDAO(connexion);
    }

    /*
     * 🔹 Pourquoi utiliser une factory pour les DAO ?
     *
     * 1️⃣ Centralisation de la création des objets :
     *    - Toutes les instances de DAO sont créées depuis un seul endroit.
     *
     * 2️⃣ Réduction des dépendances fortes :
     *    - Le reste de l'application (Main, Controller, Vue) n'a pas besoin de connaître
     *      les détails de la connexion ou de l'implémentation du DAO.
     *
     * 3️⃣ Flexibilité et testabilité :
     *    - On peut facilement remplacer le DAO par une version factice (mock) pour les tests.
     *
     * 4️⃣ Bonne pratique MVC :
     *    - Respecte la séparation des responsabilités : le modèle est géré et fourni par la factory,
     *      le controller s'en sert, et la vue reste totalement indépendante.
     */
}