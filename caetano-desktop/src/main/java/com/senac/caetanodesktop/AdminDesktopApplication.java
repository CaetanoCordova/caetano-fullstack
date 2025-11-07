package com.senac.caetanodesktop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDesktopApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdminDesktopApplication.class.getResource("cadastroadm-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1320, 1240);
        stage.setTitle("Portal de configuração");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}