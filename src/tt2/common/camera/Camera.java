/**
 * @author
 * Maksim Jaroslavcevas radioboos@gmail.com
*/

package tt2.common.camera;

import com.raylib.java.raymath.Vector2;
import com.raylib.java.raymath.Vector3;
import tt2.common.IPositioned;

import static tt2.common.Settings.DEFAULT_SPRITE_SCALE;

public class Camera implements IPositioned {
    private Vector3 position;
    private float zoom;

    public Camera(Vector3 pos) {
        position = pos;

        zoom = DEFAULT_SPRITE_SCALE;
    }

    public void move(Vector3 moveDir) {
        position.x += moveDir.x;
        position.y += moveDir.y;
        position.z += moveDir.z;
    }

    public void setZoom(float newZoom) {
        zoom = newZoom;
    }

    public float getZoom() {
        return zoom;
    }

    @Override
    public Vector3 getPosition() {
        return position;
    }

    @Override
    public void setPosition(Vector3 newPos) {
        position = newPos;
    }

    @Override
    public Vector2 getIsometricPosition() {
        return null;
    }
}
