// Character.java

import java.util.ArrayList;
import java.io.PrintWriter;
import ansi_terminal.*;
import java.util.Scanner;

public abstract class Character extends Entity {
    // the characters health points
    protected int hp;
    protected String commentary;
    private int enemiesDead;

    public Character(int row, int col, char display, Color color, int hp, String commentary) {
        super(row, col, display, color);
        this.hp = hp;
	this.commentary = commentary;
	enemiesDead = 0;
    }
/**This is method gets the current hp of a character
 *@return the integer value of the hp of a character
 *
 *
 *
 */
    // get the hp, damage, protection and name of character
    public int getHealth() {
        return hp;
    }
  /**This method gets a commentary when a character does battle
   *@return String value of whatever commentary you wrote in for battle
   *
   *
   */
    public String getCommentary()
    {
	    return commentary;
    }
    /**This a method that reports the enemies that died  so that later in conjuction with another method
     *the enenies that are dead can be removed
     *@return the number of enemies that have been killed by the player
     *
     */
    public int getEnemiesDead()
    {
	    return enemiesDead;
    }
    /**This is a method that sets the number of enemies that are dead to be zero
     *as a baseline/start
     *@param newEnemiesDead the  enemies who are dead at the beinging of the game which is always starts as zero
     *
     */
    public void setEnemiesDead(int newEnemiesDead)
    {
	    enemiesDead = newEnemiesDead;
    }
    public abstract void setHealth(int hp);
    public abstract int getDamage();
    public abstract int getProtection();
    public abstract String getName();
/**This method sets the hp of a character dependent on the interger values you passed in 
 *@param hp the health points of the character. 
 *@param t a place holder variable that is passed 0.
 *
 */
    public void setHealth(int hp, int t)
    {
	    this.hp = hp;
    }

    // do damage to another player, returns if they died
    private boolean dealDamage(Character other, Room room) {
        // this character does damage to the other character
        int damageDone = getDamage() - other.getProtection();

        // prevent negative damage
        if (damageDone < 0) {
            damageDone = 0;
        }

        // actually damage them
        other.hp -= damageDone;

        // prevent negative hp
        if (other.hp < 0) {
            other.hp = 0;
        }

        // print the info on this
        Terminal.warpCursor(room.getRows(), 0);
        if (other.hp > 0) {
	    System.out.print(getCommentary() + "\n\r");
	    //System.out.print(" \n\r");
            System.out.print(getName() + " does " + damageDone + " damage to " + other.getName()
                + ", leaving " + other.hp + " health.\n\r");
            return false;
        } else {
	    System.out.print(getCommentary() + "\n\r");
            System.out.print(getName() + " does " + damageDone + " damage to " + other.getName()
                + ", killing them.\n\r");
	    //System.out.print(" \n\r");
            return true;
        }
    }
    //save method goes here
    /**
     *This method stores information revelant to this class which in this case is character health
     *@param S  parameter is the printwriter that the information is stored into
     *
     */
    /**
    *Method to print character's health to text file.
    *@param s PrintWrite object.
    */
    @Override
    void save(PrintWriter s){
	    super.save(s);
	    s.println(getHealth());
    }
    /**
    *Method to pass scanner to super class and save health stat.
    *@param in Scanner object.
    */
    public void loadGame(Scanner in){
	   // System.out.print("I am in the character constructor\n\r");
	    //super(in);
	    super.loadGame(in);
	    setHealth(in.nextInt(), 0);
	    in.nextLine();
	    System.out.print("In the character constructor\n\r");
	    //setHealth(4, 0);
	    System.out.print(getHealth() + "\n\r");
	   // setHealth(4, 0);
    }
    

    // this method performs one round of battle between two characters
    // return false if the player has died aas a result
    public boolean fight(Character other, Room room, ArrayList<Enemy> enemies) {
        // do damage to them first
        boolean killed = dealDamage(other, room);
        if (killed) {
            enemies.remove(other);
	    enemiesDead++;
	    System.out.print("Enemies killed so far: " + enemiesDead + "\n\r");
        }
        //System.out.printf("Press any key to return...\n\r");
        Terminal.getKey();

        // don't allow dead enemies to fight back
        if (killed) {
            return true;
        }

        // now take damage from them
        if (other.dealDamage(this, room))
	{
            return false;
        }
        //System.out.printf("Press any key to return...\n\r");
        Terminal.getKey();
        return true;
    }
}

