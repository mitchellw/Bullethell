package com.enge.bullethell.Systems;

import java.util.Random;

import com.artemis.systems.VoidEntitySystem;
import com.enge.bullethell.Vector2;
import com.enge.bullethell.Entities.ShipFactory_Entity;

public class EnemySpawning_System extends VoidEntitySystem {
	private long lastSpawned;
	private Random random;
	
	public EnemySpawning_System() {
		random = new Random();
	}

	@Override
	protected void processSystem() {
		if (System.currentTimeMillis() - lastSpawned > 200) {
			lastSpawned = System.currentTimeMillis();
			ShipFactory_Entity.createEnemy1(world, new Vector2(10 + random.nextInt(460), 800), new Vector2(0, -1), 0, new Vector2(10 + random.nextInt(460), -50));
		}
	}
}
