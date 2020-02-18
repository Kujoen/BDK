package bdk.game.entities.sprites.actors.components.emitter;

import java.util.List;

import bdk.game.entities.sprites.Sprite;
import bdk.game.entities.sprites.actors.Actor;
import bdk.game.entities.sprites.actors.components.Component;
import soliture.ui.swingextensions.expandinglist.JExpandableRow;

/**
 * 
 * @author Andreas Farley
 *
 */
public abstract class Emitter extends Component {

	public Emitter(Actor parentActor) {
		super(parentActor);
	}

	public abstract void emit();
}
