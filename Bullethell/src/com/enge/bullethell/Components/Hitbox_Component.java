package com.enge.bullethell.Components;

import com.artemis.Component;

/**
 * The Hitbox component for every entity that requires it; if the hitbox is hit,
 * then decrement the health.
 * @author SFenton
 * @version 2013.04.03
 */
public class Hitbox_Component extends Component {

	public int width;
	public int height;
	
	/**
	 * Empty constructor for the class.
	 */
	public Hitbox_Component()
	{
		//Empty
	}
	
	/**
	 * Constructor that takes the width and the height.
	 * @param width The width of the hitbox.
	 * @param height The height of the hitbox.
	 */
	public Hitbox_Component(int width, int height)
	{
		this.width = width;
		this.height = height;
	}
}
