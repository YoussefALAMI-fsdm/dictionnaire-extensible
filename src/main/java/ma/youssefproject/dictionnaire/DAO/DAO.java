package ma.youssefproject.dictionnaire.DAO;

import ma.youssefproject.dictionnaire.model.Mot;

import java.sql.SQLException;
import java.util.List;

public interface DAO {

    boolean addDef ( Mot mot ) ;
    Mot getDef (Mot mot ) ;
    public boolean changeDef ( Mot nouveau ) throws SQLException ;
    public List<Mot> searchByCategorie ( String Categorie ) ;

}
