package com.enge.bullethell.Components;

import com.artemis.Component;

public class Position_Component extends Component 
{
	public int x;
	public int y;
	
	public Position_Component()
	{
		//Empty
	}
	
	public Position_Component(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
}
