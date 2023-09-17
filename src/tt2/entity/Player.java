package tt2.entity;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.raymath.Vector3;
import tt2.Tartar2;
import tt2.common.VisibilityLevel;
import tt2.common.camera.Camera;
import tt2.textures.TextureAssetManager;

public class Player extends Mob {
    public Player(Vector3 pos) {
        super(pos, new Statblock(4, 1, 1, 1));
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void render() {
        if(isDeleted())
            return;

        Camera activeCamera = Tartar2.activeScene.getActiveCamera();

        Vector2 tilePosition = getIsometricPosition();
        Vector3 cameraPosition = activeCamera.getPosition();
        float cameraZoom = activeCamera.getZoom();

        Tartar2.raylib.textures.DrawTextureEx(
                TextureAssetManager.PLAYER_TEXTURE,
                new Vector2(
                        tilePosition.x * cameraZoom * 32.0f - cameraZoom * 16 + cameraPosition.x,
                        tilePosition.y * cameraZoom * 32.0f + cameraPosition.z
                ),
                0,
                cameraZoom,
                Color.WHITE
        );
    }
}
