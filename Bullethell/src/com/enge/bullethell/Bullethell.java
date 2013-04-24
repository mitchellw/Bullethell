package com.enge.bullethell;

import com.enge.bullethell.Systems.PlayerFire_System;
import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.enge.bullethell.Entities.ShipFactory_Entity;
import com.enge.bullethell.Systems.CollisionDetection_System;
import com.enge.bullethell.Systems.InputSystem;
import com.enge.bullethell.Systems.Movement_System;
import com.enge.bullethell.Systems.Render_System;

public class Bullethell implements ApplicationListener {
	private OrthographicCamera camera;
	private World world;
	private Render_System renderSystem;
	public static int score = 0;
	public static Entity player;
	public static Sound collision;
	public static Sound death;
	public static Sound enemyCollision;
	public static Sound explosion;
	public static Sound fire;
	
	@Override
	public void create() {
		world = new World();
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		collision = Gdx.audio.newSound(Gdx.files.internal("audio/Collision8-Bit.ogg"));
		death = Gdx.audio.newSound(Gdx.files.internal("audio/Death.ogg"));
		enemyCollision = Gdx.audio.newSound(Gdx.files.internal("audio/enemycollision.ogg"));
		explosion = Gdx.audio.newSound(Gdx.files.internal("audio/MediumExplosion8-Bit.ogg"));
		fire = Gdx.audio.newSound(Gdx.files.internal("audio/lasergun_fire.wav"));

		camera = new OrthographicCamera();
		renderSystem = new Render_System(camera);
		world.setSystem(renderSystem);
		world.setSystem(new CollisionDetection_System(collision, 
				death, enemyCollision, explosion));
		world.setSystem(new Movement_System());
		world.setSystem(new PlayerFire_System(fire));

		player = ShipFactory_Entity.createPlayer(world, new Vector2(0, 0), 0);
		ShipFactory_Entity.createEnemy1(world, new Vector2(100, 600), new Vector2(0, -1), 0);
		ShipFactory_Entity.createEnemy1(world, new Vector2(250, 800), new Vector2(0, -1), 0);
		ShipFactory_Entity.createEnemy1(world, new Vector2(400, 700), new Vector2(0, -1), 0);

		world.initialize();
		Gdx.input.setInputProcessor(new InputSystem(world, camera));
	}

	@Override
	public void dispose() {
		//Clean up
	}

	@Override
	public void render() {
		world.setDelta(Gdx.graphics.getDeltaTime());
		world.process();
	}

	@Override
	public void resize(int width, int height) {
		renderSystem.resize(width, height);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
