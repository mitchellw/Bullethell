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
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.enge.bullethell.Vector2;
import com.enge.bullethell.Components.Position_Component;
import com.enge.bullethell.Components.Sprite_Component;

/**
 * This is the rendering system; it takes care of actually drawing on the application.
 * @author Wilson
 * @version 10.04.2013
 */
public class Render_System extends EntityProcessingSystem
{
	@Mapper ComponentMapper<Position_Component> positionM;
	@Mapper ComponentMapper<Sprite_Component> spriteM;
	
	private SpriteBatch batch;
	private TextureAtlas atlas;
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
    	assetManager.load("sprites.atlas", TextureAtlas.class);
    }
    
    @Override
    protected void end() {
    	batch.end();
    }
    
    @Override
    protected void begin() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        
        if (atlas == null) {
        	atlas = assetManager.get("sprites.atlas", TextureAtlas.class);
        }
    	
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
    }

    @Override
    protected void process(Entity entity) {
        // TODO: mul position by density.
    	Vector2 position = positionM.get(entity).position;
    	drawSprite(spriteM.get(entity), position);
    }
    
    private void drawSprite(Sprite_Component sprite, Vector2 position) {
    	String name = sprite.fileName;
    	TextureAtlas.AtlasRegion region = atlas.findRegion(name);
    	if (region == null) {
    		throw new RuntimeException("Couldn't find " + name);
    	}
    	
    	batch.draw(region, position.x - region.getRegionWidth() / 2,
    			position.y - region.getRegionHeight() / 2);
    }
    
    @Override
    protected boolean checkProcessing() {
    	return assetManager.update();
    }
    
    public void resize(int width, int height) {
    	// TODO: Multiply by density
        int gameWidth = (int) (800);
        int gameHeight = (int) (480);

        if (gameHeight * width < gameWidth * height) {
            float adjustedHeight = gameWidth * (height/(float) width);
            camera.setToOrtho(false, gameWidth, adjustedHeight);
            camera.translate(0, -Math.abs((gameHeight - adjustedHeight) / 2));
        } else {
            float adjustedWidth = gameHeight * (width/(float) height);
            camera.setToOrtho(false, adjustedWidth, gameHeight);
            camera.translate(-Math.abs((gameWidth - adjustedWidth)/2),0);
        }
    }
}
