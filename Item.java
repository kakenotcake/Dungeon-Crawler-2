/**
/ * This class is responsible for managing Item objects and their appropriate methods.
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
         * A getter. This method returns the weight of the Item being referred to.
         */
        public int getWeight()
        {
                return this.weight;
        }

        /**
         * A getter. This method returns the value of the Item being referred to.
         */
        public int getValue()
        {
                return this.value;
        }

        /**
         * A getter. This method returns the name of the Item being referred to.
         */
        public String getName()
        {
                return this.name;
        }

        /**
         * A getter. This method returns the Type (enumeration) of the Item being referred to.
         */
        public ItemType getType()
        {
                return this.type;
        }

	public int getStrength()
	{
		return this.strength;
	}
	public PlayerClass getPlayerClass()
	{
		return this.playerClass;
	}
	public String getCommentary()
	{
		return this.commentary;
	}

        /**
         * The toString() method for Item objects. This method is responsible for returning all of the parameters of an Item object in a legible way.
         */
	public String toString()
	{
		return this.type + " " + this.name + " " + this.weight + " " +  this.value + " " + this.strength + " " + this.playerClass;
	}
        public String toStringForSave()
        {
                return this.type + "\n" + this.name + "\n" + this.weight + "\n" + this.value + "\n" + this.strength + "\n" + this.playerClass + "\n";

        }
}

