package Game;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
/**
 * Main class that manage the game and serverSocket
 * @version 2021,2,3
 * @author Ameli
 */
public abstract class MainServer {
    /**
     * chose the port
     * main method create the serverSocket and until game is not start write a note
     * for each player create socket and accept from serverSocket
     * and also create server for them
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the port number :");
        ServerSocket serverSocket = new ServerSocket(scanner.nextInt());
        while (!Server.isStarted()) {
            System.out.println("waiting for client...");
            Socket socket = serverSocket.accept();
            new Server(socket);
        }
    }
}
