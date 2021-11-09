//PLayer class which gets/sets the health, strenght, name and defense of the player.  Also attacks the enemy and prints the player stats.

public class Player {
	private int health;
        private	int strength;
	private String name;
	private int defense;
	
	
//constructor taking in health, strenght, name, and defense stats
	public Player(int health, int strength, String name, int defense)
	{
		this.health = health;
		this.strength = strength;
		this.name = name;
		this.defense = defense;
	}
//get health
	public int getHealth()
	{
		return this.health;

	}
//set health
	public void setHealth(int h)
	{
		this.health = h;
	}
//get strenght
	public int getStrength()
	{
		return this.strength;
	}
	//set strenght
	public void setStrength(int strength)
	{
		this.strength = strength;
	}
//get name
	public String getName()
	{
		return this.name;
	}//get defense
	public int getDefense()
	{
		return this.defense;
	}//set defense
	public void setDefense(int defense)
	{
		this.defense = defense;
	}
//attack the enemy

	public void attack(Item weapon, Enemy enemy)
	{
		int playerDamage = weapon.getStrength();
		System.out.print("Player attacked enemy with " + playerDamage + " points of damage!\n\r");
		System.out.print("Enemy health was: " + enemy.getHealth() + "\n\r");
		enemy.setHealth(enemy.getHealth()-playerDamage);
		System.out.print("Enemy health now: " + enemy.getHealth() + "\n\r");
	//	int enemyAfterAttack = (enemy.getHealth() - playerDamage);


	}//print stats
	public String toString()
	{
		System.out.print("Health | Strength | Name | Defense\n\r");
		return health + " " +  strength + " " + name + " " + defense;
	}
}

