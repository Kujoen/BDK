package bdk.game.entities.sprites.actors.components.initializers;

import bdk.game.entities.sprites.actors.Actor;
import bdk.game.entities.sprites.actors.components.Component;

/**
 * 
 * @author Andreas Farley
 *
 */
public abstract class Initializer extends Component{
	
	public abstract void initializeActor(Actor a);

}
