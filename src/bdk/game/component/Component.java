package bdk.game.component;

import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Components are top-level classes that have control over a section of the
 * game.(Menu/Level)
 * 
 * @author Andreas Farley
 *
 */
public abstract class Component implements Serializable {

	// protected ArrayList<Sprite> spriteList;
	protected String componentName;
	protected int componentID;
	protected int transitionToStateID;
	
	public Component(String name) {
		this.componentName = name;
	}

	/**
	 * Updates all entities in the entityList. This is abstract since components
	 * might want to change the update order of their entities.
	 */
	public abstract void update();

	/**
	 * Renders all entities in the entityList. This is abstract since components
	 * might want to change the render order of their entities.
	 * 
	 * @param g
	 */
	public abstract void render(Graphics2D g);

	// -------------------------------------------------------------------------------|
	// GETTERS & SETTERS
	// -------------------------------------------------------------------------------|

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public int getComponentID() {
		return componentID;
	}

	public void setComponentID(int componentID) {
		this.componentID = componentID;
	}
	
	public int getTransitionToStateID() {
		return transitionToStateID;
	}

	public void setTransitionToStateID(int transitionToStateID) {
		this.transitionToStateID = transitionToStateID;
	}

}
