/**
 * @author
 * Maksim Jaroslavcevas radioboos@gmail.com
*/

package tt2.common;

import com.google.gson.JsonObject;
import com.raylib.java.raymath.Vector3;

public abstract class CommonFactory {
    static protected Vector3 getVector3(JsonObject obj) {
        float x = obj.get("x").getAsFloat();
        float y = obj.get("y").getAsFloat();
        float z = obj.get("z").getAsFloat();

        return new Vector3(x, y, z);
    }
}
