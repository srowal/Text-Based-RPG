package com.textrpg.character;

import java.util.Random;

public abstract class Character {

	protected String name;
	protected int maxHp;
	protected int hp;
	protected int level;
	protected Random rand;

	protected Character(String name, int maxHp)
	{
		rand = new Random();
		this.name = name;
		this.maxHp = maxHp;
		hp = maxHp;
	}
	
	
	public abstract int attack(); // determines how much damage a character does
	public abstract int defend(); // determines how much damage a character mitigates
	
	public boolean isAlive()
	{
		return hp > 0;
	}
	
	@Override
	public String toString()
	{
		return name + "[ HP= " + hp + "/" + maxHp + "]";
	}

	public String getName()
	{
		return name;
	}
	
	public int getHp()
	{
		return hp;
	}
	
	public int getMaxHp()
	{
		return maxHp;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setHp(int hp)
	{
		this.hp = hp;
	}
	
	
}
