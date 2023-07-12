package Game;

import Roles.*;

import java.util.List;

/**
 * this class initialize and is tread
 */
public class Initializer extends Thread {

    private final Server server;
    /**
     * Initializers constructor takes server and assign it
     * @param server
     */
    public Initializer(Server server) {
        this.server = server;
    }
    /**
     * run takes players name from players and checks for duplication
     * also takes ready from each players to start the game
     */
    @Override
    public void run() {
        try {
            System.out.println("connected");
            while (true) {
                server.setPlayerName(server.getReader().read());
                if (!Server.exist(server.getPlayerName())) {
                    server.getWriter().write("true");
                    Server.addName(server.getPlayerName());
                    break;
                } else server.getWriter().write(Print.string("this name is already taken", "r"));
            }
            String ready = server.getReader().read();
            if (ready.toLowerCase().equals("r")) {
                addServer(server);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * add server to arrayList for each player
     * @param server
     * @throws Exception
     */
    public static void addServer(Server server) throws Exception {
        Server.getServers().add(server);
        System.out.println((Server.getNumber() - Server.getServers().size() + " players need to start"));
        if (Server.getServers().size() == Server.getNumber()) {
            Server.setStarted(true);
            List<String> list = RoleSelector.getRoles(Server.getNumber());
            for (int i = 0; i < Server.getNumber(); i++) {
                setPlayer(Server.getServers().get(i), list.get(i));
                Server.getServers().get(i).start();
            }
        }
    }
    /**
     * takes server and role than specifies the name and role of each player
     * @param server
     * @param role
     */

    public static void setPlayer(Server server, String role) {
        if (role.equals("Citizen")) server.setPlayer(new Citizen(server.getPlayerName()));
        if (role.equals("Mayor")) server.setPlayer(new Mayor(server.getPlayerName()));
        if (role.equals("Sniper")) server.setPlayer(new Sniper(server.getPlayerName()));
        if (role.equals("Psychologist")) server.setPlayer(new Psychologist(server.getPlayerName()));
        if (role.equals("Doctor")) server.setPlayer(new Doctor(server.getPlayerName()));
        if (role.equals("Detective")) server.setPlayer(new Detective(server.getPlayerName()));
        if (role.equals("Bulletproof")) server.setPlayer(new Bulletproof(server.getPlayerName()));
        if (role.equals("GodFather")) server.setPlayer(new GodFather(server.getPlayerName()));
        if (role.equals("DoctorLecter")) server.setPlayer(new DoctorLecter(server.getPlayerName()));
        if (role.equals("Mafia")) server.setPlayer(new Mafia(server.getPlayerName()));
    }
}