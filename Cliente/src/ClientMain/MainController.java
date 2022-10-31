package ClientMain;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button boton1, botonPalabra, botonAñadir;
    @FXML
    private TextField texto;

    private Inicio controladorInicio;
    private Stage stage;
    private File archivo;
    private String path;
    private Client client;
    private Inicio inicio;
    private static String paths;
    FileChooser seleccionador = new FileChooser();
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
                client.enviarMsjServidor("0"+"@@@"+paths);
            }
        });
        botonPalabra.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String clientMessage = texto.getText();
                client.enviarMsjServidor("1"+ "@@@"+ clientMessage);
            }
        });
        botonAñadir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                archivo = seleccionador.showOpenDialog(new Stage());
                path = String.valueOf(archivo);
                client.enviarMsjServidor("2"+ "@@@"+ path);
                paths+=path+ " LeoEsDios ";
                System.out.println(paths);
            }
        });
    }

    static void recibirPaths(String path){
        paths = path;
        System.out.println(paths);
    }

    public void init(Stage stage, Inicio inicio) {
        //stage.getIcons().add(new Image("C:\\Users\\Yoshant\\Documents\\Felipe\\TEC\\2do Semestre\\Algoritmos y Estructuras de Datos I\\Proyecto 2\\Cliente\\src\\Imágenes\\icono.PNG"));
        this.controladorInicio = inicio;
        this.stage = stage;
    }
}