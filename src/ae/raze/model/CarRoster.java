/**
 * AE Application RAZE
 * CarRoster.java
 */
package ae.raze.model;

import java.util.ArrayList;
import java.util.List;

import com.jme3.input.InputManager;
import com.jme3.math.ColorRGBA;

/**
 * Utility to generate the Cars at boot
 * @author meyer
 *
 */
public class CarRoster {
	/**
	 * This should eventually be configurable
	 * 
	 * @param playerCar
	 * @param computerCars
	 * @param inputManager
	 */
	public static List<Car> addCars(boolean playerCar, int computerCars, InputManager inputManager) {
		
		List<Car> carRoster = new ArrayList<>(4);
		//The Car Lineup 
		int polePosition = 40;
		if (playerCar) {
			carRoster.add(new Car(inputManager, "Models/Ferrari/Car.scene", true, polePosition));
		}
		//Computer Cars
        carRoster.add(new Car(inputManager, "Models/Ferrari/Car.scene", false, polePosition+=5, ColorRGBA.Blue));
        carRoster.add(new Car(inputManager, "Models/Ferrari/Car.scene", false, polePosition+=5, ColorRGBA.Yellow));
        carRoster.add(new Car(inputManager, "Models/Ferrari/Car.scene", false, polePosition+=5, ColorRGBA.White));
        return carRoster;
	}
}
