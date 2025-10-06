package ma.youssefproject.dictionnaire.model;

public class Mot {
    // Classe entité représentant un mot dans le dictionnaire

    private int id;
    private String mot;
    private String def;
    private String categorie;

    // Constructeur public
    public Mot( int id , String mot, String def, String categorie) {
       this.id = id ;
        this.mot = mot;
        this.def = def;
        this.categorie = categorie;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getMot() {
        return mot;
    }

    public String getDef() {
        return def;
    }

    public String getCategorie() {
        return categorie;
    }

    // Setters
    public void setMot(String mot) {
        this.mot = mot;
    }

    public void setDef(String def) {
        this.def = def;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

}
