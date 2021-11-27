// Room.java
// provides code for the drawing of a room
// also provides starting locations for the player, boxes, and enemies

import java.util.ArrayList;
import ansi_terminal.*;
import java.io.*;
import java.util.Scanner;

public class Room {
    // the grid holds the room geometry
    private String[] grid;

    // the size of the room
    private int rows;
    private int cols;

    public Room(int rows, int cols, String fileName) {
        // this initializes the room to one specific space
        this.rows = rows;
        this.cols = cols;

        // the actual room geometry
        // the i cells refer to where an item should be placed at

	grid = new String[rows];
		try {
                        BufferedReader br = new BufferedReader(new FileReader(fileName));
                        String line;
			int count=0;
                        while ((line = br.readLine()) != null) {
				String newLine = line.trim();
				newLine = newLine.substring(1,newLine.length()-2);
                                grid[count++] = newLine;
                        }
 	      } catch (Exception e) {

                        e.printStackTrace();
              }
    }

    // returns the player's strting location in this room
    public Position getPlayerStart() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row].charAt(col) == '@') {
                    return new Position(row, col);
                }
            }
        }

        return null;
    }


    // returns a set of item boxes for this map, this is here because it depends on
    // the room geometry for where the boxes make sense to be
    public ArrayList<Box> getBoxes() {
        ArrayList<Box> boxes = new ArrayList<Box>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row].charAt(col) == 'i') {
                    boxes.add(new Box(row, col, ItemGenerator.generate()));
                }
            }
        }

        return boxes;
    }

    // returns a set of enemies from this map, similarly to the boxes above
    public ArrayList<Enemy> getEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row].charAt(col) == '*') {
                    enemies.add(EnemyGenerator.generate(row, col));
                }
            }
        }

        return enemies;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    // draws the map to the screen
    public void draw() {
        Terminal.clear();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                char cell = grid[row].charAt(col);
                if (cell == '#') {
                    // a unicode block symbol
                    System.out.print('\u2588');
                } else {
                    // whatever else, just draw a blank (we DONT draw starting items from map)
                    System.out.print(' ');
                }
            }

            System.out.print("\n\r");
        }
    }

    // returns if a given cell in the map is walkable or not
    public boolean canGo(int row, int col) {
        return grid[row].charAt(col) != '#';
    }

    
    public int enterRoom(int row, int col) {
	    Scanner input = new Scanner(System.in);
	    if (grid[row].charAt(col) == 'x') {
		    return 1;
	    } else if (grid[row].charAt(col) == 'y') {
		    return 2;
	    } else if (grid[row].charAt(col) == 'z') {
		    return 3;
	    } else if (grid[row].charAt(col) == 'w') {
		    return 4;
	    }
		    return 0;
    }
    //}
}



