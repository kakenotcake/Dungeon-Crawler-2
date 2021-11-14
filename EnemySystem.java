public class EnemySystem{
	private int EnemyHealth;
	private ArrayList<Enemy>enemies;
	private Position EnemyPosition;

	//hydration constructor to save information of Enemies
	EnemySystem(String fileName){
		this.enemies=new ArrayList<Enemy>();
		Scanner s = new Scanner(new FileReader(filename));
		this.EnemyHealth=s.nextInt();


	}


}
