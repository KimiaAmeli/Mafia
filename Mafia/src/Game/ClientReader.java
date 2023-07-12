package Game;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * manage reade for client
 * @version 2021,3,2
 * @author Ameli
 */

public class ClientReader extends Thread {
    private final Client client;
    private final Socket socket;
    private final DataInputStream dis;

    /**
     * takes socket and client and assign them
     * @param socket
     * @param client
     * @throws IOException
     */
    public ClientReader(Socket socket, Client client) throws IOException {
        this.client = client;
        this.socket = socket;
        dis = new DataInputStream(socket.getInputStream());
    }

    /**
     * manage the game util finish
     */
    @Override
    public void run() {
        try {
            while (true) {
                String string = read();
                System.out.println(string);
                if (string.equals(Print.string("Mafia won", "r"))||string.equals(Print.string("Citizens won", "g")))
                    System.exit(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * return something that player enters
     * @return read
     * @throws Exception
     */
    public String read() throws Exception {
        return dis.readUTF();
    }

    /**
     * close the client and player
     * @throws IOException
     */
    public void close() throws IOException {
        dis.close();
    }
}
