package ClientMain;

import java.io.*;
import java.net.Socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ListaDoblementeEnlazadaCircular4k.Nodos;
import ListaDoblementeEnlazadaCircular4k.LinkedList;
import com.sun.tools.javac.Main;

import ClientMain.MainController;

public class Client {
    private Socket socket;
    private BufferedReader readerC;
    private BufferedWriter writerC;
    private List listaNodos = new ArrayList();
    private MainController controller = new MainController();
    private String frase = "";
    private LinkedList listaenlazada = new LinkedList();
    public Client(Socket socket) {
        try {
            this.socket = socket;
            this.readerC = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writerC = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            System.out.println("Error creando el cliente");
        }
    }
    public void enviarMsjServidor(String clientMessage) {
        try {
            writerC.write(clientMessage);
            writerC.newLine();
            writerC.flush();
        } catch (IOException e) {
            System.out.println("Error enviando el mensaje al servidor");
        }
    }


    public void recibirMsjServidor() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (socket.isConnected()) {
                    try {
                        String messagefromServer = readerC.readLine();
                        System.out.println(messagefromServer);
                        String[] mensaje1 = messagefromServer.split("PPP");
                        String[] mensaje2 = mensaje1[1].split(" Yacasi ");
                        for (int x = 0; x < mensaje2.length; x++) {
                            listaNodos.add(Arrays.toString(mensaje2[x].split("@@@")));
                        }
                        System.out.println(listaNodos);
                        for(int i = 0; i < listaNodos.size(); i++){
                            String[] lista = listaNodos.get(i).toString().replace("[","").replace("]","").split(",");
                            Nodos palabra = new Nodos(lista[0],lista[1],lista[2],lista[3], i+1);
                            listaenlazada.añadir(lista[0],lista[1],lista[2],lista[3],i+1);
                        }
                        if(mensaje1[0].equals("0")){
                            controller.recibirFrase(listaenlazada.current.getPalabra(),String.valueOf(listaNodos.size()));
                            controller.cambiarLab(listaenlazada.current.pos);
                            controller.obtenerPath(listaenlazada.current.path);
                        }
                    } catch (IOException e) {
                        System.out.println("Error recibiendo mensaje del servidor");
                        break;
                    }
                }
            }
        }).start();
    }
    public void pasar(){
        listaenlazada.adelanteCurrent();
        controller.recibirFrase(listaenlazada.current.getPalabra(),String.valueOf(listaNodos.size()));
        controller.cambiarLab(listaenlazada.current.pos);
        controller.obtenerPath(listaenlazada.current.path);
    }
    public void anterior(){
        listaenlazada.atrasCurrent();
        controller.recibirFrase(listaenlazada.current.getPalabra(),String.valueOf(listaNodos.size()));
        controller.cambiarLab(listaenlazada.current.pos);
        controller.obtenerPath(listaenlazada.current.path);
    }


}

