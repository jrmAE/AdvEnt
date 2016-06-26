package ae.raze.scene;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.input.InputManager;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

import ae.raze.model.Car;
import ae.raze.util.ApplicationManager; 

/**
 * The floor of the racing track
 */ 
public class World { 
	
	/**
	 * This will be the base for the track. 
	 * Goal is to create a SuperSprint like world. 
	 */ 
	public static void createWorld() { 
		
		Node rootNode = ApplicationManager.INSTANCE.getRootNode();
		AssetManager assetManager = ApplicationManager.INSTANCE.getAssetManager();
		PhysicsSpace space = ApplicationManager.INSTANCE.getSpace();
		
		//The Sun
		AmbientLight light = new AmbientLight(); 
		light.setColor(ColorRGBA.LightGray); 
		rootNode.addLight(light); 
		
		//light for the track and items
        DirectionalLight dl = new DirectionalLight();
        dl.setDirection(new Vector3f(-0.5f, -1f, -0.3f).normalizeLocal());
        rootNode.addLight(dl);

        dl = new DirectionalLight();
        dl.setDirection(new Vector3f(0.5f, -0.1f, 0.3f).normalizeLocal());
		
//		    material.setTexture("ColorMap", assetManager.loadTexture("Textures/concrete_cracked.jpg")); 
//		Texture tex = assetManager.loadTexture("/home/meyer/eclipse/jee-mars/eclipse/workspace/AE_A1/resources/Textures/concrete_cracked.jpg");
//		Texture tex = assetManager.loadTexture( new TextureKey("Textures/concrete_cracked.jpg", false) );
//		tex.setWrap(Texture.WrapMode.Repeat); //This should set the texture to repeat.
//		material.setTexture("ColorMap", tex); // with Unshaded.j3md
		
		//The floor
		Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		material.setColor("Color", ColorRGBA.DarkGray);
		Box floorBox = new Box(85, 0.25f, 110); 
		Geometry floorGeometry = new Geometry("Floor", floorBox); 
		floorGeometry.setMaterial(material); 
		floorGeometry.setLocalTranslation(0, -2, 0); 
		floorGeometry.addControl(new RigidBodyControl(0)); 
		rootNode.attachChild(floorGeometry); 
		space.add(floorGeometry); 
		
		//The inner wall
		TrackBuilder.smallSquare();
		
		//The outer wall
		TrackBuilder.largeSquare();

	} 
	
	/**
	 * This should eventually be configurable
	 * 
	 * @param playerCar
	 * @param computerCars
	 * @param inputManager
	 */
	public static void addCars(boolean playerCar, int computerCars, InputManager inputManager) {
        //The Car Lineup 
		int polePosition = 40;
		if (playerCar) {
			new Car(inputManager, "Models/Ferrari/Car.scene", true, polePosition);
		}
		//Computer Cars
        new Car(inputManager, "Models/Ferrari/Car.scene", false, polePosition+=5, ColorRGBA.Blue);
        new Car(inputManager, "Models/Ferrari/Car.scene", false, polePosition+=5, ColorRGBA.Yellow);
        new Car(inputManager, "Models/Ferrari/Car.scene", false, polePosition+=5, ColorRGBA.White);
        
	}

}
