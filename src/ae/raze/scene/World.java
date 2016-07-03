package ae.raze.scene;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

import ae.raze.util.ApplicationManager; 

/**
 * The floor of the racing track
 */ 
public class World { 
	
	/**
	 * This will be the base for the track. 
	 * Goal is to create a SuperSprint like world. 
	 */ 
	public World() { 
		
		Node rootNode = ApplicationManager.INSTANCE.getRootNode();
		AssetManager assetManager = ApplicationManager.INSTANCE.getAssetManager();
		PhysicsSpace space = ApplicationManager.INSTANCE.getSpace();
		
		//The Sun
		addSun(rootNode);
		
		//light for the track and items
        addLight(rootNode);

		//The floor
		addFloor(rootNode, assetManager, space);

	} 
	
	/**
	 * The Sun
	 * @param rootNode Node
	 */
	private void addSun(Node rootNode) {
		AmbientLight light = new AmbientLight(); 
		light.setColor(ColorRGBA.LightGray); 
		rootNode.addLight(light); 
	}
	

	/**
	 * The Light 
	 * @param rootNode Node
	 */
	private void addLight(Node rootNode) {
        DirectionalLight dl = new DirectionalLight();
        dl.setDirection(new Vector3f(-0.5f, -1f, -0.3f).normalizeLocal());
        rootNode.addLight(dl);
        dl = new DirectionalLight();
        dl.setDirection(new Vector3f(0.5f, -0.1f, 0.3f).normalizeLocal());
	}
	
	
	/**
	 * Add the floor of the racing world
	 * @param rootNode Node
	 * @param assetManager AssetManager
	 * @param space PhysicsSpace
	 */
	private void addFloor(Node rootNode, AssetManager assetManager, PhysicsSpace space) {
		Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		material.setColor("Color", ColorRGBA.DarkGray);
		Box floorBox = new Box(85, 0.25f, 110); 
		Geometry floorGeometry = new Geometry("Floor", floorBox); 
		floorGeometry.setMaterial(material); 
		floorGeometry.setLocalTranslation(0, -2, 0); 
		floorGeometry.addControl(new RigidBodyControl(0)); 
		rootNode.attachChild(floorGeometry); 
		space.add(floorGeometry);
	}
	
}
