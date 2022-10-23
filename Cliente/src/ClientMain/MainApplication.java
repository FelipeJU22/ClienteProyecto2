package ClientMain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.IOException;

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
}
//    public static void main(String[] args) throws Exception{
//        try{
//
//            XWPFDocument docx = new XWPFDocument(new FileInputStream("C:\\Users\\Yoshant\\Documents\\Felipe\\TEC\\2do Semestre\\Algoritmos y Estructuras de Datos I\\Proyecto 2\\Cliente\\src\\main\\prueba.docx"));
//            List<XWPFParagraph> parrafo =docx.getParagraphs();
//            for(XWPFParagraph paragraph:parrafo){
//                System.out.println(paragraph.getText());
//            }
//        } catch (IOException e) {
//            System.out.println(e);
//        }
//        launch();
//    }
//}