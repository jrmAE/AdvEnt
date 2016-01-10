/**
 * AE Application RAZE
 * Person.java
 */
package ae.raze.model.character;

import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
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
		torso = new Body();
		head = new Head();
		leftLeg = new Leg();
		rightLeg = new Leg();
		leftArm = new Arm();
		rightArm = new Arm();
		
	}
	
	/**
	 * Build and return the Node of the person
	 * @return <code>Node</code>
	 */
	public Node getPerson() {
		
		/** create a blue box at coordinates (1,-1,1) */
        Geometry blue = torso.getGeom();
        blue.setLocalTranslation(new Vector3f(1,-1,1));
        
        /** create a red box straight above the blue one at (1,3,1) */
        Geometry red = head.getGeom();
        red.setLocalTranslation(new Vector3f(1,1,1));

        /** Create a pivot node at (0,0,0) and attach it to the root node */
        Node person = new Node("pivot");
        
        /** Attach the two boxes to the *pivot* node. (And transitively to the root node.) */
        person.attachChild(blue);
        person.attachChild(red);
        
//        person.rotate(.4f,.4f,0f);
        
		return person;
	}

}
