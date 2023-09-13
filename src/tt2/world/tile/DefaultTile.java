package tt2.world.tile;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.raymath.Vector3;
import tt2.Tartar2;
import tt2.common.camera.Camera;
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
        Camera activeCamera = Tartar2.activeScene.getActiveCamera();

        Vector2 tilePosition = getIsometricPosition();
        Vector3 cameraPosition = activeCamera.getPosition();
        float cameraZoom = activeCamera.getZoom();

        if (isTintColorApplied()) {
            Tartar2.raylib.textures.DrawTextureEx(
                    TextureAssetManager.BASE_TILE_TEXTURE,
                    new Vector2(
                            tilePosition.x * cameraZoom * 32.0f - cameraZoom * 16 + cameraPosition.x,
                            tilePosition.y * cameraZoom * 32.0f + cameraPosition.z - getYOffset()
                    ),
                    0,
                    cameraZoom,
                    getTintColor()
            );
        } else {
            Tartar2.raylib.textures.DrawTextureEx(
                    TextureAssetManager.BASE_TILE_TEXTURE,
                    new Vector2(
                            tilePosition.x * cameraZoom * 32.0f - cameraZoom * 16 + cameraPosition.x,
                            tilePosition.y * cameraZoom * 32.0f + cameraPosition.z - getYOffset()
                    ),
                    0,
                    cameraZoom,
                    Color.WHITE
            );
        }
    }
}
