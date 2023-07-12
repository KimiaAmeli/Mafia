package Roles;

import Game.GameHandler;

/**
 * this class is about mayor
 * @version 2021,3,2
 * @author Ameli
 */

public class Mayor extends Player {
    private boolean magicVoted=false;

    /**
     * assign field of citizen
     * @param name
     */
    public Mayor(String name) {
        super(name,WHITE);
    }

    /**
     * return magicVoted
     * @return magicVoted
     */

    public boolean isMagicVoted() {
        return magicVoted;
    }

    /**
     * set magicVoted
     * @param magicVote
     */

    public void setMagicVoted(boolean magicVote) {
        this.magicVoted = magicVote;
    }

    /**
     * magicVote
     * @param player
     */
    public void magicVote(Player player){
        deactivateAll();
        setMagicVoted(true);
        player.setVotes(200);
    }

    /**
     * act of magicVote
     */
    public void act(){
        deactivateAll();
        setMagicVoted(true);
        for (Player player:GameHandler.getAlive()){
            player.setVotes(-400);
        }
    }

    /**
     * deActive all votes
     */
    private void deactivateAll(){
        for (Player player:GameHandler.getAlive()){
            player.setVoted(true);
        }
    }
}
