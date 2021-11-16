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
        int value=rng.nextInt(3);
        switch (value){
            case 0:
                enemy=new Enemy("Ogre", row, col, 50, 30, 3);
                break;
            case 1:
                enemy=new Enemy("Skeleton", row, col, 50, 20, 1);
                break;
            case 2:
                enemy=new Enemy("Goblin", row, col, 50, 25, 2);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + value);
        }
        return enemy;
    }
}
