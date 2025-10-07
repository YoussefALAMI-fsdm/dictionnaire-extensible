package ma.youssefproject.dictionnaire.model;

import java.util.List;

public interface DAO {

    boolean addDef ( Mot mot ) ;
    Mot getDef (Mot mot ) ;
    public boolean changeDef( Mot nouveau ) ;

}
