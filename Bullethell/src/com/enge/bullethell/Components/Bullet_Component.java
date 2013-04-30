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
	public long fireRate;
	
	/**
	 * Constructor that takes a weapon type.
	 * @param weaponType The type of weapon.
	 */
	public Bullet_Component(int weaponType, long fireRate)
	{
		this.weaponType = weaponType;
		this.fireRate = fireRate;
	}
}
