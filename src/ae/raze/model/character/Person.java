/**
 * AE Application RAZE
 * Person.java
 */
package ae.raze.model.character;

import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

import ae.raze.model.character.bodypart.Arm;
import ae.raze.model.character.bodypart.Body;
import ae.raze.model.character.bodypart.Head;
import ae.raze.model.character.bodypart.Leg;

/**
 * @author meyer
 *
 */
public class Person {
	
	private Vector3f center;
	
	private Arm leftArm;
	private Arm rightArm;
	private Body torso;
	private Head head;
	private Leg leftLeg;
	private Leg rightLeg;
	
	/**
	 * Will create a person at the parameterized 3d Vector
	 * @param centralPoint - <code>Vector3f</code>
	 */
	public Person(Vector3f centralPoint) {
		center = centralPoint.clone();
		torso = new Body();
		head = new Head();
		leftLeg = new Leg(45f, 90f, 0f);
		rightLeg = new Leg(90f, 45f, 0f);
		leftArm = new Arm();
		rightArm = new Arm();
		
	}
	
	/**
	 * Build and return the Node of the person
	 * @return <code>Node</code>
	 */
	public Node getPerson() {
		
		Vector3f torsoCenter = new Vector3f(center.getX(), center.getY() -1, center.getZ());
		Vector3f headCenter = new Vector3f(center.getX(), center.getY() +1, center.getZ());
		Vector3f rightArmCenter = new Vector3f(center.getX() -2, center.getY() -.5f, center.getZ());
		Vector3f leftArmCenter = new Vector3f(center.getX() +2, center.getY() -.5f, center.getZ());
		Vector3f rightLegCenter = new Vector3f(center.getX() -1, center.getY() - 3f, center.getZ());
		Vector3f leftLegCenter = new Vector3f(center.getX() +1, center.getY() - 3, center.getZ());
		
		torso.setLocalCenter(torsoCenter);
		head.setLocalCenter(headCenter);
		rightArm.setLocalCenter(rightArmCenter);
		leftArm.setLocalCenter(leftArmCenter);
		rightLeg.setLocalCenter(rightLegCenter);
		leftLeg.setLocalCenter(leftLegCenter);
		
        /** Create a pivot node at (0,0,0) and attach it to the root node */
        Node person = new Node("pivot");
        
        /** Attach the two boxes to the *pivot* node. (And transitively to the root node.) */
        person.attachChild(torso.getGeom());
        person.attachChild(head.getGeom());
        person.attachChild(rightArm.getGeom());
        person.attachChild(leftArm.getGeom());
        person.attachChild(rightLeg.getGeom());
        person.attachChild(leftLeg.getGeom());
        
		return person;
	}

}
