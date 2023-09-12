package tt2.world.tile;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.raymath.Vector3;
import com.raylib.java.raymath.Vector3;
import tt2.Tartar2;
import tt2.textures.TextureAssetManager;

public class DefaultTile extends Tile {
    public DefaultTile(Vector3 position) {
        super(position);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render() {
        Vector2 tilePosition = getIsometricPosition();
        Vector3 cameraPosition = Tartar2.activeScene.getActiveCamera().getPosition();

        if (isTintColorApplied()) {
            Tartar2.raylib.textures.DrawTextureEx(
                    TextureAssetManager.BASE_TILE_TEXTURE,
                    new Vector2(
                            tilePosition.x * DEFAULT_TILE_SCALE * 32.0f - DEFAULT_TILE_SCALE * 16 + cameraPosition.x,
                            tilePosition.y * DEFAULT_TILE_SCALE * 32.0f + cameraPosition.z - getYOffset()
                    ),
                    0,
                    3,
                    getTintColor()
            );
        } else {
            Tartar2.raylib.textures.DrawTextureEx(
                    TextureAssetManager.BASE_TILE_TEXTURE,
                    new Vector2(
                            tilePosition.x * DEFAULT_TILE_SCALE * 32.0f - DEFAULT_TILE_SCALE * 16 + cameraPosition.x,
                            tilePosition.y * DEFAULT_TILE_SCALE * 32.0f + cameraPosition.z - getYOffset()
                    ),
                    0,
                    3,
                    Color.WHITE
            );
        }
    }
}
