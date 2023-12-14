/**
 * @author
 * Maksim Jaroslavcevas radioboos@gmail.com
*/

package tt2.common;

import com.raylib.java.raymath.Vector2;
import com.raylib.java.raymath.Vector3;

public interface IPositioned {
    public Vector3 getPosition();

    public void setPosition(Vector3 newPos);

    public Vector2 getIsometricPosition();
}
