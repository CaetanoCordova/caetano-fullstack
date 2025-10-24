module com.senac.caetanodesktop.caetanodesktop {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.senac.caetanodesktop.caetanodesktop to javafx.fxml;
    exports com.senac.caetanodesktop.caetanodesktop;
}