/**
 * AE Application RAZE
 * Track.java
 */
package ae.raze;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

import ae.raze.util.GeometryBuilder;

/**
 * @author meyer
 *
 */
public class Track {
	
	/**
	 * Eventually this should pick from a list and return the selected one.
	 */
	public static Node createTrack(AssetManager assetManager) {
		//TODO - working here blender needs a UV map for my ogre export of the walls
		Spatial wallSpatial = assetManager.loadModel("Models/tracks/generic_walls.scene"); 
		Node walls = (Node)wallSpatial;
//		Geometry wallGeo = GeometryBuilder.findGeom(walls, "Walls");
		return walls;
	}

}
