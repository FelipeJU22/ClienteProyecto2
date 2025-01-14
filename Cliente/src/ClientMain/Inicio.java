package ClientMain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.awt.*;
import java.util.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import java.util.Calendar;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;


import static ClientMain.MainController.recibirPaths;
import static ClientMain.Ordenamiento.Quick_Nombre.quicksort;
import static ClientMain.Ordenamiento.BubbleSort.bubbleSort;
import static ClientMain.Ordenamiento.RadixSort.radixSort;

/**
 * Clase "offline", utilizada para poder cargar los documentos a utilizar en el proyecto.
 */
public class Inicio implements Initializable{

    private Stage stage;
    private File archivo;
    private String path;
    static String direcciones = "";
    @FXML
    private TableView<Archivos> tabla;
    @FXML
    private Button seleccionar;
    @FXML
    private TableColumn<Archivos,String> nombreArch;
    @FXML
    private TableColumn<Archivos,String> fecha;
    @FXML
    private TableColumn<Archivos,String> cantidad;
    private ObservableList<Archivos> lista;
    private List paths = new ArrayList();
    private List nombres = new ArrayList();
    private List fechas = new ArrayList();
    private List palabras = new ArrayList();
    FileChooser seleccionador = new FileChooser();
    private static String[] listaPaths = {};
    private static String[] listaNombre = {};
    private static int[] listaPal = {};
    private static String[] listaFecha = {};
    /**
     * Método utilizada para seleccionar los archivos, y mostrar en pantalla las caracteristicas principales de este
     */
    public void seleccionar(ActionEvent event) throws FileNotFoundException {
        lista = FXCollections.observableArrayList();
        archivo = seleccionador.showOpenDialog(new Stage());
        path = String.valueOf(archivo);
        System.out.println(archivo);
        String nombre = archivo.getName();
        nombres.add(nombre);

        //Cosas de última modificación
        long ms = archivo.lastModified();
        Date d = new Date(ms);
        Calendar c = new GregorianCalendar();
        c.setTime(d);
        String dia = Integer.toString(c.get(Calendar.DATE));
        String mes = Integer.toString(c.get(Calendar.MONTH));
        String annio = Integer.toString(c.get(Calendar.YEAR));
        String hora = Integer.toString(c.get(Calendar.HOUR_OF_DAY));
        String minuto = Integer.toString(c.get(Calendar.MINUTE));
        String segundo = Integer.toString(c.get(Calendar.SECOND));
        fechas.add(dia+"/"+mes+"/"+annio);
        //

        String[] partes = nombre.split("\\.");
        String tipo = partes[partes.length-1];

        //Scanners
        if(tipo.equals("docx")){
            try{
                 XWPFDocument docx = new XWPFDocument(new FileInputStream(path));
                 List<XWPFParagraph> parrafo =docx.getParagraphs();
                 String textoFullDocx = "";
                 List finalDocx = new ArrayList();
                 for(XWPFParagraph paragraph:parrafo){
                    textoFullDocx = textoFullDocx + " " + paragraph.getText();
                 }
                 String[] divididoDocx = textoFullDocx.split(" ");
                 for(int i = 0; i < divididoDocx.length ; i++){
                    String elemento = divididoDocx[i];
                 if(!elemento.equals(" ")){
                    finalDocx.add(divididoDocx[i]);
                    }
                 }
                 int tamano1 = divididoDocx.length-1;
                 palabras.add(tamano1);
            } catch (IOException e) {
                System.out.println(e);
            }

        }
        else if(tipo.equals("pdf")){
             try {
                 File file = new File(path);
                 PDDocument document = Loader.loadPDF(file);
                 PDFTextStripper pdftext = new PDFTextStripper();
                 String pdftextdata = pdftext.getText(document);
                 List finalPDF = new ArrayList();
                 String[] divididoPDF = pdftextdata.split("[ \n\t\r,.;:!?(){}]");
                 for(int i = 0; i < divididoPDF.length ; i++){
                    String elemento = divididoPDF[i];
                 if(!elemento.equals("")){
                    finalPDF.add(divididoPDF[i]);
                 }
                 }
                 int tamano2 = finalPDF.size();
                 palabras.add(tamano2);
             } catch (IOException e) {
                 e.printStackTrace();
             }

        }
        else if(tipo.equals("txt")){
             File file = new File(path);
             Scanner scan = new Scanner(file);
             String textoFulltxt= "";
             List finalTXT = new ArrayList();
             while(scan.hasNextLine()){
                textoFulltxt = textoFulltxt + scan.nextLine();
             }
             String[] divididoTXT = textoFulltxt.split("[ \n\t\r,.;:!?(){}]");
             for(int i = 0; i < divididoTXT.length ; i++){
                String elemento = divididoTXT[i];
                if(!elemento.equals("")){
                finalTXT.add(divididoTXT[i]);
             }
             }
             int tamano3 = finalTXT.size();
             palabras.add(tamano3);
             }
        else{
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setContentText("Tipo de archivo incorrecto");
            alerta.showAndWait();

        }
        //

        paths.add(path);

        String[] listaNombres = new String[nombres.size()];
        String[] listaFechas = new String[nombres.size()];
        int[] listaPalabras = new int[nombres.size()];
        String[] list = new String[nombres.size()];
        for(int i = 0; i < nombres.size(); i++){
            lista.add(new Archivos(nombres.get(i).toString(), fechas.get(i).toString(),Integer.parseInt(palabras.get(i).toString()),paths.get(i).toString()));
            listaNombres[i] = lista.get(i).getNombre();
            listaFechas[i] = lista.get(i).getFechaCreacion();
            listaPalabras[i] = lista.get(i).getCantPal();
            list[i] = lista.get(i).getDireccion();
        }
        listaNombre = listaNombres;
        listaPal = listaPalabras;
        listaFecha = listaFechas;
        listaPaths = list;
        this.nombreArch.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.fecha.setCellValueFactory(new PropertyValueFactory<>("fechaCreacion"));
        this.cantidad.setCellValueFactory(new PropertyValueFactory<>("cantPal"));

        this.tabla.setItems(lista);



        crearDirecciones(path);
    }
    /**
     * Método utilizada, para crear el mensaje a enciar al servidor, el cual contiene los paths de los documentos.
     */
    public String crearDirecciones(String path){
        direcciones += path + " LeoEsDios ";
        System.out.println(direcciones);
        return direcciones;
    }
    /**
     * Método utilizada para poder pasar de la ventana inicio, a la ventana principal, la cuál conecta con el servidor
     */
    public void pasar(ActionEvent event) throws IOException {
        enviarPaths(direcciones);
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
    /**
     * Método que envía  los paths a la ventana principal
     */
    public void enviarPaths(String path){
        recibirPaths(path);
    }
    /**
     * Método que setea la ventana inicio como principal
     */
    public void setStage(Stage primaryStage){
        stage = primaryStage;
    }

    /**
     * Método que inicializa el programa
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    /**
     * Método que ordena los datos de la tabla y los paths que envía el sistema, en orden alfabetico
     */
    public void ordenarQuick(ActionEvent event) {
        lista.clear();
        String[] listaaa = listaNombre;

        quicksort(listaaa, 0, listaaa.length-1);
        for(int i = 0; i < listaaa.length; i++){
            lista.add(new Archivos(listaaa[i], listaFecha[i], listaPal[i], listaPaths[i]));
        }
        this.nombreArch.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.fecha.setCellValueFactory(new PropertyValueFactory<>("fechaCreacion"));
        this.cantidad.setCellValueFactory(new PropertyValueFactory<>("cantPal"));
        this.tabla.setItems(lista);
    }
    /**
     * Método que ordena los datos de la tabla y los paths que envía el sistema, en orden de fecha de más vieja a más nueva
     */
    public void ordenarBubble(ActionEvent event){
        lista.clear();
        int[] milisegundos = new int[listaFecha.length];
        for(int i = 0; i < listaFecha.length; i++){
            milisegundos [i]= Integer.parseInt(listaFecha[i].replace("/",""));
        }
        bubbleSort(milisegundos);
        String[] tiempo = new String[milisegundos.length];
        for(int i = 0; i < milisegundos.length; i++){
            tiempo[i] = String.valueOf(milisegundos[i]);
        }
        for(int i = 0; i < milisegundos.length; i++){
            lista.add(new Archivos(listaNombre[i], tiempo[i], listaPal[i], listaPaths[i]));
        }
        this.nombreArch.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.fecha.setCellValueFactory(new PropertyValueFactory<>("fechaCreacion"));
        this.cantidad.setCellValueFactory(new PropertyValueFactory<>("cantPal"));
        this.tabla.setItems(lista);

    }
    /**
     * Método que ordena los datos de la tabla y los paths que envía el sistema, en orden ascendente con la cantidad de palabras
     */
    public void ordenarRadix(ActionEvent event){
        lista.clear();
        int[] radixList = listaPal;
        radixSort(radixList, radixList.length);
        for(int i = 0; i < radixList.length; i++){
            lista.add(new Archivos(listaNombre[i], listaFecha[i], radixList[i], listaPaths[i]));
        }
        this.nombreArch.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.fecha.setCellValueFactory(new PropertyValueFactory<>("fechaCreacion"));
        this.cantidad.setCellValueFactory(new PropertyValueFactory<>("cantPal"));
        this.tabla.setItems(lista);

    }
}



