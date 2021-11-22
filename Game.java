// Game.java
// contains logic for running the Game

import java.util.ArrayList;
import ansi_terminal.*;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.PrintWriter;
public class Game {
    private ArrayList<Room> rooms;
    private Player player;
    private ArrayList<Box> boxes;
    private ArrayList<Enemy> enemies;
    private int currentRoom;
    private File file;

    public Game() 
    {  
	rooms = new ArrayList<Room>();
	rooms.add(new Room(30,60,"World.txt"));
	currentRoom = 0;
        player = new Player(rooms.get(currentRoom).getPlayerStart(), "");
        boxes = rooms.get(currentRoom).getBoxes();
        enemies = rooms.get(currentRoom).getEnemies();
 	rooms.add(new Room(15,36,"Room1.txt"));
	rooms.add(new Room(15,50,"Room2.txt"));
	rooms.add(new Room(15,37,"Room3.txt"));

	String name = Terminal.getLine("What is your name adventurer? ");
	player.setName(name);
	System.out.print("\n\r");
	setClass();
	player.setCommentary();
	file = new File("save.txt");
    }
    public void setClass()
    {
	System.out.print("What is your class?\n\n\r");
	System.out.print("1. Mage\n\r2. Bard\n\r3. Paladin\n\r4. Assasin\n\r5. Archer\n\n\r");
	System.out.print("Enter the number: ");
	Scanner input = new Scanner(System.in);
	int num = input.nextInt();
	if (num < 1 || num > 5)
	{
		System.out.print("\nInvalid input.\n\r");
		setClass();
	}
	else
	{
		player.setPlayerType(num);
	}
	System.out.print("\n\r");
	player.setStarterWeapon();
	player.setStarterArmor();
	Terminal.pause(1.5);
    }

    // prints a help menu to the left of the map
    private void showHelp() {
        String[] cmds = {"Commands:",
                         "---------",
                         "Move: Arrow Keys",
                         "Pickup an item: p",
                         "Drop an item: d",
                         "List items: i",
                         "Equip weapon: w",
                         "Equip armor: a",
			 "View stats: v",
			 "Use aid: u",
                         "Quit: q",
			 "Save: s",
			 "Load: l"
        };
        Terminal.setForeground(Color.GREEN);
        for (int row = 0; row < cmds.length; row++) {
            Terminal.warpCursor(row + 1, rooms.get(currentRoom).getCols());
            System.out.print(cmds[row]);
        }
        Terminal.reset();
    }

    // right under the map we keep a line for status messages
    private void setStatus(String mesg) {
        // clear anything old first
        Terminal.warpCursor(rooms.get(currentRoom).getRows(), 0);
        for (int i = 0; i < 100; i++) {
            System.out.print(" ");
        }

        // then print the message
        Terminal.warpCursor(rooms.get(currentRoom).getRows(), 0);
        System.out.print(mesg);
    }

    // code for when the player tries to pickup an item
    private void pickup() {
        Box thing = checkForBox();
        if (thing == null) {
            setStatus("There is nothing here to pick up...");
            Terminal.pause(1.25);
        } else {
            if (player.getInventory().add(thing.getItem())) {
                setStatus("You added the " + thing.getItem().getName() + " to your inventory.");
                boxes.remove(thing);
            } else {
                setStatus("This is too large for you to add!");
            }
            Terminal.pause(1.25);
        }
    }

    // code for when the player tries to drop an item
    private void drop() {
          player.getInventory().drop();;
	  Box thing = checkForBox();
          if (thing == null) {
            Item dropped = player.getInventory().getDropped();
            if (dropped != null) {
                boxes.add(new Box(player.getRow(), player.getCol(), dropped));
            }
            redrawMapAndHelp();
        } else {
            setStatus("You cannot drop something on an existing item...");
            Terminal.pause(1.25);
        }
    }

