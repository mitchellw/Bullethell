package com.enge.bullethell.Components;

import com.artemis.Component;

public class Health_Component extends Component {
	public int health;
	
	public Health_Component() {
		this.health = 1;
	}
	
	public Health_Component(int health) {
		this.health = health;
	}
}
