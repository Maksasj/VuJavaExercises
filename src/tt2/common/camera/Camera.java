package tt2.common.camera;

import com.raylib.java.raymath.Vector2;
import com.raylib.java.raymath.Vector3;
import tt2.common.IPositioned;

public class Camera implements IPositioned {
    private Vector3 position;

    public Camera(Vector3 pos) {
        position = pos;
    }

    public void move(Vector3 moveDir) {
        position.x += moveDir.x;
        position.y += moveDir.y;
        position.z += moveDir.z;
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
