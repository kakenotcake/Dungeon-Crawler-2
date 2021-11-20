//Generates random enemies with a type and health stat

import java.util.Random;

public class EnemyGenerator {
    private static Random rng=new Random();
    /*
    this method returns a random Enemy depending on the number of the random integer
    the respective case will return the item associated with it
     */
    public static Enemy generate(int row, int col){
        Enemy enemy;
        int value=rng.nextInt(6);
        switch (value){
            case 0:
                enemy=new Enemy("Ogre", row, col, 50, 30, 3, "Ogre clubbed you in the head!                                                     ");
                break;
            case 1:
                enemy=new Enemy("Skeleton", row, col, 50, 20, 1, "Skeleton poked you really hard with its boney finger!                         ");
                break;
            case 2:
                enemy=new Enemy("Goblin", row, col, 50, 25, 2, "Goblin tied your shoe laces together making you to trip!                        ");
                break;
	    case 3:
		enemy = new Enemy("Skeleton Horseman", row, col, 50, 28, 4, "Skeleton Horseman charges toward you full speed!                   ");
		break;
	    case 4:
	        enemy = new Enemy("Mechanical Spider", row, col, 50, 20, 2, "Mechanical Spider hits you with its poisonous silk!                ");
		break;
	    case 5:
	        enemy = new Enemy("Hell Hound", row, col, 50, 25, 3, "Hell Hound takes a bite of your arm!                                      ");
		break;
            default:
                throw new IllegalStateException("Unexpected value: " + value);
        }
        return enemy;
    }
}
