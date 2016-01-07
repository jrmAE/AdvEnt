/**
 * AE Application RAZE
 * Person.java
 */
package ae.raze.model.character;

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
		head = new Head();
		leftLeg = new Leg();
		rightLeg = new Leg();
		leftArm = new Arm();
		rightArm = new Arm();
		
	}
	
	/**
	 * Build and return the geometry of the person
	 * @return <code>Geometry</code>
	 */
	public Geometry getPerson() {
		return leftArm.getGeom();
//		return torso.getGeom();
//		return head.getGeom();
	}

}
