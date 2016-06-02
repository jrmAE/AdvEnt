/**
 * AE Application RAZE
 * Leg.java
 */
package ae.raze.model.character.bodypart;

import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Cylinder;

/**
 * @author meyer
 *
 */
public class Leg extends BodyPart {
	
	public Leg(Float xAngle, Float yAngle, Float zAngle) {
		Cylinder b = new Cylinder(10, 25, .25f, 4, true);
        geom = new Geometry("Cylinder", b);  // create cube geometry from the shape
        geom.setMaterial(getDefaultMaterial(ColorRGBA.Green));
        geom.rotate(xAngle, yAngle, zAngle); //right leg = 90, left leg = 45 - >90, 90, 0
	}

}
