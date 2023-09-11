package tt2.world.tile;

import com.raylib.java.raymath.Vector2;
import com.raylib.java.raymath.Vector3;
import tt2.common.IRenderable;
import tt2.world.Entity;

public abstract class Tile extends Entity implements IRenderable {
    static final float DEFAULT_TILE_SCALE = 3.0f;
    public Tile(Vector3 pos) {
        super(pos);
    }
}
