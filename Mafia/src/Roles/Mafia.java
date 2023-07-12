package Roles;

import Game.Print;

/**
 * this class is about Mafia
 * @version 2021,3,2
 * @author Ameli
 */

public class Mafia  extends Player {
    /**
     * assign field of citizen
     * @param name
     */
    public Mafia(String name) {
        super(name,BLACK);
    }

    /**
     * kill player
     * @param player
     * @return
     */
    public String kill(Player player) {
        player.setTarget(true);
        return Print.string("A mafia", "r") + " shot " + Print.string(player.getName(), "r");
    }
}
