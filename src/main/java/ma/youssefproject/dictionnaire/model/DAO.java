package ma.youssefproject.dictionnaire.model;

import java.util.List;

public interface DAO {

    boolean addDef ( Mot mot ) ;
    Mot getDef (Mot mot ) ;
    boolean changeDef ( int id , String nouveldef ) ;

}
