package com.enge.bullethell.Components;

import com.artemis.Component;

/**
 * The bullet component class.  Picks the weapon type for the
 * entity.
 * @author SFenton
 * @version 2013.04.03
 */
public class Bullet_Component extends Component 
{
	public int weaponType;
	
	/**
	 * Empty constructor for the class.
	 */
	public Bullet_Component()
	{
		//Empty
	}
	
	/**
	 * Constructor that takes a weapon type.
	 * @param weaponType The type of weapon.
	 */
	public Bullet_Component(int weaponType)
	{
		this.weaponType = weaponType;
	}
}
