// Main.java
// contains the main class for running the game

import ansi_terminal.*;

public class Main {
    public static void main(String args[]) {
        // put termain in raw mode
        Terminal.rawMode();
	System.out.print("\nIn Times long gone, humanity existed during an era of chaos and hostility. \n\r");
	System.out.print("Vile creatures spawned daily from volatile pools of ebony miasma, which burned, pillaged, and ravaged the land. \n\r");
	System.out.print("The horrid monsters were enticed by the high concentration of negative emptions humans projected outward. \n\r");
	System.out.print("Spearheading their army was the Demon Emperor Laplace: the demon of the apocalypse, the master of the dark arcane arts, the master of the dark arcane arts, the master of the Hundred Hand First, and leader of Hexagram, a group of elite eldritch horrors that held positions as generals. \n\r");
	System.out.print("Together he and his army drove humanity to the bring of extinction and whatever modest amount of humnans remaining were primed to serve as livestock and sloves. \n\r");
	//Terminal.pause(4);
	System.out.print("Although the situation seemed bleak and without a light in sight, a hero emerged amongst the chaos. \n\r");
	System.out.print("His name was Rudra, and with his trusted sword, Clarent, Rudra single handedly beat back the Demon Emperor's forces and rallied the remains of humanity together. \n\r");
	System.out.print("During the final confrontation, Rudra managed to strike down Laplace and seal away his demon generals but not without being struck down by Laplace's last act of hateful definance. \n\r");
	//Terminal.pause(4);
	System.out.print("In the modern-day, the world is controlled under the banner of the Kingdom of Arcadium. \n\r");
	System.out.print("The members of Hexagram have been reawakened and begun causing carnage across the land marching with the reborn demon army seeking bloos sacrifices to revive their falled lord. \n\r");
	System.out.print("Descendent of Rudra rise up and like your ancestor show them humanity's infinite capacity for evolution!\n\n\r");
	//Terminal.pause(4);

        // make and run the Game
        Game game = new Game();
        game.run();

        // put terminal back into cooked mode
        Terminal.cookedMode();
    }
}

