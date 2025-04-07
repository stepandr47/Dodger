package New_Gaming;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	//Checks on any changes that need to be made to a game object
	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.tick(); 
		}
	}
	
	//Creating the image of a game object
	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	//Single addition of a game object
	public void addObject(GameObject object) {
		this.object.add(object);
	}

	//Single removal of a game object
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	//Clears all game objects except for the player
	public void clearEnemies() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			if (tempObject.getId() == ID.Player) {
				object.clear();
				addObject(new Player((int)tempObject.getX(), (int)tempObject.getY(), ID.Player, this));
			}
		}
	}
	
	//Clears all game objects
	public void clear() {
		for (int i = 0; i < object.size(); i++) {
			object.clear();
		}
	}
}
