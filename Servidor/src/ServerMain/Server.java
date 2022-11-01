package ServerMain;

import ArbolBB.Arbol;
import ArbolBB.Nodo;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import javax.management.StringValueExp;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader readerS;
    private BufferedWriter writerS;
    private List mensaje = new ArrayList();
    private List superLista = new ArrayList();
    private List listaParseada = new ArrayList();
    private List listaStrings = new ArrayList();
    private String[] arboles = new String[0];
    public Arbol arbol = new Arbol();
    static String lista = "";

    public Server(ServerSocket serverSocket) {
        try {
            this.serverSocket = serverSocket;
            this.socket = serverSocket.accept();
            this.readerS = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writerS = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            System.out.println("Error conectando con el servidor");
        }
    }

    public void enviarMsjClient(String serverMessage) {
        try {
            writerS.write(serverMessage);
            writerS.newLine();
            writerS.flush();
        } catch (IOException e) {
            System.out.println("Error enviando el mensaje");
        }
    }

    public void recibirMsjClient() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (socket.isConnected()) {
                    String a = "";
                    try {
                        String messageFromClient = readerS.readLine();
                        //System.out.println(messageFromClient);
                        String [] mensaje = messageFromClient.split("@@@");
                        //System.out.println(Arrays.toString(mensaje));;
                        if(mensaje[0].equals("0")){
                            String [] arboles = mensaje[1].split(" LeoEsDios ");
                            System.out.println(Arrays.toString(arboles));
                            for(int i = 0; i < arboles.length; i++){
                                String[] partes = arboles[i].split("\\.");
                                String tipo = partes[partes.length-1];
                                if(tipo.equals("docx")){
                                    try {
                                        XWPFDocument docx = new XWPFDocument(new FileInputStream(arboles[i]));
                                        List<XWPFParagraph> parrafo = docx.getParagraphs();
                                        String textoFullDocx = "";
                                        List finalDocx = new ArrayList();
                                        for (XWPFParagraph paragraph : parrafo) {
                                            textoFullDocx = textoFullDocx + " " + paragraph.getText();
                                        }
                                        String[] divididoDocx = textoFullDocx.split(" ");
                                        String Docx = "";
                                        for (int j = 0; j < divididoDocx.length; j++) {
                                            String elemento = divididoDocx[j];
                                            if (!elemento.equals(" ")){
                                                if(!elemento.equals(""))
                                                    finalDocx.add(divididoDocx[j]);
                                                    String Docxtemp = finalDocx.toString().replace("[", "");
                                                    Docx = Docxtemp.replace("]", "");
                                            }
                                        }
                                        int tamano1 = divididoDocx.length - 1;
                                        superLista.add(finalDocx);
                                        listaStrings.addAll(finalDocx);
                                    } catch (IOException e) {
                                        System.out.println(e);
                                    }
                                }
                                if(tipo.equals("pdf")){
                                    try {
                                        File file = new File(arboles[i]);
                                        PDDocument document = Loader.loadPDF(file);
                                        PDFTextStripper pdftext = new PDFTextStripper();
                                        String pdftextdata = pdftext.getText(document);
                                        List finalPDF = new ArrayList();
                                        String[] divididoPDF = pdftextdata.split("[ \n\t\r,.;:!?(){}]");
                                        String pdf = "";
                                        for (int j = 0; j < divididoPDF.length; j++) {
                                            String elemento = divididoPDF[j];
                                            if (!elemento.equals("")) {
                                                finalPDF.add(divididoPDF[j]);
                                                String pdftemp = finalPDF.toString().replace("[", "");
                                                pdf = pdftemp.replace("]", "");
                                            }
                                        }
                                        int tamano2 = finalPDF.size();
                                        superLista.add(finalPDF);
                                        listaStrings.addAll(finalPDF);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                }
                                if(tipo.equals("txt")) {
                                    File file = new File(arboles[i]);
                                    Scanner scan = new Scanner(file);
                                    String textoFulltxt = "";
                                    List finalTXT = new ArrayList();
                                    while (scan.hasNextLine()) {
                                        textoFulltxt = textoFulltxt + scan.nextLine();
                                    }
                                    String[] divididoTXT = textoFulltxt.split("[ \n\t\r,.;:!?(){}]");
                                    String TXT = "";
                                    for (int j = 0; j < divididoTXT.length; j++) {
                                        String elemento = divididoTXT[j];
                                        if (!elemento.equals("")) {
                                            finalTXT.add(divididoTXT[j]);
                                            String TXTtemp = finalTXT.toString().replace("[", "");
                                            TXT = TXTtemp.replace("]", "");
                                        }
                                    }
                                    int tamano3 = finalTXT.size();
                                    superLista.add(finalTXT);
                                    listaStrings.addAll(finalTXT);
                                }
                                //
                            }
                            System.out.println(superLista + "       SUPERLISTA");
                            System.out.println(listaStrings + "    STRINGS");
                            System.out.println(Arrays.toString(arboles)+ "    ARBOLES");
                            String[] listafinalsiosi = new String[4];
                            int numero = 1;
                            for(int x = 0; x < superLista.size(); x++){
                                int prueba = 1;
                                String[] lista = superLista.get(x).toString().replace("[","").replace("]","").split(",");
                                System.out.println(Arrays.toString(lista)+"    LISTA");
                                for(int y = 0; y < lista.length; y++){
                                    for(int z = 0; z < listaStrings.size(); z++){
                                        if((listaStrings.get(z)).equals(lista[y]) || (" "+listaStrings.get(z)).equals(lista[y])){
                                            listafinalsiosi[0] = listaStrings.get(z).toString();
                                            listafinalsiosi[1] = arboles[x];
                                            listafinalsiosi[2] = String.valueOf(numero);
                                            listafinalsiosi[3] = String.valueOf(prueba);
                                            prueba++;
                                            numero++;
                                            listaParseada.add(Arrays.toString(listafinalsiosi)) ;
                                            break;
                                        }
                                    }
                                }
                            }
                            //System.out.println(listaParseada);
                            enviarLista(listaStrings.toString());
                            for(int k = 0; k < listaParseada.size(); k++){
                                //System.out.println(listaParseada.get(k));
                                String[] listaNodos = listaParseada.get(k).toString().replace("[","").replace("]","").split(",");
                                Nodo doc = new Nodo(listaNodos[0],listaNodos[1],listaNodos[2].replace(" ", ""),listaNodos[3].replace(" ", ""));
                                arbol.addNode(listaNodos[0],listaNodos[1],listaNodos[2].replace(" ", ""),listaNodos[3].replace(" ", ""));
                            }

                            /** Aquí se crea el arbol de fish*/


                            superLista.clear();
                            listaStrings.clear();
                            listaParseada.clear();
                        }
                        if(mensaje[0].equals("1")){
                            enviarMsjClient( "0"+"PPP"+arbol.findNode(mensaje[1]));
                        }
                        if(mensaje[0].equals("2")){
                            System.out.println("Añadir: " + mensaje[1]);

                        }
                        if(mensaje[0].equals("3")){
                            System.out.println("Enviar archivos para abrir");
                        }

                    } catch (IOException e) {
                        System.out.println("Error recibiendo mensaje");
                        break;
                    }
                }
            }
        }).start();
    }
    public void enviarLista(String lista){
        arbol.recibirLista(lista);
    }
}