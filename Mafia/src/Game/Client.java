package Game;

import Roles.Player;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * this class manage client
 * @version 2021,3,2
 * @author Ameli
 */
public class Client {

    private final Socket socket;
    private final BufferedReader br;
    private final ClientReader reader;
    private final GameWriter writer;

    /**
     * start the client
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        new Client();
    }

    /**
     * create socket and manage each client
     * takes port
     * @throws Exception
     */
    public Client() throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the port number :");
        socket = new Socket("localhost", Integer.parseInt(br.readLine()));
//        socket = new Socket("localhost", 1111);
        reader = new ClientReader(socket,this);
        writer = new GameWriter(socket);
        initiate();
    }

    /**
     * manage console foe clients
     * get name and check it and get ready to start
     * @throws Exception
     */
    public void initiate() throws Exception {
        while (true) {
            System.out.print("Enter your name : ");
            String name = br.readLine();
            writer.write(name);
            String valid = reader.read();
            if (valid.equals("true")) break;
            else System.out.println(valid);
        }
        while (true) {
            System.out.print("Type «R» if you are ready : ");
            String ready = br.readLine();
            if (ready.toLowerCase().equals("r")) {
                writer.write(ready);
                System.out.println("please wait for other clients...");
                break;
            } else System.out.print(Print.string("Just ", "r"));
        }
        System.out.println(reader.read());
        startGame();
    }

    /**
     * start the game ytil finish
     * @throws Exception
     */
       public void startGame() throws Exception {
        reader.start();
        while (true){
            String string=br.readLine();
            writer.write(string);
            if (string.equals("exit")){
                writer.close();
                reader.close();
                break;
            }
        }
        System.exit(0);
    }

    /**
     * return writer for each player
     * @return writer
     */
    public GameWriter getWriter() {
        return writer;
    }
}
