package bdk.game.entities.sprites.actors.components.emitter;

import bdk.game.entities.sprites.actors.Actor;
import bdk.game.entities.sprites.actors.components.Component;
import soliture.ui.swingextensions.expandinglist.ExpandableRow;

/**
 * 
 * @author Andreas Farley
 *
 */
public abstract class Emitter extends Component {
	public Emitter(Actor parentActor) {
		super(parentActor);
	}
}
