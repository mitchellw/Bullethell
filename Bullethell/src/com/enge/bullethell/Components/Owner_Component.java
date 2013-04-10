package com.enge.bullethell.Components;

import com.artemis.Component;

/**
 * The component that will determine who the owner of the entity is. 1 is player, 0 is enemy
 * @author SFenton
 * @version 2013.04.03
 */
public class Owner_Component extends Component 
{
	public int owner;
	
	/**
	 * Empty constructor for the class.
	 */
	public Owner_Component()
	{
		//Empty
	}
	
	/**
	 * Sets the owner in the constructor.
	 * @param owner The owner of the component.
	 */
	public Owner_Component(int owner)
	{
		this.owner = owner;
	}
}
