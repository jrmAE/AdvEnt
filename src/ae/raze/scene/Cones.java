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
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Dome;

/**
 * TODO this needs reorganized.... its no longer just road cones...
 * 
 * Provides common methods to generate a track for
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

		//North Wall
		generateHorizWalls(35,-40,80,assetManager,rootNode,space);

		//South Wall
		generateHorizWalls(-30,-40,80,assetManager,rootNode,space);

		//West Wall
		generateVertWalls(-30,-40,65,assetManager,rootNode,space);

		//East Wall
		generateVertWalls(-30,40,65,assetManager,rootNode,space);

	}
	
	private static void generateVertWalls(float x, float z, int countUntil, AssetManager assetManager, Node rootNode, PhysicsSpace space) {
		for (int i = 0; i < countUntil; i++) { 
			Box box = new Box(1,1,1);
			Geometry boxGeometry = new Geometry("Box", box); 
			Material boxMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
			boxMaterial.setColor("Color", ColorRGBA.Orange);
			boxGeometry.setMaterial(boxMaterial); 
			boxGeometry.setLocalTranslation(x+i, 0, z); 
			// RigidBodyControl automatically uses box collision shapes when attached to single geometry with box mesh 
			boxGeometry.addControl(new RigidBodyControl(0)); 
			rootNode.attachChild(boxGeometry); 
			space.add(boxGeometry); 
		} 
	}
	
	private static void generateHorizWalls(float x, float z, int countUntil, AssetManager assetManager, Node rootNode, PhysicsSpace space) {
		for (int i = 0; i < countUntil; i++) { 
			Box box = new Box(1,1,1);
			Geometry boxGeometry = new Geometry("Box", box); 
			Material boxMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
			boxMaterial.setColor("Color", ColorRGBA.Orange);
			boxGeometry.setMaterial(boxMaterial); 
			boxGeometry.setLocalTranslation(x, 0, z+i); 
			// RigidBodyControl automatically uses box collision shapes when attached to single geometry with box mesh 
			boxGeometry.addControl(new RigidBodyControl(0)); 
			rootNode.attachChild(boxGeometry); 
			space.add(boxGeometry); 
		} 
	}
	
	/**
	 * Use this to generate a series of Road Cones
	 */
	private static void generateRoadCones(AssetManager assetManager, Node rootNode, PhysicsSpace space) { 
		for (int i = 0; i < 12; i++) { 
			Vector3f v3f = new Vector3f(0, 0, 0);
			Dome dome = new Dome(v3f, 2, 4, .75f, true);
			Geometry domeGeometry = new Geometry("Dome", dome); 
			Material coneMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
			coneMaterial.setColor("Color", ColorRGBA.Orange);
			domeGeometry.setMaterial(coneMaterial); 
			domeGeometry.setLocalTranslation(-45f+i, 1f, -30f); 
			// RigidBodyControl automatically uses box collision shapes when attached to single geometry with box mesh 
			domeGeometry.addControl(new RigidBodyControl(1)); 
			rootNode.attachChild(domeGeometry); 
			space.add(domeGeometry); 
		} 
	}

}
