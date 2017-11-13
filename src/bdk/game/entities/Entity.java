package bdk.game.entities;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * An entity is an instance of an entity-object which is on the level and being updated.
 * Currently just using rendered Entities.
 * @author Kuj
 *
 */
public abstract class Entity implements Serializable{

	//Every entity has an position
	protected double x, y;
	
	//Every entity can update
	public abstract void update();
	
	public Entity(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	// All classes of the type entity can be listened to--------------------|
	//Transient because we cant serialize this
	transient protected final List<PropertyChangeListener> listeners = new ArrayList<>();
	
	protected void firePropertyChange(String property, Object oldValue, Object newValue) {
		for (PropertyChangeListener l : listeners) {
			l.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
		}
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		listeners.add(listener);
	}
	// ---------------------------------------------------------------------|
}
