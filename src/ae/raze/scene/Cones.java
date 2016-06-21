/**
 * AE Application RAZE
 * Cones.java
 */
package ae.raze.scene;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Dome;

/**
 * Provides common methods to generate 'cones' that creates a track for
 * the cars to follow
 * 
 * @author meyer
 *
 */
public class Cones {
	
	/**
	 * This should transform into the 'level 1' track, just a square. 
	 * @param rootNode
	 * @param space
	 * @param assetManager
	 */
	public static void addCones(Node rootNode, PhysicsSpace space, AssetManager assetManager) {
		// road cones
		for (int i = 0; i < 12; i++) { 
			Vector3f v3f = new Vector3f(0, 0, 0);
			Dome dome = new Dome(v3f, 2, 4, .75f, true);
			Geometry domeGeometry = new Geometry("Dome", dome); 
			Material coneMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
			coneMaterial.setColor("Color", ColorRGBA.Orange);
			domeGeometry.setMaterial(coneMaterial); 
			domeGeometry.setLocalTranslation(-45f+i, 1f, -3f); 
			// RigidBodyControl automatically uses box collision shapes when attached to single geometry with box mesh 
			domeGeometry.addControl(new RigidBodyControl(1)); 
			rootNode.attachChild(domeGeometry); 
			space.add(domeGeometry); 
		} 
	}

}
