package com.senac.caetanodesktop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuController {

    public void sair(){
        System.exit(0);
    }

    public void abrirMenuTesteApiBanco(ActionEvent event) throws  Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/senac/caetanodesktop/testeapibanco-view.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    public void cadastroAdministrador(ActionEvent event) throws Exception{
        FXMLLoader loaderAdm = new FXMLLoader(getClass().getResource("/com/senac/caetanodesktop/cadastroadm-view.fxml"));
        Scene sceneConfigAdm = new Scene(loaderAdm.load());
        Stage stageConfigAdm = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageConfigAdm.setScene(sceneConfigAdm);
    }
}
