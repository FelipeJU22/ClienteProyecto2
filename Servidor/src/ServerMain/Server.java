package ServerMain;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader readerS;
    private BufferedWriter writerS;

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
                    try {
                        String messageFromClient = readerS.readLine();
                        System.out.println(messageFromClient);
                    } catch (IOException e) {
                        System.out.println("Error recibiendo mensaje");
                        break;
                    }
                }
            }
        }).start();
    }
}