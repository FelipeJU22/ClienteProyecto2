package main;

import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Inicio {
    private Stage stage;
    private File archivo;
    FileChooser seleccionador = new FileChooser();
    public void seleccionar(ActionEvent event){
        archivo = seleccionador.showOpenDialog(new Stage());
    }
    public void pasar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cliente.fxml"));
        Parent root = loader.load();
        MainController controller = loader.getController();
        Scene scene = new Scene (root);
        Stage stage = new Stage ();
        stage.setScene(scene);
        controller.init(stage, this);
        stage.show();
        this.stage.close();
    }
    public void setStage(Stage primaryStage){
        stage = primaryStage;
    }
}
