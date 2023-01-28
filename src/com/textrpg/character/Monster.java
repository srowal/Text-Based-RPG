package com.textrpg.character;

public class Monster extends Character {

	public Monster(String name, int maxHp)
	{
		super(name, maxHp);
	}
	
	
	@Override
	public int attack()
	{
		return 10 + rand.nextInt(5);

	}

	@Override
	public int defend()
	{
		return 2 + rand.nextInt(5);

	}

}
