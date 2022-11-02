package ClientMain;

import ListaDoblementeEnlazadaCircular4k.LinkedList;
import ListaDoblementeEnlazadaCircular4k.Nodos;
import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
/**
 * Clase del controlador de la ventana de búsqueda, en esta se buscan palabras, se añaden y eliminan documentos, y se visualizan los
 * resultados de la busqueda de palabras.
 */
public class MainController implements Initializable {

    @FXML
    private Button boton1, botonPalabra, botonAñadir, botonEliminar;
    @FXML
    private TextField texto;
    @FXML
    private TextArea textA;
    @FXML
    private Label posicion;
    @FXML
    private Label pal1;
    @FXML
    private Label pal2;
    @FXML
    private Label pal3;
    private Inicio controladorInicio;
    private Stage stage;
    private File archivo;
    private String path;
    private Client client;
    private LinkedList linkedList = new LinkedList();
    private Inicio inicio;
    public static String mensaje = "";
    public static String pos = "";
    public static String total = "";
    public static String direccion = "";
    public static int iteracionesBB = 0;
    private static String paths;
    FileChooser seleccionador = new FileChooser();
    /**
     * Método que inicializa la ventana, en esta se inicializa la conección con el servidor y se le envían los paths de los
     * documentos guardados.
     */
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
        botonEliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Iteraciones en ambos árboles");
                alerta.setContentText("Iteraciones para el árbol binario: " + iteracionesBB + "\n" + "Iteraciones para el árbol AVL: " + "Perdón profe :c");
                alerta.showAndWait();
            }
        });

    }
    /**
     * Método el cual recibe los paths
     */
    static void recibirPaths(String path){
        paths = path;
        System.out.println(paths);
    }
    /**
     * Método que setea como stage principal la ventana del buscador
     */
    public void init(Stage stage, Inicio inicio) {
        //stage.getIcons().add(new Image("C:\\Users\\Yoshant\\Documents\\Felipe\\TEC\\2do Semestre\\Algoritmos y Estructuras de Datos I\\Proyecto 2\\Cliente\\src\\Imágenes\\icono.PNG"));
        this.controladorInicio = inicio;
        this.stage = stage;
    }
    /**
     * Método que recibe los resultados de busqueda del servidor
     */
    public void recibirFrase(String frase, String palabras) {
        MainController.total = palabras;
        MainController.mensaje = frase;
        System.out.println(mensaje);

    }
    /**
     * Método que coloca los resultados en pantalla
     */
    public void colocar(ActionEvent event){
        System.out.println(mensaje);
        String[] separado = mensaje.split(" ");
        if(separado.length==4) {
            pal1.setText(separado[1]);
            pal2.setText(separado[2]);
            pal3.setText(separado[3]);
        }
        else{
            pal1.setText("");
            pal2.setText(separado[0]);
            pal3.setText(separado[1]);
        }
        textA.setText(mensaje);
        posicion.setText(pos + " de " + total);
    }
    /**
     * Método que pasa de resultado al siguiente
     */
    public void pasarPal(ActionEvent event){
        client.pasar();
    }
    /**
     * Método que pasa de resultado al anterior
     */
    public void antPal(ActionEvent event){
        client.anterior();
    }
    /**
     * Método que pasa de resultado al siguiente y lo muestra como un numero
     */
    public void cambiarLab(int posi){
        MainController.pos = String.valueOf(posi);
    }
    /**
     * Método que obtiene el path del archivo
     */
    public void obtenerPath(String link){
        MainController.direccion = link;
    }
    /**
     * Método que muestra el path de un archivo.
     */
    public void getPath(ActionEvent event) throws IOException {
        File objetoFile = new File(direccion.replace(" ",""));
        Desktop.getDesktop().open(objetoFile);
    }
    public void getIteracionesBB(int cont){
        MainController.iteracionesBB = cont;
    }
}