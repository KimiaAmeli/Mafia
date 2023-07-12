package Game;

import Roles.Player;

/**
 * this class manage the console and color of text
 * @version 2021,2,3
 * @author Ameli
 */
public abstract class Print {
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    private static final String WHITE = "\u001B[37m";
    public static final String BG = "\u001B[47m";
    private static final String Color = BLUE;
    private static final String L = Color + "│" + RESET;
    private static final String L0 = Color + "║" + RESET;
    private static final String L1 = Color + "═" + RESET;
    private static final String L2 = Color + "╔" + RESET;
    private static final String L3 = Color + "╗" + RESET;
    private static final String L4 = Color + "╚" + RESET;
    private static final String L5 = Color + "╝" + RESET;
    private static final String L6 = Color + "╦" + RESET;
    private static final String L7 = Color + "╩" + RESET;
    private static final String L8 = Color + "╠" + RESET;
    private static final String L9 = Color + "╣" + RESET;
    private static final String dot = Color + "." + RESET;
    private static final int k = 12;

    /**
     * return colorful text
     * @param text
     * @param color
     * @return text
     */
    public static String string(String text, String color) {
        switch (color) {
            case "r":
                return (RED + text + RESET);
            case "g":
                return (GREEN + text + RESET);
            case "b":
                return (BLUE + text + RESET);
            case "y":
                return (YELLOW + text + RESET);
            case "w":
                return (WHITE + text + RESET);
            default:
                return (text);
        }
    }

    /**
     * specifies team of player
     * @param string
     * @return string
     */
    public static String team(String string) {
        if (string.equals(Player.WHITE)) return Print.string("⚫", "g");
        return Print.string("⚫", "r");
    }

    /**
     * clean the console
     * @return string
     */
    public static String clean() {
        String string = "";
        for (int i = 0; i < 25; i++)
            string = string + "\n";
        return string;
    }

    /**
     * manage chart
     * @param string
     * @param x
     * @return string
     */
    public static String space(String string, int x) {
        string = string + "";
        int l = string.length();
        StringBuilder stringBuilder = new StringBuilder(string);
        for (int i = 0; i < x - l; i++) {
            stringBuilder.append(" ");
        }
        string = stringBuilder.toString();
        return string;
    }

    /**
     * return panel on the day
     * @param numberOfDay
     * @return string
     */
    public static String getDayPanel(int numberOfDay) {
//        System.out.println("A " + GameHandler.getAlive().toString());
//        System.out.println("D " + GameHandler.getDead().toString());
        String string = "\n" + L2 + line(k + 3) + L3 + "\n";
        string = string + L0 + BG + space("     Day " + numberOfDay, k + 3) + RESET + L0 + "\n";
        string = string + Print.getAllNames();
        string = string + "\n" + GameHandler.getEvents();
        if (GameHandler.isInquiry()) {
            String roles = "inquiry : ";
            for (Player player : GameHandler.getDead())
                roles = roles + player.getRoleString() + "   ";
            return string + "\n" + roles;
        }
        return string;
    }

    /**
     * return panel on the vote
     * @return string
     */
    public static String getEvnPanel() {
        String string = "\n" + L2 + line(k + 3) + L3 + "\n";
        string = string + L0 + BG + space("     Vote", k + 3) + RESET + L0 + "\n";
        string = string + Print.getAliveNames();
        return string;
    }

    /**
     * return panel at night
     * @return string
     */
    public static String getNgtPanel() {
        String string = "\n" + L2 + line(k + 3) + L3 + "\n";
        string = string + L0 + BG + space("     Night", k + 3) + RESET + L0 + "\n";
        string = string + Print.getAliveNames();
        return string;
    }

    /**
     * return panel for mafia at night
     * @return string
     */
    public static String getMafiaNgtPanel() {
        String string = getNgtPanel();
        string = string + mafiaPanel() + "\n";
        return string;
    }

    /**
     * return panel for mafia
     * @return string
     */
    public static String mafiaPanel() {
        String string = "";
        for (Player player : GameHandler.getAlive())
            if (player.getTeam().equals(Player.BLACK))
                string = string + player.getName() + " : " + player.toString() + "    ";
        return string + "\n";
    }

    /**
     * return players name
     * @return string
     */
    public static String getAllNames() {
        String string = L8 + line(2) + L6 + line(k) + L9 + "\n";
        for (int i = 0; i < GameHandler.getAlive().size(); i++)
            string = string + L0 + space((i + 1) + "", 2) + L0 + space(GameHandler.getAlive().get(i).getName(), k) + L0 + "\n";
        for (Player player : GameHandler.getDead())
            string = string + L0 + "☠ " + L0 + Print.string(space(player.getName(), k), "w") + L0 + "\n";
        string = string + L4 + line(2) + L7 + line(k) + L5 + "\n";
        return string;
    }

    /**
     * return alive players name
     * @return string
     */
    public static String getAliveNames() {
        String string = L8 + line(2) + L6 + line(k) + L9 + "\n";
        for (int i = 0; i < GameHandler.getAlive().size(); i++)
            string = string + L0 + space((i + 1) + "", 2) + L0 + space(GameHandler.getAlive().get(i).getName(), k) + L0 + "\n";
        string = string + L4 + line(2) + L7 + line(k) + L5 + "\n";
        return string;
    }

    public static String L() {
        return L;
    }

    /**
     * create line
     * @param x
     * @return string
     */
    public static String line(int x) {
        String string = "";
        for (int i = 0; i < x; i++)
            string = string + L1;
        return string;
    }


}
