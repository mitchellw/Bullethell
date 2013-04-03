package com.enge.bullethell.Components;

import com.artemis.Component;

public class Position_Component extends Component 
{
	private int x;
	private int y;
	
	public void setCoord(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
}
