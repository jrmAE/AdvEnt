/**
 * AE Application RAZE
 * ApplicationManager.java
 */
package ae.raze.util;

import com.jme3.asset.AssetManager;

/**
 * @author meyer
 *
 */
public enum ApplicationManager {
	INSTANCE;
	
	private AssetManager assetMgr = null;
	
	public void setAssetManager(AssetManager assetMgr) {
		this.assetMgr = assetMgr;
	}
	
	public AssetManager getAssetManager() {
		return this.assetMgr;
	}

}
