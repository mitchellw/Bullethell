package com.enge.bullethell.Systems;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;

//components: sprite, position, owner (?)
public class Render_System extends EntityProcessingSystem{

    public Render_System(Aspect aspect) {
        super(aspect);
    }

    @Override
    protected void process(Entity entity) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
