package ma.youssefproject.dictionnaire.model;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {

    // Détection automatique de Docker : si le conteneur contient /app/data, on utilise ce chemin
    private static final String DOCKER_PATH = "/app/data";
    private static final String LOCAL_PATH = getLocalDataPath();

    public static final String emplacementDB = isDocker() ? DOCKER_PATH : LOCAL_PATH;
    private static Connection connexion = null;

    // Méthode pour détecter si l'application tourne dans Docker
    private static boolean isDocker() {
        File dockerDir = new File(DOCKER_PATH);
        return dockerDir.exists() || dockerDir.mkdirs();
    }

    // Retourne le chemin de données local selon le système
    private static String getLocalDataPath() {
        String home = System.getProperty("user.home");
        String os = System.getProperty("os.name").toLowerCase();

        StringBuilder appDir = new StringBuilder(home);

        if (os.contains("win"))
            appDir.append(File.separator).append("AppData")
                    .append(File.separator).append("Local")
                    .append(File.separator).append("Dictionnaire-extensible");
        else if (os.contains("mac"))
            appDir.append(File.separator).append("Library")
                    .append(File.separator).append("Application Support")
                    .append(File.separator).append("Dictionnaire-extensible");
        else
            appDir.append(File.separator).append(".local")
                    .append(File.separator).append("share")
                    .append(File.separator).append("Dictionnaire-extensible");

        return appDir.toString();
    }

    public static Connection getConnexion() {
        File f = new File(emplacementDB);

        if (!f.exists()) {
            if (!f.mkdirs()) {
                System.err.println("Impossible de créer le dossier pour la DB !");
                return null;
            }
        }

        if (connexion == null) {
            try {
                String url = "jdbc:sqlite:" + emplacementDB + File.separator + "dictionnaire.db";
                connexion = DriverManager.getConnection(url);
            } catch (SQLException e) {
                System.err.println("Problème de connexion avec la DB !!! : " + e.getMessage());
            }
        }
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
