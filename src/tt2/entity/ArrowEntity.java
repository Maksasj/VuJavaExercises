package tt2.entity;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.raymath.Vector3;
import tt2.Tartar2;
import tt2.common.IStatable;
import tt2.common.camera.Camera;
import tt2.textures.TextureAssetManager;

public class ArrowEntity extends Entity {
    private Vector3 direction;
    private float arrowSpeed;
    private float travelDistance;
    private Vector3 startPos;

    public ArrowEntity(Vector3 pos, Vector3 direction, float arrowSpeed, float travelDistance) {
        super(pos);

        startPos = new Vector3(pos.x, pos.y, pos.z);

        this.travelDistance = travelDistance;
        this.direction = direction;
        this.arrowSpeed = arrowSpeed;
    }

    @Override
    public void tick() {
        super.tick();

        Vector3 toMove = new Vector3(
                direction.x * arrowSpeed,
                direction.y * arrowSpeed,
                direction.z * arrowSpeed
        );

        movePosition(toMove);

        Vector3 position = getPosition();

        float x0 = position.x - startPos.x;
        float y0 = position.y - startPos.y;
        float z0 = position.z - startPos.z;

        float traveledDistance = x0*x0 + y0*y0 + z0*z0;

        if(traveledDistance >= travelDistance*travelDistance)
            markAsDeleted();
    }

    @Override
    public void render() {
        Camera activeCamera = Tartar2.activeScene.getActiveCamera();

        Vector2 tilePosition = getIsometricPosition();
        Vector3 cameraPosition = Tartar2.activeScene.getActiveCamera().getPosition();
        float cameraZoom = activeCamera.getZoom();

        Tartar2.raylib.textures.DrawTextureEx(
                TextureAssetManager.ARROW_TEXTURE,
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
