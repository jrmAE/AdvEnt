/**
 * AE Application RAZE
 * ApplicationManager.java
 */
package ae.raze.util;

import java.util.List;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;

import ae.raze.model.Car;
import ae.raze.scene.CameraView;
import ae.raze.scene.Track;
import ae.raze.scene.World;

/**
 * Singleton to manage the stuff that everything that the nodes
 * of this game need. 
 * 
 * @author meyer
 *
 */
public enum ApplicationManager {
	
	INSTANCE;
	private PhysicsSpace space;
	private AssetManager assetMgr = null;
	private Node rootNode;
	private World world;
	private Track track;
	private Camera camera;
	private List<Car> carRoster;
	
	/**
	 * @return the carRoster
	 */
	public List<Car> getCarRoster() {
		return carRoster;
	}

	/**
	 * @param carRoster the carRoster to set
	 */
	public void setCarRoster(List<Car> carRoster) {
		this.carRoster = carRoster;
	}

	/**
	 * @return the world
	 */
	public World getWorld() {
		return world;
	}

	/**
	 * @param world the world to set
	 */
	public void setWorld(World world) {
		this.world = world;
	}

	/**
	 * @return the track
	 */
	public Track getTrack() {
		return track;
	}

	/**
	 * @param track the track to set
	 */
	public void setTrack(Track track) {
		this.track = track;
	}

	/**
	 * @return the camera
	 */
	public Camera getCamera() {
		return camera;
	}

	/**
	 * @param camera the camera to set
	 */
	public void setCamera(Camera camera) {
		
		CameraView.setTopDownView(camera);
		this.camera = camera;
	}

	/**
	 * @return the rootNode
	 */
	public Node getRootNode() {
		return rootNode;
	}

	/**
	 * @param rootNode the rootNode to set
	 */
	public void setRootNode(Node rootNode) {
		this.rootNode = rootNode;
	}

	/**
	 * @return the space
	 */
	public PhysicsSpace getSpace() {
		return space;
	}

	/**
	 * @param space the space to set
	 */
	public void setSpace(PhysicsSpace space) {
		this.space = space;
	}

	public void setAssetManager(AssetManager assetMgr) {
		this.assetMgr = assetMgr;
	}
	
	public AssetManager getAssetManager() {
		return this.assetMgr;
	}

}
