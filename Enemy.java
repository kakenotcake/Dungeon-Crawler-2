//Enemy class which gets and sets health, attacks the player, and prints stats

import java.util.Random;

public class Enemy {
    private EnemyType type;
    private int health=100;
    private static Random rng=new Random();
    private Item armor;
    private int weight;
//constructor taking in enemy type and health
    public Enemy(EnemyType type,int health){
        this.type=type;
        this.health=health;
    }
//getter for health
    public int getHealth() {
        return health;
    }
//setter for health
    public void setHealth(int health) {
        this.health = health;
    }
//method to attack player
    public void attack(Player player,Item armor){
        //need to test
        int damage= rng.nextInt(15);
	int defense = 10*armor.getWeight() / 100;
	int actualDamage = damage - defense;
	System.out.print("Enemy attacked player with " + actualDamage + " points of damage!\n\r");
	System.out.print("Player health was: " + player.getHealth() + "\n\r");
	player.setHealth(player.getHealth()-actualDamage);
	System.out.print("Player health is now: " + player.getHealth() + "\n\r");
    }
    //print enemy stats
    public void print()
    {
	    System.out.print("Type: " + type + " Health: " + health + "\n\r");
    }
}

