package tt2.world;

import com.raylib.java.raymath.Vector2;
import tt2.common.IPositioned;
import tt2.common.IStepable;
import tt2.common.ITickable;

public class Entity implements ITickable, IStepable, IPositioned {
    private Vector2 position;

    public Entity(Vector2 pos) {
        position = pos;
    }

    @Override
    public void step() {

    }

    @Override
    public void tick() {

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
        float x = position.x;
        float y = position.y;

        return new Vector2(0.5f * x + y * -0.5f, 0.25f * x + y * 0.25f);
    }
}
