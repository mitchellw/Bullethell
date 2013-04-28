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
import com.enge.bullethell.Systems.InputSystem;
import com.enge.bullethell.Systems.Movement_System;
import com.enge.bullethell.Systems.Path_System;
import com.enge.bullethell.Systems.Render_System;

public class Bullethell implements ApplicationListener {
	private OrthographicCamera camera;
	private World world;
	private Render_System renderSystem;
	public static int score = 0;
	public static Entity player;
	public static Entity splashScreen;
	public static Entity scoreEntity;
	public static GameState gameState;
	private Sound collision;
	private Sound death;
	private Sound enemyCollision;
	private Sound explosion;
	private Sound fire;
	private Music bgmusic;

	@Override
	public void create() {
		gameState = GameState.START;
		world = new World();

		fire = Gdx.audio.newSound(Gdx.files.internal("audio/lasergun_fire.wav"));
		collision = Gdx.audio.newSound(Gdx.files.internal("audio/Collision8-Bit.ogg"));
		death = Gdx.audio.newSound(Gdx.files.internal("audio/Death.ogg"));
		enemyCollision = Gdx.audio.newSound(Gdx.files.internal("audio/enemycollision.ogg"));
		explosion = Gdx.audio.newSound(Gdx.files.internal("audio/MediumExplosion8-Bit.ogg"));
		fire = Gdx.audio.newSound(Gdx.files.internal("audio/lasergun_fire.wav"));
		bgmusic = Gdx.audio.newMusic(Gdx.files.internal("audio/POL-tekno-labs-short.mp3"));
		bgmusic.setVolume(0.75f);
		bgmusic.setLooping(true);
		bgmusic.play();


		camera = new OrthographicCamera();
		renderSystem = new Render_System(camera);
		world.setSystem(renderSystem);
		world.setSystem(new CollisionDetection_System(collision,
				death, enemyCollision, explosion));
		world.setSystem(new Movement_System());
		world.setSystem(new PlayerFire_System(fire));
		world.setSystem(new Path_System());

		splashScreen = world.createEntity().addComponent(new Position_Component(new Vector2(240, 400))).addComponent(new Sprite_Component("splash"));
		splashScreen.addToWorld();
		scoreEntity = world.createEntity().addComponent(new Position_Component(new Vector2(20, 780))).addComponent(new Font_Component(null));
		

		world.initialize();
		Gdx.input.setInputProcessor(new InputSystem(world, camera));
	}

	@Override
	public void dispose() {
		collision.dispose();
		death.dispose();
		enemyCollision.dispose();
		explosion.dispose();
		fire.dispose();
		bgmusic.dispose();
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
		//collision.dispose();
		//death.dispose();
		//enemyCollision.dispose();
		//explosion.dispose();
		//fire.dispose();
	}

	@Override
	public void resume() {
		//fire = Gdx.audio.newSound(Gdx.files.internal("audio/lasergun_fire.wav"));
		//collision = Gdx.audio.newSound(Gdx.files.internal("audio/Collision8-Bit.ogg"));
		//death = Gdx.audio.newSound(Gdx.files.internal("audio/Death.ogg"));
		//enemyCollision = Gdx.audio.newSound(Gdx.files.internal("audio/enemycollision.ogg"));
		//explosion = Gdx.audio.newSound(Gdx.files.internal("audio/MediumExplosion8-Bit.ogg"));
	}
}
