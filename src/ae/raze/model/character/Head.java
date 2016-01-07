/**
 * AE Application RAZE
 * Head.java
 */
package ae.raze.model.character;

import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;

/**
 * @author meyer
 *
 */
public class Head extends BodyPart {
	
	public Head() {
		Sphere b = new Sphere(10, 10, 1); // create cube shape
        geom = new Geometry("Sphere", b);  // create cube geometry from the shape
        geom.setMaterial(getDefaultMaterial(ColorRGBA.Red));
	}


}
