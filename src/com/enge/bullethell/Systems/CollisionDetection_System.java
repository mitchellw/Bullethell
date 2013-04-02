package com.enge.bullethell.Systems;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;

//for health and collisions
//components: health, hitbox, owner, position
public class CollisionDetection_System extends EntityProcessingSystem {

    public CollisionDetection_System(Aspect aspect) {
        super(aspect);
    }

    @Override
    protected void process(Entity entity) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
