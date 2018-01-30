package bdk.game.entities;

import bdk.game.component.Level;

public class EntityLink {

	private Level level;
	private Entity entity;
	
	private int tickToSpawn;

	/**
	 * The EntityLink is used to link an entity to a level. The name of the
	 * actor and actorcollection of the selected entity are saved in this class,
	 * aswell as when it should spawn and on what tile. At level start all used
	 * actors are precached in a list. When its time to spawn, the EntityLink
	 * will grab a copy of the entity its linked to the level and add it to the
	 * levels entitybuffer. An EntityController only holds information for a single
	 * entity spawn.
	 * 
	 * @param level
	 * 	The level wich will recieve the entity
	 * @param entity
	 * 	The entity to link, note that the entity itself will not be saved in the link
	 */
	public EntityLink(Level level, Entity entity) {

	}

}
