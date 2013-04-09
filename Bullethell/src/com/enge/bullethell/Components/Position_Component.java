package com.enge.bullethell.Components;

import com.artemis.Component;
import com.enge.bullethell.Vector2;

public class Position_Component extends Component 
{
	public Vector2 position;
	
	public Position_Component()
	{
		//Empty
	}
	
	public Position_Component(Vector2 vector) {
		this.position = vector;
	}
	
	public Position_Component(float x, float y)
	{
		this.position = new Vector2(x, y);
	}
}
