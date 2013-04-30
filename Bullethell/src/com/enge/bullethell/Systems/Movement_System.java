package com.enge.bullethell.Systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.enge.bullethell.Bullethell;
import com.enge.bullethell.GameState;
import com.enge.bullethell.Owner;
import com.enge.bullethell.Vector2;
import com.enge.bullethell.Components.Owner_Component;
import com.enge.bullethell.Components.Position_Component;
import com.enge.bullethell.Components.Velocity_Component;

/**
 * This system takes the position and velocity of an entity, and finds
 * the new position.
 * @author Tyler, Wilson
 * @version 2013.04.05
 *
 */

//components: position, velocity
public class Movement_System extends EntityProcessingSystem {
	
	@Mapper ComponentMapper<Position_Component> positionM;
	@Mapper ComponentMapper<Velocity_Component> velocityM;
	@Mapper ComponentMapper<Owner_Component> ownerM;
	
	
	/**
	 * Constructor for the Movement_System class.
	 */
    @SuppressWarnings("unchecked")
	public Movement_System()
    {
        super(Aspect.getAspectForAll(Position_Component.class, Velocity_Component.class, Owner_Component.class));
    }
    
    /**
     * Gets the entity's position and velocity and updates those components.
     * @param entity The entity to update position for 
     */
    public void updatePosition(Entity entity)
    {
        Vector2 position = positionM.get(entity).position;
        Vector2 velocity = velocityM.get(entity).velocity;

        
        positionM.get(entity).position = position.add(velocity);
    }

    
    /**
     * Updates the position of the entity (moving the entity).
     * @param entity The entity to move
     */
    @Override
    protected void process(Entity entity) {
    	if (Bullethell.gameState == GameState.PLAYING) {
    		//To change body of implemented methods use File | Settings | File Templates.
    		updatePosition(entity);


    		Vector2 position = positionM.get(entity).position;
    		//Entity is removed if it goes outside the boundaries of the World.
    		if (position.y > 830 || position.x < -30 || position.x > 510 || position.y < -30) {
    			Owner owner = ownerM.get(entity).owner;
    			if (owner == Owner.COMPUTER)
    			{
    				entity.deleteFromWorld();
    			}
    			else
    			{
    				Vector2 newPos = Vector2.Zero;
    				if (position.y > 830)
    				{
    					positionM.get(entity).position = newPos.add(position.x, 830);
    				}
    				if (position.x < 0)
    				{
    					positionM.get(entity).position = newPos.add(0, position.y);
    				}
    				if (position.x > 495)
    				{
    					positionM.get(entity).position = newPos.add(495, position.y);
    				}
    				if (position.y < -30)
    				{
    					positionM.get(entity).position = newPos.add(position.x, -30);
    				}
    			}
    		}
    	}
    	else {
    		entity.deleteFromWorld();
    	}
    }
}
