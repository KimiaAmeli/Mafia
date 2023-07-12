package Game;

import Roles.Player;

import java.util.ArrayList;

/**
 *handler the game
 * create arrayList for dead and alive player
 * and manage them
 * @version 2021,3,2
 * @author Ameli
 */
public abstract class GameHandler {
    private static final ArrayList<Player> dead = new ArrayList<>();
    private static final ArrayList<Player> alive = new ArrayList<>();
    private static boolean inquiry = false;
    private static boolean shot = false;
    private static String events = "";
    /**
     * add player to arrayList from alive players
     * @param player
     */
    public static void add(Player player) {
        alive.add(player);
    }
    /**
     * return arrayList for dead player
     * @return dead
     */
    public static ArrayList<Player> getDead() {
        return dead;
    }
    /**
     * return arrayList for alive player
     * @return alive
     */
    public static ArrayList<Player> getAlive() {
        return alive;
    }
    /**
     * hit one player and if the number of his lives become zero kill it and reseat player
     * @param player
     */
    public static void hit(Player player) {
        player.hit();
        if (player.getLives() == 0) kill(player);
        player.reset();
    }

    /**
     * add player dead in arrayList dead and remove from alive arrayList
     * @param player
     */
    public static void kill(Player player) {
        dead.add(player);
        alive.remove(player);
    }

    /**
     * check players that killed
     * @param player
     */
    public static void checkKilledPlayers(Player player) {
//        System.out.println(player.toString()+player.getName()+" "+player.getLives());
        if (player.isTarget() && !player.isSaved()) hit(player);
        else player.reset();
    }

    /**
     * check that vote of which player is most and kill his
     * @param me
     * @return boolean
     */
    public static boolean checkVotedPlayers(Player me) {
        for (Player player : alive)
            if (me != player)
                if (me.getVotes() <= player.getVotes())
                    return false;
        if (me.getVotes() > 0)
            return true;
        return false;
    }
    /**
     * check that player is alive or not
     * @param player
     * @return boolean
     */
    public static boolean isAlive(Player player) {
        return alive.contains(player);
    }

    /**
     * return inquiry
     * @return inquiry
     */
    public static boolean isInquiry() {
        return inquiry;
    }

    /**
     * set inquiry
     * @param inquiry
     */
    public static void setInquiry(boolean inquiry) {
        GameHandler.inquiry = inquiry;
    }

    /**
     * get events
     * @return events
     */

    public static String getEvents() {
        return events;
    }

    /**
     * set events
     * @param events
     */
    public static void setEvents(String events) {
        GameHandler.events = events;
    }

    /**
     * add even on one day
     * @param event
     */
    public static void addEvent(String event) {
        GameHandler.events = GameHandler.events + "\n" + event;
    }

    /**
     * return shot
     * @return shot
     */
    public static boolean isShot() {
        return shot;
    }

    /**
     * set shot
     * @param shot
     */
    public static void setShot(boolean shot) {
        GameHandler.shot = shot;
    }

    /**
     * return boolean that check alive player
     * @param role
     * @return boolean
     */
    public static boolean isAlive(String role) {
        for (Player player : alive)
            if (player.getRoleString().equals(role))
                return true;
        return false;
    }

    /**
     * check end of game
     * @return
     * @throws Exception
     */
    public static boolean checkEnd() throws Exception {
        int blacks = 0, whites = 0;
        for (Player player : alive)
            if (player.getTeam().equals(Player.WHITE))
                whites++;
            else
                blacks++;
        if (blacks >= whites) {
            Server.sendAllExcept(null, Print.string("Mafia won", "r"));
            return true;
        }
        if (blacks == 0) {
            Server.sendAllExcept(null, Print.string("Citizens won", "g"));
            return true;
        }
        return false;
    }
}
