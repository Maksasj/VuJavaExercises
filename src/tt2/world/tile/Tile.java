package tt2.world.tile;

import com.raylib.java.raymath.Vector2;
import com.raylib.java.textures.Texture2D;
import tt2.common.IRenderable;
import tt2.world.Entity;

public abstract class Tile extends Entity implements IRenderable {
    public Tile(Vector2 pos) {
        super(pos);
    }
}
