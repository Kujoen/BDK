package bdk.game.entities.sprites.actors;

import java.io.Serializable;
import java.util.ArrayList;

public class ActorCollection implements Serializable{
	
	private ArrayList<Actor> actorList;
	private int actorAmount = 0;
	private String collectionName;
	
	public ActorCollection(String name){
		this.collectionName = name;
		this.actorList = new ArrayList<Actor>();
	}
	
	public void addActor(Actor actorToAdd){
		actorList.add(actorToAdd);
		actorAmount++;
	}
	
	public void removeActorAt(int index){
		if(!actorList.isEmpty()){
			if(index < actorList.size()){
				actorList.remove(index);		
				actorAmount--;
			}
		}
	}
	
	
	//----------------------------------------------------------------------------------|
	//GETTERS AND SETTERS
	//----------------------------------------------------------------------------------|

	public Actor getActorAt(int index){
		return actorList.get(index);
	}
	
	public int getActorAmount(){
		return actorAmount;
	}
	
	public ArrayList<Actor> getActorList() {
		return actorList;
	}

	public void setActorList(ArrayList<Actor> actorList) {
		this.actorList = actorList;
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

}
