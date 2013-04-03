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
	
<<<<<<< HEAD
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
=======
	public Bullet_Component() {
		this.weaponType = 0;
	}
	
	public Bullet_Component(int bulletType) {
		this.weaponType = bulletType;
>>>>>>> 4790f0d6f01b66541186a13b698518ced23a5e74
	}
}
