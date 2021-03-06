//Inventory Funtions: Add to inventory, get total weight, drop items, print inventory list, equip weapon and equip armor

import java.util.ArrayList;
import java.util.Scanner;
public class Inventory
{
	private ArrayList<Item> inventory = new ArrayList<Item>();
	private int maxWeight;
	private Item equippedWeapon;
	private Item equippedArmor;
	private int totalWeight = 0;
	private Player player;
	private Item dropped;

	Scanner input = new Scanner(System.in);

	public Inventory(int maxWeight) //constructor taking in max weight
	{
		this.maxWeight = maxWeight;
	}
	public boolean add(Item item) //method to add item
	{
		int newTotalWeight = totalWeight + item.getWeight(); //determine what weight would be if item was added
		if (newTotalWeight <= maxWeight) //if new weight is less than or equal to max weight proceed with adding to inventory and return true
		{
			inventory.add(item);
			totalWeight += item.getWeight();
			System.out.print("Item has been added.\n\r");
			return true;
		}
		else //if too heavy return false
		{
			System.out.print("Item is too heavy.  Cannot be added.\n\r");
			return false;
		}
	}
	public int totalWeight() //returns total weight in inventory
	{
		return totalWeight;
	}
	public void print() //prints inventory list
	{
		if (inventory.size() != 0)
		{
			System.out.print("Item Type | Name | Weight | Value | Strength\n\r");
			for (int i = 0; i < inventory.size(); i++)
			{
				System.out.print(i+1 + ". " + inventory.get(i) + "\n\r");
			}
			System.out.print("Total weight: " + totalWeight()+ "\n\r");
		}
		else //if inventory is empty
		{
			System.out.print("Your inventory is empty. Weight is 0.\n\r");
		}
	}
	public void drop() //method to drop items from inventory
	{
		if (inventory.size() != 0)
		{
			System.out.print("Which item would you like to drop? Enter the item number: \n\r");
			print();
			System.out.print((inventory.size()+1) + ". Cancel\n\r");
			int selection = input.nextInt(); //collect input from user
			if (selection > 0 && selection <= inventory.size()) //if selection is a valid item in inventory
			{
				if (!inventory.get(selection-1).equals(equippedWeapon) && !inventory.get(selection-1).equals(equippedArmor)) //if item is not an equipped item proceed as is
				{
					totalWeight  = totalWeight - inventory.get(selection-1).getWeight(); //subtract items weight from total weight
					dropped = inventory.get(selection-1); //asign to dropped item
					inventory.remove(selection-1); //remove item from arraylist
					System.out.print("Item " + selection + " has been removed.\n\r");
					System.out.print("The total weight is now: " + totalWeight()+"\n\r");
				}
				else //if item is an equipped weapon or armor ask if they want to proceed with removing from inventory
				{
					ArrayList<Item> weapons = new ArrayList<Item>();
					ArrayList<Item> armor = new ArrayList<Item>();
					System.out.print("The item you selected is equipped. Do you want to continue? (Y/N)\n\r");
					input.nextLine();
					String answer = input.nextLine(); //collect input from user
					boolean answerBool = answer.equalsIgnoreCase("Y"); //ignore case
					if(answerBool) //is answer is yes
					{
						for (int i = 0; i < inventory.size(); i++)
						{
							if (inventory.get(i).getType()==ItemType.Weapon)
							{
								weapons.add(inventory.get(i));
							}
							if (inventory.get(i).getType()==ItemType.Armor)
							{
								armor.add(inventory.get(i));
							}
						}

						if (weapons.size() >=  2 && inventory.get(selection-1).getType()==ItemType.Weapon) //removes weapon and equipped weapon is set to null
						{
							totalWeight = totalWeight - inventory.get(selection-1).getWeight();
							dropped = inventory.get(selection-1);
							inventory.remove(selection-1);
							System.out.print("Item " + selection + " has been removed and un-equipped.\n\r");
							System.out.print("The total weight is now: " + totalWeight() + "\n\r");
							equipWeapon();
						}
						else if (armor.size() >=  2 && inventory.get(selection-1).getType()==ItemType.Armor)//removes armor and equipped armor variable is set to null
						{
							totalWeight = totalWeight - inventory.get(selection-1).getWeight();
							dropped = inventory.get(selection-1);
							inventory.remove(selection-1);
							System.out.print("Item " + selection + " has been removed and un-equipped.\n\r");
							System.out.print("The total weight is now: " + totalWeight()+"\n\r");
							equipArmor();
						}
						else
						{
							System.out.print("You do not have another weapon/armor to equip.  Cannot remove item.\n\r");
						}
					}
					else //if answer is no repeats the drop method
					{
						drop();
					}
				}
			}
			else if (selection==inventory.size()+1) //if cancel is selected
			{
			}
			else //invalid input
			{
				System.out.print("Not a valid item number.\n\r");
			}
		}
		else //inventory is empty
		{
			System.out.print("Your inventory is empty.\n\r");
		}
	}

