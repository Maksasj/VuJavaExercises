package tt2.entity;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.raymath.Vector3;
import tt2.Tartar2;
import tt2.textures.TextureAssetManager;

import static tt2.world.tile.Tile.DEFAULT_TILE_SCALE;

public class Player extends Entity {
    public Player(Vector3 pos) {
        super(pos);
    }

    @Override
    public void render() {
        Vector2 tilePosition = getIsometricPosition();
        Vector3 cameraPosition = Tartar2.activeScene.getActiveCamera().getPosition();

        Tartar2.raylib.textures.DrawTextureEx(
                TextureAssetManager.PLAYER_TEXTURE,
                new Vector2(
                        tilePosition.x * DEFAULT_TILE_SCALE * 32.0f - DEFAULT_TILE_SCALE * 16 + cameraPosition.x,
                        tilePosition.y * DEFAULT_TILE_SCALE * 32.0f + cameraPosition.z
                ),
                0,
                3,
                Color.WHITE
        );
    }
}
