/**
 * AE Application RAZE
 * CameraView.java
 */
package ae.raze.scene;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;

/**
 * @author meyer
 *
 */
public class CameraView {
	
    /**
     * Sets the camera to be a top down view.
     */
    public static void setTopDownView(Camera cam) {
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
}
