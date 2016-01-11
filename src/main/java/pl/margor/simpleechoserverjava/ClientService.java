package pl.margor.simpleechoserverjava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
 * @author margor
 */
public class ClientService implements Runnable {

    final Socket clientSocket;
    static final String CLOSE_COMMAND = "END";

    public ClientService(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));)
        {
            String rcvLine;
            do {
                rcvLine = in.readLine();
                switch (rcvLine) {
                    case CLOSE_COMMAND:
                        clientSocket.close();
                        break;
                    default:
                        out.println(rcvLine);
                        out.flush();
                }
            } while (rcvLine != null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
