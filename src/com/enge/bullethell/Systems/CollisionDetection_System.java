package com.enge.bullethell.Systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.enge.bullethell.Vector2;
import com.enge.bullethell.Components.Hitbox_Component;
import com.enge.bullethell.Components.Position_Component;

//for health and collisions
//components: health, hitbox, owner, position
public class CollisionDetection_System extends EntityProcessingSystem {

	@Mapper ComponentMapper<Position_Component> positionM;
	@Mapper ComponentMapper<Hitbox_Component> hitboxM;
    public CollisionDetection_System(Aspect aspect) {
        super(aspect);
    }

    // I'm not sure how this method behaves for entities that have extremely different sizes. 
    // I imagine it will work though
    
    public boolean isIntersecting(Entity entity1, Entity entity2)
    {
    	int width1 = hitboxM.get(entity1).width;
    	int height1 = hitboxM.get(entity1).height;
    	int width2 = hitboxM.get(entity2).width;
    	int height2 = hitboxM.get(entity2).height;
    	
    	Vector2 topLeft1 = positionM.get(entity1).position.sub(width1 / 2, height1 / 2);
    	Vector2 topLeft2 = positionM.get(entity2).position.sub(width2 / 2, height2 / 2);
    	Vector2 topRight1 = topLeft1.add(width1, 0);
    	Vector2 topRight2 = topLeft2.add(width2, 0);
    	Vector2 botRight1 = topRight1.add(0, height1);
    	Vector2 botRight2 = topRight2.add(0, height2);
    	Vector2 botLeft1 = topLeft1.add(0, height1);
    	Vector2 botLeft2 = topLeft2.add(0, height2);
    	
    	
    	
    	if (topLeft1.x < botRight2.x && topLeft1.y < botRight2.y)
    	{
    		return true;
    	}
    	if (botRight1.x > topLeft2.x && botRight1.y > topLeft2.y)
    	{
    		return true;
    	}
    	if (topRight1.x > botLeft2.x && topRight1.y < botLeft2.y)
    	{
    		return true;
    	}
    	if (botLeft1.x < topRight2.x && botLeft1.y > topRight2.y)
    	{
    		return true;
    	}
    	
    	return false;
    }
    
    @Override
    protected void process(Entity entity) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
