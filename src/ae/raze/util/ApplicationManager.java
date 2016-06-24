/**
 * AE Application RAZE
 * ApplicationManager.java
 */
package ae.raze.util;

import com.jme3.asset.AssetManager;
import com.jme3.bounding.BoundingVolume;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.scene.Node;

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
	private BoundingVolume outerWall = null;
	private BoundingVolume innerWall = null;
	
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

	/**
	 * @return the outerWall
	 */
	public BoundingVolume getOuterWall() {
		return outerWall;
	}

	/**
	 * @param outerWall the outerWall to set
	 */
	public void setOuterWall(BoundingVolume outerWall) {
		this.outerWall = outerWall;
	}

	/**
	 * @return the innerWall
	 */
	public BoundingVolume getInnerWall() {
		return innerWall;
	}

	/**
	 * @param innerWall the innerWall to set
	 */
	public void setInnerWall(BoundingVolume innerWall) {
		this.innerWall = innerWall;
	}
	
	

}
