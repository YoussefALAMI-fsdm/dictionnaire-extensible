package ma.youssefproject.dictionnaire.model;
                                                        // @ les echange entre DAO et Mot doit etre tout en LowerCasse pour pas confondre
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MotDAO implements DAO {

    private static Connection connexion ;

   public MotDAO () { // Etablir la connexion et creation des tables , index IF NOT EXISTS
        this.connexion = DataBase.getConnexion() ;

        if ( this.connexion == null ) {
            System.err.println("Probleme dans la connexion avec la DB");
            throw new RuntimeException("Impossible de se connecter à la base de données");
        }

       if ( ! DataBase.creerDB() ) {
           System.err.println("Probleme dans la creation des tables et index du DB");
           System.exit(-1);
       }
    }

    @Override
    public boolean addDef(Mot mot) {

        String sql = "INSERT INTO mots(mot,def,categorie) VALUES (?,?,?);" ;

    try (PreparedStatement stmt = connexion.prepareStatement(sql)) { // PreparedStatement permet un traitement pro des requete SQL

    stmt.setString(1,mot.getMot());
    stmt.setString(2,mot.getDef());
    stmt.setString(3,mot.getCategorie());
    stmt.executeUpdate() ;
    return true ;

    } catch ( SQLException e ) {
        System.err.println("Erreur dans l'ajout du def !"+e.getMessage());
        return false ;
    }

    }

    @Override
    public List<Mot> getDef(Mot mot) {

        List<Mot> mots = new ArrayList<>() ;

        String sql = "SELECT id, mot, def, categorie " +
                "FROM mots " +
                "WHERE mot LIKE ? COLLATE NOCASE;" ;

        try (PreparedStatement stmt = connexion.prepareStatement(sql)) {

            stmt.setString(1, "%"+mot.getMot()+"%");
            ResultSet rs = stmt.executeQuery() ;


            while ( rs.next() ) {
                Mot m = new Mot(
                        rs.getInt("id"),        // id
                        rs.getString("mot"),    // mot
                        rs.getString("def"),    // définition
                        rs.getString("categorie") // catégorie
                );

                  mots.add(m) ;

            }
       ;
        } catch (SQLException e ) {
            System.err.println("Probleme : "+e.getMessage()) ;
        }
        return mots ;
    }

    @Override
    public boolean changeDef(int id , String nouvelDef ) {

        String sql = "UPDATE mots SET def = ? WHERE id = ?";

        try (PreparedStatement stmt = connexion.prepareStatement(sql)) {
           stmt.setString(1,nouvelDef);
            stmt.setInt(2,id);
            stmt.executeUpdate() ;
            return true ;

        } catch ( SQLException e ) {
            System.err.println("Probleme dans change Def");
            return false ;

        }
    }
}