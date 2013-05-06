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
	private long lastIntervalDecreased;
	private long spawnInterval;
	private Random random;

	/**
	 * Constructor for the EnemySpawning_System class.
	 */
	public EnemySpawning_System() {
		random = new Random();
		lastSpawned = System.currentTimeMillis();
		lastIntervalDecreased = lastSpawned;
		spawnInterval = 250;
	}

	public void reset() {
	    spawnInterval = 250;
	}

	/**
	 * Spawns an enemy with a random position every 200 milliseconds.
	 */
	@Override
	protected void processSystem() {
		if (System.currentTimeMillis() - lastSpawned > spawnInterval) {
			lastSpawned = System.currentTimeMillis();
			ShipFactory_Entity.createEnemy1(world, new Vector2(10 + random.nextInt(460), 800), new Vector2(0, -1), 0, new Vector2(10 + random.nextInt(460), -50));

			if (System.currentTimeMillis() - lastIntervalDecreased > 5 * spawnInterval) {
			    spawnInterval = Math.max(spawnInterval - 2, 125);
			    lastIntervalDecreased = System.currentTimeMillis();
			}
		}
	}
}
