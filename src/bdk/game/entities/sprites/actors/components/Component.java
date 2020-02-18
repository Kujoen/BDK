package bdk.game.entities.sprites.actors.components;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bdk.editor.actor.BDKActorEditor;
import bdk.game.entities.sprites.actors.Actor;
import bdk.game.entities.sprites.actors.components.emitter.EmitContinously;
import bdk.game.entities.sprites.actors.components.emitter.EmitOnce;
import soliture.ui.swingextensions.expandinglist.JExpandableRow;

/**
 * 
 * @author Andreas Farley
 *
 */
public abstract class Component implements Serializable{

	protected Actor parentActor;
	
	public Component(Actor parentActor) {
		this.parentActor = parentActor;
	}
	
	public abstract JExpandableRow getComponentRow(BDKActorEditor bdkActorEditor);

	public String toString() {
		return this.getClass().getSimpleName();
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------------|
	// Static methods
	// --------------------------------------------------------------------------------------------------------------------------------------------------------|
	public static List<Component> getSelectableEmittersFor(Actor anActor) {
		List<Component> emitterList = new ArrayList<Component>();
		// Actor has no emitters
		if (anActor.getEmitter() == null) {
			emitterList.add(new EmitOnce(anActor));
			emitterList.add(new EmitContinously(anActor));
		}
		return emitterList;
	}
	public static List<Component> getSelectableInitializersFor(Actor aActor) {
		List<Component> initializerList = new ArrayList<Component>();
		return initializerList;
	}
	public static List<Component> getSelectableOperatorsFor(Actor aActor) {
		List<Component> operatorList = new ArrayList<Component>();
		return operatorList;
	}
	// --------------------------------------------------------------------------------------------------------------------------------------------------------|
	// Getters & Setters
	// --------------------------------------------------------------------------------------------------------------------------------------------------------|
	public Actor getParentActor() {
		return parentActor;
	}

	public void setParentActor(Actor parentActor) {
		this.parentActor = parentActor;
	}

}
