package com.enge.bullethell.Systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.enge.bullethell.Vector2;
import com.enge.bullethell.Components.Position_Component;
import com.enge.bullethell.Components.Sprite_Component;

//components: sprite, position, owner (?)
public class Render_System extends EntityProcessingSystem{
	@Mapper ComponentMapper<Position_Component> positionM;
	@Mapper ComponentMapper<Sprite_Component> spriteM;

    public Render_System(Aspect aspect) {
        super(Aspect.getAspectForAll(Position_Component.class, Sprite_Component.class));
    }

    @Override
    protected void process(Entity entity) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
    
    private void drawSprite(String fileName, Vector2 position) {
    	
    }
}