	/**This method tells you what item has been dropped
	 *@return Item dropped
	 */
	public Item getDropped()
	{
		return dropped;
	}

	/**This method looks at the whole inventory and asks what weapon the user wants to equip.
	 *
	 */
	public void equipWeapon() //method to assign weapon to equipped weapon variable
	{
		if (inventory.size() != 0) //if inventory is not empty
		{
			ArrayList<Item> weapons = new ArrayList<Item>(); //array just to store weapons
			int count = 0;
			System.out.print("Which weapon would you like to equip?\n\r");
			System.out.print("Weapon name and strength:\n\r");
			for (int i = 0; i < inventory.size(); i++) //check to see if there are weapons in inventory and print them
			{
				if (inventory.get(i).getType()==ItemType.Weapon)
				{
					count++;
					weapons.add(inventory.get(i));
					System.out.print(count + ". " + inventory.get(i).getName() + " (" + inventory.get(i).getStrength() + ")\n\r");
				}
			}
			if (weapons.size()!=0) //if there weapons in inventory
			{
				System.out.print((count+1) + ". Cancel\n\r");
				int selection = input.nextInt(); //collect input from user
				if (selection != (count+1))
				{
					equippedWeapon = weapons.get(selection-1); //assign equipped weapon
					System.out.print("You have equipped the " + equippedWeapon.getName() + "\n\r");
				
				}
			}
			else //no weapons in inventory
			{
				System.out.print("There are no weapons in your inventory.\n\r");
			}
		}
		else //inventory is empty
		{
			System.out.print("Your inventory is empty.\n\r");
		}
	}

        /**This method sets the Starter Weapon based on the class chosen at the beginning
        *@param weapon the weapon that is equipped
        */
	public void equipStarterWeapon(Item weapon)
	{
		equippedWeapon = weapon;
		System.out.print("Starter weapon " + weapon.getName() + " has been equipped.\n\r");
	}

	/**This is method sets the Starter armor based on the class chosen at the beginning
	 *@param armor the armor that is equipped
	 *
	 */
	public void equipStarterArmor(Item armor)
	{
		equippedArmor = armor;
		System.out.print("Starter armor " + armor.getName() + " has been equipped.\n\r");
	}

	public void equipArmor() //method to equip armor
	{
		if (inventory.size() != 0) //if inventory is not empty
		{
			ArrayList<Item> armor = new ArrayList<Item>(); //arraylist just for armor
			int count = 0;
			System.out.print("Which armor would you like to equip?\n\r");
			System.out.print("Armor name and strength:\n\r");
			for (int i = 0; i < inventory.size(); i++) //check for armor in inventory and print them
			{
				if (inventory.get(i).getType()==ItemType.Armor)
				{
					count++;
					armor.add(inventory.get(i));
					System.out.print(count + ". " + inventory.get(i).getName() + " (" +  inventory.get(i).getStrength() + ")\n\r");
				}
			}
			if (armor.size()!=0) //if armor is in inventory
			{
				System.out.print((count+1) + ". Cancel\n\r");
				int selection = input.nextInt(); //collect input from user
				if (selection != (count+1))
				{
					equippedArmor = armor.get(selection-1); //assign equipped armor
					System.out.print("You have equipped the " + equippedArmor.getName() + "\n\r");
				}
			}
			else //no armor in inventory
			{
				System.out.print("There is no armor in your inventory.\n\r");
			}
		}
		else //inventory is empty
		{
			System.out.print("Your inventory is empty.\n\r");
		}
	}

