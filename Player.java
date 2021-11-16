// Player.java

import ansi_terminal.*;

public class Player extends Character {
import java.io.PrintWriter;
    private Inventory inventory;
    private String name;
    private row;
    private col;


    public Player(Position start, PlayerClass classType) {
        // our starting details
        super(start.getRow(), start.getCol(), '@', Color.CYAN, 50);

	this.classType = classType;

        // we can carry 100 pounds of items
        inventory = new Inventory(200);
       	
    }
    void save(PrintWriter o){
	    o.println(name);
	    o.println(row);
	    o.println(col);

    
    
    }

    public PlayerClass getPlayerClass()
    {
	    return this.classType;
    }


    public void setPlayerClass(PlayerClass x)
    {
	    this.classType = x;
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
        return "Player";
    }
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

    public Inventory getInventory() {
        return inventory;
    }
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
    public PlayerClass getPlayerClass()
    {
	    return playerClass;
    }
    public void setStarterWeapon()
    {
	    Item weapon = null;
	    boolean stop = false;
	    while (stop==false) 
	    {
		    weapon = ItemGenerator.generate();
		    if ((weapon.getType().equals(ItemType.Weapon)) && (weapon.getPlayerClass().equals(playerClass)))
		    {
			    stop = true;
		    }
	    }
	    System.out.print("Your starting weapon is: " + weapon + "\n\r");
	    inventory.add(weapon);
	    inventory.equipStarterWeapon(weapon);
    }
    public void setStarterArmor()
    {
	    Item armor = null;
	    boolean stop = false;
	    while (stop==false)
	    {
		    armor = ItemGenerator.generate();
		    if ((armor.getType().equals(ItemType.Armor)) && (armor.getPlayerClass().equals(playerClass)))
		    {
			    stop = true;
		    }
	    }
	    System.out.print("Your starting armor is: " + armor + "\n\r");
	    inventory.add(armor);
	    inventory.equipStarterArmor(armor);
    }
    public void printStats()
    {
	    System.out.print("Player's health: " + getHealth() + "\n\r");
	    System.out.print("Player's damage: " + getDamage() + "\n\r");
	    System.out.print("Player's protection: " + getProtection() + "\n\r");
	    System.out.print("Player's class: " + getPlayerClass() + "\n\r");
	    System.out.print("Equipped weapon: " + inventory.getEquippedWeapon() + "\n\r");
	    System.out.print("Equipped armor: " + inventory.getEquippedArmor() + "\n\r");
    }
}
	
				    
