// Box.java
// represents a pickup-able item
import ansi_terminal.*;
import java.util.Scanner;
import java.io.PrintWriter;

public class Box extends Entity {
    // the Item that is in the box
    private Item item;

    // add a box with a given item in it
    public Box(int row, int col, Item item) {
        super(row, col, 'i', Color.MAGENTA);
        this.item = item;
    }
/**
 *This is a method that store/saves all revelant information concerning this class
 *in this case it stores items
 *@param x  printwriter which stores/saves the information
 *
 */    
    @Override
    void save(PrintWriter x){
	    super.save(x);
	    x.println("Box");
	    x.println(getItem().toStringForSave());
    }

    
   /* public void loadGame(Scanner in){
	    super.loadGame(in);
	    in.nextLine();
	    String i = getItem().toString();
	    i=in.nextLine();
    }*/





    public Item getItem() {
        return item;
    }
}


