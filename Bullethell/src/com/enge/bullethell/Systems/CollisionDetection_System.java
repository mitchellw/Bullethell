package com.enge.bullethell.Systems;

import java.util.ArrayList;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.World;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.enge.bullethell.Bullethell;
import com.enge.bullethell.Vector2;
import com.enge.bullethell.Components.Bullet_Component;
import com.enge.bullethell.Components.Health_Component;
import com.enge.bullethell.Components.Hitbox_Component;
import com.enge.bullethell.Components.Owner_Component;
import com.enge.bullethell.Components.Position_Component;
import com.enge.bullethell.Components.Score_Component;

/**
 * System that determines whether two entities are intersecting and 
 * reacts appropriately.
 * @version 09.04.2013
 */
public class CollisionDetection_System extends EntityProcessingSystem {

	private ArrayList<Entity> entities;
	
	@Mapper ComponentMapper<Position_Component> positionM;
	@Mapper ComponentMapper<Hitbox_Component> hitboxM;
	@Mapper ComponentMapper<Bullet_Component> bulletM;
	@Mapper ComponentMapper<Owner_Component> ownerM;
	@Mapper ComponentMapper<Health_Component> healthM;
	@Mapper ComponentMapper<Score_Component> scoreM;
	private World world;
	
	/**
	 * Constructor for the class.
	 * @param aspect aspect
	 */
    @SuppressWarnings("unchecked")
	public CollisionDetection_System(World world) {
        super(Aspect.getAspectForAll(Position_Component.class,
        		Hitbox_Component.class, Owner_Component.class));
        		//.getAspectForOne(Health_Component.class, Score_Component.class));
        this.world = world;
        entities = new ArrayList<Entity>();
    }

