package ma.youssefproject.dictionnaire.controller;

import ma.youssefproject.dictionnaire.model.Mot;
import ma.youssefproject.dictionnaire.model.MotDAO;

import java.util.List;

public class DictionnaireController {


    private static MotDAO dao = new MotDAO() ;

    public static Mot rechercherDef (String motchercher ) {

        Mot mot = new Mot (-1,motchercher,null,null) ;

       return dao.getDef(mot) ;

    }

    public static String ajouterDef (String motAjouter , String def , String categorie ) {

        Mot mot = new Mot (-1,motAjouter,def,categorie) ;

        if (dao.addDef(mot))
            return "Le mot est ajouter avec success !" ;
        else
            return "Probleme d'ajout du mot dans le dictionnaire" ;
    }

    public static String modifierDef ( int id , String nouvelDef ) {

       if ( dao.changeDef(id,nouvelDef) )
           return "Definition est modifié avec success !" ;
       else
           return "Probleme lors de modification du definition" ;
    }
}
