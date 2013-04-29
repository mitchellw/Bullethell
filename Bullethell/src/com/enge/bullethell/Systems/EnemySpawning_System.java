package com.enge.bullethell.Systems;

import java.util.Random;

import com.artemis.systems.VoidEntitySystem;
import com.enge.bullethell.Vector2;
import com.enge.bullethell.Entities.ShipFactory_Entity;

/**
 * System that handles the spawning of enemies in the World.
 * @author Wilson, Sam, Tyler
 * @version 29.04.2013
 */
public class EnemySpawning_System extends VoidEntitySystem {
	private long lastSpawned;
	private Random random;
	
	/**
	 * Constructor for the EnemySpawning_System class.
	 */
	public EnemySpawning_System() {
		random = new Random();
	}

	/**
	 * Spawns an enemy with a random position every 200 milliseconds. 
	 */
	@Override
	protected void processSystem() {
		if (System.currentTimeMillis() - lastSpawned > 200) {
			lastSpawned = System.currentTimeMillis();
			ShipFactory_Entity.createEnemy1(world, new Vector2(10 + random.nextInt(460), 800), new Vector2(0, -1), 0, new Vector2(10 + random.nextInt(460), -50));
		}
	}
}
