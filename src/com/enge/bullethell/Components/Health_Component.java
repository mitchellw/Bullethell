package com.enge.bullethell.Components;

import com.artemis.Component;

/**
 * The health component of whatever entity is going to use it.
 * @author SFenton
 * @version 2013.04.03
 */
public class Health_Component extends Component 
{

	public int health;

	/**
	 * Empty constructor for the class.
	 */
	public Health_Component()
	{
		this.health = 1;
	}
	
	/**
	 * Sets the health for the component in the constructor.
	 * @param health The new health.
	 */
	public Health_Component(int health)
	{
		this.health = health;
	}
}
