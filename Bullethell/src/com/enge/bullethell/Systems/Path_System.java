package com.enge.bullethell.Systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.enge.bullethell.Bullethell;
import com.enge.bullethell.Vector2;
import com.enge.bullethell.Components.Destination_Component;
import com.enge.bullethell.Components.Position_Component;
import com.enge.bullethell.Components.Velocity_Component;
import com.enge.bullethell.Entities.ShipFactory_Entity;

public class Path_System extends EntityProcessingSystem {
	@Mapper ComponentMapper<Destination_Component> destM;
	@Mapper ComponentMapper<Position_Component> posM;
	@Mapper ComponentMapper<Velocity_Component> velM;

	public Path_System() {
		super(Aspect.getAspectForAll(Destination_Component.class, Position_Component.class, Velocity_Component.class));
	}

	@Override
	protected void process(Entity entity) {
		Vector2 destinationPosition = destM.get(entity).destination;
		if (destinationPosition == null) {
			return;
		}
		
		Vector2 shipPosition = posM.get(entity).position;
		Vector2 velocity = destinationPosition.sub(shipPosition).unit().mul(ShipFactory_Entity.PLAYER_VELOCITY);
		
		if (destinationPosition.sub(velocity).len() > ShipFactory_Entity.PLAYER_VELOCITY) {
		    velM.get(entity).velocity = velocity;
		}
		else {
		    velM.get(entity).velocity = Vector2.Zero;
		    posM.get(entity).position = destinationPosition;
		    destM.get(entity).destination = null;
		}
	}

}
