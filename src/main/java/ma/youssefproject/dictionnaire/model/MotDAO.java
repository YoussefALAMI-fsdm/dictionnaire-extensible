package ma.youssefproject.dictionnaire.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MotDAO implements DAO {

    private final Connection connexion;

    public MotDAO(Connection connexion) {
        this.connexion = connexion;

        if (connexion == null) {
            throw new RuntimeException("Impossible de se connecter à la base de données");
        }

        // Suppression de l'appel statique à DataBase.creerDB()
        // La DB et les tables sont déjà créées lors de l'instanciation de DataBase
    }

    @Override
    public boolean addDef(Mot mot) {
        String sql = "INSERT INTO mots(mot, def, categorie) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connexion.prepareStatement(sql)) {
            stmt.setString(1, mot.getMot());
            stmt.setString(2, mot.getDef());
            stmt.setString(3, mot.getCategorie());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erreur ajout définition : " + e.getMessage());
            return false;
        }
    }

    @Override
    public Mot getDef(Mot mot) {
        Mot def = null;
        String sql = "SELECT id, mot, def, categorie FROM mots WHERE mot = ? COLLATE NOCASE";
        try (PreparedStatement stmt = connexion.prepareStatement(sql)) {
            stmt.setString(1, mot.getMot());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                def = new Mot(
                        rs.getInt("id"),
                        rs.getString("mot"),
                        rs.getString("def"),
                        rs.getString("categorie")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erreur recherche définition : " + e.getMessage());
        }
        return def;
    }

                                                       // # throws SQLExeption
              // signifie que cette methode leve SQLExeption , a gerer par le controller ( la class qui appel cette methode )
    @Override
    public boolean changeDef ( Mot nouveau ) throws SQLException {     // ( SUCCESS = true , EXISTS = false )

        if ( getDef(nouveau) != null ) { // C a d le mot deja existe ( UNIQUE INDEX )
            return false ;
        }

        String sql = "UPDATE mots SET mot = ?, def = ? , categorie = ?  WHERE id = ?";

        try (PreparedStatement stmt = connexion.prepareStatement(sql)) {
            stmt.setString(1,nouveau.getMot());
            stmt.setString(2,nouveau.getDef());
            stmt.setString(3,nouveau.getCategorie());
            stmt.setInt(4,nouveau.getId());
            stmt.executeUpdate();
            return true ;
        }
    }
}
