package tt2.common;

import com.raylib.java.raymath.Vector2;

public class Camera implements IPositioned {
    private Vector2 position;

    public Camera(Vector2 pos) {
        position = pos;
    }

    public void move(Vector2 moveDir) {
        position.x += moveDir.x;
        position.y += moveDir.y;
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public void setPosition(Vector2 newPos) {
        position = newPos;
    }

    @Override
    public Vector2 getIsometricPosition() {
        return null;
    }
}
