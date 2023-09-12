package tt2.entity;

import com.raylib.java.raymath.Vector3;
import tt2.common.IRenderable;

public abstract class Entity extends GameObject implements IRenderable {
    public Entity(Vector3 pos) {
        super(pos);
    }
}
