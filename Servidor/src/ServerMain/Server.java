package ServerMain;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Server {
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader readerS;
    private BufferedWriter writerS;
    private List mensaje = new ArrayList();

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
                        System.out.println(messageFromClient);
                        String [] mensaje = messageFromClient.split("@@@");
                        String recibido = Arrays.toString(mensaje);
                        System.out.println(Arrays.toString(mensaje));;
                        if(mensaje[0].equals("0")){
                            System.out.println("Crear árbol binario");
                            System.out.println();
                        }
                        if(messageFromClient.equals("paths recibidos: ")){

                            a = messageFromClient;
                            System.out.println(a);
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