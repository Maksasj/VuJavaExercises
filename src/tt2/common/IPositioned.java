package tt2.common;

import com.raylib.java.raymath.Vector2;

public interface IPositioned {
    public Vector2 getPosition();

    public void setPosition(Vector2 newPos);

    public Vector2 getIsometricPosition();
}
