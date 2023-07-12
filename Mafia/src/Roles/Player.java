package Roles;

import Game.GameHandler;
import Game.Print;

/**
 * this class is about player
 * @version 2021,3,2
 * @author Ameli
 */

public abstract class Player {
    public static final String WHITE = "white";
    public static final String BLACK = "black";
    private final String name;
    private int lives;
    private boolean saved;
    private boolean target;
    private final String team;
    private int votes;
    private boolean voted;
    private boolean silent;

    /**
     * assign field of player
     * @param name
     * @param team
     */
    protected Player(String name, String team) {
        GameHandler.add(this);
        this.team = team;
        this.lives = 1;
        this.name = name;
        this.votes = 0;
        this.voted=false;
        this.silent=false;
        reset();
    }

    /**
     * reset player
     */
    public void reset() {
        saved = false;
        target = false;
        voted=false;
        votes = 0;
    }

    /**
     * return team
     * @return team
     */
    public String getTeam() {
        return team;
    }

    /**
     * return name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * return true or false to target
     * @return boolean
     */
    public boolean isTarget() {
        return target;
    }

    /**
     * set target
     * @param target
     */
    public void setTarget(boolean target) {
        this.target = target;
    }

    /**
     * return save
     * @return saved
     */
    public boolean isSaved() {
        return saved;
    }

    /**
     * set saved
     * @param saved
     */
    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    /**
     * get lives
     * @return lives
     */
    public int getLives() {
        return lives;
    }

    /**
     * set lives
     * @param lives
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**return alive
     * @return live
     */
    public boolean isAlive() {
        return lives != 0;
    }

    /**
     * get votes
     * @return votes
     */
    public int getVotes() {
        return votes;
    }

    /**
     * set votes
     * @param votes
     */
    public void setVotes(int votes) {
        this.votes = votes;
    }

    /**
     * return voted
     * @returnvoted
     */
    public boolean isVoted() {
        return voted;
    }

    /**
     * set voted
     * @param voted
     */
    public void setVoted(boolean voted) {
        this.voted = voted;
    }

    /**
     * return silent
     * @return silent
     */
    public boolean isSilent() {
        return silent;
    }

    /**
     * set silent
     * @param silent
     */
    public void setSilent(boolean silent) {
        this.silent = silent;
    }

    /**
     * vote for each player
     * @param player
     */
    public void vote(Player player) {
        setVoted(true);
        player.setVotes(player.getVotes() + 1);
    }

    /**
     * hir player
     */
    public void hit() {
        setLives(getLives() - 1);
    }

    /**
     * get role for player
     * @return role
     */
    public String getRoleString() {
        String role = this.getClass().toString();
        role = role.substring(role.indexOf('.') + 1);
        return role;
    }

    /**
     * get team for player
     * @return team
     */
    public String getTeamString() {
        return Print.team(team);
    }

    /**
     * to string for players name and team
     * @return
     */
    @Override
    public String toString() {
        return getRoleString() + getTeamString();
    }


}
