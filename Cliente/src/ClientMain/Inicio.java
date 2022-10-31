package ClientMain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.util.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Arc;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import java.util.Calendar;
import javax.management.StringValueExp;
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
        if(tipo.equals("pdf")){
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
        if(tipo.equals("txt")){
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
        //

        paths.add(path);


        for(int i = 0; i < nombres.size(); i++){
            lista.add(new Archivos(nombres.get(i).toString(), fechas.get(i).toString(),Integer.parseInt(palabras.get(i).toString()),""));
        }
        this.nombreArch.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.fecha.setCellValueFactory(new PropertyValueFactory<>("fechaCreacion"));
        this.cantidad.setCellValueFactory(new PropertyValueFactory<>("cantPal"));

        this.tabla.setItems(lista);
        crearDirecciones(path);
    }
    public String crearDirecciones(String path){
        direcciones += path + " LeoEsDios ";
        System.out.println(direcciones);
        return direcciones;
    }
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
    public void enviarPaths(String path){
        recibirPaths(path);
    }
    public void setStage(Stage primaryStage){
        stage = primaryStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}



