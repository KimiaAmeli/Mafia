package Roles;

import Game.GameHandler;
import Game.Print;

/**
 * this class is about sniper
 * @version 2021,3,2
 * @author Ameli
 */

public class Sniper extends Player {
    /**
     * assign field of Psychologist
     * @param name
     */
    public Sniper(String name) {
        super(name, WHITE);
    }

    /**
     * act for sniper
     * @param player
     * @return
     */
    public String act(Player player) {
        if (player.getTeam().equals(BLACK)) player.setTarget(true);
        else GameHandler.hit(this);
        return "You " + Print.string("shot ", "y") + player.getName();
    }
}
