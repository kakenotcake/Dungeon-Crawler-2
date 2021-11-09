/**
  * Class to perform all the main game fucntions.  Drawing the map, printing the commands, detecting commands, moving enemies, managing items, and battling.
 */

import ansi_terminal.*;
import java.util.Scanner;
import java.util.Random;

public class DungeonCrawler {
    private static Scanner input = new Scanner(System.in);
    private static boolean done = false;
    private static Inventory inventory;
    private static Player player;
    private static Enemy enemy1, enemy2, enemy3, enemy4, enemy5;
    private static int hrow = 2;
    private static int hcol = 39;
    private static int erow1 = 14;
    private static int ecol1 = 29;
    private static int erow2 = 18;
    private static int ecol2 = 47;
    private static int erow3 = 28;
    private static int ecol3 = 42;
    private static int erow4 = 30;
    private static int ecol4 = 16;
    private static int erow5 = 32;
    private static int ecol5 = 50;
    private static int irow1 = 19;
    private static int icol1 = 23;
    private static int irow2 = 28;
    private static int icol2 = 9;
    private static int irow3 = 28;
    private static int icol3 = 70;
    private static int irow4 = 30;
    private static int icol4 = 70;
    private static int irow5 = 32;
    private static int icol5 = 70;
    private static int irow6 = 34;
    private static int icol6 = 40;
    private static Item item1 = ItemGenerator.generate();
    private static Item item2 = ItemGenerator.generate();
    private static Item item3 = ItemGenerator.generate();
    private static Item item4 = ItemGenerator.generate();
    private static Item item5 = ItemGenerator.generate();
    private static Item item6 = ItemGenerator.generate();
    private static String name = "";
    private static String[] grid = {
        "                                 #############                                  ",
        "                                 ##         ##                                  ",
        "                                 ##         ##                                  ",
        "                                 ##         ##                                  ",
        "                                 ##         ##                                  ",
        "                                 #####   #####                                  ",
        "                                    ##   ##                                     ",
        "                                    ##   ##                                     ",
        "                                    ##   ##                                     ",
        "                                    ##   ##                                     ",
        "                  ####################   ###################                    ",
        "                  ##                                      ##                    ",
        "                  ##                                      ##                    ",
        "                  ##                                      ##                    ",
        "                  ##                                      ##                    ",
        "                  ##                                      ##                    ",
        "  ##################                                      ##                    ",
        "  ##                                                      ##                    ",
        "  ##                                                      ##                    ",
        "  ##   #############                                      ##                    ",
        "  ##   ##         ##                                      ##                    ",
        "  ##   ##         ##########################   #############                    ",
        "  ##   ##                                 ##   ##                               ",
        "  ##   ##                                 ##   ##                               ",
        "  ##   ##                                 ##   ##                               ",
        "  ##   ####################               ##   ##                               ",
        "  ##                     ##        #########   ##########       ###########     ",
        "  ##                     ##        ##                  ##       ##       ##     ",
        "  ##                     ##        ##                  ##       ##       ##     ",
        "  ##                     ##        ##                  ###########       ##     ",
        "  ##                     ##        ##                                    ##     ",
        "  ##                     ##        ##                  ###########       ##     ",
        "  ##                     ##        ##                  ##       ##       ##     ",
        "  ##                     ##        ##                  ##       ##       ##     ",
        "  #########################        ##                  ##       ###########     ",
        "                                   ##                  ##                       ",
        "                                   ######################                       ",
        "                                                                                ",
        "                                                                                ",
        "                                                                                "
    };
//method to draw the map, place enemies, and items
    public static void drawMap() {
        Terminal.clear();
        for (int row = 0; row < 40; row++) {
            for (int col = 0; col < 80; col++) {	
                if (row == hrow && col == hcol) {
                    Terminal.setForeground(Color.MAGENTA);
                    System.out.print("S");
                    Terminal.reset();
                }
	        else  if (row == erow1 && col == ecol1)
		{
			Terminal.setForeground(Color.GREEN);
			System.out.print("E");
			Terminal.reset();
		}
		else if (row == erow2 && col == ecol2)
		{
			Terminal.setForeground(Color.GREEN);
			System.out.print("E");
			Terminal.reset();
		}
		else if (row == erow3 && col == ecol3)
		{
			Terminal.setForeground(Color.GREEN);
			System.out.print("E");
			Terminal.reset();
		}
		else if (row == erow4 && col == ecol4)
		{
			Terminal.setForeground(Color.GREEN);
			System.out.print("E");
			Terminal.reset();
		}
		else if (row == erow5 && col == ecol5)
		{
			Terminal.setForeground(Color.GREEN);
			System.out.print("E");
			Terminal.reset();
		}
		else if(row == irow1 && col == icol1)
		{
			Terminal.setForeground(Color.YELLOW);
			System.out.print("I");
			Terminal.reset();
		}
		else if(row == irow2 && col == icol2)
		{
			Terminal.setForeground(Color.YELLOW);
			System.out.print("I");
			Terminal.reset();
		}
		else if(row == irow3 && col == icol3)
		{
			Terminal.setForeground(Color.YELLOW);
			System.out.print("I");
			Terminal.reset();
		}
		else if (row == irow4 && col == icol4)
		{
			Terminal.setForeground(Color.YELLOW);
			System.out.print("I");
			Terminal.reset();
		}
		else if (row == irow5 && col == icol5)
		{
			Terminal.setForeground(Color.YELLOW);
			System.out.print("I");
			Terminal.reset();
		}
		else {
                    char cell = grid[row].charAt(col);
                    if (cell == '#') {
                        // a unicode block symbol
                        System.out.print('\u2588');
                    } else {
                        System.out.print(cell);
                    }
                }
            }

            System.out.print("\n\r");
        }
    }

