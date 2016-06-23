package ae.raze.scene;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.light.AmbientLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box; 

/**
 * The floor of the racing track
 */ 
public class World { 

	/**
	 * This will be the base for the track. 
	 * Goal is to create a SuperSprint like world. 
	 *  
	 * @param rootNode 
	 * @param assetManager 
	 * @param space 
	 */ 
	public static void createWorld(Node rootNode, AssetManager assetManager, PhysicsSpace space) { 

		AmbientLight light = new AmbientLight(); 
		light.setColor(ColorRGBA.LightGray); 
		rootNode.addLight(light); 
		Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//		    material.setTexture("ColorMap", assetManager.loadTexture("Textures/concrete_cracked.jpg")); 
		material.setColor("Color", ColorRGBA.DarkGray);
//		Texture tex = assetManager.loadTexture("/home/meyer/eclipse/jee-mars/eclipse/workspace/AE_A1/resources/Textures/concrete_cracked.jpg");
//		Texture tex = assetManager.loadTexture( new TextureKey("Textures/concrete_cracked.jpg", false) );
//		tex.setWrap(Texture.WrapMode.Repeat); //This should set the texture to repeat.
//		material.setTexture("ColorMap", tex); // with Unshaded.j3md
		
		Box floorBox = new Box(85, 0.25f, 110); 
		Geometry floorGeometry = new Geometry("Floor", floorBox); 
		floorGeometry.setMaterial(material); 
		floorGeometry.setLocalTranslation(0, -2, 0); 

		// Plane plane = new Plane(); 
		// plane.setOriginNormal(new Vector3f(0, 0.25f, 0), Vector3f.UNIT_Y); 
		// floorGeometry.addControl(new RigidBodyControl(new PlaneCollisionShape(plane), 0)); 
		floorGeometry.addControl(new RigidBodyControl(0)); 
		rootNode.attachChild(floorGeometry); 
		space.add(floorGeometry); 
		
		TrackBuilder.addCones(rootNode, space, assetManager);
		
		//TODO - working here blender needs a UV map for my ogre export of the walls
		new Track(assetManager, "Models/tracks/generic_walls.scene",rootNode, space);
	} 

}
