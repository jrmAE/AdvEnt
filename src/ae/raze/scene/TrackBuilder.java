/**
 * AE Application RAZE
 * Cones.java
 */
package ae.raze.scene;

import java.util.ArrayList;
import java.util.List;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

import ae.raze.util.ApplicationManager;

/**
 * 
 * Provides common methods to generate a track for
 * the cars to follow
 * 
 * @author meyer
 *
 */
public class TrackBuilder {
	
	/**
	 * This should transform into the 'level 1' track, just a square. 
	 * @param rootNode
	 * @param space
	 * @param assetManager
	 */
	public static List<Geometry> smallSquare() {

		List<Geometry> wallGeoList = new ArrayList<>();
		//North Wall
		wallGeoList.addAll(generateHorizWalls(35,-40,80));

		//South Wall
		wallGeoList.addAll(generateHorizWalls(-30,-40,80));

		//West Wall
		wallGeoList.addAll(generateVertWalls(-30,-40,65));

		//East Wall
		wallGeoList.addAll(generateVertWalls(-30,40,65));
		
		return wallGeoList;
		
	}
	
	/**
	 * This should transform into the 'level 1' track, just a square. 
	 * @param rootNode
	 * @param space
	 * @param assetManager
	 */
	public static List<Geometry> largeSquare() {

		List<Geometry> wallGeoList = new ArrayList<>();
		
		//North Wall
		wallGeoList.addAll(generateHorizWalls(60,-80,160));

		//South Wall
		wallGeoList.addAll(generateHorizWalls(-50,-80,160));

		//West Wall
		wallGeoList.addAll(generateVertWalls(-50,-80,110));

		//East Wall
		wallGeoList.addAll(generateVertWalls(-50,80,110));
		
		return wallGeoList;

	}
	
	private static List<Geometry>  generateVertWalls(float x, float z, int countUntil) {
		
		AssetManager assetManager = ApplicationManager.INSTANCE.getAssetManager();
		Node rootNode = ApplicationManager.INSTANCE.getRootNode();
		PhysicsSpace space = ApplicationManager.INSTANCE.getSpace();
		List<Geometry> wallGeoList = new ArrayList<>();
		
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
			wallGeoList.add(boxGeometry);
		} 
		return wallGeoList;
	}
	
	private static List<Geometry> generateHorizWalls(float x, float z, int countUntil) {
		AssetManager assetManager = ApplicationManager.INSTANCE.getAssetManager();
		Node rootNode = ApplicationManager.INSTANCE.getRootNode();
		PhysicsSpace space = ApplicationManager.INSTANCE.getSpace();
		List<Geometry> wallGeoList = new ArrayList<>();
		for (int i = 0; i < countUntil; i++) { 
			Box box = new Box(1,1,1);
			Geometry boxGeometry = new Geometry("Box", box); 
			Material boxMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
			boxMaterial.setColor("Color", ColorRGBA.Orange);
			boxGeometry.setMaterial(boxMaterial); 
			boxGeometry.setLocalTranslation(x, 0, z+i); 
			// RigidBodyControl automatically uses box collision shapes when attached to single geometry with box mesh 
			boxGeometry.addControl(new RigidBodyControl(0)); 
			wallGeoList.add(boxGeometry);
			rootNode.attachChild(boxGeometry); 
			space.add(boxGeometry); 
		} 
		return wallGeoList;
	}

}
