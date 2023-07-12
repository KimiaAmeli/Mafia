package Game;

import Roles.*;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * this class for reader in server
 * @version 2021,3,2
 * @author Ameli
 */
public class ServerReader extends Thread {
    private final Server server;
    private final Socket socket;
    private final DataInputStream dis;
    /**
     * serverReaders constructor takes socket and server and assign them
     * also create dataInputStream for each socket
     * @param socket
     * @param server
     * @throws IOException
     */
    public ServerReader(Socket socket, Server server) throws IOException {
        this.server = server;
        this.socket = socket;
        dis = new DataInputStream(socket.getInputStream());
    }

    /**
     * manage the game in server
     */
    @Override
    public void run() {
        try {
            while (true) {
                String string = read();
                if (string.equals("exit")) {
                    GameHandler.kill(server.getPlayer());
                    dis.close();
                    server.getWriter().close();
                    server.close();
                    Server.getServers().remove(server);
                    Server.sendAllExcept(null, server.getPlayer().getName() + Print.string(" left the game.", "r"));
                    break;
                } else {
                    if (GameHandler.isAlive(server.getPlayer())) {
                        if (server.isDay()) {
                            if (!server.getPlayer().isSilent()) {
                                day(string);
                            }
                        } else if (server.isEvn()) {
                            evn(string);
                        } else if (server.isNgt()) {
                            ngt(string);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * send chat to all players except himself whit the name of player on the they
     * @param string
     * @throws Exception
     */
    private void day(String string) throws Exception {
        Server.sendAllExcept(server, server.getPlayerName() + " : " + string);
    }
    /**
     * manage what happens in the voting
     * @param string
     * @throws IOException
     */
    private void evn(String string) throws IOException {
        if (string.startsWith("*") && server.getPlayer().getRoleString().equals("Mayor")) {
            Mayor me = (Mayor) server.getPlayer();
            try {
                if (string.equals("*")) {
                    me.act();
                    Server.sendAllExcept(null, "Mayor " + Print.string("abrogated voting", "r"));
                } else {
                    int index = Integer.parseInt(string.substring(1)) - 1;
                    Player player = GameHandler.getAlive().get(index);
                    if (!me.isMagicVoted()) {
                        me.magicVote(player);
                        Server.sendAllExcept(null, Print.string("Mayor", "g") + " voted for " + Print.string(player.getName(), "r"));
                    }
                }
            } catch (Exception e) {
                server.getWriter().write(Print.string("Just", "r") + " Enter number (or * for abrogate or *num for magic vote).");
            }
        } else {
            try {
                int index = Integer.parseInt(string) - 1;
                Player player = GameHandler.getAlive().get(index);
                if (!server.getPlayer().isVoted()) {
                    server.getPlayer().vote(player);
                    Server.sendAllExcept(null, server.getPlayerName() + " voted for " + Print.string(player.getName() + " " + player.getVotes(), "r"));
                }
            } catch (Exception e) {
                if (server.getPlayer().getRoleString().equals("Mayor"))
                    server.getWriter().write(Print.string("Just", "r") + " Enter number (or * for abrogate or *num for magic vote).");
                else
                    server.getWriter().write(Print.string("Just", "r") + " Enter number.");
            }
        }
    }

    /**
     * manage what happens at night
     * @param string
     * @throws IOException
     */
    private void ngt(String string) throws IOException {
        try {
            if (server.getPlayer().getRoleString().equals("Detective")) {
                int index = Integer.parseInt(string) - 1;
                Player player = GameHandler.getAlive().get(index);
                Detective detective = (Detective) server.getPlayer();
                server.send(detective.act(player));
            }
            if (server.getPlayer().getRoleString().equals("Sniper")) {
                int index = Integer.parseInt(string) - 1;
                Player player = GameHandler.getAlive().get(index);
                Sniper sniper = (Sniper) server.getPlayer();
                server.send(sniper.act(player));
            }
            if (server.getPlayer().getRoleString().equals("Psychologist")) {
                int index = Integer.parseInt(string) - 1;
                Player player = GameHandler.getAlive().get(index);
                Psychologist psychologist = (Psychologist) server.getPlayer();
                server.send(psychologist.act(player));
            }
            if (server.getPlayer().getRoleString().equals("Doctor")) {
                int index = Integer.parseInt(string) - 1;
                Player player = GameHandler.getAlive().get(index);
                Doctor doctor = (Doctor) server.getPlayer();
                server.send(doctor.act(player));
            }
            if (server.getPlayer().getRoleString().equals("Bulletproof")) {
                int index = Integer.parseInt(string);
                Bulletproof bulletproof = (Bulletproof) server.getPlayer();
                if (index == 1) bulletproof.act();
            }
            if (server.getPlayer().getRoleString().equals("GodFather")) {
                int index = Integer.parseInt(string) - 1;
                Player player = GameHandler.getAlive().get(index);
                GodFather godFather = (GodFather) server.getPlayer();
                if (player.getTeam().equals(Player.WHITE)) {
                    if (!GameHandler.isShot()) {
                        Server.sendAllMafia(godFather.kill(player));
                        GameHandler.setShot(true);
                    }
                }
            }
            if (server.getPlayer().getRoleString().equals("DoctorLecter")) {
                int index = Integer.parseInt(string) - 1;
                Player player = GameHandler.getAlive().get(index);
                DoctorLecter doctorLecter = (DoctorLecter) server.getPlayer();
                if (player.getTeam().equals(Player.WHITE)) {
                    if (!GameHandler.isShot()) {
                        if (!GameHandler.isAlive("GodFather")) {
                            Server.sendAllMafia(doctorLecter.kill(player));
                            GameHandler.setShot(true);
                        } else {
                            Server.sendAllMafia(server.getPlayer().getName() + " chose " + player.getName());
                        }
                    }
                } else {
                    Server.sendAllMafia(doctorLecter.act(player));
                }
            }
            if (server.getPlayer().getRoleString().equals("Mafia")) {
                int index = Integer.parseInt(string) - 1;
                Player player = GameHandler.getAlive().get(index);
                Mafia mafia = (Mafia) server.getPlayer();
                if (player.getTeam().equals(Player.WHITE)) {
                    if (!GameHandler.isShot()) {
                        if (!GameHandler.isAlive("GodFather") && !GameHandler.isAlive("DoctorLecter")) {
                            Server.sendAllMafia(mafia.kill(player));
                            GameHandler.setShot(true);
                        } else {
                            Server.sendAllMafia(server.getPlayer().getName() + " chose " + player.getName());
                        }
                    }
                }
            }


        } catch (Exception e) {
            server.getWriter().write(Print.string("Just", "r") + " Enter number.");
        }
    }
    /**
     * return something that players write
     * @return read
     * @throws Exception
     */
    public String read() throws Exception {
        return dis.readUTF();
    }

}
