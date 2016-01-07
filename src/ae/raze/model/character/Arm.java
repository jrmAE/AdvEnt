/**
 * AE Application RAZE
 * Arm.java
 */
package ae.raze.model.character;

import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Cylinder;

/**
 * @author meyer
 *
 */
public class Arm extends BodyPart {
	
	public Arm() {
		Cylinder b = new Cylinder(10, 10, 1, 2);
        geom = new Geometry("Cylinder", b);  // create cube geometry from the shape
        geom.setMaterial(getDefaultMaterial(ColorRGBA.Green));
	}

}
