package ServerMain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.ServerSocket;
/**
 * Clase que sirve para inicializar el servidor.
 */
public class ServerApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ServerApp.class.getResource("servidor.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("ServerTextFinder");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Método que crea el servidor.
     */
    public static void main(String[] args) {
        try{
            Server server = new Server(new ServerSocket(1234));
            server.recibirMsjClient();
        }
        catch (IOException e) {
            System.out.println("Error al crear servidor");
        }
    }
}