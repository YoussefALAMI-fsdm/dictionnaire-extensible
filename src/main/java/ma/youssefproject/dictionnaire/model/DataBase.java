package ma.youssefproject.dictionnaire.model;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {

    private static Connection connexion = null;

    // Détection automatique du chemin de la DB selon l'OS
    private static final String emplacementDB = detectDBPath();

    private static String detectDBPath() {
        String os = System.getProperty("os.name").toLowerCase();
        String home = System.getProperty("user.home");

        // Chemin Docker uniquement si Linux et dossier existe
        File dockerDir = new File("/app/data");
        if (os.contains("nix") || os.contains("nux") || os.contains("aix")) { // Linux/Unix
            if (dockerDir.exists()) {
                return dockerDir.getAbsolutePath();
            }
        }

        // Sinon chemin local selon OS
        StringBuilder localPath = new StringBuilder(home);
        if (os.contains("win")) {
            localPath.append(File.separator).append("AppData")
                    .append(File.separator).append("Local")
                    .append(File.separator).append("Dictionnaire-extensible");
        } else if (os.contains("mac")) {
            localPath.append(File.separator).append("Library")
                    .append(File.separator).append("Application Support")
                    .append(File.separator).append("Dictionnaire-extensible");
        } else {
            localPath.append(File.separator).append(".local")
                    .append(File.separator).append("share")
                    .append(File.separator).append("Dictionnaire-extensible");
        }

        return localPath.toString();
    }

    public static Connection getConnexion() {
        File f = new File(emplacementDB);
        if (!f.exists() && !f.mkdirs()) {
            System.err.println("Impossible de créer le dossier pour la DB !");
            return null;
        }

        if (connexion == null) {
            try {
                String url = "jdbc:sqlite:" + emplacementDB + File.separator + "dictionnaire.db";
                connexion = DriverManager.getConnection(url);
            } catch (SQLException e) {
                System.err.println("Problème de connexion avec la DB !!! : " + e.getMessage());
            }
        }

        System.out.println("DB sera créée ici : " + emplacementDB + File.separator + "dictionnaire.db");
        return connexion;
    }

    public static boolean creerDB() {
        String sql1 = "CREATE TABLE IF NOT EXISTS mots (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "mot TEXT NOT NULL, " +
                "def TEXT, " +
                "categorie TEXT" +
                ");";
        String sql2 = "CREATE INDEX IF NOT EXISTS idx_mot ON mots(mot);";
        String sql3 = "CREATE INDEX IF NOT EXISTS idx_id ON mots(id);";

        if (connexion == null) {
            System.err.println("Problème lors de la connexion au DB ! Création annulée !");
            return false;
        }

        try (Statement stmt = connexion.createStatement()) {
            stmt.execute(sql1);
            stmt.execute(sql2);
            stmt.execute(sql3);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static void close() {
        if (connexion != null) {
            try {
                connexion.close();
                connexion = null;
            } catch (SQLException e) {
                System.err.println("Erreur fermeture DB : " + e.getMessage());
            }
        }
    }
}
