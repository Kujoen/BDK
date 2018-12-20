package bdk.game.entities;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bdk.game.component.level.Level;
import javafx.geometry.Point2D;

/**
 * An Entity is an object which has a position on the gamecanvas and can be
 * updated.
 * 
 * @author Andreas Farley
 *
 */
public abstract class Entity implements Serializable {
	private static final long serialVersionUID = -4302764479516437594L;

	// ---------------------------------------------------------------------|
	protected String entityName;
	protected Point2D position;
	// ---------------------------------------------------------------------|

	public Entity(String entityName) {
		this.entityName = entityName;
	}

	public abstract void update();

	// -------------------------------------------------------------------------------|
	// GETTERS & SETTERS
	// -------------------------------------------------------------------------------|

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	// ---------------------------------------------------------------------|

	// All classes of the type entity can be listened to--------------------|
	// Transient because we can't serialize this.
	protected transient List<PropertyChangeListener> listeners = new ArrayList<>();

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

}
