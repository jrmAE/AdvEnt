/**
 * AE Application RAZE
 * Car.java
 */
package ae.raze.model;

import com.jme3.asset.AssetManager;
import com.jme3.bounding.BoundingBox;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.FastMath;
import com.jme3.math.Matrix3f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;

import ae.raze.util.GeometryBuilder;

/**
 * @author meyer
 */
public class Car implements ActionListener {
	
    private VehicleControl player;
    private float wheelRadius;
    private float steeringValue = 0;
    private float accelerationValue = 0;
    private Node carNode;
    private String carModel = null;
    
    /**
     * @param assetMgr
     * @param inputManager
     * @param carModel - String. For example: "Models/Ferrari/Car.scene"
     */
    public Car(AssetManager assetMgr, InputManager inputManager, String carModel) {
    	this.carModel = carModel;
    	buildCar(assetMgr);
    	setupKeys(inputManager);
    }
	
    private void setupKeys(InputManager inputManager) {
        inputManager.addMapping("Lefts", new KeyTrigger(KeyInput.KEY_H));
        inputManager.addMapping("Rights", new KeyTrigger(KeyInput.KEY_K));
        inputManager.addMapping("Ups", new KeyTrigger(KeyInput.KEY_U));
        inputManager.addMapping("Downs", new KeyTrigger(KeyInput.KEY_J));
        inputManager.addMapping("Space", new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addMapping("Reset", new KeyTrigger(KeyInput.KEY_RETURN));
        inputManager.addListener(this, "Lefts");
        inputManager.addListener(this, "Rights");
        inputManager.addListener(this, "Ups");
        inputManager.addListener(this, "Downs");
        inputManager.addListener(this, "Space");
        inputManager.addListener(this, "Reset");
    }
    
    /**
     * TODO
     * we want to have a total of four cars race, so this needs to be 
     * built differently
     */
    private void buildCar(AssetManager assetManager) {
        float stiffness = 120.0f;//200=f1 car
        float compValue = 0.2f; //(lower than damp!)
        float dampValue = 0.3f;
        final float mass = 400;

        //Load model and get chassis Geometry
        carNode = (Node)assetManager.loadModel(carModel);
        carNode.setShadowMode(ShadowMode.Cast);
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
     * TODO
     * make this appear quicker in the camera
     * @see com.jme3.input.controls.ActionListener#onAction(java.lang.String, boolean, float)
     */
    public void onAction(String binding, boolean keyPressed, float tpf) {
        if (binding.equals("Lefts")) {
            if (keyPressed) {
                steeringValue += .5f;
            } else {
                steeringValue += -.5f;
            }
            player.steer(steeringValue);
        } else if (binding.equals("Rights")) {
            if (keyPressed) {
                steeringValue += -.5f;
            } else {
                steeringValue += .5f;
            }
            player.steer(steeringValue);
        } //note that our fancy car actually goes backwards..
        else if (binding.equals("Ups")) {
            if (keyPressed) {
                accelerationValue -= 800;
            } else {
                accelerationValue += 800;
            }
            player.accelerate(accelerationValue);
            player.setCollisionShape(CollisionShapeFactory.createDynamicMeshShape(GeometryBuilder.findGeom(carNode, "Car")));
        } else if (binding.equals("Downs")) {
            if (keyPressed) {
                if (Math.abs(player.getCurrentVehicleSpeedKmHour()) < 1.0f) {
                	//reverse!
                	player.accelerate(800f);
                } else {
                	player.brake(40f);
                }
                
            } else {
            	//don't brake and don't increase the speed of reversing
            	player.accelerate(0f);
            	player.brake(0f);
            }
        } else if (binding.equals("Reset")) {
            if (keyPressed) {
                player.setPhysicsLocation(Vector3f.ZERO);
                player.setPhysicsRotation(new Matrix3f());
                player.setLinearVelocity(Vector3f.ZERO);
                player.setAngularVelocity(Vector3f.ZERO);
                player.resetSuspension();
            } else {
            }
        }
    }
}