    /**
     * Compares two entities' hitboxes and determines if they are intersecting.
     * @param entity1 The first entity to compare.
     * @param entity2 The second entity to compare.
     * @return Whether or not ehe entities are intersecting.
     */
    public boolean isIntersecting(Entity entity1, Entity entity2)
    {
    	int width1 = hitboxM.get(entity1).width;
    	int height1 = hitboxM.get(entity1).height;
    	int width2 = hitboxM.get(entity2).width;
    	int height2 = hitboxM.get(entity2).height;
    	
    	Vector2 topLeft1 = positionM.get(entity1).position.add(-width1 / 2, height1 / 2);
    	Vector2 topLeft2 = positionM.get(entity2).position.add(-width2 / 2, height2 / 2);
    	Vector2 topRight1 = topLeft1.add(width1, 0);
    	Vector2 topRight2 = topLeft2.add(width2, 0);
    	Vector2 botLeft1 = topLeft1.sub(0, height1);
    	Vector2 botLeft2 = topLeft2.sub(0, height2);
    	
    	if (topLeft1.x < topRight2.x && topRight1.x > topLeft2.x &&
    		botLeft1.y < topLeft2.y && topLeft1.y > botLeft2.y) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * Processes a given entity versus every other entity in the list.
     * @param entity The entity to compare to the others.
     */
    @Override
    protected void process(Entity entity) {
        for (int i = 0; i < entities.size(); i++)
        {
        	if (entity != entities.get(i) && isIntersecting(entities.get(i), entity))
        	{
        		System.out.println("In");
        		//If both are bullets, let them pass
        		if (!scoreM.has(entity) && !scoreM.has(entities.get(i)))
        		{
        			//Do nothing it's fine
        		}
        		
        		//If one is a bullet and the other is not, cases
        		else if (scoreM.has(entity) && !scoreM.has(entities.get(i)))
        		{
        			//If the ship is enemy and the bullet is enemy, do nothing
        			if (ownerM.get(entity).owner == 0 &&
        					ownerM.get(entities.get(i)).owner == 0)
        			{
        				//Do nothing; enemy bullets should not affect enemies
        			}
        			
        			//If the ship is enemy and the bullet is player, decrement enemy health
        			else if (ownerM.get(entity).owner == 0 &&
        					ownerM.get(entities.get(i)).owner == 1)
        			{
        				healthM.get(entity).health -= 5;
        				if (healthM.get(entity).health <= 0)
        				{
        					Bullethell.score += scoreM.get(entity).score;
        					world.deleteEntity(entity);
        					entities.remove(entity);
        				}
        				world.deleteEntity(entities.get(i));
        				entities.remove(i);
        			}
        			
        			//If the ship is player and the bullet is enemy, decrement player health
        			else
        			{
        				//Decrement by 5?  Why not
        				healthM.get(entity).health -= 5;
        				if (healthM.get(entity).health <= 0)
        				{
        					//End the game
        					world.deleteEntity(entity);
        					entities.remove(entity);
        					break;
        				}
        				world.deleteEntity(entities.get(i));
        				entities.remove(i);
        			}
        		}
        		
        		//Now reverse it
        		else if (!scoreM.has(entity) && scoreM.has(entities.get(i)))
        		{
        			//If the bullet is enemy and the ship is enemy, do nothing
        			if (ownerM.get(entity).owner == 0 &&
        					ownerM.get(entities.get(i)).owner == 0)
        			{
        				//Do nothing; enemy bullets should not affect enemies
        			}
        			
        			//If the bullet is enemy and the ship is friendly
        			else if (ownerM.get(entity).owner == 0 &&
        					ownerM.get(entities.get(i)).owner == 1)
        			{
        				healthM.get(entities.get(i)).health -= 5;
        				if (healthM.get(entities.get(i)).health <= 0)
        				{
        					//End the game
        					world.deleteEntity(entities.get(i));
        					entities.remove(i);
        					break;
        				}
        				world.deleteEntity(entity);
        				entities.remove(entity);
        			}
        			
        			//If the bullet is player and the ship is enemy, decrement
        			else
        			{
        				//Decrement by 5?  Why not
        				healthM.get(entities.get(i)).health -= 5;
        				if (healthM.get(entities.get(i)).health <= 0)
        				{
        					Bullethell.score += scoreM.get(entities.get(i)).score;
        					world.deleteEntity(entities.get(i));
        					entities.remove(entities.get(i));
        				}
        				world.deleteEntity(entity);
        				entities.remove(entity);
        			}
        		}
        		
        		//If both are ships
        		else if (scoreM.has(entity) && scoreM.has(entities.get(i)))
        		{
        			if (ownerM.get(entity).owner == 1 && ownerM.get(entities.get(i)).owner 
        					== 1)
        			{
        				//Do nothing; we don't care if enemy ships interact
        			}
        			else if (ownerM.get(entity).owner == 1 && ownerM.get(entities.get(i)).owner 
        					== 0)
        			{
        				healthM.get(entity).health -= 1;
        				if (healthM.get(entity).health <= 0)
        				{
        					Bullethell.score += scoreM.get(entity).score;
        					world.deleteEntity(entity);
        					entities.remove(entity);
        				}
        				healthM.get(entities.get(i)).health -= 1;
        				if (healthM.get(entities.get(i)).health <= 0)
        				{
        					//End the game?
        					world.deleteEntity(entities.get(i));
        					entities.remove(entities.get(i));
        					break;
        				}
        			}
        			else if (ownerM.get(entity).owner == 0 && ownerM.get(entities.get(i)).owner 
        					== 1)
        			{
        				healthM.get(entity).health -= 1;
        				if (healthM.get(entity).health <= 0)
        				{
        					//End the game?
        					world.deleteEntity(entity);
        					entities.remove(entity);
        					break;
        				}
        				healthM.get(entities.get(i)).health -= 1;
        				if (healthM.get(entities.get(i)).health <= 0)
        				{
        					//End the game?
        					Bullethell.score += scoreM.get(entities.get(i)).score;
        					world.deleteEntity(entities.get(i));
        					entities.remove(entities.get(i));
        				}
        			}
        		}
        	}
        }
    }
    
    /**
     * Inserts an entity into the Array List.
     * @param entity The entity to insert.
     */
    @Override
    protected void inserted(Entity entity) {
    	entities.add(entity);
    }
    
    /**
     * Removes an entity from the list.
     * @param entity The entity to remove.
     */
    @Override
    protected void removed(Entity entity) {
    	entities.remove(entity);
    }
}
