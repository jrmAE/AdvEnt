/**
 * AE Application RAZE
 * BodyPart.java
 */
package ae.raze.model.character.bodypart;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;

import ae.raze.util.ApplicationManager;

/**
 * @author meyer
 *
 */
public abstract class BodyPart {

	protected Geometry geom;

	protected Material getDefaultMaterial(ColorRGBA color) {
		Material mat = new Material(ApplicationManager.INSTANCE.getAssetManager(),"Common/MatDefs/Misc/Unshaded.j3md");
		mat.setColor("Color", color);   // set color of material to blue
		return mat;
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
