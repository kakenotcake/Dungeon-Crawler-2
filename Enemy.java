// Enemy.java

import java.util.Random;
import ansi_terminal.*;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Enemy extends Character {
    private String name;
    private int damage;
    private int protection;
    private static Random rng;
    private boolean battleActive;
    private int health;
    private int row;
    private int col;
    //private ArrayList<Enemy> enemies;

    public Enemy(String name, int row, int col, int hp, int damage, int protection, String commentary) {
        super(row, col, '*', Color.RED, hp, commentary);
        this.name = name;
        this.damage = damage;
        this.protection = protection;
        this.battleActive = false;
        rng = new Random();  
    }
    // method that saves information
    


    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getProtection() {
        return protection;
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public void setHealth(int extraHp)
    {
    }
    public void setBattleActive() {
        battleActive = true;
    }
    @Override
    void save(PrintWriter q){
	    q.println("Enemy");
	    super.save(q);
	    q.println(name);
	    q.println(protection);   
	    q.println(damage); 
	    q.println(commentary);
    }
    /*public void loadGame(Scanner in){
	    int enemyCount = in.nextInt();
	    in.nextLine();
	    in.nextLine();
	    for (int i = 0; i < enemyCount; i++)
	    {
		    int row = in.nextInt();
		    int col = in.nextInt();
		    in.nextLine();
		    in.nextLine();
		    int hp = in.nextInt();
		    in.nextLine();
		    String name = in.nextLine();
		    int protection = in.nextInt();
		    in.nextLine();
		    String commentary = in.nextLine();
		    in.nextLine();
		    enemies.add(new Enemy(name, row, col, hp, damage, protection, commentary));
	    }
	   /*super.loadGame(in);
	   in.nextLine();
	   name=in.nextLine();
	   protection=in.nextInt();
    
    }
    public ArrayList<Enemy> getEnemies()
    {
	    return enemies;
    }*/

    // randomly move this enemy in the room
    public void walk(Room room) {
        // if a battle is active with this enemy, they DONT walk right after
        if (battleActive) {
            battleActive = false;
            return;
        }

        // loop forever until we move correctly
        while (true) {
            int choice = rng.nextInt(4);
            switch (choice) {
                case 0:
                    if (move(0, 1, room)) return;
                    break;
                case 1:
                    if (move(0, -1, room)) return;
                    break;
                case 2:
                    if (move(1, 0, room)) return;
                    break;
                case 3:
                    if (move(-1, 0, room)) return;
                    break;
            }
        }
    }
}


