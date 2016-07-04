/**
 * AE Application RAZE
 * CarListener.java
 */
package ae.raze.model;

import com.jme3.bullet.control.VehicleControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.input.controls.ActionListener;
import com.jme3.math.Matrix3f;
import com.jme3.math.Vector3f;

import ae.raze.util.GeometryBuilder;

/**
 * Handles the movement for the cars
 * @author meyer
 */
public class CarListener implements ActionListener {
	
	private static final float STEER_VALUE = .5f;
	private static final float ACCEL_VALUE = 800f;
	private static final float BRAKE_VALUE = 40;
	private Car car;
	
	public CarListener(Car car) {
		this.car = car;
	}

	/* (non-Javadoc)
	 * @see com.jme3.input.controls.ActionListener#onAction(java.lang.String, boolean, float)
	 */
	@Override
	public void onAction(String binding, boolean keyPressed, float tpf) {

		VehicleControl player = car.getPlayer();
		
    	if (!car.isPlayer()) {
    		return;
    	}
    	
        if (binding.equals("Lefts")) {
            if (keyPressed) {
            	car.addSteeringValue(STEER_VALUE);
            } else {
            	car.addSteeringValue(-STEER_VALUE);
            }
        } else if (binding.equals("Rights")) {
            if (keyPressed) {
            	car.addSteeringValue(-STEER_VALUE);
            } else {
            	car.addSteeringValue(STEER_VALUE);
            }
        } //note that our fancy car actually goes backwards..
        else if (binding.equals("Ups")) {
            if (keyPressed) {
            	car.addAccelerationValue(-ACCEL_VALUE);
            } else {
            	car.addAccelerationValue(ACCEL_VALUE);
            }
            player.setCollisionShape(CollisionShapeFactory.createDynamicMeshShape(GeometryBuilder.findGeom(car.getCarNode(), "Car")));
        } else if (binding.equals("Downs")) {
            if (keyPressed) {
                if (Math.abs(player.getCurrentVehicleSpeedKmHour()) < 1.0f) {
                	//reverse!
                	player.accelerate(ACCEL_VALUE);
                } else {
                	player.brake(BRAKE_VALUE);
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
