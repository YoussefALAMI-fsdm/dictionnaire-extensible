module ma.youssefproject.dictionnaire {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.xerial.sqlitejdbc; // pour SQLite

    // Les controllers doivent être ouverts à JavaFX
    opens ma.youssefproject.dictionnaire.controller to javafx.fxml;
    opens ma.youssefproject.dictionnaire.App to javafx.fxml;

    // Exports uniquement ce qui doit être accessible depuis d'autres modules (optionnel)
    exports ma.youssefproject.dictionnaire.App;
    exports ma.youssefproject.dictionnaire.model;
    exports ma.youssefproject.dictionnaire.DAO;
}
