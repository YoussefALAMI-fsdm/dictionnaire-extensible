package ma.youssefproject.dictionnaire.model;

public class Mot { // C'est une class de type Entité qui defini les different objet en mémoire ,a fin de les manipuler via controlleur ou DAO ( acces au DB )

    private StringBuilder mot ;
    private StringBuilder def ;
    private String categorie ;
    private int id ;


    Mot( StringBuilder mot , StringBuilder def ) {
        this.mot = mot ;
        this.def = def ;
    }

    public String getMot () {
        return mot.toString() ;
    }

    public String getDef () {
        return def.toString() ;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setMot(StringBuilder mot) {
        this.mot = mot;
    }

    public void setDef(StringBuilder def) {
        this.def = def;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}
