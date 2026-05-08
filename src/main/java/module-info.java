module com.gestionestudiantesmedicina {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires java.desktop;

    opens com.gestionestudiantesmedicina to javafx.fxml;
    opens com.gestionestudiantesmedicina.controller to javafx.fxml;
    //se supone que es para hibernate
    opens com.gestionestudiantesmedicina.entities to org.hibernate.orm.core, javafx.base;

    exports com.gestionestudiantesmedicina.controller;
    exports com.gestionestudiantesmedicina;
}
