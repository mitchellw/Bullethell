package com.enge.bullethell.Systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.enge.bullethell.Vector2;
import com.enge.bullethell.Components.Position_Component;
import com.enge.bullethell.Components.Sprite_Component;

//components: sprite, position, owner (?)
public class Render_System extends EntityProcessingSystem{
	@Mapper ComponentMapper<Position_Component> positionM;
	@Mapper ComponentMapper<Sprite_Component> spriteM;
	
	private SpriteBatch batch;
	private AssetManager assetManager;
	private OrthographicCamera camera;

    public Render_System(OrthographicCamera camera) {
        super(Aspect.getAspectForAll(Position_Component.class, Sprite_Component.class));

        batch = new SpriteBatch();
        Gdx.gl.glClearColor(0, 0, 0, 1);

        assetManager = new AssetManager();

        this.camera = camera;
    }
    
    @Override
    protected void initialize() {
    	// TODO: make actual atlas
    	assetManager.load("enemy.png", Sprite.class);
    }
    
    @Override
    protected void begin() {
    	// TODO: Check that atlas is loaded
    	
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
    }

    @Override
    protected void process(Entity entity) {
        // TODO: mul position by density.
    	Vector2 position = positionM.get(entity).position;
    	
    	assetManager.get("enemy.png");
    }
    
    private void drawSprite(String fileName, Vector2 position) {
    	
    }
}
