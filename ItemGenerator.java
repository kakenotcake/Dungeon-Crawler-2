import java.util.Collections;
import java.util.ArrayList;
/** This class is responsible for generating random Item objects with the static method generate()
 * It is also responsible for generating Class specific objects for the Player with the method generatePlayerSpecificItem().
 *
 * @Author Kelsey Payne and Katelyn Hernandez
 * @version 2021
 */
public class ItemGenerator {



        /**
         * This method creates a list of reserve items and places an assortment of Items inside of it
	 * Then, it shuffles all of the elements in the list and returns whatever the first element is after shuffling.
	 *
	 * @return the generated Item object
         */
        public static Item generate() {

                ArrayList<Item> reserves = new ArrayList<Item>();

                Item masterSword = new Item(ItemType.Weapon, "Master Sword", 30, 780, 40, PlayerClass.Paladin, "You swing your mighty master sword and strike the enemy!");
                reserves.add(masterSword);
                Item fish = new Item(ItemType.Weapon, "Mighty Fish Lord", 45, 29, 32, PlayerClass.Archer, "You aim your Mighty Fish Lord at the enemy and hit it with a big ole' seabass.");
                reserves.add(fish);
		Item spear = new Item(ItemType.Weapon, "Dragonspine Spear", 24, 113, 20, PlayerClass.Assassin, "With a cold grace, you strike the enemy with your Dragonspine Spear.");
		reserves.add(spear);
		Item axe = new Item(ItemType.Weapon, "Ebony axe", 28, 200, 37, PlayerClass.Assassin, "You swing your ebony axe and cleave the enemy with devastating might.");
		reserves.add(axe);
		Item cucco = new Item(ItemType.Weapon, "Golden Cucco", 30, 999, 23, PlayerClass.Bard, "A swarm of mighty cuccos attack your enemy in a mess of feathers and talons!");
		reserves.add(cucco);
                Item hylianShield = new Item(ItemType.Armor, "Hylian Shield", 50, 380, 10, PlayerClass.Archer);
                reserves.add(hylianShield);
		Item royalGarb = new Item(ItemType.Armor, "Royal Guard's Cloak", 22, 140, 11, PlayerClass.Assassin);
		reserves.add(royalGarb);
		Item scale = new Item(ItemType.Armor, "Dragonscale Bracers", 31, 320, 16, PlayerClass.Archer);
		reserves.add(scale);
		Item chestplate = new Item(ItemType.Armor, "Glass Chestplate", 35, 250, 22, PlayerClass.Paladin);
		reserves.add(chestplate);
                Item soup = new Item(ItemType.Other, "Mysterious Soup", 32, 650, 12);
                reserves.add(soup);
                Item pie = new Item(ItemType.Other, "Apple Pie", 2, 18, 8);
                reserves.add(pie);
                Item bees = new Item(ItemType.Other, "Peculiar Beehive", 8, 43, 9);
                reserves.add(bees);
		Item walnut = new Item(ItemType.Other, "Inconspicuous Walnut", 12, 55, 15);
		reserves.add(walnut);
		Item ocarina = new Item(ItemType.Other, "Royal Ocarina", 6, 120, 13);
		reserves.add(ocarina);

                Collections.shuffle(reserves);
                return reserves.get(0);

        }

	/**This method behaves in the same way was the generate() method but only generates a Player's starting gear depending
	 * on what class they chose at the start of the game.
	 *
	 * @return the generated Item object
	 */
	public static Item generatePlayerSpecificItem() {

		ArrayList<Item> classItems = new ArrayList<Item>();

		Item skywardHarp = new Item(ItemType.Weapon, "Skyward Harp", 20, 500, 26, PlayerClass.Bard, "You use your Skyward Harp to play a beautiful song, bringing your enemy to tears.");
                classItems.add(skywardHarp);
		Item coat = new Item(ItemType.Armor, "Virtuoso's Trench Coat", 36, 90, 18, PlayerClass.Bard);
		classItems.add(coat);
		Item darts = new Item(ItemType.Weapon, "Poison Darts", 9, 64, 16, PlayerClass.Assassin, "Perfect shot! Your poison dart hits the enemy right in the eye!");
                classItems.add(darts);
		Item slippers = new Item(ItemType.Armor, "Rubber Slippers", 16, 72, 8, PlayerClass.Assassin);
                classItems.add(slippers);
		Item sacredBlade = new Item(ItemType.Weapon, "Goddess Blade", 22, 320, 30, PlayerClass.Paladin, "The Goddess Blade strikes true! A heavenly ray of light sears your enemy where they stand.");
		classItems.add(sacredBlade);
		Item guardian = new Item(ItemType.Armor, "Guardian Graves", 42, 185, 10, PlayerClass.Paladin);
                classItems.add(guardian);
		Item wabbajack = new Item(ItemType.Weapon, "Wabbajack", 17, 350, 28, PlayerClass.Mage, "You point your Wabbajack at your enemy and turn them into a sweet roll (yum).");
                classItems.add(wabbajack);
		Item prayer = new Item(ItemType.Armor, "Holy Prayer", 33, 777, 12, PlayerClass.Mage);
                classItems.add(prayer);
		Item bow = new Item(ItemType.Weapon, "Great Eagle Bow", 24, 148, 32, PlayerClass.Archer, "Your aim does not falter. A deadly sharp arrow strikes your enemy with a mighty impact.");
		classItems.add(bow);
		Item bracers = new Item(ItemType.Armor, "Steel bracers", 14, 65, 20, PlayerClass.Archer);
		classItems.add(bracers);

		Collections.shuffle(classItems);
		return classItems.get(0);
	}

}

