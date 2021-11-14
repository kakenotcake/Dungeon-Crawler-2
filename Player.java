// Player.java

import ansi_terminal.*;

public class Player extends Character {
    private Inventory inventory;
    private PlayerType playerType;
    private String name;

    public Player(Position start) {
        // our starting details
        super(start.getRow(), start.getCol(), '@', Color.CYAN, 50);

        // we can carry 100 pounds of items
        inventory = new Inventory(100);
       	
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
		    this.playerType = PlayerType.Mage;
	    }
	    else if (num==2)
	    {
		    this.playerType = PlayerType.Warrior;
	    }
	    else if(num==3)
	    {
		    this.playerType = PlayerType.Archer;
	    }
	    else
	    {
		    this.playerType = PlayerType.Assasin;
	    }
    }
    public PlayerType getPlayerType()
    {
	    return playerType;
    }
    public void setStarterWeapon()
    {
	    Item weapon = null;
	    boolean stop = false;
	    while (stop==false) 
	    {
		    weapon = ItemGenerator.generate();
		    if ((weapon.getType().equals(ItemType.Weapon)) && (weapon.getPlayerType().equals(playerType)))
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
		    if ((armor.getType().equals(ItemType.Armor)) && (armor.getPlayerType().equals(playerType)))
		    {
			    stop = true;
		    }
	    }
	    System.out.print("Your starting armor is: " + armor + "\n\r");
	    inventory.add(armor);
	    inventory.equipStarterArmor(armor);
    }
}
	
				    
