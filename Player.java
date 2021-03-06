// Player.java
//import java.io.PrintWriter;
import ansi_terminal.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Player extends Character {
    private Inventory inventory;
    private String name;
    private int row;
    private int col;
    private PlayerClass playerClass;


    public Player(Position start, String commentary) {
        // our starting details
        super(start.getRow(), start.getCol(), '@', Color.CYAN, 50, commentary);
        // we can carry 100 pounds of items
        inventory = new Inventory(200);
       	
    }


    @Override
    public int getDamage() {
        Item weapon = inventory.getEquippedWeapon();
        if (weapon != null) {
            return weapon.getStrength();
        } else {
            // if we have no weapon, our fists are pretty weak...
            return 1;
        }
    }

    @Override
    public String getName() {
        return name;
    }

   /**This is a method that sets the name of the Player to the String value they passed in
    *@param name the string value of name
    *
    */
    public void setName(String name)
    {
	    this.name = name;
    }

    @Override
    public int getProtection() {
        Item armor = inventory.getEquippedArmor();
        if (armor != null) {
            return armor.getStrength();
        } else {
            // without armor, we have no protection
            return 0;
        }
    }
    @Override
    /**This method sets the hp of the player by taking in the existing hp and adding extra hp when aid is used
     *@param extraHp the additional hp from using aid
     *
     *
     */
    public void setHealth(int extraHp)
    {
	    this.hp = extraHp + this.hp;
    }
    /**This method sets the commentary that occurs when the player attacks with a equipped weapon
     *
     */
    public void setCommentary()
    {
	    this.commentary = inventory.getEquippedWeapon().getCommentary();
    }
    public Inventory getInventory() {
        return inventory;
    }
    /**This method sets the class of the Player based the numerical value that the player picks
     *@param num interger value of number inputted
     *
     */
    public void setPlayerType(int num)
    {
	    if (num==1)
	    {
		    this.playerClass = PlayerClass.Mage;
	    }
	    else if(num == 2)
	    {
		    this.playerClass = PlayerClass.Bard;
	    }
	    else if(num == 3)
	    {
		    this.playerClass = PlayerClass.Paladin;
	    }
	    else if(num==4)
	    {
		    this.playerClass = PlayerClass.Assassin;
	    }
	    else
	    {
		    this.playerClass = PlayerClass.Archer;
	    }

    }
    /**This method retreives information on what class the player chose to be used later
     *@return the class that the player chose at the begining of the game  
     */
    public PlayerClass getPlayerClass()
    {
	    return playerClass;
    }
    /**This method sets the starter weapon based on the class that the player chose
     *
     */
    public void setStarterWeapon()
    {
	    Item weapon = null;
	    boolean stop = false;
	    while (stop==false) 
	    {
		    weapon = ItemGenerator.generatePlayerSpecificItem();
		    if ((weapon.getType().equals(ItemType.Weapon)) && (weapon.getPlayerClass().equals(playerClass)))
		    {
			    stop = true;
		    }
	    }
	    System.out.print("Your starting weapon is: " + weapon.getName() + "\n\r");
	    inventory.add(weapon);
	    inventory.equipStarterWeapon(weapon);
    }


    /**This method sets the starter aromor based on the class that the player chose
     *
     */
    public void setStarterArmor()
    {
	    Item armor = null;
	    boolean stop = false;
	    while (stop==false)
	    {
		    armor = ItemGenerator.generatePlayerSpecificItem();
		    if ((armor.getType().equals(ItemType.Armor)) && (armor.getPlayerClass().equals(playerClass)))
		    {
			    stop = true;
		    }
	    }
	    System.out.print("Your starting armor is: " + armor.getName() + "\n\r");
	    inventory.add(armor);
	    inventory.equipStarterArmor(armor);
    }
    /**This method prints out the health of the player, the damage they delt, their protection value, their class
     *their equiped weapon and armor
     *
     */
    public void printStats()
    {
	    System.out.print(name+"\n\r");
	    System.out.print("Player's health: " + getHealth() + "\n\r");
	    System.out.print("Player's damage: " + getDamage() + "\n\r");
	    System.out.print("Player's protection: " + getProtection() + "\n\r");
	    System.out.print("Player's class: " + getPlayerClass() + "\n\r");
	    System.out.print("Equipped weapon: " + inventory.getEquippedWeapon().toString() + "\n\r");
	    System.out.print("Equipped armor: " + inventory.getEquippedArmor().toString() + "\n\r");
    }
    /**
     *This method allows the player  to use items that restore health like health potions
     *The player can only use it if they have one in the inventory
     */
    public void useAid()
    {
	    Item aid = inventory.getAid();
	    if (aid != null)
	    {
		    System.out.print("Strength from aid is: " + aid.getStrength() + "\n\r");
	    	    setHealth(aid.getStrength());
		    if (getHealth() > 50) {
			    System.out.print("Extra health obtained!\n\r");
		    }
	    	    System.out.print("Player's health increased to " + getHealth() + "\n\r");
	            inventory.removeUsedAid(aid);
	    }

    }
    /**
     *This method stores information revelant to the class which in this case is
     *the name the player decided on at the start of the game and the
     *class the player chose at the start of the game
     *@param p  parameter is the printwriter where all the revelant information is stored
     */
    @Override
    void save(PrintWriter p){
	    p.println("Player");
	    super.save(p);
	    p.println(name);
	    p.println(getPlayerClass());
	    //p.println(getProtection());
	   // p.println(getInventory());
    }
    /**
    *Method that reads data from text file, passes scanner to super class,  and saves data to variables.
    *@param in Scanner object.
    */
    public void loadGame(Scanner in){
	   // System.out.print("I am in the player constructor\n\r");
	  //  Character tempNewChar  = super(in);
	  //  super(in);
	    super.loadGame(in);
	    String name = in.nextLine();
	    setName(name);
	    String pc = in.nextLine();
	    if (pc.equals("Mage"))
	    {
		    setPlayerType(1);
	    }
	    else if(pc.equals("Bard"))
	    {
		    setPlayerType(2);
	    }
	    else if(pc.equals("Paladin"))
	    {
		    setPlayerType(3);
	    }
	    else if(pc.equals("Archer"))
	    {
		    setPlayerType(4);
	    }
	    else
	    {
		    System.out.print("Error\n\r"); 
	    }

	    System.out.print("In the playaer constructor\n\r");
	    System.out.print(name + "\n\r" + pc + "\n\r");
    }
}
	    

	
				    
