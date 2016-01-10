/**
 * AE Application RAZE
 * Body.java
 */
package ae.raze.model.character.bodypart;

import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

/**
 * @author meyer
 *
 */
public class Body extends BodyPart {
	
	public Body() {
		Box b = new Box(1, 1, 1); // create cube shape
        geom = new Geometry("Box", b);  // create cube geometry from the shape
        geom.setMaterial(getDefaultMaterial(ColorRGBA.Blue));
	}

}
