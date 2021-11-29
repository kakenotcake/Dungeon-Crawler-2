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
    private File room1;
    private Enemy enemy;

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
	room1 =  new File("saveroom1.txt");
    }
    public void setClass()
    {
	System.out.print("What is your class??\n\n\r");
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
          player.getInventory().drop();
	  Terminal.pause(1.25);
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
		saveGame();
		System.out.print("Game saved sucessfully.\n\r");
	    	break;
	    case l:
		//loading method
		System.out.print("Loading . . .\n\r");

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
	    PrintWriter pw = null;
	    try {
		    pw = new PrintWriter(file);
		    pw.println("room");
		    pw.println(currentRoom);
		    player.save(pw);
		    pw.println(enemies.size());
		    for (int i = 0; i < enemies.size(); i++)
		    {
			    enemies.get(i).save(pw);
		    }
		    pw.println();
		    pw.println(player.getInventory().getSize());
		    pw.println(player.getInventory().toString());
		    pw.println(boxes.size());
		    for (int i = 0; i < boxes.size(); i++)
		    {
			    boxes.get(i).save(pw);

		    }
		    pw.println("stop");
		    pw.close();
		    //System.out.print("Game saved successfully.\n\r");
		    Terminal.pause(1.5);
	    }
	    catch (FileNotFoundException e)
	    {
		    e.printStackTrace();
	    }
    }
    void loadGame() throws FileNotFoundException
    {
	    //System.out.print("Loading your previous save...\n\r");
	    Terminal.pause(1.5);
	    ArrayList<Enemy> tempEnemies = new ArrayList<Enemy>();
	    ArrayList<Item> tempInventory = new ArrayList<Item>();
	    ItemType itemType;
	    PlayerClass playerClass;
	    ArrayList<Box> tempBoxes = new ArrayList<Box>();
	    Scanner in = new Scanner(file);
	    while (in.hasNextLine())
	    {
		if (in.nextLine().equals("room"));
		{
			currentRoom = in.nextInt();
			in.nextLine();
		}
	    	if (in.nextLine().equals("Player"))
	    	{
			player.loadGame(in);

	    	}
		int enemyCount = in.nextInt();
		in.nextLine();
		if (in.nextLine().equals("Enemy"))
		{
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
				int damage = in.nextInt();
				in.nextLine();
				String commentary = in.nextLine();
				in.nextLine();
				tempEnemies.add(new Enemy(name, row, col, hp, damage, protection, commentary));
			}
		}
		int numItems = in.nextInt();
		in.nextLine();
		for (int i = 0; i < numItems; i++)
		{
			System.out.print("In the items for loop\n\r");
			String type = in.nextLine();
			System.out.print("item type is: " + type + "\n\r");
			if (type.equals("Weapon"))
			{
				itemType = ItemType.Weapon;
			}
			else if (type.equals("Armor"))
			{
				itemType = ItemType.Armor;
			}
			else
			{
				itemType = ItemType.Other;
			}
			System.out.print("item type is: " + itemType + "\n\r");
			String name = in.nextLine();
			int weight = in.nextInt();
			int value = in.nextInt();
			int strength = in.nextInt();
			in.nextLine();
			String pclass = in.nextLine();
			if (pclass.equals("Mage"))
			{
				playerClass = PlayerClass.Mage;
			}
			else if(pclass.equals("Bard"))
			{
				playerClass = PlayerClass.Bard;
			}
			else if(pclass.equals("Paladin"))
			{
				playerClass = PlayerClass.Paladin;
			}
			else if(pclass.equals("Assassin"))
			{
				playerClass = PlayerClass.Assassin;
			}
			else if (pclass.equals("Archer"))
			{
				playerClass = PlayerClass.Archer;
			}
			else
			{
				playerClass = PlayerClass.None;
			}
			System.out.print("player class is: " + pclass + "\n\r");
			tempInventory.add(new Item(itemType, name, weight, value, strength, playerClass));
			System.out.print("made it to the bottom of the loop\n\r");
		}

		in.nextLine();
		int itemCount = in.nextInt();
		for (int i = 0; i < itemCount; i++)
		{
			System.out.print("In the items for loop\n\r");
			int row = in.nextInt();
			int col = in.nextInt();
			in.nextLine();
			in.nextLine();
			in.nextLine();
			String type = in.nextLine();
			if (type.equals("Weapon"))
			{
				itemType = ItemType.Weapon;
			}
			else if (type.equals("Armor"))
			{
				itemType = ItemType.Armor;
			}
			else
			{
				itemType = ItemType.Other;
			}
			String name = in.nextLine();
			int weight = in.nextInt();
			int value = in.nextInt();
			int strength = in.nextInt();
			in.nextLine();
			String pclass = in.nextLine();
			if (pclass.equals("Mage"))
			{
				playerClass = PlayerClass.Mage;
			}
			else if (pclass.equals("Bard"))
			{
				playerClass = PlayerClass.Bard;
			}
			else if(pclass.equals("Paladin"))
			{
				playerClass = PlayerClass.Paladin;
			}
			else if(pclass.equals("Assassin"))
			{
				playerClass = PlayerClass.Assassin;
			}
			else if(pclass.equals("Archer"))
			{
				playerClass = PlayerClass.Archer;
			}
			else
			{
				playerClass = PlayerClass.None;
			}
			tempBoxes.add(new Box(row, col, new Item(itemType, name, weight, value, strength, playerClass)));
		}
		in.nextLine();
		in.nextLine();
	    }
	    enemies = tempEnemies;
	   // enemies = enemy.getEnemies();
	    player.getInventory().setInventory(tempInventory);
	    boxes = tempBoxes;
	    run();
    }
    private void saveRoom()
    {
	    PrintWriter pw = null;
	    try {
		    pw = new PrintWriter(room1);
		    pw.println(currentRoom);
		    pw.println(enemies.size());
		    for (int i = 0; i < enemies.size(); i++)
		    {
			    enemies.get(i).save(pw);
		    }
		    pw.println(boxes.size());
	            for (int i = 0; i < boxes.size(); i++)
		    {
			    boxes.get(i).save(pw);
		    }
		    pw.println("stop");
		    pw.close();
	    }
	    catch (FileNotFoundException e)
	    {
		    e.printStackTrace();
	    }
    }
    private void loadRoom() throws FileNotFoundException
    {
	    ArrayList<Enemy> tempEnemies = new ArrayList<Enemy>();
	    ArrayList<Box> tempBoxes = new ArrayList<Box>();
	    ItemType itemType;
	    PlayerClass playerClass;
	    Scanner in = new Scanner(room1);
	    while (in.hasNextLine())
	    {
		    currentRoom = in.nextInt();
		    System.out.print("current room is: " + currentRoom + "\n\r");
		    int enemyCount = in.nextInt();
		    System.out.print("Enemy count is: " + enemyCount + "\n\r");
		    in.nextLine();
		    for (int i = 0; i < enemyCount; i++)
		    {
			    System.out.print("I am in the loop\n\r");
			    in.nextLine();
			    int row = in.nextInt();
			    int col = in.nextInt();
			    in.nextLine();
			    in.nextLine();
			    int hp = in.nextInt();
			    in.nextLine();
			    String name = in.nextLine();
			    int protection = in.nextInt();
			    int damage = in.nextInt();
			    in.nextLine();
			    String commentary = in.nextLine();
			    tempEnemies.add(new Enemy(name, row, col, hp, damage, protection, commentary));
		    }
		    int boxCount = in.nextInt();
		    for (int i = 0; i < boxCount; i++)
		    {
			    int row = in.nextInt();
			    int col = in.nextInt();
			    in.nextLine();
			    in.nextLine();
			    in.nextLine();
			    String type = in.nextLine();
			    if (type.equals("Weapon"))
			    {
				    itemType = ItemType.Weapon;
			    }
			    else if (type.equals("Armor"))
			    {
				    itemType = ItemType.Armor;
			    }
			    else
			    {
				    itemType = ItemType.Other;
			    }
			    String name = in.nextLine();
			    int weight = in.nextInt();
			    int value = in.nextInt();
			    int strength = in.nextInt();
			    in.nextLine();
			    String pclass = in.nextLine();
			    if (pclass.equals("Mage"))
			    {
				    playerClass = PlayerClass.Mage;
			    }
			    else if(pclass.equals("Bard"))
			    {
				    playerClass = PlayerClass.Bard;
			    }
			    else if(pclass.equals("Paladin"))
			    {
				    playerClass = PlayerClass.Paladin;
			    }
			    else if(pclass.equals("Assassin"))
			    {
				    playerClass = PlayerClass.Assassin;
			    }
			    else
			    {
				    playerClass = PlayerClass.None;
			    }
			    tempBoxes.add(new Box(row, col, new Item(itemType, name, weight, value, strength, playerClass)));
		    }
		    in.nextLine();
		    in.nextLine();
	    }
	    enemies = tempEnemies;
	    boxes = tempBoxes;
	    run();
    }


  


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
	    int x = 0;
	    x = rooms.get(currentRoom).enterRoom(player.getRow(), player.getCol());


	    if (x == 1) {
		    setStatus("Would you like to enter a new room? 1. yes, 2. no\n\r");
		    if (askToEnter() == true) {
	            saveRoom();
		    //saveGame();
		    currentRoom = 1;
		    redrawMapAndHelp();
		    player.setPosition(rooms.get(currentRoom).getPlayerStart().getRow(), rooms.get(currentRoom).getPlayerStart().getCol()-1);
		    boxes = rooms.get(currentRoom).getBoxes();
		    enemies = rooms.get(currentRoom).getEnemies();
		    return true;
		    }
	    } else if (x == 2) {
		    setStatus("You hear a terrible scraping noise from the next room. . .\n\rWould you like to explore it? 1. yes / 2. no\n\r");
		    if (askToEnter() == true) {
		    saveGame();
		    currentRoom = 2;
		    redrawMapAndHelp();
		    player.setPosition(rooms.get(currentRoom).getPlayerStart().getRow(), rooms.get(currentRoom).getPlayerStart().getCol());
                    boxes = rooms.get(currentRoom).getBoxes();
                    enemies = rooms.get(currentRoom).getEnemies();
		    return true;
		    }
            } else if (x == 3) {
		   setStatus("You're getting a bad feeling . . .\n\rWould you like to explore it? 1. yes / 2. no\n\r");
		   if (askToEnter() == true) {
		   saveGame();
		   currentRoom = 3;
		   redrawMapAndHelp();
		   player.setPosition(rooms.get(currentRoom).getPlayerStart().getRow(), rooms.get(currentRoom).getPlayerStart().getCol());
                   boxes = rooms.get(currentRoom).getBoxes();
                   enemies = rooms.get(currentRoom).getEnemies();
		   return true;
		   }
	    } else if (x == 4) {
		    setStatus("Return to the overworld? 1. yes / 2. no\n\r");
		    if (askToEnter() == true) {
		    try { 
			    loadRoom();
			   // loadGame(); 
		    } catch(FileNotFoundException r) {
                        System.out.println("File was not found");
		    }
		    return true;
	         }
	    }
	    return false;
	   
	  
    }

    public boolean askToEnter() {
	    Scanner input = new Scanner(System.in);
	    int choice = input.nextInt();

	    if (choice == 1) {
		    return true;
	    } else if (choice == 2) {
		    return false;
	    } else {
		    setStatus("Please enter a valid input.\n\r" + askToEnter());
	    }
	    return false;
    }


    public void run() {
        // draw these for the first time now
        redrawMapAndHelp();

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
	    

	    checkRoom();
        }
    }
}

