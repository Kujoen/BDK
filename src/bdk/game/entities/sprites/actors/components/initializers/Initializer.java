package bdk.game.entities.sprites.actors.components.initializers;

import java.util.ArrayList;

import bdk.game.entities.sprites.actors.Actor;
import bdk.game.entities.sprites.actors.ActorSprite;
import bdk.game.entities.sprites.actors.components.Component;

/**
 * 
 * @author Andreas Farley
 *
 */
public abstract class Initializer extends Component{
	
	public Initializer(Actor parentActor) {
		super(parentActor);
	}
	public abstract void initializeActorSprites(ArrayList<ActorSprite> actorSpriteList);
}
