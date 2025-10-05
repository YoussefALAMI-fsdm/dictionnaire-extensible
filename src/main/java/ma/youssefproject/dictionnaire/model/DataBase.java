package ma.youssefproject.dictionnaire.model;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {

    public static final String emplacementDB = getDataUserDirectory() + File.separator + "database" ; // C:\Users\<nom>\AppData\Local\Dictionnaire-extensible/database
    private static Connection connexion = null ; // A ne pas initialiser ici directement car si connexion a echoué lors du chargement de class il reste le long du programme ( car static )


    private static String getDataUserDirectory () {

        String home = System.getProperty("user.home") ;
        String os = System.getProperty("os.name").toLowerCase() ;

         StringBuilder appDir = new StringBuilder(home) ;

        if ( os.contains("win") )  // C:\Users\<nom>\AppData\Local\Dictionnaire-extensible
            appDir.append(File.separator).append("AppData").append(File.separator).append("Local").append("Dictionnaire-extensible") ;

          else if ( os.contains("mac") ) // /Users/<nom>/Library/Application Support/Dictionnaire-extensible
            appDir.append(File.separator).append("Library").append(File.separator).append("Application Support").append("Dictionnaire-extensible") ;

            else // /home/<nom>/.local/share/Dictionnaire-extensible
            appDir.append(File.separator).append(".local").append(File.separator).append("share").append("Dictionnaire-extensible") ;


        return appDir.toString() ;
    }

    public static Connection getConnexion () {

        File f = new File(emplacementDB) ;

        if ( !f.exists() ) {   // Pour qoui le dossier frere "database" du projet

            if ( ! f.mkdirs() )
                return null ;
        }

        if ( connexion == null ) {

            try {
                String url = "jdbc:sqlite:" + emplacementDB + File.separator + "dictionnaire.db";
                  connexion = DriverManager.getConnection(url) ;
            } catch ( SQLException e ) {
                System.err.println("Probleme de connexion avec la DB !!! : "+e.getMessage());
            }
        }
        return connexion ;
    }

    public static boolean creerDB () { // Appeler pour creer les tables si non ecore existant ( ⚠️ ne doit pas etre appelé que apres faire du connexion )

        String sql1 = "CREATE TABLE IF NOT EXISTS mots (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                " mot TEXT NOT NULL ," +
                " def TEXT" +
                ");" ;

        String sql2 = " CREATE INDEX IF NOT EXISTS idx_mot ON mots(mot) ;" ;

        if ( connexion == null ) { // Verification pour eviter le NPE

            System.err.println("Probleme lors su connexion au DB !! , creation annulée ! ");
            return false ;
        }

    try ( Statement stmt = connexion.createStatement() ) {

        stmt.execute(sql1) ;
        stmt.execute(sql2) ;

    } catch ( SQLException e ) {
        System.err.println(e.getMessage());
        return false ;
    }
    return true ;
    }

    public static void close () {

        if ( connexion != null ) {

               try {
                  connexion.close();
                  connexion = null; // le rendre en etat propre
               } catch (SQLException e) {
                   System.err.println("Erreur fermeture DB : " + e.getMessage());
               }
            }
    }
}