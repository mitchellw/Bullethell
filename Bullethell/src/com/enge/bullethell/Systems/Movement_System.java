package com.enge.bullethell.Systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.enge.bullethell.Vector2;
import com.enge.bullethell.Components.Position_Component;
import com.enge.bullethell.Components.Velocity_Component;

//components: position, velocity
public class Movement_System extends EntityProcessingSystem{
	
	@Mapper ComponentMapper<Position_Component> positionM;
	@Mapper ComponentMapper<Velocity_Component> velocityM;
	
	

    @SuppressWarnings("unchecked")
	public Movement_System(Aspect aspect) {
        super(Aspect.getAspectForAll(Position_Component.class, Velocity_Component.class));
    }
    
    public void updatePosition(Entity entity)
    {
        Vector2 position = positionM.get(entity).position;
        Vector2 velocity = velocityM.get(entity).velocity;
        		
        position = position.add(velocity);
        positionM.get(entity).position = position;
    }

    
    @Override
    protected void process(Entity entity) {
        //To change body of implemented methods use File | Settings | File Templates.
    	updatePosition(entity);
    }
}
