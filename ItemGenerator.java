import java.util.Collections;
import java.util.ArrayList;
/**
 * This class is responsible for generating random Item objects with the static method generate().
 */
public class ItemGenerator {



        /**
         * This method creates a list of reserve items and places an assortment of Items inside of it. Then, it shuffles all of the elements in the list and returns whatever the first element is after shuffling.
         */
        public static Item generate() {

                ArrayList<Item> reserves = new ArrayList<Item>();

                Item masterSword = new Item(ItemType.Weapon, "Master Sword", 30, 780, 40);
                reserves.add(masterSword);
                Item skywardHarp = new Item(ItemType.Weapon, "Skyward Harp", 20, 500, 26);
                reserves.add(skywardHarp);
                Item fish = new Item(ItemType.Weapon, "Mighty Fish Lord", 45, 29, 32);
                reserves.add(fish);
		Item wabbajack = new Item(ItemType.Weapon, "Wabbajack", 17, 350, 28);
		reserves.add(wabbajack);
		Item darts = new Item(ItemType.Weapon, "Poison Darts", 9, 64, 16);
		reserves.add(darts);
                Item hylianShield = new Item(ItemType.Armor, "Hylian Shield", 50, 380, 0);
                reserves.add(hylianShield);
                Item prayer = new Item(ItemType.Armor, "Holy Prayer", 33, 777, 0);
                reserves.add(prayer);
                Item guardian = new Item(ItemType.Armor, "Guardian Graves", 42, 185, 0);
                reserves.add(guardian);
		Item royalGarb = new Item(ItemType.Armor, "Royal Guard's Cloak", 22, 140, 0);
		reserves.add(royalGarb);
		Item slippers = new Item(ItemType.Armor, "Rubber Slippers", 16, 72, 0);
		reserves.add(slippers);
                Item star = new Item(ItemType.Other, "Star Fragment", 32, 650, 0);
                reserves.add(star);
                Item pie = new Item(ItemType.Other, "Apple Pie", 2, 18, 0);
                reserves.add(pie);
                Item bees = new Item(ItemType.Other, "Peculiar Beehive", 8, 43, 80);
                reserves.add(bees);
		Item cane = new Item(ItemType.Other, "Crow's Head Cane", 12, 55, 0);
		reserves.add(cane);
		Item ocarina = new Item(ItemType.Other, "Royal Ocarina", 6, 120, 0);
		reserves.add(ocarina);

                Collections.shuffle(reserves);
                return reserves.get(0);

        }
}

