package Roles;

import Game.Print;

/**
 * this class is about doctor
 * @version 2021,3,2
 * @author Ameli
 */

public class Doctor extends Player {
    /**
     * assign field of citizen
     * @param name
     */
    public Doctor(String name) {
        super(name, WHITE);
    }

    /**
     * act of doctor
     * @param player
     * @return
     */
    public String act(Player player) {
        player.setSaved(true);
        return "You " + Print.string("saved ", "g") + player.getName();
    }
}
