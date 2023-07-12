package Roles;

import Game.Print;

/**
 * this class is about godFather
 * @version 2021,3,2
 * @author Ameli
 */

public class GodFather extends Player {
    /**
     * assign field of citizen
     * @param name
     */
    public GodFather(String name) {
        super(name, BLACK);
    }

    /**
     * kill player
     * @param player
     * @return
     */
    public String kill(Player player) {
        player.setTarget(true);
        return Print.string("GodFather", "r") + " shot " + Print.string(player.getName(), "r");
    }
}
