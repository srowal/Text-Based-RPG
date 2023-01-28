package com.textrpg.character;

public class Player extends Character {
	
	public Player(String name)
	{
		super(name, 100);
	}

	@Override
	public int attack()
	{
		return 10 + rand.nextInt(5);
	}

	@Override
	public int defend()
	{
		return 5 + rand.nextInt(5);
		
	}
}
