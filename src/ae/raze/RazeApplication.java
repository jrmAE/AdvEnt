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
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.light.DirectionalLight;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.shadow.BasicShadowRenderer;

import ae.raze.model.Car;
import ae.raze.scene.World;

/**
 * Have you played SuperSprint on NES? 
 * The goal is that this game will feel like that
 * @author meyer
 *
 */
public class RazeApplication extends SimpleApplication {

    private BulletAppState bulletAppState;

    /**
     * Where the magic happens
     * @param args
     */
    public static void main(String[] args) {
    	RazeApplication app = new RazeApplication();       
        app.start();
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
        World.createWorld(rootNode, assetManager, bulletAppState.getPhysicsSpace());
        
        //TODO reposition the car
        Car car = new Car(assetManager, inputManager, "Models/Ferrari/Car.scene");
        rootNode.attachChild(car.getCarNode());
        getPhysicsSpace().add(car.getPlayer());
        
        DirectionalLight dl = new DirectionalLight();
        dl.setDirection(new Vector3f(-0.5f, -1f, -0.3f).normalizeLocal());
        rootNode.addLight(dl);

        dl = new DirectionalLight();
        dl.setDirection(new Vector3f(0.5f, -0.1f, 0.3f).normalizeLocal());
    }

    /**
     * Sets the camera to be a top down view.
     */
    private void setTopDown() {
        Vector3f left = new Vector3f(0,0,0);
        Vector3f up = new Vector3f(0,0,50);
        Vector3f direction = new Vector3f(0,0,45);
        Vector3f height = new Vector3f(0,150, 0);
        cam.setAxes(left, up, direction);
        cam.setLocation(height);
        //This quaternion rotates the camera 90* from default
        Quaternion q = new Quaternion(.5f, .5f, -.5f, .5f);
        cam.setRotation(q);
    }
    
    private PhysicsSpace getPhysicsSpace() {
        return bulletAppState.getPhysicsSpace();
    }

    @Override
    public void simpleUpdate(float tpf) {
    	//TODO a heads up display could be updated here
    }
}
