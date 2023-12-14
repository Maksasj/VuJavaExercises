/**
 * @author
 * Maksim Jaroslavcevas radioboos@gmail.com
*/

package tt2.entity;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.raymath.Vector3;
import org.lwjgl.system.CallbackI;
import tt2.Tartar2;
import tt2.common.ITexture;
import tt2.common.IsometricRotation;
import tt2.common.VisibilityLevel;
import tt2.common.camera.Camera;
import tt2.textures.SubTexture;
import tt2.textures.TextureAssetManager;

public class Player extends Mob {
    private IsometricRotation rotation;

    public Player(Vector3 pos) {
        super(pos, new Statblock(4, 1, 1, 1));

        rotation = IsometricRotation.LEFT_UP;
    }

    @Override
    public void tick() {
        super.tick();
    }

    public void setRotation(IsometricRotation rotation) {
        this.rotation = rotation;
    }

    private ITexture getTexture() {
        return switch (rotation) {
            case LEFT_UP -> TextureAssetManager.mobsTexture.getSubTexture(0);
            case RIGHT_UP -> TextureAssetManager.mobsTexture.getSubTexture(1);
            case RIGHT_DOWN -> TextureAssetManager.mobsTexture.getSubTexture(2);
            case LEFT_DOWN -> TextureAssetManager.mobsTexture.getSubTexture(3);
        };
    }

    @Override
    public void render() {
        if(isDeleted())
            return;

        Camera activeCamera = Tartar2.activeScene.getActiveCamera();

        Vector2 tilePosition = getIsometricPosition();
        Vector3 cameraPosition = activeCamera.getPosition();
        float cameraZoom = activeCamera.getZoom();

        Vector2 texturePos = new Vector2(
                tilePosition.x * cameraZoom * 32.0f - cameraZoom * 16 + cameraPosition.x,
                tilePosition.y * cameraZoom * 32.0f + cameraPosition.z
        );

        getTexture().render(texturePos, cameraZoom, Color.WHITE);
    }
}
