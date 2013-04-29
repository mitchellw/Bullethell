package com.enge.bullethell;

import com.enge.bullethell.Components.Font_Component;
import com.enge.bullethell.Components.Position_Component;
import com.enge.bullethell.Components.Sprite_Component;
import com.enge.bullethell.Systems.PlayerFire_System;
import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.enge.bullethell.Entities.ShipFactory_Entity;
import com.enge.bullethell.Systems.CollisionDetection_System;
import com.enge.bullethell.Systems.EnemySpawning_System;
import com.enge.bullethell.Systems.InputSystem;
import com.enge.bullethell.Systems.Movement_System;
import com.enge.bullethell.Systems.Path_System;
import com.enge.bullethell.Systems.Render_System;

/**
 * This is our "main" class.  It initializes our world, sprites, audio, and everything
 * that we need for the game to run.
 * @author All Members
 * @version 29.04.2013
 */
public class Bullethell implements ApplicationListener {
	private OrthographicCamera camera;
	private World world;
	private Render_System renderSystem;
	
	//Initial score.
	public static int score = 0;
	
	//Entities the game will use
	public static Entity player;
	public static Entity splashScreen;
	public static Entity scoreEntity;
	
	//State of the game.
	public static GameState gameState;
	
	//Spawning system for enemies.
	public static EnemySpawning_System spawnSystem;
	
	//Sounds and music played at appropriate times during the game.
	private Sound collision;
	private Sound death;
	private Sound enemyCollision;
	private Sound explosion;
	private Sound fire;
	private Music bgmusic;

	@Override
	/**
	 * This method initializes everything vital to getting the game working.
	 */
	public void create() {
		//Starts everything.
		gameState = GameState.START;

		//Audio is initialized and set to the proper volume level.
		fire = Gdx.audio.newSound(Gdx.files.internal("audio/lasergun_fire.wav"));
		collision = Gdx.audio.newSound(Gdx.files.internal("audio/Collision8-Bit.ogg"));
		death = Gdx.audio.newSound(Gdx.files.internal("audio/Death.ogg"));
		enemyCollision = Gdx.audio.newSound(Gdx.files.internal("audio/enemycollision.ogg"));
		explosion = Gdx.audio.newSound(Gdx.files.internal("audio/MediumExplosion8-Bit.ogg"));
		fire = Gdx.audio.newSound(Gdx.files.internal("audio/lasergun_fire.wav"));
		bgmusic = Gdx.audio.newMusic(Gdx.files.internal("audio/POL-tekno-labs-short.mp3"));
		bgmusic.setVolume(0.75f);
		
		//Loops and plays the background music.
		bgmusic.setLooping(true);
		bgmusic.play();

		resetWorld();
	}

	@Override
	/**
	 * This method disposes all audio in the game.  Called when the game ends.
	 */
	public void dispose() {
		collision.dispose();
		death.dispose();
		enemyCollision.dispose();
		explosion.dispose();
		fire.dispose();
		bgmusic.dispose();
	}

	@Override
	/**
	 * Renders everything in the world.
	 */
	public void render() {
		world.setDelta(Gdx.graphics.getDeltaTime());
		world.process();
	}

	@Override
	/**
	 * Resizes everything to the dimensions of the current screen.
	 */
	public void resize(int width, int height) {
		renderSystem.resize(width, height);
	}

	@Override
	/**
	 * Pauses.  (unused)
	 */
	public void pause() {
		//collision.dispose();
		//death.dispose();
		//enemyCollision.dispose();
		//explosion.dispose();
		//fire.dispose();
	}

	@Override
	/**
	 * Resumes.  (unused)
	 */
	public void resume() {
		//fire = Gdx.audio.newSound(Gdx.files.internal("audio/lasergun_fire.wav"));
		//collision = Gdx.audio.newSound(Gdx.files.internal("audio/Collision8-Bit.ogg"));
		//death = Gdx.audio.newSound(Gdx.files.internal("audio/Death.ogg"));
		//enemyCollision = Gdx.audio.newSound(Gdx.files.internal("audio/enemycollision.ogg"));
		//explosion = Gdx.audio.newSound(Gdx.files.internal("audio/MediumExplosion8-Bit.ogg"));
	}
	
	public void resetWorld() {
		world = new World();
		player = ShipFactory_Entity.createPlayer(world, new Vector2(0, 0), 0);
		player.disable();
		
		camera = new OrthographicCamera();
		renderSystem = new Render_System(camera);
		world.setSystem(renderSystem);
		world.setSystem(new CollisionDetection_System(collision,
				death, enemyCollision, explosion));
		world.setSystem(new Movement_System());
		world.setSystem(new PlayerFire_System(fire));
		world.setSystem(new Path_System());
		spawnSystem = new EnemySpawning_System();

		splashScreen = world.createEntity().addComponent(new Position_Component(new Vector2(240, 400))).addComponent(new Sprite_Component("splash"));
		splashScreen.addToWorld();

		score = 0;
		scoreEntity = world.createEntity().addComponent(new Position_Component(new Vector2(20, 780))).addComponent(new Font_Component(null));
		
		world.initialize();
		Gdx.input.setInputProcessor(new InputSystem(world, camera));
	}
}
