package tt2.world.tile;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.raymath.Vector3;
import tt2.Tartar2;
import tt2.common.GameController;
import tt2.common.camera.Camera;
import tt2.entity.Player;
import tt2.textures.TextureAssetManager;

public class PortalTile extends Tile {
    public PortalTile(Vector3 position) {
        super(position, TileDensity.DENSE);
    }

    @Override
    public void step() {
        super.step();

        Player player = Tartar2.gameScene.getGameController().getPlayer();
        if(player == null)
            return;

        Vector3 playerPos = player.getIntermediatePosition();
        Vector3 selfPos = getPosition();

        int playerPosX = Math.round(playerPos.x);
        int playerPosY = Math.round(playerPos.y) - 1;
        int playerPosZ = Math.round(playerPos.z);

        int selfPosX = Math.round(selfPos.x);
        int selfPosY = Math.round(selfPos.y);
        int selfPosZ = Math.round(selfPos.z);

        if(playerPosX == selfPosX && playerPosY == selfPosY && playerPosZ == selfPosZ)
            Tartar2.gameScene.nextLevel();
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
            tilePosition.x * cameraZoom * 32.0f - cameraZoom * 16 + cameraPosition.x,
            tilePosition.y * cameraZoom * 32.0f + cameraPosition.z - getYOffset()
        );

        TextureAssetManager.tileTextures.getSubTexture(3).render(texturePos, cameraZoom, tintColor);
    }
}
