module com.gestionestudiantesmedicina {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires java.sql;
    requires org.hibernate.orm.core;

    opens com.gestionestudiantesmedicina to javafx.fxml;

    //se supone que es para hibernate
    opens com.gestionestudiantesmedicina.entities to org.hibernate.orm.core, javafx.base;
    //opens com.gestionestudiantesmedicina to javafx.fxml;

    exports com.gestionestudiantesmedicina;
}
