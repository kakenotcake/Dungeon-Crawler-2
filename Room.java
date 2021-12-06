import java.util.ArrayList;
import ansi_terminal.*;
import java.io.*;
import java.util.Scanner;
/** This class provides code for the drawing of a room, and it also provides starting locations for the player, boxes, and enemies
* This class also handles baseline code for entering/exiting multiple rooms.
*
* @Author Kelsey Payne and Ian Finlayson
* @version 2021
*/

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
        // print out a Room from a file using BufferedReader
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
       // ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row].charAt(col) == '*') {
                    enemies.add(EnemyGenerator.generate(row, col));
                }
            }
        }
	return enemies;
    }

    // returns the number of rows from a given Room
    public int getRows() {
        return rows;
    }
    
    // returns the number of cols from a given Room
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
    /**
     *This method moniters where a character on the screen moves
     *and determines whether or not they can advance in that direction (Hash means no go)
     *@param row row the character is trying to move to
     *@param col col the character is trying to move to
     *@return true or false that determines whether or not the character can advance in that direction (false the area is not walkable)
     */
    public boolean canGo(int row, int col) {
        return grid[row].charAt(col) != '#';
    }

    /**This method moniters where the Player is stepping and returns an int if the Player steps on a special tile with x, y, z, or w
     * This int represents a new Room that can be accessed dependning on the tile the Player is on.
     *
     * @param row the row where the Player is currently at
     * @param col the col where the Player is currently at
     * @return an int between 4 if the Player steps on a tile marked with x, y, z, or w, or 0 if not
     */
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
}



