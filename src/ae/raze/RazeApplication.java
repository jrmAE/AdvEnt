/**
 * AE application RAZE
 */
package ae.raze;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;

import ae.raze.model.character.Person;
import ae.raze.util.ApplicationManager;

/**
 * @author meyer
 */
public class RazeApplication extends SimpleApplication {

	/* (non-Javadoc)
	 * @see com.jme3.app.SimpleApplication#simpleInitApp()
	 */
	@Override
	public void simpleInitApp() {
		
		ApplicationManager.INSTANCE.setAssetManager(assetManager);
        Person character1 = new Person(new Vector3f(1,-1,1));
        rootNode.attachChild(character1.getPerson());

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RazeApplication app = new RazeApplication();
        app.start(); // start the game

	}

}
