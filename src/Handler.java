
/**
 * Handler Class
 */
import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	//LinkedList of all objects in game
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick() {
		//Cycle through all objects and call their tick method
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
			
			if (object.get(i).removed) {
		          object.remove(i);
			}
		}
	}
	
	public void render(Graphics g) {
		//Render all game objects
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	//Add and remove game objects
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
}
