/**
 * @author
 * Maksim Jaroslavcevas radioboos@gmail.com
*/

package tt2.world.tile;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.raymath.Vector3;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.textures.rTextures;
import tt2.Tartar2;
import tt2.common.VisibilityLevel;
import tt2.common.camera.Camera;
import tt2.textures.TextureAssetManager;

public class DefaultTile extends Tile {
    public DefaultTile(Vector3 position) {
        super(position, TileDensity.DENSE);
    }

    @Override
    public void render() {
        Camera activeCamera = Tartar2.activeScene.getActiveCamera();

        Vector2 tilePosition = getIsometricPosition();
        Vector3 cameraPosition = activeCamera.getPosition();
        float cameraZoom = activeCamera.getZoom();

        Color tintColor = Color.WHITE;

        if (isTintColorApplied())
            tintColor = getTintColor();

        Vector2 texturePos = new Vector2(
                tilePosition.x * cameraZoom * 32.0f + cameraPosition.x - cameraZoom * 16,
                tilePosition.y * cameraZoom * 32.0f + cameraPosition.z - getYOffset()
        );

        TextureAssetManager.tileTextures.getSubTexture(0).render(texturePos, cameraZoom, tintColor);
    }
}
