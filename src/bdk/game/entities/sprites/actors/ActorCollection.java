package bdk.game.entities.sprites.actors;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bdk.editor.main.BdkMainWindow;
import bdk.editor.util.BdkException;

/**
 * 
 * @author Andreas Farley
 *
 */
public class ActorCollection implements Serializable {

	// --------------------------------------------------------------------------|
	// Used for file IO
	public static final String COLLECTION_PATH = BdkMainWindow.getGameName() + "/sprites/actors/collections";

	// Runtime variables --------------------------------------------------------|
	transient private List<PropertyChangeListener> listeners = new ArrayList<>();
	transient private Map<String, Actor> actorCache;
	// --------------------------------------------------------------------------|
	private List<Actor> actorList;
	private String collectionName;
	// --------------------------------------------------------------------------|

	public ActorCollection(String name) {
		this.collectionName = name;
		this.actorList = new ArrayList<Actor>();
	}
	
	
	// Runtime Actor hashing------------------------------------------------------|
	
	public void cacheActors() {
		actorCache = new HashMap<String, Actor>();
		actorList.stream().forEach(actor -> actorCache.put(actor.getEntityName(), actor));
	}
	
	// Adding/Removing actors-----------------------------------------------------|

	public void addActor(Actor actorToAdd) {
		if (actorToAdd != null) {
			List<Actor> oldValue = this.actorList;
			actorList.add(actorToAdd);
			firePropertyChange("actorList", oldValue, this.actorList);
		} else {
			BdkException.throwWithMessage(
					"Tried to add an actor that was null to the actorlist of actorCollection " + collectionName);
		}
	}

	public void removeActorAt(int index) {
		if (!actorList.isEmpty()) {
			if (index < actorList.size() && index > -1) {
				List<Actor> oldValue = (List<Actor>) this.actorList;
				actorList.remove(index);
				firePropertyChange("actorList", oldValue, this.actorList);
			} else {
				throw new IndexOutOfBoundsException();
			}
		}
	}

	// LISTENER STUFF-------------------------------------------------------|
	public void refreshListenerList() {
		listeners = new ArrayList<>();
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		listeners.add(listener);
	}

	private void firePropertyChange(String property, Object oldValue, Object newValue) {
		for (PropertyChangeListener l : listeners) {
			l.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
		}
	}

	// ----------------------------------------------------------------------------------|
	// GETTERS AND SETTERS
	// ----------------------------------------------------------------------------------|

	public Actor getActorAt(int index) {
		return actorList.get(index);
	}

	public int getCollectionSize() {
		if (actorList != null) {
			return actorList.size();
		}
		return -1;
	}

	public void setActorList(ArrayList<Actor> actorList) {
		List<Actor> oldValue = (List<Actor>) this.actorList;
		this.actorList = actorList;
		firePropertyChange("actorList", oldValue, actorList);
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		String oldValue = this.collectionName;
		this.collectionName = collectionName;
		firePropertyChange("collectionName", oldValue, actorList);
	}


	public Map<String, Actor> getActorCache() {
		return actorCache;
	}


	public void setActorCache(Map<String, Actor> actorCache) {
		this.actorCache = actorCache;
	}


	public static String getCollectionPath() {
		return COLLECTION_PATH;
	}

}
