package ClientMain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
/**
 * Clase que inicializa el código, enviando a la ventana "inicio" como ventana principal.
 */
public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("inicio.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Inicio");
        stage.setScene(scene);
        Inicio controller = fxmlLoader.getController();
        controller.setStage(stage);
        //stage.getIcons().add(new Image("C:\\Users\\Yoshant\\Documents\\Felipe\\TEC\\2do Semestre\\Algoritmos y Estructuras de Datos I\\Proyecto 2\\Cliente\\src\\Imágenes\\icono.PNG"));
        stage.show();
    }

    public static void main(String[] args) throws Exception{
        launch();
    }
}


















