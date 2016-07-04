/**
 * AE Application RAZE
 * Car.java
 */
package ae.raze.model;

import com.jme3.asset.AssetManager;
import com.jme3.bounding.BoundingBox;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;

import ae.raze.ai.Behavior;
import ae.raze.ai.Personality;
import ae.raze.util.ApplicationManager;
import ae.raze.util.GeometryBuilder;

/**
 * TODO This class has gotten too big
 * 
 * @author meyer
 */
public class Car {
	
	private CarListener listener;
    private VehicleControl player;
    private Behavior behavior;
    private float steeringValue = 0;
    private float accelerationValue = 0;
    private Node carNode;
    private String carModel = null;
    private ColorRGBA carColor = ColorRGBA.Red;
    private boolean isPlayer = true;
    
	/**
     * @param assetMgr
     * @param inputManager
     * @param carModel - String. For example: "Models/Ferrari/Car.scene"
     */
    public Car(InputManager inputManager, String carModel, boolean isPlayer, float startingLocation) {
    	this(inputManager, carModel, isPlayer, startingLocation, ColorRGBA.Red);
    }
    
	/**
     * @param assetMgr
     * @param inputManager
     * @param carModel - String. For example: "Models/Ferrari/Car.scene"
     */
    public Car(InputManager inputManager, String carModel, boolean isPlayer, float startingLocation, ColorRGBA carColor) {
    	this.carModel = carModel;
    	this.carColor = carColor;
    	this.isPlayer = isPlayer;
    	if (!isPlayer) {
    		behavior = new Behavior(this, Personality.PASSIVE);
    	}
    	buildCar(startingLocation);
    	setupKeys(inputManager);
    }
	
    private void setupKeys(InputManager inputManager) {
    	listener = new CarListener(this);
        inputManager.addMapping("Lefts", new KeyTrigger(KeyInput.KEY_H));
        inputManager.addMapping("Rights", new KeyTrigger(KeyInput.KEY_K));
        inputManager.addMapping("Ups", new KeyTrigger(KeyInput.KEY_U));
        inputManager.addMapping("Downs", new KeyTrigger(KeyInput.KEY_J));
        inputManager.addMapping("Space", new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addMapping("Reset", new KeyTrigger(KeyInput.KEY_RETURN));
        inputManager.addListener(listener, "Lefts");
        inputManager.addListener(listener, "Rights");
        inputManager.addListener(listener, "Ups");
        inputManager.addListener(listener, "Downs");
        inputManager.addListener(listener, "Space");
        inputManager.addListener(listener, "Reset");
    }
    
    /**
     * This is provided by the sample. It reads the Blender-generated
     * Ogre 3d scene file and constructs it into a 3d object. 
     * 
     * @param startingLocation - Where the car should be placed at the start.
     */
    private void buildCar(float startingLocation) {
        float stiffness = 120.0f;//200=f1 car
        float compValue = 0.2f; //(lower than damp!)
        float dampValue = 0.3f;
        final float mass = 400;
        float wheelRadius = 0f;
        AssetManager assetManager = ApplicationManager.INSTANCE.getAssetManager();
        Node rootNode = ApplicationManager.INSTANCE.getRootNode();
        PhysicsSpace space = ApplicationManager.INSTANCE.getSpace();

        //Load model and get chassis Geometry
        carNode = (Node)assetManager.loadModel(carModel);
        carNode.setLocalTranslation(startingLocation, 0, 0);
        carNode.setShadowMode(ShadowMode.Cast);
        
        Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//	    material.setTexture("ColorMap", assetManager.loadTexture("Textures/concrete_cracked.jpg")); 
        material.setColor("Color", carColor);
        carNode.setMaterial(material);
        
        Geometry chassis = GeometryBuilder.findGeom(carNode, "Car");
        BoundingBox box = (BoundingBox) chassis.getModelBound();

        //Create a hull collision shape for the chassis
        CollisionShape carHull = CollisionShapeFactory.createDynamicMeshShape(chassis);

        //Create a vehicle control
        player = new VehicleControl(carHull, mass);
        carNode.addControl(player);

        //Setting default values for wheels
        player.setSuspensionCompression(compValue * 2.0f * FastMath.sqrt(stiffness));
        player.setSuspensionDamping(dampValue * 2.0f * FastMath.sqrt(stiffness));
        player.setSuspensionStiffness(stiffness);
        player.setMaxSuspensionForce(10000);

        //Create four wheels and add them at their locations
        //note that our fancy car actually goes backwards..
        Vector3f wheelDirection = new Vector3f(0, -1, 0);
        Vector3f wheelAxle = new Vector3f(-1, 0, 0);

        Geometry wheel_fr = GeometryBuilder.findGeom(carNode, "WheelFrontRight");
        wheel_fr.center();
        box = (BoundingBox) wheel_fr.getModelBound();
        wheelRadius = box.getYExtent();
        float back_wheel_h = (wheelRadius * 1.7f) - 1f;
        float front_wheel_h = (wheelRadius * 1.9f) - 1f;
        player.addWheel(wheel_fr.getParent(), box.getCenter().add(0, -front_wheel_h, 0),
                wheelDirection, wheelAxle, 0.2f, wheelRadius, true);

        Geometry wheel_fl = GeometryBuilder.findGeom(carNode, "WheelFrontLeft");
        wheel_fl.center();
        box = (BoundingBox) wheel_fl.getModelBound();
        player.addWheel(wheel_fl.getParent(), box.getCenter().add(0, -front_wheel_h, 0),
                wheelDirection, wheelAxle, 0.2f, wheelRadius, true);

        Geometry wheel_br = GeometryBuilder.findGeom(carNode, "WheelBackRight");
        wheel_br.center();
        box = (BoundingBox) wheel_br.getModelBound();
        player.addWheel(wheel_br.getParent(), box.getCenter().add(0, -back_wheel_h, 0),
                wheelDirection, wheelAxle, 0.2f, wheelRadius, false);

        Geometry wheel_bl = GeometryBuilder.findGeom(carNode, "WheelBackLeft");
        wheel_bl.center();
        box = (BoundingBox) wheel_bl.getModelBound();
        player.addWheel(wheel_bl.getParent(), box.getCenter().add(0, -back_wheel_h, 0),
                wheelDirection, wheelAxle, 0.2f, wheelRadius, false);

        player.getWheel(2).setFrictionSlip(4);
        player.getWheel(3).setFrictionSlip(4);
        
        rootNode.attachChild(getCarNode());
        space.add(getPlayer());

    }

    public void setBehavior(Personality personality) {
    	this.behavior = new Behavior(this, personality);
    }
    
    public Behavior getBehavior() {
    	return this.behavior;
    }
    
    /**
	 * @return the player
	 */
	public VehicleControl getPlayer() {
		return player;
	}

	/**
	 * @return the carNode
	 */
	public Node getCarNode() {
		return carNode;
	}
	
	/**
	 * @return Vector3f. The current location of the car. 
	 */
	public Vector3f currentLocation() {
		return carNode.getLocalTranslation();
	}
	
	public Vector3f currentDirection() {
		Vector3f vector = new Vector3f();
    	player.getForwardVector(vector);
    	return vector;
	}

	/**
	 * @return the isPlayer
	 */
	public boolean isPlayer() {
		return isPlayer;
	}

	/**
	 * @return the steeringValue
	 */
	public void addSteeringValue(float value) {
		steeringValue += value;
		player.steer(steeringValue);
	}

	/**
	 * @return the accelerationValue
	 */
	public void addAccelerationValue(float value) {
		accelerationValue += value;
		player.accelerate(accelerationValue);
	}
}
