package bdk.game.entities.sprites.actors.components;

import java.util.ArrayList;
import java.util.List;

import bdk.editor.actor.componentpanel.rows.ComponentRow;
import bdk.game.entities.sprites.actors.Actor;
import bdk.game.entities.sprites.actors.components.emitter.EmitContinuously;
import bdk.game.entities.sprites.actors.components.emitter.EmitOnce;

/**
 * 
 * @author Andreas Farley
 *
 */
public abstract class Component {
	
	public abstract ComponentRow getComponentRow();
	
	/**
	 * Return the classes name 
	 */
	public String toString() {
		return this.getClass().getSimpleName();
	}
	
	// Static methods used by other classes for data collection***************************|
	public static List<Component> getSelectableEmittersFor(Actor anActor){
		
		List<Component> emitterList = new ArrayList<Component>();
		
		//Actor has no emitters
		if(anActor.getEmitter() == null) {
			emitterList.add(new EmitOnce());
			emitterList.add(new EmitContinuously());
		}
	
		return emitterList;
	}
	
	public static List<Component> getSelectableInitializersFor(Actor aActor){
		List<Component> initializerList = new ArrayList<Component>();
		return initializerList;
	}
	
	public static List<Component> getSelectableOperatorsFor(Actor aActor){
		List<Component> operatorList = new ArrayList<Component>();
		return operatorList;
	}
	// *************************************************************************************|
}
