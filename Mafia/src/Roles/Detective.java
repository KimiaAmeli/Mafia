package Roles;

import Game.Print;
/**
 * this class is about detective
 * @version 2021,3,2
 * @author Ameli
 */
public class Detective extends Player {
    /**
     * assign field of detective
     * @param name
     */
    public Detective(String name) {
        super(name,WHITE);
    }

    /**
     * act of detective
     * @param player
     * @return
     */
    public String act(Player player){
        if (player.getRoleString().equals("GodFather"))return player.getName()+" is "+ Print.team(WHITE);
        return player.getName()+" is "+ Print.team(player.getTeam());
    }
}
