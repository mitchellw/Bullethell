package com.enge.bullethell.Components;

import com.artemis.Component;
import com.enge.bullethell.Vector2;

public class Velocity_Component extends Component 
{
	public Vector2 velocity;
	
	public Velocity_Component()
	{
		velocity = Vector2.Zero;
	}
	
	public Velocity_Component(Vector2 vector) {
		this.velocity = vector;
	}
	
	public Velocity_Component(float vX, float vY)
	{
		this.velocity = new Vector2(vX, vY);
	}

}
