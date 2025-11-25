package com.senac.caetanodesktop.controller;

import com.google.gson.Gson;
import com.senac.caetanodesktop.model.DAO.EnderecoResponseDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CadastroAdm {

    @FXML
    private TextField txtSecret;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCpf;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtSenha;

    @FXML
    private TextField txtEndereco;

    @FXML
    private TextField txtCep;

    public void voltar (ActionEvent event) throws Exception{
        FXMLLoader loaderTeste = new FXMLLoader(getClass().getResource("/com/senac/caetanodesktop/menu-view.fxml"));
        Scene sceneTeste = new Scene(loaderTeste.load()); //Scene passe o loader
        Stage stageTeste = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageTeste.setScene(sceneTeste);
    }



    public void enviarUsuarioAdmBanco(ActionEvent event) {
        try {
            var urlEndereco = "https://viacep.com.br/ws/"+txtCep.getText()+"/json/";
            URL url1 = new  URL(urlEndereco);
            HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type","application/json");

            int statusCep = conn.getResponseCode();

            if(statusCep == 200){
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    txtEndereco.setText(response.toString());

                    Gson gson = new Gson();

                    EnderecoResponseDto enderecoResponseDto = gson.fromJson( txtEndereco.getText(), EnderecoResponseDto.class);


                    var urlCaetano = "http://localhost:8080/usuarios/adm";

                    URL url2 = new URL(urlCaetano); //criando endereco
                    HttpURLConnection com = (HttpURLConnection) url2.openConnection(); //conectando
                    com.setRequestMethod("POST"); //setando um metodo
                    com.setDoOutput(true); //esperando uma resposta
                    com.setRequestProperty("Content-Type", "application/json"); //setando propriedades

                    //Cria um Json via texto e concatena

                    String json = String.format(" {\"secret\":\"%s\", \"nome\":\"%s\", \"cpf\":\"%s\", \"email\":\"%s\", \"senha\":\"%s\", \"cep\":\"%s\", \"endereco\":\"%s\"}",
                            txtSecret.getText(),
                            txtNome.getText(),
                            txtCpf.getText(),
                            txtEmail.getText(),
                            txtSenha.getText(),
                            txtCep.getText(),
                            enderecoResponseDto.getLogradouro()+", "+enderecoResponseDto.getBairro()+", "+enderecoResponseDto.getLocalidade()+", "+enderecoResponseDto.getUf()
                    );

                    try(OutputStream os = com.getOutputStream()) {
                        os.write(json.getBytes());
                    }

                    int statusAdm = com.getResponseCode(); //vai la na api e vai retornar o metodo

                    if (statusAdm == 200){

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Cadastro feito.");
                        alert.setHeaderText("ADMIN cadastrado com sucesso.");

                        alert.showAndWait();

                    }else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erro");
                        alert.setHeaderText("Algo deu errado. Verifique se este e-mail e/ou CPF são válidos.");
                        alert.showAndWait();
                    }
                    com.disconnect();
                }
                catch (Exception e){
                    System.out.println(e.toString());
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Algo deu errado. Verifique se este é um CEP inválido.");
                alert.showAndWait();
            }
            conn.disconnect();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Algo deu errado. Verifique a excessão.");
            alert.showAndWait();
        }
    }

    public void sair(){
        System.exit(0);
    }
}
