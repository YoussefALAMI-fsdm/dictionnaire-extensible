package ma.youssefproject.dictionnaire.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {

    public static final String emplacementDB = "jdbc:sqlite:dictionnaire.db" ;
    private static Connection connexion = null ;

    public static Connection getConnexion () {

        if ( connexion == null ) {

            try {
                  connexion = DriverManager.getConnection(emplacementDB) ;
            } catch ( SQLException e ) {
                System.err.println("Probleme de connexion avec la DB !!! : "+e.getMessage());
            }
        }
        return connexion ;
    }

    public static void creerDB () { // Appeler pour creer les tables si non ecore existant

        String sql = "CREATE TABLE IF NOT EXISTS mots ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT , " +
                " mot TEXT NOT NULL ," +
                " def TEXT " +
                ") ;" ;

    try ( Connection connexion = getConnexion() ; Statement stmt = connexion.createStatement() ) {

        stmt.execute(sql) ;

    } catch ( SQLException e ) {
        System.err.println(e.getMessage());
    }
    }
}
