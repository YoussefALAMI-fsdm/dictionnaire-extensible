package ma.youssefproject.dictionnaire.controller;

import ma.youssefproject.dictionnaire.model.Mot;
import ma.youssefproject.dictionnaire.model.MotDAO;

import java.sql.SQLException;
import java.util.List;

public class DictionnaireController {

    private final MotDAO dao;  // instance de DAO

    // Constructeur : injection du DAO pour plus de flexibilité et testabilité
    public DictionnaireController(MotDAO dao) {
        this.dao = dao;
    }

    // Recherche d'une définition
    public Mot rechercherDef(String motChercher) {
        Mot mot = new Mot(-1, motChercher, null, null);
        return dao.getDef(mot);
    }

    // Ajouter une définition
    public String ajouterDef(String motAjouter, String def, String categorie) {
        Mot mot = new Mot(-1, motAjouter, def, categorie);
        if (dao.addDef(mot)) {
            return "Le mot a été ajouté avec succès !";
        } else {
            return "Problème d'ajout du mot dans le dictionnaire";
        }
    }

    // Modifier une définition
    public String modifierDef(Mot ancien, String nom, String def, String categorie) {

        // Si champ vide, on garde l'ancienne valeur
        String nouveauMot = nom.isEmpty() ? ancien.getMot() : nom;
        String nouvelleDef = def.isEmpty() ? ancien.getDef() : def;
        String nouvelleCategorie = categorie.isEmpty() ? ancien.getCategorie() : categorie;

        Mot nouveau = new Mot(ancien.getId(), nouveauMot, nouvelleDef, nouvelleCategorie);


        try {
         if (dao.changeDef(nouveau)) // true
             return "le Mot \""+nouveau.getMot()+"\" est modifier avec success !" ;
         else
             return "le Mot \""+nouveau.getMot()+"\" deja existe ! " ;
        } catch (SQLException e) {
            return ("Probleme SQL : " + e.getMessage()); // @ A supprimer le getMessage () et le remplacer par logger
        }
        }

}