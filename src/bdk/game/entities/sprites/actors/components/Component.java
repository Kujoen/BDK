package bdk.game.entities.sprites.actors.components;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bdk.editor.actor.BDKActorEditor;
import bdk.game.entities.sprites.actors.Actor;
import bdk.game.entities.sprites.actors.ActorSprite;
import bdk.game.entities.sprites.actors.components.emitter.EmitContinously;
import bdk.game.entities.sprites.actors.components.emitter.EmitOnce;
import bdk.game.entities.sprites.actors.components.operators.LifespanDecay;
import bdk.game.entities.sprites.actors.components.operators.MovementBasic;
import bdk.game.entities.sprites.actors.components.operators.RotationBasic;
import bdk.game.main.Game;
import bdk.util.ui.BDKWarningDialog;
import soliture.ui.swingextensions.expandinglist.JExpandableRow;

/**
 * 
 * @author Andreas Farley
 *
 */
public abstract class Component implements Serializable{
	private static final long serialVersionUID = 1L;
	
	protected Actor parentActor;
	protected ActorSprite actorSprite;
	protected int componentTick = 0;
	
	public abstract JExpandableRow getComponentRow(BDKActorEditor bdkActorEditor);
	public abstract void reset();

	public Component(Actor parentActor) {
		this.parentActor = parentActor;
	}

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
		
		if(MovementBasic.rejectForActor(aActor) != true) {
			operatorList.add(new MovementBasic(aActor));
		}
		
		if(RotationBasic.rejectForActor(aActor) != true) {
			operatorList.add(new RotationBasic(aActor));
		}
		
		if(LifespanDecay.rejectForActor(aActor) != true) {
			operatorList.add(new LifespanDecay(aActor));
		}
		
		return operatorList;
	}
	
	public static boolean rejectForActor(Actor targetActor) {
		Game.getLogger().log(java.util.logging.Level.SEVERE, "Component subclass did not implement the rejectFor static method", new Exception("Not implemented by subclass"));
		BDKWarningDialog.showWarning("Component subclass did not implement the rejectFor static method");
		return true;
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
	public ActorSprite getActorSprite() {
		return actorSprite;
	}
	public void setActorSprite(ActorSprite actorSprite) {
		this.actorSprite = actorSprite;
	}
	public int getComponentTick() {
		return componentTick;
	}
	public void setComponentTick(int componentTick) {
		this.componentTick = componentTick;
	}

}
