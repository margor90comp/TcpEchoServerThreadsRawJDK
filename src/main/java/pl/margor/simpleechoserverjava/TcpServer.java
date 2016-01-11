package pl.margor.simpleechoserverjava;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * @author margor
 */
public class TcpServer {

    final int port;
    final ServerSocket serverSocket;

    public TcpServer(int port) throws IOException {
        this.port = port;
        serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        while (true) {
            Socket clientSocket = serverSocket.accept();
            ClientService clientSrv = new ClientService(clientSocket);
            new Thread(clientSrv).start();
        }
    }

    public static void main(String[] args) throws IOException {
        TcpServer tcpServer = new TcpServer(9999);
        tcpServer.start();
    }

}
