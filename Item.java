/** This class is responsible for managing Item objects and their appropriate methods.
 *
 * @author Ian Finlayson, Nahom Tilahun, Katelyn Hernandez, Kelsey Payne
 * @version 2021
 */
public class Item {

        private ItemType type;
        private String name;
        private int weight;
        private int value;
        private int strength;
	private PlayerClass playerClass;
	private String commentary;

        public Item(ItemType type, String name, int weight, int value, int strength, PlayerClass playerClass)

        {
                this.type = type;
                this.name = name;
                this.weight = weight;
                this.value = value;
                this.strength = strength;
		this.playerClass = playerClass;

        }
	public Item (ItemType type, String name, int weight, int value, int strength, PlayerClass playerClass, String commentary)
	{
		this.type = type;
		this.name = name;
		this.weight = weight;
		this.value = value;
		this.strength = strength;
		this.playerClass = playerClass;
		this.commentary = commentary;
	}
	public Item(ItemType type, String name, int weight, int value, int strength)
	{
		this.type = type;
		this.name =name;
		this.weight = weight;
		this.value = value;
		this.strength = strength;
	}

        /**
         * A getter- this method returns the weight of the Item being referred to.
	 *
	 * @return the int representing the Item's weight
         */
        public int getWeight()
        {
                return this.weight;
        }

        /**
         * A getter- this method returns the value of the Item being referred to.
	 *
	 * @return the int representing the Item's value
         */
        public int getValue()
        {
                return this.value;
        }

        /**
         * A getter- this method returns the name of the Item being referred to.
	 *
	 * @return the String name of an Item
         */
        public String getName()
        {
                return this.name;
        }

        /**
         * A getter- this method returns the Type (enumeration) of the Item being referred to.
	 *
	 * @return the enum ItemType of an Item
         */
        public ItemType getType()
        {
                return this.type;
        }

        /**
	 * A getter- this method returns the strength of the Item being referred to.
	 *
	 * @return the int representing an Item's strength
	 */
	public int getStrength()
	{
		return this.strength;
	}

	/**This method gets the class that the player chose at the begining of the game.
	 *
	 *@return the class of the player
	 */
	public PlayerClass getPlayerClass()
	{
		return this.playerClass;
	}

	/**This method gets the commentary that occurs when an action occurs.
	 *
	 *@return the string containing the commentary based on an action occurring
	 */
	public String getCommentary()
	{
		return this.commentary;
	}

        /** This method is responsible for returning all of the properties of an Item object
	 * in a legible way.
	 *
	 * @return a String containing the Item's properies
         */
	public String toString()
	{
		return this.type + " | " + this.name + " | " + this.weight + " | " +  this.value + " | " + this.strength;
	}

	/**This method is responsible for returning all of the properties of an Item object when the game is saved.
	 *
	 * @return a String containing the saved Item's properies
	 */
        public String toStringForSave()
        {
                return this.type + "\n" + this.name + "\n" + this.weight + "\n" + this.value + "\n" + this.strength + "\n" + this.playerClass + "\n";


        }
}

