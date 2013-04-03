package com.enge.bullethell.Components;

import com.artemis.Component;

public class Bullet_Component extends Component 
{
	private int weaponType;
	
	public void setWeaponType(int weaponType)
	{
		this.weaponType = weaponType;
	}
	
	public int getWeaponType()
	{
		return weaponType;
	}
}
