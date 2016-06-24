/**
 * AE Application RAZE
 * Behavior.java
 */
package ae.raze.ai;

import com.jme3.bounding.BoundingVolume;
import com.jme3.collision.CollisionResults;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

import ae.raze.model.Car;
import ae.raze.util.ApplicationManager;

/**
 * TODO beginning work for the AI for the CPU cars
 * 
 * @author meyer
 *
 */
public class Behavior {
	
	Car computerCar;
	
	public Behavior(Car car) {
		computerCar = car;
	}
	
	public void doStuff() {
		
		Vector3f currentLocation = computerCar.currentLocation();
		
		float X = computerCar.currentDirection().getX();
    	float Z = computerCar.currentDirection().getZ();
    	
    	boolean north = false;
    	boolean east = false;
    	boolean south = false;
    	boolean west = false;
    	
    	if (X < -.1f) {
    		north = true;
    	} else if (X > .1f) {
    		south = true;
    	}
    	

		if (Z < -.1f) {
			east = true;
		} else if (Z > .1f) {
			west = true;
		}
		
		if (north) {
//			System.out.println("north");
		}
		if (south) {
//			System.out.println("south");
		}
		if (east) {
			
		}
		if (west) {
			lookAhead(currentLocation, null);
		}
	}
	
	private void lookAhead(Vector3f currentLocation, Vector3f direction) {
		
//		boolean collide = false;
		for (int i = 0; i < 10; i++) {
			CollisionResults results = new CollisionResults();
			Box box = new Box(0,0,0);
			Geometry boxGeometry = new Geometry("Box", box); 
			boxGeometry.setLocalTranslation(currentLocation.x-i, currentLocation.y, currentLocation.z); 
//			boolean collideWith = false;
			BoundingVolume outerWalls = ApplicationManager.INSTANCE.getOuterWall();
			int result = boxGeometry.collideWith(outerWalls, results);
			
//			System.out.println("Checking result: " + result);
			System.out.println("RESULTS: " + results.toString());
			
		}
		
		
	}

}
