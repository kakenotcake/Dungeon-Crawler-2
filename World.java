import java.io.*;
import java.util.ArrayList;
public class World {
	private ArrayList<String> roomOne;
        private ArrayList<String> roomTwo;
        private ArrayList<String> roomThree;

        public World()
	{
		roomOne = new ArrayList<String>();
		roomTwo = new ArrayList<String>();
		roomThree = new ArrayList<String>();
	}

	public void printRoomOne() throws Exception
	{

		String fileName = "Room1.txt";

		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line;
		while ((line = br.readLine()) != null) {
			roomOne.add(line);
		}

		for (int i = 0; i < roomOne.size(); i++) {
			System.out.print(roomOne.get(i) + "\n\r");
		}

	}

	public void printRoomTwo() throws Exception
	{
		String fileName = "Room2.txt";

		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line;
		while ((line = br.readLine()) != null) {
			roomTwo.add(line);
		}

		for (int i = 0; i < roomTwo.size(); i++) {
			System.out.print(roomTwo.get(i) + "\n\r");
		}
	}
}



