package Roles;

import Game.GameHandler;

/**
 * this class is about bulletproof
 * @version 2021,3,2
 * @author Ameli
 */
public class Bulletproof extends Player {
    private int ability;

    /**
     * assign field of bulletproof
     * @param name
     */
    public Bulletproof(String name) {
        super(name, WHITE);
        setLives(2);
        this.ability=2;
    }

    /**
     * act of bulletproof
     */
    public void act() {
        if (ability > 0) {
            GameHandler.setInquiry(true);
            ability--;
        }
    }
}
