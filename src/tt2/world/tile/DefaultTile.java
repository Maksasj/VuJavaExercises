package tt2.world.tile;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import tt2.Tartar2;
import tt2.textures.TextureAssetManager;

public class DefaultTile extends Tile {
    public DefaultTile(Vector2 position) {
        super(position);
    }

    @Override
    public void render() {
        Vector2 tilePosition = getIsometricPosition();
        Vector2 cameraPosition = Tartar2.activeScene.getActiveCamera().getPosition();

        Tartar2.raylib.textures.DrawTexture(
            TextureAssetManager.BASE_TILE_TEXTURE,
            (int) (tilePosition.x * 32.0f - 16 + cameraPosition.x),
            (int) (tilePosition.y * 32.0f + cameraPosition.y),
            Color.WHITE
        );
    }
}
