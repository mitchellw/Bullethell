package com.enge.bullethell.Components;

import com.artemis.Component;

public class Bullet_Component extends Component 
{
	public int weaponType;
	
	public Bullet_Component() {
		this.weaponType = 0;
	}
	
	public Bullet_Component(int bulletType) {
		this.weaponType = bulletType;
	}
}
