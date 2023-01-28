package com.textrpg.gameengine;

import com.textrpg.character.*;
import com.textrpg.character.Character;

import java.util.Random;
import java.util.Scanner;

public class GameEngine {

	static Scanner sc = new Scanner(System.in);
	static Random rand = new Random();
	static Player player;
	static int round = 0;
	
	static String[] exploreRoll = {"monster", "monster", "monster", "potion", "trap", "empty"};
	static String[] monsters = {"Mummy", "Zombie", "Zombie","Zombie", "Vampire", "Vampire"};

	// prompts user for an integer, validates and returns the int 
	public static int choose(int numOptions)
	{
		int userChoice = -1;

		while (userChoice < 1 || userChoice > numOptions)
		{
			System.out.print("\n->");

			try
			{
				userChoice = Integer.parseInt(sc.nextLine());
				
				if (userChoice < 1 || userChoice > numOptions)
				{
					System.out.println("\nERROR, please select one of the options");
				}
			} 
			catch (NumberFormatException e)
			{
				System.out.println("\nERROR, Please enter an Integer");
			}


		}

		return userChoice;
	}

	// prompts the user to press enter key
	static void enterToContinue()
	{
		System.out.println("\nPress Enter to continue.");
		sc.nextLine();
	}
	
	// runs the game
	public static void gameStart()
	{
		System.out.println("\nWhat is this Hero's name?");
		
		player = new Player(sc.next() + sc.nextLine());
		
		int bossFightRound = 8 + rand.nextInt(5);
		
		// main game loop
		while (round < bossFightRound)
		{
			printMenu();
		}
		
		System.out.println("You see a big monster approaching, it looks like a BOSS!!");
		enterToContinue();
		
		// Boss Fight
		combat(new Monster("BOSS", 150));
		
		System.out.println("Congrats You Won!!");	
	}
	
	// prints the choices the user can make
	static void printMenu()
	{
		System.out.println("What should " + player.getName() + " do?");
		System.out.println("1 - Keep exploring");
		System.out.println("2 - Info");
		System.out.println("3 - Quit");
		
		switch(choose(3))
		{
		case 1:
			explore();
			break;
		case 2:
			System.out.println(player.toString());
			break;
		case 3:
			System.out.println("You have decided to quit, better luck next time!");
			System.exit(0);
		}
		enterToContinue();

		
	}
	
	// the player explores and runs into 1 of 4 random events
	static void explore()
	{
		round ++;
		switch(exploreRoll[rand.nextInt(exploreRoll.length)])
		{
		case "monster":
			randomMonster();
			break;
		case "potion":
			System.out.println("You find a potion that heals you for 20 HP");
			player.setHp(player.getHp() + 20);
			if (player.getHp() > player.getMaxHp())
			{
				player.setHp(player.getMaxHp());
			}
			break;
		case "trap":
			System.out.println("You fall into a trap, lose 10 HP");
			player.setHp(player.getHp() - 10);
			if (!player.isAlive())
			{
				System.out.println("Your hero has fallen, better luck next time.");
				System.exit(0);
			}
			break;
		case "empty":
			System.out.println("You walk into an empty room.");
		}
	}
	
	// generate a random monster with 20 to 30 HP
	static void randomMonster()
	{
		Monster monster = new Monster(monsters[rand.nextInt(monsters.length)], 20 + rand.nextInt(11));
		System.out.println("A new " + monster.getName() + " appeared!");
		combat(monster);
	}
	
	// combat system
	static void combat(Monster monster)
	{
		System.out.println("Prepare for Battle.");
		
		while (player.isAlive() && monster.isAlive())
		{
			System.out.println(player + "-------" + monster);
			System.out.println("\nChoose your action");
			System.out.println("1 - Fight");
			System.out.println("2 - Run");
			
			int choice = choose(2);
			if (choice == 1)
			{
				fight(player, monster);
				
				if (monster.isAlive())
				{
					fight(monster, player);
				}
			}
			else if (choice == 2)
			{
				if (rand.nextInt(100) < 25)
				{
					System.out.println("You ran away succesfully");
					break;
					
				}
				System.out.println("Usuccesful, prepare for enemy attack");
				enterToContinue();
				fight(monster, player);
			}
			
		}
		
	}

	// the attacker attacks the defender
	static void fight(Character attacker, Character defender)
	{
		int dmg = 0;
		
		dmg = attacker.attack() - defender.defend();
		if (dmg < 0)
		{
			dmg = 0;
		}
		
		defender.setHp(defender.getHp() - dmg);
		
		System.out.println(attacker.getName() + " attacks " + defender.getName() + " for " + dmg + " damage.");
		
		if (!defender.isAlive())
		{
			System.out.println(defender.getName() + " has fallen.");
			if (defender == player)
			{
				System.out.println("GAME OVER!");
				System.exit(0);
			}
		}
		
	}
	


}
