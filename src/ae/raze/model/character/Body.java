/**
 * AE Application RAZE
 * Body.java
 */
package ae.raze.model.character;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

import ae.raze.util.ApplicationManager;

/**
 * @author meyer
 *
 */
public class Body extends Box {
	
	private Geometry geom;
	
	public Body() {
		Box b = new Box(1, 1, 1); // create cube shape
        geom = new Geometry("Box", b);  // create cube geometry from the shape
        Material mat = new Material(ApplicationManager.INSTANCE.getAssetManager(),"Common/MatDefs/Misc/Unshaded.j3md");  // create a simple material
        mat.setColor("Color", ColorRGBA.Blue);   // set color of material to blue
        geom.setMaterial(mat);
	}

	/**
	 * @return the geom
	 */
	public Geometry getGeom() {
		return geom;
	}

	/**
	 * @param geom the geom to set
	 */
	public void setGeom(Geometry geom) {
		this.geom = geom;
	}
	
	

}
