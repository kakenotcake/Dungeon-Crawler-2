// Entity.java
// this class represents one moveable, drawable thing in the game
import java.io.PrintWriter;
import ansi_terminal.*;
import java.util.Scanner;

public class Entity {
    // the location of the entity in space
    private Position position;

    // the character used to draw it
    private char display;

    // the color used for drawing
    private Color color;

    public Entity(int row, int col, char display, Color color) {
        position = new Position(row, col);
        this.display = display;
        this.color = color;
    }

    // move the entity to a new location
    public void setPosition(int row, int col) {
        position = new Position(row, col);
    }

    // get the position of this entity
    public Position getPosition() {
        return position;
    }

    public int getRow() {
        return position.getRow();
    }

    public int getCol() {
        return position.getCol();
    }
    /**
     *This method stores all the revelant to the class which are the entity's position (row,colm)
     *and the character value they are stored at
     *@param  printwriter is the parameter where the information is stored for the save
     *
     */
    void save(PrintWriter o){
	    o.println(position.getRow());
	    o.println(position.getCol());
	    o.println(display);

    }
    public void loadGame(Scanner in){
	    System.out.print("I am in the entity constructor.\n\r");
	    int row = in.nextInt();
	    int col = in.nextInt();
	    in.nextLine();
	    String hold = in.nextLine();
	    display = hold.charAt(0);
	    setPosition(row, col);
	    System.out.print(getRow() + "\n\r" + getCol() + "\n\r" + display + "\n\r");

	   /* in.nextLine();
	    int row=position.getRow();
	    int col=position.getCol();
	    row=in.nextInt();
	    col=in.nextInt();
	    display=in.next().charAt(0);*/
	   

    }

    // translate the entity in space, unless it would hit a wall
    public boolean move(int rowChange, int colChange, Room room) {
        // find new position
        int newRow = position.getRow() + rowChange;
        int newCol = position.getCol() + colChange;

        if (room.canGo(newRow, newCol)) {
            // draw a space where it currently is
            Terminal.warpCursor(position.getRow(), position.getCol());
            System.out.print(" ");

            // and then move it
            position = new Position(newRow, newCol);
            return true;
        } else {
            return false;
        }
    }

    // draw this entity to the screen
    public void draw() {
        Terminal.warpCursor(position.getRow(), position.getCol());
        Terminal.setForeground(color);
        System.out.print(display);
        Terminal.reset();
    }
}