    public static void drawInfo() {
        //method to draw the info
        Terminal.setForeground(Color.CYAN);
        Terminal.warpCursor(1, 82);
        System.out.print("Commands:");
        Terminal.warpCursor(2, 85);
        System.out.print("Move: arrow keys");
        Terminal.warpCursor(3, 85);
        System.out.print("Pickup: P"); 
        Terminal.warpCursor(4, 85);
	System.out.print("View Inventory: V");
	Terminal.warpCursor(5, 85);
	System.out.print("View Player Stats: S");
	Terminal.warpCursor(6, 85);
	System.out.print("Equip Weapon: Q");
	Terminal.warpCursor(7, 85);
	System.out.print("Equip Armor: W");
	Terminal.warpCursor(8, 85);
	System.out.print("Drop Item: D");
	Terminal.warpCursor(40, 0);
        Terminal.reset();
    }
//method for gameplay
    public static void game() {
	// add weapon and armor to inventory at the start of the game
	    inventory = new Inventory(150);
	   // Item item;
	   Item weapon;
	   Item armor;
	    do
	    {
		    weapon = ItemGenerator.generate();
	    }
	    while (weapon.getType()!= ItemType.Weapon);
	    inventory.add(weapon);
	    do 
	    {
		    armor = ItemGenerator.generate();
	    }
	    while (armor.getType() != ItemType.Armor);
            inventory.add(armor);

            //create player
	    player = new Player(100, weapon.getStrength(), name, armor.getWeight());
	    //instantiate enemies
	    enemy1 = EnemyGenerator.generate();
	    enemy2 = EnemyGenerator.generate();
	    enemy3 = EnemyGenerator.generate();
	    enemy4 = EnemyGenerator.generate();
	    enemy5 = EnemyGenerator.generate();
	    //equip weapin and armor
	    System.out.print("\n\r");
	    System.out.print("You have a piece of armor and weapon in your inventory. Let's start by equipping them: \n\r");
	    Terminal.pause(1.5);
	    inventory.equipWeapon();
	    inventory.equipArmor();
	    
	    //while player is still palying game
           while (!done) {
                 char cell = grid[hrow].charAt(hcol);
	         moveEnemy(); 
		 drawMap();
		 drawInfo();
	 	 viewItem();
            
            // this "blocks" until we get a key from the user
	    // detect key inputs from user and do appropriate actions
            Key key = Terminal.getKey();
            switch (key) { //end game
                case ESCAPE:
                    done = true;
                    break;
                case LEFT: //go left
		    char nextLeft = grid[hrow].charAt(hcol-1);
		    if (nextLeft !='#')
		    {
			    hcol--;
		    }
		    //detect enemy 1
		    if (hrow == erow1 && hcol == ecol1)
		    {
			    System.out.print("PREPARE FOR BATTLE ENEMY 1\n\r");
			    battle(enemy1);
			    Terminal.pause(1.5);
	
		    }
		    //detect enemy 2
		    if (hrow == erow2 && hcol == ecol2)
		    {
			    System.out.print("PREPARE FOR BATTLE ENEMY 2.\n\r");
			    battle(enemy2);
			    Terminal.pause(1.5);
		    }
		    //detect enemy 3
		    if (hrow == erow3 && hcol == ecol3)
		    {
			    System.out.print("PREPARE FOR BATTLE ENEMY3.\n\r");
			    battle(enemy3);
			    Terminal.pause(1.5);
		    }
		    //detect enemy 4
		    if (hrow == erow4 && hcol == ecol4)
		    {
			    System.out.print("PREPARE FOR BATTLE WITH ENEMY 4.\n\r");
			    battle(enemy4);
			    Terminal.pause(1.5);
		    }
		    //detect enemy 5
		    if (hrow == erow5 && hcol == ecol5)
		    {
			    System.out.print("PPREPARE FOR BATTLE WITH ENEMY 5\n\r");
			    battle(enemy5);
			    Terminal.pause(1.5);
		    }
		    break;

                case RIGHT: //go right
		    char nextRight = grid[hrow].charAt(hcol+1);
		    if (nextRight != '#')
		    {
			    hcol++;
		    }
		    //detect enemy 1
		    if (hrow==erow1 && hcol==ecol1)
		    {
			    System.out.print("PREPARE FOR BATTLE ENEMY 1.\n\r");
			    battle(enemy1);
			    Terminal.pause(1.5);
		    }
		    //detect enemy 2
		    if (hrow == erow2 && hcol == ecol2)
		    {
			    System.out.print("PREPARE FOR BATTLE ENEMY 2.\n\r");
			    battle(enemy2);
			    Terminal.pause(1.5);
		    }
		    //detect enemy 3
		    if (hrow == erow3 && hcol == ecol3)
		    {
			    System.out.print("PREPARE FOR BATTLE WITH ENEMY 3.\n\r");
			    battle(enemy3);
			    Terminal.pause(1.5);
		    }
		    //detect enemy 4
		    if (hrow == erow4 && hcol == ecol4)
		    {
			    System.out.print("PREPARE FOR BATTLE WITH ENEMY 4.\n\r");
			    battle(enemy4);
			    Terminal.pause(1.5);
		    }
		    //detect enemy r5
		    if (hrow == erow5 && hcol == ecol5)
		    {
			    System.out.print("PREPARE FOR BATTLE WITH ENEMY 5.\n\r");
			    battle(enemy5);
			    Terminal.pause(1.5);
		    }

		    break;
                case UP:  //go up
		    char nextUp = grid[hrow-1].charAt(hcol);
		    if (nextUp != '#')
		    {
			    hrow--;
		    }
		    //detect enemy 1
		    if (hrow==erow1 && hcol==ecol1)
		    {
			    System.out.print("PREPARE FOR BATTLE ENEMY 1.\n\r");
			    battle(enemy1);
			    Terminal.pause(1.5);
		    }
		    //detect enemy 2
		    if (hrow == erow2 && hcol == ecol2)
		    {
			    System.out.print("PREPARE FOR BATTLE ENEMY 2.\n\r");
			    battle(enemy2);
			    Terminal.pause(1.5);
		    }
		    //detect enemy 3
		    if (hrow == erow3 && hcol == ecol3)
		    {
			    System.out.print("PREPARE FOR BATTLE WITH ENEMY 3.\n\r");
			    battle(enemy3);
			    Terminal.pause(1.5);
		    }
		    //detect enemy 4
		    if (hrow == erow4 && hcol == ecol4)
		    {
			    System.out.print("PREPARE FOR BATTLE WITH ENEMY 4.\n\r");
			    battle(enemy4);
			    Terminal.pause(1.5);
		    }
		    //detect enemy 5
		    if (hrow == erow5 && hcol == ecol5)
		    {
			    System.out.print("PREPARE FOR BATTLE WITH ENEMY 5.\n\r");
			    battle(enemy5);
			    Terminal.pause(1.5);
		    }
		    break;
                case DOWN: //go down
		    char nextDown = grid[hrow+1].charAt(hcol);
		    if (nextDown != '#')
		    {
			    hrow++;
		    }
		    //detect enemy 1
		    if (hrow == erow1 && hcol == ecol1)
		    {
			    System.out.print("PREPARE FOR BATTLE ENEMY 1\n\r");
			    battle(enemy1);
			    Terminal.pause(1.5);
		    }
		    //detect enemy 2
		    if (hrow == erow2 && hcol == ecol2)
		    {
			    System.out.print("PREPARE FOR BATTLE ENEMY 2\n\r");
			    battle(enemy2);
			    Terminal.pause(1.5);
		    }
		    //detect enemy 3
		    if (hrow == erow3 && hcol == ecol3)
		    {
			    System.out.print("PREPARE FOR BATTLE WITH ENEMY 3.\n\r");
			    battle(enemy3);
			    Terminal.pause(1.5);
		    }
		    //detect enemy 4
		    if (hrow == erow4 && hcol == ecol4)
		    {
			    System.out.print("PREPARE FOR BATTLE WITH ENEMY 4.\n\r");
			    battle(enemy4);
			    Terminal.pause(1.5);
		    }
		    //detect enemy 5
		    if (hrow == erow5 && hcol == ecol5)
		    {
			    System.out.print("PREPARE FOR BATTLE WITH ENEMY 5.\n\r");
			    battle(enemy5);
			    Terminal.pause(1.5);
		    }
		    break;
                case P: //pickup item
		    System.out.print("There is nothing to pickup here.\n\r");
		    Terminal.pause(1.5);
		    break;
		case V: //view inventory
		    inventory.print();
		    Terminal.pause(1.5);
		    break;
		case S: //view player stats
		    System.out.print(player.toString() + "\n\r");
		    System.out.print("Equipped Weapon: " + inventory.getEquippedWeapon() + "\n\r");
		    System.out.print("Equipped Armor: " + inventory.getEquippedArmor() + "\n\r");
		    Terminal.pause(1.5);
		    break;
	 	case Q: //equip weapon
		    inventory.equipWeapon();
		    weapon = inventory.getEquippedWeapon();
		    player.setStrength(weapon.getStrength());
		    Terminal.pause(1.5);
		    break; 
		case W: //equip armor
		    inventory.equipArmor();
		    armor = inventory.getEquippedArmor();
		    player.setDefense(armor.getWeight());
		    Terminal.pause(1.5);
		    break;
		case D: //drop item
		    inventory.drop();
		    Terminal.pause(1.5);
		    break;
	    }

        }
    }
    //method to randomy move the enemies
    public static void moveEnemy()
    {
	   Random rand = new Random();
	   int num = rand.nextInt(4);
	   //go left
	   if (num == 0)
	   {
		  if (erow1!=0&&ecol1!=0)
		   {
			char nextLeft1 = grid[erow1].charAt(ecol1-1);
			if (nextLeft1 != '#')
		   	{	
				ecol1--;
		   	}
		   }
	          if (erow2 !=0 && ecol2 != 0)
		  {   	
		   	char nextLeft2 = grid[erow2].charAt(ecol2-1);
			 if (nextLeft2 != '#')
		   	{
			   	ecol2--;
		   	
			}
		  }
		  if (erow3 != 0 && ecol3 != 0)
		  {
		   
		   	char nextLeft3 = grid[erow3].charAt(ecol3-1);
			if (nextLeft3 != '#')
		   	{
			   	ecol3--;
		   	}
		  }
		  if (erow4 != 0 && ecol4 != 0)
		  {	
		   	char nextLeft4 = grid[erow4].charAt(ecol4-1);
			 if (nextLeft4 != '#')
		   	{
			   	ecol4--;
		   	}
		  }
		  if (erow5 != 0 && ecol5 != 0)
		  {
		   	char nextLeft5 = grid[erow5].charAt(ecol5-1);
		  	if (nextLeft5 != '#')
		   	{
			   	ecol5--;
			}
		   }
	   }
	   // go right
	   else if (num == 1)
	   {
		   if (erow1 !=0 && ecol1 != 0)
		   {
		   	char nextRight1 = grid[erow1].charAt(ecol1+1);
			 if (nextRight1 != '#')
	           	{
				ecol1++;
		   	}
		   }
		   if (erow2 !=0 && ecol2 != 0)
		   {
		   	char nextRight2 = grid[erow2].charAt(ecol2+1);
			 if (nextRight2 != '#')
		   	{		
			   	ecol2++;
		   	}
		   }
		   if (erow3 !=0 && ecol3 !=0)
		   {
		   	char nextRight3 = grid[erow3].charAt(ecol3+1);
			 if (nextRight3 != '#')
		   	 {
			 	ecol3++;
		   	 }
		   }
		   if (erow4 !=0 && ecol4 != 0)
		   {
			   
		   	char nextRight4 = grid[erow4].charAt(ecol4+1);
			 if (nextRight4 != '#')
		   	 {
			 	ecol4++;
		   	 }
		   }
		   if (erow5 !=0 && ecol5 !=0)
		   {
		   	char nextRight5 = grid[erow5].charAt(ecol5+1);

		 	if (nextRight5 != '#')
		   	{
				ecol5++;
		   	}
		   }	
	   }
	   // go up
	   else if(num==2)
	   {
		   if (erow1 != 0 && ecol1 != 0)
		   {
		   	char nextUp1 = grid[erow1-1].charAt(ecol1);
		   	if (nextUp1 != '#')
		   	{
				erow1--;
		   	}
		   }
		   if (erow2 != 0 && ecol2 != 0)
		   {
		   	char nextUp2 = grid[erow2-1].charAt(ecol2);
		   	if (nextUp2 != '#')
		   	{
				erow2--;
		   	}
		   }
		   if (erow3 != 0 && ecol3 != 0)
		   {
		   	char nextUp3 = grid[erow3-1].charAt(ecol3);
		   	if (nextUp3 != '#')
		   	{
				erow3--;
		   	}
		   }
		   if (erow4 != 0 && ecol4 != 0)
		   {
		   	char nextUp4 = grid[erow4-1].charAt(ecol4);
		   	if (nextUp4 != '#')
		   	{
				erow4--;
		   	}
		   }
		   if (erow5 != 0 && ecol5 != 0)
		   {
		   	char nextUp5 = grid[erow5-1].charAt(ecol5);
		   	if (nextUp5 != '#')
		   	{
				erow5--;
		   	}
		   }

	   }
	   //go down
	   else
	   {
		   if (erow1 != 0 && ecol1 != 0)
		   {
		   	char nextDown1 = grid[erow1+1].charAt(ecol1);
		   	if (nextDown1 != '#')
		   	{
				erow1++;
		   	}
		   }
		   if (erow2 != 0 && ecol2 != 0)
		   {
		   	char nextDown2 = grid[erow2+1].charAt(ecol2);
		   	if(nextDown2 != '#')
		   	{
				erow2++;
		   	}
		   }
		   if (erow3 != 0 && ecol3 != 0)
		   {
		   	char nextDown3 = grid[erow3+1].charAt(ecol3);
		   	if (nextDown3 != '#')
		   	{
				erow3++;
		   	}
		   }
		   if (erow4 != 0 && ecol4 != 0)
		   {
		   	char nextDown4 = grid[erow4+1].charAt(ecol4);
		   	if (nextDown4 != '#')
		   	{
				erow4++;
		   	}
		   }
		   if (erow5 != 0 && ecol5 != 0)
		   {
		   	char nextDown5 = grid[erow5+1].charAt(ecol5);
		   	if (nextDown5 != '#')
		   	{
				erow5++;
		   	}
		   }
	   }
    }
    //method to view item when it is landed on
    public static void viewItem()
    {
	    if (hrow == irow1 && hcol == icol1)
	    {
		    System.out.print("You have landed on: \n\r");
		    System.out.print(item1.toString() + "\n\r");
		    pickupItem(item1);
		    Terminal.pause(1.5);
	    }
	    if (hrow == irow2 && hcol == icol2)
	    {
		    System.out.print("You have landed on: \n\r");
		    System.out.print(item2.toString() + "\n\r");
		    pickupItem(item2);
		    Terminal.pause(1.5);
	    }
	    if (hrow == irow3 && hcol == icol3)
	    {
		    System.out.print("You have landed on: \n\r");
		    System.out.print(item3.toString() + "\n\r");
		    pickupItem(item3);
		    Terminal.pause(1.5);
	    }
	    if (hrow == irow4 && hcol == icol4)
	    {
		    System.out.print("You have landed on: \n\r");
		    System.out.print(item4.toString() + "\n\r");
		    pickupItem(item4);
		    Terminal.pause(1.5);
	    }
	    if (hrow == irow5 && hcol == icol5)
	    {
		    System.out.print("You have landed on: \n\r");
		    System.out.print(item5.toString() + "\n\r");
		    pickupItem(item5);
		    Terminal.pause(1.5);
	    }
    }
    //method to pickup the item
    public static void pickupItem(Item item)
    {
	    System.out.print("If you would like to pickup this item, enter P. If not, keep moving.\n\r");
	    Key key = Terminal.getKey();
	    if (key==Key.P)
	    {
		    inventory.add(item);
	    }

    }
    //method to battle with the enemies
    public static void battle(Enemy enemy)
    {

	    int round = 1;
	    Random rand = new Random();
	    int num = rand.nextInt(1); //see who goes first
	    System.out.print("Round " + round + "!\n\r");
	    if (num==0) //player goes first
	    {
		    boolean stop = false;
		    System.out.print("Player goes first.\n\r");
		    while (stop != true)
		    {
			round++;
			System.out.print("Round " + round + "!\n\r");
		    	player.attack(inventory.getEquippedWeapon(), enemy);
		    	Terminal.pause(1.5);
		    	if (enemy.getHealth() <= 0)
		    	{
			    stop = true;
			    System.out.print("THE ENEMY HAS BEEN DEFEATED!\n\r");
			    break;
		    	}
			enemy.attack(player, inventory.getEquippedArmor());
			Terminal.pause(1.5);
			if (player.getHealth() <= 0)
			{
				stop = true;
				System.out.print("You have died... GAME OVER\n\r");
				done = true;
				break;
			}
		    }

	    }
	    else//enemy goes first
	    {
		    boolean stop = false;
		    System.out.print("Enemy goes first.\n\r");
		    while (stop != true)
		    {
			round++;
			System.out.print("Round " + round + "!\n\r");
		    	enemy.attack(player, inventory.getEquippedArmor());
		    	Terminal.pause(1.5);
		    	if (player.getHealth() <= 0)
		    	{	
			    stop = true;
			    System.out.print("You have died...GAME OVER\n\r");
			    done = true;
			    break;
		    	}
			player.attack(inventory.getEquippedWeapon(), enemy);
			Terminal.pause(1.5);
			if (enemy.getHealth() <= 0)
			{
				stop = true;
				System.out.print("THE ENEMY HAS BEEN DEFEATED!\n\r");
				break;
			}
		    }
	    }
	    //ststements to remove the enemies from the map
	    if (enemy==enemy1)
	    {
		    erow1 = 0;
		    ecol1 = 0;
	    }
	    else if (enemy==enemy2)
	    {
		    erow2 = 0;
		    ecol2 = 0;
	    }
	    else if (enemy==enemy3)
	    {
		    erow3 = 0;
		    ecol3 = 0;
	    }
	    else if (enemy==enemy4)
	    {
		    erow4 = 0;
		    ecol4 = 0;
	    }
	    else
	    {
		    erow5 = 0;
		    ecol5 = 0;
	    }
	    //if all enemies have been defeated
	    if (erow1 == 0 && ecol1 == 0 && erow2 == 0 && ecol2 == 0 && erow3 == 0 && ecol3 == 0 && erow4 == 0 & ecol4 == 0 && erow5 == 0 & ecol5 == 0)
	    {
		    System.out.print("YOU WON! CONGRATULATIONS!!!!\n\r");
		    done = true;
	    }


    }
    public static void main(String args[]) {
        // we must call rawMode when we start the program, or else nothing else will really work!
        Terminal.rawMode();
        System.out.print("\nIn times long gone, humanity existed during an era of chaos and hostility.\n\r");
	System.out.print("Vile creatures spawned daily from volatile pools of ebony miasma, which burned, pillaged, and ravaged the land.\n\r");
	System.out.print("The horrid monsters were enticed by the high concentration of negative emotions humans projected outward.\n\r");
	System.out.print("Spearheading their army was the Demon Emperor Laplace: the demon of the apocalypse, the master of the dark arcane arts,the master of the Hundred Hand        Fist, and leader of Hexagram, a group of elite eldritch horrors that held positions as generals.\n\r");
        System.out.print("Together he and his army drove humanity to the brink of extinction and whatever modest amount of humans remaining were primed to serve as livestock          and slaves.\n\r");
	Terminal.pause(4);
	System.out.print("Although the situation seemed bleak and without a light in sight, a hero emerged amongst the chaos.\n\r");
	System.out.print("His name was Rudra, and with his trusted sword,Clarent Rudra single handily beat back the Demon Empereor\'s forces and rallied the remains of                humanity togather.\n\r");
	System.out.print("During the final confrontation, Rudra managed to strike down Laplace and seal away his demon generals but not without being struck down by Laplace\'s        last act of hateful defiance.\n\r");
	Terminal.pause(4);
	System.out.print("In the modern-day, the world is controlled under the banner of the Kingdom of Arcadium.\n\r");
	System.out.print("The memebers of Hexagram have been reawakened and begun causing carnage across the land marching with the reborn demon army seeking blood sarfices to        revive their fallen lord.\n\r");
	System.out.print("Descedent of Rudra rise up and like your ancestor show them humantity\'s infinite capacity for evolution!\n\n\r");
	Terminal.pause(4);

        name = Terminal.getLine("What is your name adventurer? ");

        game();

        // we must call cookedMode before quitting or the terminal will be weirded out after program ends
        Terminal.cookedMode();
    }
}

