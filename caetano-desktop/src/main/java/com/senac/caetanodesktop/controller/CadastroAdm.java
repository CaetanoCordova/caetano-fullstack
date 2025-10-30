package com.senac.caetanodesktop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CadastroAdm {

    @FXML
    private TextArea txtSecret;

    @FXML
    private TextArea txtNome;

    @FXML
    private TextArea txtCpf;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtSenha;

    public void voltar (ActionEvent event) throws Exception{
        FXMLLoader loaderTeste = new FXMLLoader(getClass().getResource("/com/senac/caetanodesktop/menu-view.fxml"));
        Scene sceneTeste = new Scene(loaderTeste.load()); //Scene passe o loader
        Stage stageTeste = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageTeste.setScene(sceneTeste);
    }

    public void enviarUsuarioAdmBanco(ActionEvent event) {
        try {

            var urlCaetano = "http://localhost:8080/usuarios/adm";

            URL url = new URL(urlCaetano); //criando endereco
            HttpURLConnection com = (HttpURLConnection) url.openConnection(); //conectando
            com.setRequestMethod("POST"); //setando um metodo
            com.setDoOutput(true); //esperando uma resposta
            com.setRequestProperty("Content-Type", "application/json"); //setando propriedades

            //Criar um Json via texto, contatenar texto com os valores da minha variavel

            String json = String.format(" {\"secret\":\"%s\", \"nome\":\"%s\", \"cpf\":\"%s\", \"email\":\"%s\", \"senha\":\"%s\"}",
                    txtSecret.getText(),
                    txtNome.getText(),
                    txtCpf.getText(),
                    txtEmail.getText(),
                    txtSenha.getText());

            //escrever no corpo da minha requisicao

            try(OutputStream os = com.getOutputStream()) {
                os.write(json.getBytes());
            }

            //resposta

            int status = com.getResponseCode(); //vai la na api e vai retornar o metodo

            if (status == 200){

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cadastro feito.");
                alert.setHeaderText("ADMIN cadastrado com sucesso.");

                alert.showAndWait();

            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro.");
                alert.setHeaderText(null);

                alert.showAndWait();
            }
            com.disconnect();

        } catch (Exception e){

        }
    }
}
