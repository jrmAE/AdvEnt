/**
 * AE Application RAZE
 * GeometryBuilder.java
 */
package ae.raze.util;

import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 * @author meyer
 */
public class GeometryBuilder {
    /**
     * Seems to search the .scene file for a spatial with the given name
     * TODO need to learn more about this
     * @param spatial
     * @param name
     * @return
     */
    public static Geometry findGeom(Spatial spatial, String name) {
        if (spatial instanceof Node) {
            Node node = (Node) spatial;
            for (int i = 0; i < node.getQuantity(); i++) {
                Spatial child = node.getChild(i);
                Geometry result = findGeom(child, name);
                if (result != null) {
                    return result;
                }
            }
        } else if (spatial instanceof Geometry) {
            if (spatial.getName().startsWith(name)) {
                return (Geometry) spatial;
            }
        }
        return null;
    }
}
