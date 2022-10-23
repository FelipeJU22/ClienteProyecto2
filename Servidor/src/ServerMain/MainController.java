package ServerMain;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Button boton1;
    @FXML
    private TextField texto;
    private Server server;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        try{
            server = new Server(new ServerSocket(1234));
        }
        catch (IOException e) {
            System.out.println("Error al crear servidor");
        }

        server.recibirMsjClient();
        boton1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String serverMessage = texto.getText();
                server.enviarMsjClient(serverMessage);
            }
        });
    }

}