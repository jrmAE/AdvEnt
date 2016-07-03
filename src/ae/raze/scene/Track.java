/**
 * AE Application RAZE
 * Track.java
 */
package ae.raze.scene;

import java.util.ArrayList;
import java.util.List;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

import ae.raze.util.ApplicationManager;

/**
 * TODO
 * This class is a  mess... It needs to create the walls and then provide 
 * 
 * This is track wall based on a 3d model exported from Blender. 
 * This should be expande further. For now... it'll be ignored
 * 
 * When called, this can be instantiated as the following:
 * new Track("Models/tracks/generic_walls.scene");	
 * 
 * @author meyer
 */
public class Track {
	
	private List<Geometry> wallGeometry = new ArrayList<Geometry>();
	private Node trackNode = null;
	private Spatial trackSpatial = null;
	private Geometry trackGeo = null;
	private String trackScene = "Models/tracks/generic_walls.scene";
	
	
	/**
	 * TODO clean up the parameters - rootnode and physics space don't belong
	 * @param assetManager
	 * @param trackScene - String. Such as "Models/tracks/generic_walls.scene"
	 */
	public Track() {
		//this.trackScene = trackScene;
		createTrack();
	}
	
	private void createTrack() {
		//The inner wall
		wallGeometry.addAll(TrackBuilder.smallSquare());
		
		//The outer wall
		wallGeometry.addAll(TrackBuilder.largeSquare());
		
		mapDrivingArea();
		
	}
	
	private void mapDrivingArea() { 
		Vector3f startingLine = new Vector3f(40, 0, 0);
		
		AssetManager assetManager = ApplicationManager.INSTANCE.getAssetManager();
		Node rootNode = ApplicationManager.INSTANCE.getRootNode();
		PhysicsSpace space = ApplicationManager.INSTANCE.getSpace();
		
//		//TODO generateVertWalls(-50,-80,110);
//		for (int i = 0; i< 50; i++){
//		
//			Box box = new Box(1,1,1);
//			Geometry boxGeometry = new Geometry("Box", box); 
//			Material boxMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//			boxMaterial.setColor("Color", ColorRGBA.Blue);
//			boxGeometry.setMaterial(boxMaterial); 
//			boxGeometry.setLocalTranslation(37+i,0,0); 
//			// RigidBodyControl automatically uses box collision shapes when attached to single geometry with box mesh 
//			boxGeometry.addControl(new RigidBodyControl(0)); 
//			rootNode.attachChild(boxGeometry); 
//			space.add(boxGeometry); 
//
//		}
//		boolean collide = false;
//		for (int i = 0; i < 10; i++) {
//			CollisionResults results = new CollisionResults();
//			Box box = new Box(0,0,0);
//			Geometry boxGeometry = new Geometry("Box", box); 
//			boxGeometry.setLocalTranslation(currentLocation.x-i, currentLocation.y, currentLocation.z); 
//			boolean collideWith = false;
//			BoundingVolume outerWalls = ApplicationManager.INSTANCE.getOuterWall();
//			int result = boxGeometry.collideWith(outerWalls, results);
//			
//		}
		
		
		//start at the startline line
		//proceed forward until we can turn left (heading south)
		//proceed forward until we can turn left (heading east)
		//proceed forward until we can turn left (heading north)
		//proceed forward until the starting line (heading west)
		//at the starting line look one space up.
		//  if one space up is still an open line, repeat
		//  if one space up is the wall we are done. 
		//apply values
		//start with a default set of values
		// - middle line has the highest value.
		
		//this map will be updated and each car will have its own evaluation of 
		//the current 'high' values. 
		
	}
	
	/**
	 * Eventually this should pick from a list and return the selected one.
	 */
	@SuppressWarnings("unused")
	private void createBlenderTrack() {
		AssetManager assetManager = ApplicationManager.INSTANCE.getAssetManager();
		Node rootNode = ApplicationManager.INSTANCE.getRootNode();
		PhysicsSpace space = ApplicationManager.INSTANCE.getSpace();
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
