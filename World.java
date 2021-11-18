import java.io.*;
public class World {
	private String[] roomOne;
        private String[] roomTwo;
        private String[] roomThree;

        public World(String[] roomOne, String[] roomTwo, String[] roomThree)
	{
		this.roomOne = roomOne;
		this.roomTwo = roomTwo;
		this.roomThree = roomThree;
	}

	public void printNewRoom(String fileName) throws Exception
	{
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}

	}
}



