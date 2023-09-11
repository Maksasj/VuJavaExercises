package tt2.world.tile;

import com.raylib.java.core.Color;
import tt2.Tartar2;
import tt2.textures.TextureAssetManager;

public class DefaultTile extends Tile {
    public DefaultTile() {

    }

    @Override
    public void render() {
        Tartar2.raylib.textures.DrawTexture(TextureAssetManager.BASE_TILE_TEXTURE, 100, 100, Color.WHITE);
    }
}
