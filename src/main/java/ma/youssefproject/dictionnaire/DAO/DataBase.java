package ma.youssefproject.dictionnaire.DAO;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {

    private final Connection connexion;
    private final String dbPath;

    public DataBase() throws RuntimeException {
        this.dbPath = detectDBPath();

        File f = new File(dbPath);
        if (!f.exists() && !f.mkdirs()) {
            throw new RuntimeException("Impossible de créer le dossier pour la DB ! (" + dbPath + ")");
        }

        try {
            String url = "jdbc:sqlite:" + dbPath + File.separator + "dictionnaire.db";
            this.connexion = DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException("Problème de connexion à la DB : " + e.getMessage(), e);
        }

        if (!creerDB()) {
            throw new RuntimeException("Impossible de créer les tables et index de la DB !");
        }
    }

    /**
     * Détecte le chemin de la base de données selon l'OS
     * et gère le cas Docker si aucun volume n'est monté
     */
    private static String detectDBPath() {
        String os = System.getProperty("os.name").toLowerCase();
        String home = System.getProperty("user.home");

        StringBuilder localPath = new StringBuilder(home);

        if (os.contains("win")) {
            localPath.append(File.separator).append("AppData")
                    .append(File.separator).append("Local")
                    .append(File.separator).append("Dictionnaire-extensible")
                    .append(File.separator).append("database");
        } else if (os.contains("mac")) {
            localPath.append(File.separator).append("Library")
                    .append(File.separator).append("Application Support")
                    .append(File.separator).append("Dictionnaire-extensible")
                    .append(File.separator).append("database");
        } else { // Linux / Unix
            localPath.append(File.separator).append(".local")
                    .append(File.separator).append("share")
                    .append(File.separator).append("Dictionnaire-extensible")
                    .append(File.separator).append("database");
        }

        // Vérification Docker Linux/Unix si aucune DB existante dans l'OS
        File dockerDir = new File("/app/data");
        if ((os.contains("nix") || os.contains("nux") || os.contains("aix"))
                && !new File(localPath.toString()).exists()
                && dockerDir.exists()) {
            return dockerDir.getAbsolutePath();
        }

        return localPath.toString();
    }

    /**
     * Retourne la connexion active
     */
    public Connection getConnexion() {
        return connexion;
    }

    /**
     * Création des tables et index si non existants
     */
    public boolean creerDB() {
        String sqlTable = "CREATE TABLE IF NOT EXISTS mots (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "mot TEXT NOT NULL, " +
                "def TEXT, " +
                "categorie TEXT);";

        String sqlIndexMot = "CREATE UNIQUE INDEX IF NOT EXISTS idx_mot ON mots(mot);";

        try (Statement stmt = connexion.createStatement()) {
            stmt.execute(sqlTable);
            stmt.execute(sqlIndexMot);
        } catch (SQLException e) {
            System.err.println("Erreur création tables/index : " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Ferme la connexion
     */
    public void close() {
        try {
            if (connexion != null && !connexion.isClosed()) {
                connexion.close();
            }
        } catch (SQLException e) {
            System.err.println("Erreur fermeture DB : " + e.getMessage());
        }
    }
}
