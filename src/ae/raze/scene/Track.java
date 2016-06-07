/**
 * AE Application RAZE
 * Track.java
 */
package ae.raze.scene;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 * Everything you'll need for a track.
 * 
 * @author meyer
 */
public class Track {
	
	private Node trackNode = null;
	private Spatial trackSpatial = null;
	private Geometry trackGeo = null;
	private String trackScene = null;
	
	/**
	 * @param assetManager
	 * @param trackScene - String. Such as "Models/tracks/generic_walls.scene"
	 */
	public Track(AssetManager assetManager, String trackScene) {
		this.trackScene = trackScene;
		createTrack(assetManager);
	}
	/**
	 * Eventually this should pick from a list and return the selected one.
	 */
	private void createTrack(AssetManager assetManager) {
		trackSpatial = assetManager.loadModel(trackScene); 
		trackNode = (Node)trackSpatial;
		findGeom(trackSpatial);
		trackGeo.addControl(new RigidBodyControl(0));
	}
	
	/**
	 * From the Track's Node element, get the Geometry
	 * @param spatial
	 */
	private void findGeom(Spatial spatial) {
	    if (spatial instanceof Node) {
	        Node node = (Node) spatial;
	        for (int i = 0; i < node.getQuantity(); i++) {
	            Spatial child = node.getChild(i);
	            findGeom(child);
	        }
	    } else if (spatial instanceof Geometry) {
	    	System.out.println("B" + spatial.getName());
	    	trackGeo = (Geometry)spatial;
	    }
	}
	/**
	 * @return the trackNode
	 */
	public Node getTrackNode() {
		return trackNode;
	}
	/**
	 * @return the trackSpatial
	 */
	public Spatial getTrackSpatial() {
		return trackSpatial;
	}
	/**
	 * @return the trackGeo
	 */
	public Geometry getTrackGeo() {
		return trackGeo;
	}
	/**
	 * @return the trackScene
	 */
	public String getTrackScene() {
		return trackScene;
	}
}
