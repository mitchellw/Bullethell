package com.enge.bullethell.Systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.enge.bullethell.Vector2;
import com.enge.bullethell.Components.Destination_Component;
import com.enge.bullethell.Components.Position_Component;
import com.enge.bullethell.Components.Score_Component;
import com.enge.bullethell.Components.Velocity_Component;
import com.enge.bullethell.Entities.ShipFactory_Entity;

public class Path_System extends EntityProcessingSystem {
	@Mapper ComponentMapper<Destination_Component> destM;
	@Mapper ComponentMapper<Position_Component> posM;
	@Mapper ComponentMapper<Velocity_Component> velM;
	@Mapper ComponentMapper<Score_Component> scoreM;

	@SuppressWarnings("unchecked")
	public Path_System() {
		super(Aspect.getAspectForAll(Destination_Component.class, Position_Component.class,
				Velocity_Component.class, Score_Component.class));
	}

	@Override
	protected void process(Entity entity) {
		Vector2 destinationPosition = destM.get(entity).destination;
		if (destinationPosition == null) {
			return;
		}
		
		Vector2 shipPosition = posM.get(entity).position;
		float linearVelocity;
		Vector2 velocity;
		if (scoreM.get(entity).score == -1) {
			linearVelocity = ShipFactory_Entity.PLAYER_VELOCITY;
		}
		else {
			linearVelocity = ShipFactory_Entity.ENEMY1_VELOCITY;
		}
		velocity = destinationPosition.sub(shipPosition).unit().mul(linearVelocity);
		
		if (destinationPosition.sub(velocity).len() > linearVelocity) {
		    velM.get(entity).velocity = velocity;
		}
		else {
		    velM.get(entity).velocity = Vector2.Zero;
		    posM.get(entity).position = destinationPosition;
		    destM.get(entity).destination = null;
		}
	}

}
