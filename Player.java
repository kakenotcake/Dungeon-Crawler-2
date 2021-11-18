// Player.java
import java.io.PrintWriter;
import ansi_terminal.*;
import java.io.PrintWriter;

public class Player extends Character {
    private Inventory inventory;
    private String name;
    private int row;
    private int col;


    public Player(Position start) {
        // our starting details
        super(start.getRow(), start.getCol(), '@', Color.CYAN, 50);
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
    @Override
    public void setHealth(int extraHp)
    {
	    this.hp = extraHp + this.hp;
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
    public void useAid()
    {
	    Item aid = inventory.getAid();
	    System.out.print("Strength from aid is: " + aid.getStrength() + "\n\r");
	    setHealth(aid.getStrength());
	    System.out.print("Player's health increased to " + getHealth() + "\n\r");
	    inventory.removeUsedAid(aid);

    }
    @Override
    void save(PrintWriter p){
	    super.save(p);
	    p.println(name);
	    p.println(getPlayerClass());
	    p.println(getInventory());
    }
    public Player(Scanner in){
	    super(in);
	    String type=getPlayerClass();
	    Inventory storage=getInventory();
	    name=in.nextLine();
	    type=in.nextLine();
	    storage=in.nextLine();
    }

}
	
				    
