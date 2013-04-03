package com.enge.bullethell.Components;

import com.artemis.Component;

public class Hitbox_Component extends Component {

	public int width;
	public int height;
	
	public Hitbox_Component()
	{
		//Empty
	}
	
	public Hitbox_Component(int width, int height)
	{
		this.width = width;
		this.height = height;
	}
}
