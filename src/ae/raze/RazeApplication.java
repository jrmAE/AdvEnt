/*
 * Copyright (c) 2009-2012 jMonkeyEngine
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'jMonkeyEngine' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package ae.raze;

import com.jme3.app.SimpleApplication;
import com.jme3.bounding.BoundingBox;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.math.FastMath;
import com.jme3.math.Matrix3f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.shadow.BasicShadowRenderer;

import ae.raze.util.GeometryBuilder;

/**
 * Have you played SuperSprint on NES? 
 * The goal is that this game will feel like that
 * @author meyer
 *
 */
public class RazeApplication extends SimpleApplication implements ActionListener {

    private BulletAppState bulletAppState;
    private VehicleControl player;
    private float wheelRadius;
    private float steeringValue = 0;
    private float accelerationValue = 0;
    private Node carNode;

    public static void main(String[] args) {
    	RazeApplication app = new RazeApplication();       
        app.start();
    }

    private void setupKeys() {
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

    @Override
    public void simpleInitApp() {
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        if (settings.getRenderer().startsWith("LWJGL")) {
            BasicShadowRenderer bsr = new BasicShadowRenderer(assetManager, 512);
            bsr.setDirection(new Vector3f(-0.5f, -0.3f, -0.3f).normalizeLocal());
        }

        setTopDown();
        setupKeys();
        World.createWorld(rootNode, assetManager, bulletAppState.getPhysicsSpace());
        buildCar();

        DirectionalLight dl = new DirectionalLight();
        dl.setDirection(new Vector3f(-0.5f, -1f, -0.3f).normalizeLocal());
        rootNode.addLight(dl);

        dl = new DirectionalLight();
        dl.setDirection(new Vector3f(0.5f, -0.1f, 0.3f).normalizeLocal());
    }

    private void setTopDown() {
        Vector3f left = new Vector3f(0,0,0);
        Vector3f up = new Vector3f(0,0,50);
        Vector3f direction = new Vector3f(0,0,45);
        Vector3f height = new Vector3f(0,100, 0);
        cam.setAxes(left, up, direction);
        cam.setLocation(height);
    }
    
    private PhysicsSpace getPhysicsSpace() {
        return bulletAppState.getPhysicsSpace();
    }

    
    /**
     * TODO
     * we want to have a total of four cars race, so this needs to be 
     * built differently
     */
    private void buildCar() {
        float stiffness = 120.0f;//200=f1 car
        float compValue = 0.2f; //(lower than damp!)
        float dampValue = 0.3f;
        final float mass = 400;

        //Load model and get chassis Geometry
        carNode = (Node)assetManager.loadModel("Models/Ferrari/Car.scene");
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

        rootNode.attachChild(carNode);
        getPhysicsSpace().add(player);
        
//		//TODO - working here blender needs a UV map for my ogre export of the walls
//		rootNode.attachChild(Track.createTrack(assetManager));
    }

    /**
     * TODO
     * make this appear quicker in the camera
     * @see com.jme3.input.controls.ActionListener#onAction(java.lang.String, boolean, float)
     */
    public void onAction(String binding, boolean value, float tpf) {
        if (binding.equals("Lefts")) {
            if (value) {
                steeringValue += .5f;
            } else {
                steeringValue += -.5f;
            }
            player.steer(steeringValue);
        } else if (binding.equals("Rights")) {
            if (value) {
                steeringValue += -.5f;
            } else {
                steeringValue += .5f;
            }
            player.steer(steeringValue);
        } //note that our fancy car actually goes backwards..
        else if (binding.equals("Ups")) {
            if (value) {
                accelerationValue -= 800;
            } else {
                accelerationValue += 800;
            }
            player.accelerate(accelerationValue);
            player.setCollisionShape(CollisionShapeFactory.createDynamicMeshShape(GeometryBuilder.findGeom(carNode, "Car")));
        } else if (binding.equals("Downs")) {
            if (value) {
                player.brake(40f);
            } else {
                player.brake(0f);
            }
        } else if (binding.equals("Reset")) {
            if (value) {
                System.out.println("Reset");
                player.setPhysicsLocation(Vector3f.ZERO);
                player.setPhysicsRotation(new Matrix3f());
                player.setLinearVelocity(Vector3f.ZERO);
                player.setAngularVelocity(Vector3f.ZERO);
                player.resetSuspension();
            } else {
            }
        }
    }

    @Override
    public void simpleUpdate(float tpf) {
    	//TODO should I be doing anything as the car moves?
    }
}
