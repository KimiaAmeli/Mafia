package Game;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
/**
 * this class is write anyThing for socket
 * @version 2021,3,2
 * @author Ameli
 */
public class GameWriter {
    private final Socket socket;
    private final DataOutputStream dos;
    /**
     * GameWriters constructor takes socket and assign it
     * also create dataOutputStream for each socket
     * @param socket
     * @throws IOException
     */
    public GameWriter(Socket socket) throws IOException {
        this.socket = socket;
        dos = new DataOutputStream(socket.getOutputStream());
    }
    /**
     * takes string and write for player
     * @param string
     * @throws IOException
     */
    public void write(String string) throws IOException {
        try {
            dos.writeUTF(string);
            dos.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * close it
     * @throws IOException
     */
    public void close() throws IOException {
        dos.close();
    }
}