	public Item getEquippedWeapon()
	{
		return equippedWeapon;
	}
	public void setEquippedWeapon(Item weapon)
	{
		equippedWeapon = weapon;
	}

	public Item getEquippedArmor()
	{
		return equippedArmor;
	}
	public void setEquippedArmor(Item armor)
	{
		equippedArmor = armor;
	}
	/**This method looks through the inventory and asks if the player wants to use consumeable items like hp potions
	 *that are labeled by the Other enumeration.
	 *@return aid to be used
	 *
	 */
	public Item getAid()
	{
		ArrayList<Item> aid = new ArrayList<Item>();
		for (int i = 0; i < inventory.size(); i++)
		{
			if (inventory.get(i).getType().equals(ItemType.Other))
			{
				aid.add(inventory.get(i));
			}
		}
		if (aid.size() != 0)
		{
			System.out.print("Which item would you like to use?\n\r");
			System.out.print("Aid name and strength:\n\r");
			int count = 0;
			for (int i = 0; i < aid.size(); i++)
			{
				count++;
				System.out.print(count + ". " + aid.get(i).getName() + " (" + aid.get(i).getStrength() + ")\n\r");
			}
			int answer = input.nextInt();

			if (aid.get(answer-1).getName().equals("Apple Pie")) {
				System.out.print("You take a bite of the Apple Pie. The sweet taste of cinnamon warms your spirit.\n\r");
			} else if (aid.get(answer-1).getName().equals("Royal Ocarina")) {
				System.out.print("You play a sweet, energetic  melody on the Royal Ocarina. A youthful vigor fills your veins.\n\r");
			} else if (aid.get(answer-1).getName().equals("Inconspicuous Walnut")) {
				System.out.print("You pop the walnut in your mouth. It tastes like how a firework looks.\n\r");
			} else if (aid.get(answer-1).getName().equals("Peculiar Beehive")) {
				System.out.print("You nibble a piece of honeycomb from the Peculiar Beehive. The honey leaves a sharp, spicy sensation on your tongue.\n\r");
			} else if (aid.get(answer-1).getName().equals("Mysterious Soup")) {
				System.out.print("You take a tentaive sip of the bottled soup. It has a distinctly milky flavor. It's slightly unnerving.\n\r");
			}
			return aid.get(answer-1);
		}
		else
		{
			System.out.print("Sorry, you do not have aid in your inventory. Try picking up an item\n\r");
		}
		return null;
	}

	/**This method looks through the inventory and removes the consumable aid item that was used.
	 *
	 */
	public void removeUsedAid(Item usedAid)
	{
		inventory.remove(usedAid);
	}

	/**This method converts the type of the inventory into a readable form.
	 *
	 *@return String of Inventory
	 *
	 */
	public String toString()
	{
		String inv = "";
		for (int i = 0; i < inventory.size(); i++)
		{
			inv = inv + inventory.get(i).toStringForSave();
		}
		return inv;
	}
	/**This is a method gets the amount of items inside the inventory.
	 *@return integer representing the current size of the Inventory
	 *
	 */
	public int getSize()
	{
		return inventory.size();
	}

	/**This method sets the the items inside the inventory.
	 *
	 */
	public void setInventory(ArrayList<Item> inv)
	{
		inventory = inv;
	}


}
				



