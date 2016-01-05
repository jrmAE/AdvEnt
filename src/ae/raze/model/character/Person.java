/**
 * AE Application RAZE
 * Person.java
 */
package ae.raze.model.character;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Geometry;

/**
 * @author meyer
 *
 */
public class Person {
	
	private Arm leftArm;
	private Arm rightArm;
	private Body torso;
	private Head head;
	private Leg leftLeg;
	private Leg rightLeg;
	
	public Person() {
		torso = new Body();
		
	}
	
	/**
	 * Build and return the geometry of the person
	 * @return <code>Geometry</code>
	 */
	public Geometry getPerson() {
		return torso.getGeom();
	}

}
