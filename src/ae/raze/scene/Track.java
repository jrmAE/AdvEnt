/**
 * AE Application RAZE
 * Track.java
 */
package ae.raze.scene;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.PhysicsSpace;
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
	 * TODO clean up the parameters - rootnode and physics space don't belong
	 * @param assetManager
	 * @param trackScene - String. Such as "Models/tracks/generic_walls.scene"
	 */
	public Track(AssetManager assetManager, String trackScene, Node rootNode, PhysicsSpace space) {
		this.trackScene = trackScene;
		createTrack(assetManager, rootNode, space);
	}
	
	/**
	 * Eventually this should pick from a list and return the selected one.
	 */
	private void createTrack(AssetManager assetManager, Node rootNode, PhysicsSpace space) {
		trackSpatial = assetManager.loadModel(trackScene); 
		trackNode = (Node)trackSpatial;
		findGeom(trackSpatial);
		trackNode.setLocalTranslation(-45, 0, 0);
		trackGeo.scale(.7f);
		trackGeo.addControl(new RigidBodyControl(0));
		rootNode.attachChild(trackGeo); 
		space.add(trackGeo); 
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
