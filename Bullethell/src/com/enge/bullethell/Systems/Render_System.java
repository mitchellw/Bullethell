package com.enge.bullethell.Systems;

import java.util.ArrayList;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;

//components: sprite, position, owner (?)
public class Render_System extends EntityProcessingSystem
{
	private ArrayList<Entity> entities;
	
    public Render_System(Aspect aspect) {
        super(aspect);
    }

    @Override
    protected void process(Entity entity) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
    
    @Override
    protected void inserted(Entity entity) {
    	entities.add(entity);
    }
    
    @Override
    protected void removed(Entity entity) {
    	entities.remove(entity);
    }
}
