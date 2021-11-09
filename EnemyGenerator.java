//Generates random enemies with a type and health stat

import java.util.Random;

public class EnemyGenerator {
    private static Random rng=new Random();
    /*
    this method returns a random Enemy depending on the number of the random integer
    the respective case will return the item associated with it
     */
    public static Enemy generate(){
        Enemy enemy;
        int value=rng.nextInt(3);
        switch (value){
            case 0:
                enemy=new Enemy(EnemyType.OGRE,110);
                break;
            case 1:
                enemy=new Enemy(EnemyType.SKELETON,90);
                break;
            case 2:
                enemy=new Enemy(EnemyType.GOBLIN,60);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + value);
        }
        return enemy;
    }
}
