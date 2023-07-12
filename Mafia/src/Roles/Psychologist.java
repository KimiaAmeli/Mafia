package Roles;

import Game.GameHandler;
import Game.Print;

/**
 * this class is about Psychologist
 * @version 2021,3,2
 * @author Ameli
 */

public class Psychologist extends Player {

    private int ability;

    /**
     * assign field of Psychologist
     * @param name
     */
    public Psychologist(String name) {
        super(name, WHITE);
        this.ability = 100;
    }

    /**
     * act for Psychologist
     * @param player
     * @return
     */
    public String act(Player player) {
        if (ability > 0) {
            player.setSilent(true);
            ability--;
            GameHandler.addEvent(Print.string(player.getName() + " muted ", "y"));
            return "You " + Print.string("muted " + player.getName(), "y");
        }
        return Print.string("You couldn't mute anyone.", "y");
    }
}
