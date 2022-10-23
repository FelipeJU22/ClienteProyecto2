package main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button boton1;
    @FXML
    private TextField texto;
    private Inicio controladorInicio;
    private Stage stage;

    private Client client;
    private String path;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        try{
            client = new Client(new Socket("localhost", 1234));
            System.out.println("Conectado al servidor");
        }
        catch (IOException e){
            System.out.println("Error conectando al servidor");
        }
        client.recibirMsjServidor();
        boton1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String clientMessage = texto.getText();
                client.enviarMsjServidor(clientMessage);
            }
        });
    }

    public void init(Stage stage, Inicio inicio) {
        stage.getIcons().add(new Image("C:\\Users\\Yoshant\\Documents\\Felipe\\TEC\\2do Semestre\\Algoritmos y Estructuras de Datos I\\Proyecto 2\\Cliente\\src\\Imágenes\\icono.PNG"));
        this.controladorInicio = inicio;
        this.stage = stage;
    }
}