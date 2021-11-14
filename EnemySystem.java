import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
public class EnemySystem{
    private int EnemyHealth;
    private ArrayList<Enemy> enemies;


    //hydration constructor to save information of Enemies
    EnemySystem(String fileName)throws Exception {
        this.enemies=new ArrayList<Enemy>();
        Scanner stdin = new Scanner(new FileReader(fileName));
        this.EnemyHealth=stdin.nextInt();
        try{
            while(true){
                Enemy a=new Enemy(stdin);
                enemies.add(a);
            }


        }catch (NoMoreEnemiesException e){

        }


    }


}


