package tt2.entity;

import com.raylib.java.raymath.Vector2;
import com.raylib.java.raymath.Vector3;
import tt2.common.IRenderable;

import javax.swing.plaf.synth.SynthTextAreaUI;

public abstract class Entity extends GameObject implements IRenderable {
    private final Vector3 intermediatePosition;

    public Entity(Vector3 pos) {
        super(pos);

        intermediatePosition = new Vector3(pos.x, pos.y, pos.z);
    }

    private void handleSmoothMovement() {
        Vector3 newPos = getPosition();

        newPos.x += (intermediatePosition.x - newPos.x) / 10.0f;
        newPos.y += (intermediatePosition.y - newPos.y) / 10.0f;
        newPos.z += (intermediatePosition.z - newPos.z) / 10.0f;
    }

    @Override
    public void tick() {
        handleSmoothMovement();
    }

    public void movePosition(Vector3 movePos) {
        intermediatePosition.x += movePos.x;
        intermediatePosition.y += movePos.y;
        intermediatePosition.z += movePos.z;
    }
}
