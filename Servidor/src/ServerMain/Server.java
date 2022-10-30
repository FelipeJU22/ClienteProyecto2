package ServerMain;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Server {
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader readerS;
    private BufferedWriter writerS;
    private List mensaje = new ArrayList();
    private List superLista = new ArrayList();

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
                        System.out.println(Arrays.toString(mensaje));;
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
                                            if (!elemento.equals(" ")) {
                                                finalDocx.add(divididoDocx[j]);
                                                String Docxtemp = finalDocx.toString().replace("[", "");
                                                Docx =Docxtemp.replace("]", "");
                                            }
                                        }
                                        int tamano1 = divididoDocx.length - 1;
                                        superLista.add(Docx);

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
                                                pdf =pdftemp.replace("]", "");
                                            }
                                        }
                                        int tamano2 = finalPDF.size();
                                        superLista.add(pdf);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                }
                                if(tipo.equals("txt")){
                                    File file = new File(arboles[i]);
                                    Scanner scan = new Scanner(file);
                                    String textoFulltxt= "";
                                    List finalTXT = new ArrayList();
                                    while(scan.hasNextLine()){
                                        textoFulltxt = textoFulltxt + scan.nextLine();
                                    }
                                    String[] divididoTXT = textoFulltxt.split("[ \n\t\r,.;:!?(){}]");
                                    String TXT = "";
                                    for(int j = 0; j < divididoTXT.length ; j++){
                                        String elemento = divididoTXT[j];
                                        if(!elemento.equals("")){
                                            finalTXT.add(divididoTXT[j]);
                                            String TXTtemp = finalTXT.toString().replace("[", "");
                                            TXT = TXTtemp.replace("]", "");
                                        }
                                    }
                                    int tamano3 = finalTXT.size();
                                    superLista.add(TXT);
                                }
                                //
                            }
                            System.out.println(superLista);
                        }
                        if(mensaje[0].equals("1")){
                            System.out.println("Buscar una palabra");
                        }
                        if(mensaje[0].equals("2")){
                            System.out.println("Añadir archivo a arbol binario");
                        }
                        if(mensaje[0].equals("3")){
                            System.out.println("Eliminar archivo árbol binario");
                        }
                        if(mensaje[0].equals("4")){
                            System.out.println("Eliminar todo el proyecto");
                        }
                        if(mensaje[0].equals("5")){
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
}