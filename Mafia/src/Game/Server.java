package Game;

import Roles.*;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * this class create tread to  handle game and players
 * @version 2021,2,3
 * @author Ameli
 */
public class Server extends Thread {
    public static String DAY = "day", EVN = "evn", NGT = "ngt";
    public static final int dayDuration = 5000, evnDuration = 10000, ngtDuration = 20000;
    private static ArrayList<String> names = new ArrayList<>();
    private static ArrayList<Server> servers = new ArrayList<>();
    private static final int number = 6;
    private static boolean started = false;
    private int numberOfDay = 0;
    private final Socket socket;
    private final ServerReader reader;
    private final GameWriter writer;
    private final Initializer initializer;
    private Player player;
    private String name;
    private String state;
    /**
     * servers constructor that assign socket and create reader,writer,initializer for each player
     * and start the initializer
     * @param socket
     * @throws IOException
     */
    public Server(Socket socket) throws IOException {
        this.socket = socket;
        reader = new ServerReader(socket, this);
        writer = new GameWriter(socket);
        initializer = new Initializer(this);
        initializer.start();
    }

    /**
     * start tread and manage the game unit finish
     */
    @Override
    public void run() {
        try {
            writer.write(Print.string("Game has started", "g") + "\n" + "You are " + player.toString());
            reader.start();
            while (true) {
                GameHandler.checkKilledPlayers(player);
                if (GameHandler.checkEnd())break;
                setDay();
                setEvn();
                if (GameHandler.checkEnd())break;
                setNgt();
            }
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * send massage and chat
     * @param string
     * @throws Exception
     */

    public void send(String string) throws Exception {
        writer.write(string);
    }
    /**
     * send string to all of servers except the speaker himself
     * @param serverThread
     * @param string
     * @throws Exception
     */
    public static void sendAllExcept(Server serverThread, String string) throws Exception {
        for (Server server : servers)
            if (!server.equals(serverThread))
                server.send(string);
    }

    /**
     * send players name that are mafia
     * @param string
     * @throws Exception
     */

    public static void sendAllMafia(String string) throws Exception {
        for (Server server : servers)
            if (server.getPlayer().getTeam().equals(Player.BLACK))
                server.send(string);
    }
    /**
     * add players name to arrayList
     * @param string
     */
    public static void addName(String string) {
        names.add(string);
    }

    /**
     * check that the players name is repetitive or not
     * @param string
     * @return true or false
     */
    public static boolean exist(String string) {
        for (String name : names)
            if (name.equals(string))
                return true;
        return false;
    }

    /**
     * manage phase of day
     * @throws Exception
     */
    private void setDay() throws Exception {
        GameHandler.setShot(false);
        numberOfDay++;
        state = DAY;
        send(Print.getDayPanel(numberOfDay));
        if (role("Mafia") || role("GodFather") || role("DoctorLecter")) send(Print.mafiaPanel());
        sleep(1000);
        GameHandler.setInquiry(false);
        GameHandler.setEvents("");
        sleep(dayDuration);
    }

    /**
     * manage phase of voting
     * @throws Exception
     */
    private void setEvn() throws Exception {
        player.reset();
        state = EVN;
        send(Print.getEvnPanel());
        sleep(evnDuration);
        boolean maxVoted = GameHandler.checkVotedPlayers(player);
        sleep(500);
        if (maxVoted) {
            GameHandler.kill(player);
            sendAllExcept(null, player.getName() + Print.string(" has been kiked out", "y"));
        }
    }

    /**
     * manage phase of night
     * @throws Exception
     */
    private void setNgt() throws Exception {
        player.reset();
        state = NGT;
        sleep(1000);
        if (role("Detective") || role("Doctor") || role("Sniper") || role("Psychologist")) {
            send(Print.getNgtPanel());
        } else if (role("Mafia") || role("GodFather") || role("DoctorLecter")) {
            send(Print.getMafiaNgtPanel());
        } else if (role("Bulletproof")) {
            send("Do you want to get dead list roles?  1." + Print.string("yes", "g") + "  2." + Print.string("no", "r"));
        }
        sleep(ngtDuration);
    }

    /**
     * return players role
     * @param string
     * @return
     */

    private boolean role(String string) {
        return player.getRoleString().equals(string);
    }
    /**
     * return players name
     * @return name
     */

    public String getPlayerName() {
        return name;
    }

    /**
     * return reader for each player
     * @return reader
     */

    public ServerReader getReader() {
        return reader;
    }

    /**
     * return writer for each player
     * @return writer
     */
    public GameWriter getWriter() {
        return writer;
    }
    /**
     * set the players name
     * @param name
     */
    public void setPlayerName(String name) {
        this.name = name;
    }
    /**
     * return arrayList for players name
     * @return names
     */

    public static ArrayList<String> getNames() {
        return names;
    }
    /**
     * return arrayList for server
     * @return servers
     */

    public static ArrayList<Server> getServers() {
        return servers;
    }
    /**
     * return the number of player in game
     * @return numberOfPlayer
     */
    public static int getNumber() {
        return number;
    }
    /**
     * specifies that game is started or not
     * @return true or false
     */
    public static boolean isStarted() {
        return started;
    }
    /**
     * set that game is started or not
     * @param started
     */
    public static void setStarted(boolean started) {
        Server.started = started;
    }
    /**
     * return player
     * @return player
     */
    public Player getPlayer() {
        return player;
    }
    /**
     * set player
     * @param player
     */

    public void setPlayer(Player player) {
        this.player = player;
    }
    /**
     * return number of day in game
     * @return numberOfDay
     */

    public int getNumberOfDay() {
        return numberOfDay;
    }

    /**
     * return boolean for state day
     * @return boolean
     */
    public boolean isDay() {
        return state.equals("day");
    }

    /**
     * return boolean for state evening
     * @return boolean
     */
    public boolean isEvn() {
        return state.equals("evn");
    }

    /**
     * return boolean for state night
     * @return boolean
     */
    public boolean isNgt() {
        return state.equals("ngt");
    }

    /**
     * return to string for the name and team for player
     * @return
     */

    @Override
    public String toString() {
        return player.getName() + "-" + player.getLives();
    }

    /**
     * close the socket and finish the player
     * @throws IOException
     */
    public void close() throws IOException {
        socket.close();
    }
}
