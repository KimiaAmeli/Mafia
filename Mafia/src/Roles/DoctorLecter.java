package Roles;

import Game.Print;

/**
 * this class is about doctorLector
 * @version 2021,3,2
 * @author Ameli
 */

public class DoctorLecter  extends Player {
    /**
     * assign field of citizen
     * @param name
     */
    public DoctorLecter(String name) {
        super(name,BLACK);
    }

    /**
     * act of doctorLector
     * @param player
     * @return
     */
    public String act(Player player) {
        player.setSaved(true);
        return Print.string("DoctorLecter", "r") + Print.string(" saved ","g") + Print.string(player.getName(), "r");
    }

    /**
     * kill player
     * @param player
     * @return
     */
    public String kill(Player player) {
        player.setTarget(true);
        return Print.string("DoctorLecter", "r") + " shot " + Print.string(player.getName(), "r");
    }
}
