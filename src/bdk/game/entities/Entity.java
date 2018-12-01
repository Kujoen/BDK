package bdk.game.entities;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bdk.game.component.level.Level;

/**
 * An entity is an instance of an entity-object which is on the level and being
 * updated. Currently just using rendered Entities.
 * 
 * @author Soliture
 *
 */
public abstract class Entity implements Serializable {

	// Every entity has an position
	protected double x, y;
	
	// And a name
	protected String entityName;

	// When added to a level the entity will have to have access to the level. This
	// information is only needed during and therefore transient.
	protected transient Level level;

	// Entity cant update/render when its not added to a level.
	protected transient boolean isInitialized = false;

	// Every entity can update. Only sprites can render (See sprite subclass).
	public abstract void update();

	public Entity(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Called by the EntityLink when its time to spawn the entity
	 * @param level
	 */
	public void initializeForLevel(Level level) {
		this.level = level;
		isInitialized = true;
	}

	// All classes of the type entity can be listened to--------------------|
	// Transient because we can't serialize this.
	transient protected List<PropertyChangeListener> listeners = new ArrayList<>();

	public void refreshListenerList() {
		listeners = new ArrayList<>();
	}

	protected void firePropertyChange(String property, Object oldValue, Object newValue) {
		for (PropertyChangeListener l : listeners) {
			l.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
		}
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		listeners.add(listener);
	}
	// ---------------------------------------------------------------------|
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public boolean isInitialized() {
		return isInitialized;
	}

	public void setInitialized(boolean isInitialized) {
		this.isInitialized = isInitialized;
	}
}