    // handle the key which was read - return false if we quit the game
    private boolean handleKey(Key key) {
        switch (key) {
            case p:
                pickup();
                break;

            case i:
                player.getInventory().print();
		Terminal.pause(2);
                redrawMapAndHelp();
                break;

            case d:
                drop();
                break;

            case w:
                player.getInventory().equipWeapon();
		player.setCommentary();
		Terminal.pause(2);
                redrawMapAndHelp();
                break;

            case a:
                player.getInventory().equipArmor();
		Terminal.pause(2);
                redrawMapAndHelp();
                break;
            
	    case v: 
		player.printStats();
		Terminal.pause(2);
		redrawMapAndHelp();		
		break;

            case u:
		player.useAid();
		Terminal.pause(2);
		redrawMapAndHelp();
		break;

	    case s:
	       //save method	
	    /*	System.out.print("Option to save.\n\r");
		File file = new File("save.txt");
		PrintWriter pw = null;

		try {
			pw = new PrintWriter(file);
			player.save(pw);
			for (int i = 0; i < enemies.size(); i++)
			{
				enemies.get(i).save(pw);
			}
			pw.close();
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} */

		saveGame();

	    	break;
	    case l:
		//loading method
		System.out.print("Option to load.\n\r");
                try{
			loadGame();


                }catch(FileNotFoundException r){
                        System.out.println("File was not found");
                }
		break;

            // handle movement
            case LEFT: player.move(0, -1, rooms.get(currentRoom));
                break;
            case RIGHT: player.move(0, 1, rooms.get(currentRoom));
                break;
            case UP: player.move(-1, 0, rooms.get(currentRoom));
                break;
            case DOWN: player.move(1, 0, rooms.get(currentRoom));
                break;

            // and finally the quit command
            case q:
                return false;
        }

        return true;
    }
    private void saveGame() 
    {
	    //file = new File("save.txt");
	    PrintWriter pw = null;

	    try {
		    pw = new PrintWriter(file);
		    player.save(pw);
		    for (int i = 0; i < enemies.size(); i++)
		    {
			    enemies.get(i).save(pw);
		    }
		    pw.close();
	    }
	    catch (FileNotFoundException e)
	    {
		    e.printStackTrace();
	    }
    }
    void loadGame() throws FileNotFoundException
    {
	    System.out.print("I am in the loadGame method\n\r");
	    Scanner in = new Scanner(file);
	    while (in.hasNextLine())
	    {
	    	if (in.nextLine().equals("Player"))
	    	{
			player.loadGame(in);

	    	}
	    }	
    }



   /* void loadGame()throws FileNotFoundException {
	    ArrayList<Enemy>enemies=new ArrayList<>();//arraylist holding enemies
	    Scanner in=new Scanner(System.in);
	    File F;//file for Player information
	    try{
		    F=new File("Player.txt");
		    in=new Scanner(F);
	   }catch(FileNotFoundException e){
            System.out.println("File was not found");
            return;
           }
	    Player e=new Player(in);
	    //file for inventory information
	    File G;
	    try{
		    G=new File("Enemy.txt");
		    in=new Scanner(G);
	    }catch(FileNotFoundException r){
		    System.out.println("File was not found");
		    return;
	    }
	    Enemy f=new Enemy(in);
	    enemies.add(f);

	    File H;//file for Inventory information
	    try{
		    H=new File("Inventory.txt");
		    in=new Scanner(H);
	    }catch(FileNotFoundException w){
		    System.out.println("File was not found");
		    return;
	    }
	    Box t=new Box(in);

	    in.close();//closing the file
    }*/

    // this is called when we need to redraw the room and help menu
    // this happens after going into a menu like for choosing items
    private void redrawMapAndHelp() {
        rooms.get(currentRoom).draw();
        showHelp();
    }

    // returns a Box if the player is on it -- otherwise null
    private Box checkForBox() {
        Position playerLocation = player.getPosition();

        for (Box box : boxes) {
            if (playerLocation.equals(box.getPosition())) {
                return box;
            }
        }

        return null;
    }



    // check for battles and return false if player has died
    private boolean checkBattles() {
        Position playerLocation = player.getPosition();

        // look for an enemy that is close
        Enemy opponent = null;
        for (Enemy enemy : enemies) {
            if (playerLocation.isAdjacent(enemy.getPosition())) {
                opponent = enemy;
            }
        }

        // now do the battle
        if (opponent != null) {
            opponent.setBattleActive();
            return player.fight(opponent, rooms.get(currentRoom), enemies);
        }

        return true;
    }

    private boolean checkRoom() {
	    Position playerLocation = player.getPosition();
	    //Position x = new Position(17, 34);
	    Entity x = new Entity(6, 9, 'x', Color.MAGENTA);

        	   if (playerLocation.isAdjacent(x.getPosition())) {
		    currentRoom = 1;
		    redrawMapAndHelp();
		    player = new Player(rooms.get(currentRoom).getPlayerStart(), "");
		    boxes = rooms.get(currentRoom).getBoxes();
		    enemies = rooms.get(currentRoom).getEnemies();
		    
		    return true;
	   } else {
		    return false;
	    }
    }

    public void run() {
        // draw these for the first time now
        redrawMapAndHelp();
	checkRoom();

        boolean playing = true;
        while (playing) {
            // draw the entities
            for (Box box : boxes) {
                box.draw();
            }
            for (Enemy enemy : enemies) {
                enemy.draw();
            }
            player.draw();

            // read a key from the user
            Terminal.warpCursor(rooms.get(currentRoom).getRows() + 1, 0);
            Key key = Terminal.getKey();
            playing = handleKey(key);

            // clear status by default
            setStatus("");

            // move the enemies
            for (Enemy enemy : enemies) {
                enemy.walk(rooms.get(currentRoom));
            }

            // check for battles
            if (checkBattles() == false) {
                setStatus("You have been killed :(\n\r");
                playing = false;
            }

            // check if we are on a box and print what's in it
            Box thingHere = checkForBox();
            if (thingHere != null) {
                setStatus("Here you find: " + thingHere.getItem().getName());
            }
        }
    }
}

