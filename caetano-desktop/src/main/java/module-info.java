module com.senac.caetanodesktop.caetanodesktop {
    requires javafx.controls;
    requires javafx.fxml;

    requires jakarta.persistence;
    requires org.hibernate.orm.core;

    opens com.senac.caetanodesktop to javafx.fxml;
    opens com.senac.caetanodesktop.model to org.hibernate.orm.core;

    exports com.senac.caetanodesktop;
    exports com.senac.caetanodesktop.controller;
    opens com.senac.caetanodesktop.controller to javafx.fxml;
}
