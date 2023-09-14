package tt2.world.tile;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.raymath.Vector3;
import tt2.Tartar2;
import tt2.common.GameController;
import tt2.common.VisibilityLevel;
import tt2.common.camera.Camera;
import tt2.entity.Player;
import tt2.scene.GameScene;
import tt2.textures.TextureAssetManager;
import tt2.world.World;

public class StairsTile extends Tile {
    private Tile sideTile;
    private Tile upSideTile;

    public StairsTile(Vector3 position) {
        super(position, TileDensity.HOLLOW);

        sideTile = null;
        upSideTile = null;
    }

    @Override
    public void tick() {
        updateSideTiles();

        Player player = Tartar2.gameScene.getGameController().getPlayer();
        if(player == null)
            return;

        int playerX = Math.round(player.getIntermediatePosition().x);
        int playerY = Math.round(player.getIntermediatePosition().y);
        int playerZ = Math.round(player.getIntermediatePosition().z);

        int selfX = Math.round(getPosition().x);
        int selfY = Math.round(getPosition().y);
        int selfZ = Math.round(getPosition().z);

        if((playerX == selfX) && (playerY == selfY) && (playerZ == selfZ))
            player.movePosition(new Vector3(0.0f, 1.0f, -1.0f));
    }

    @Override
    public void doVisibilityPostProcessing() {
        if(sideTile != null && sideTile.getVisibilityLevel() == VisibilityLevel.TRANSPARENT)
                sideTile.setVisibilityLevel(VisibilityLevel.SEMI_VISIBLE);

        if(upSideTile != null && upSideTile.getVisibilityLevel() == VisibilityLevel.TRANSPARENT)
            upSideTile.setVisibilityLevel(VisibilityLevel.SEMI_VISIBLE);
    }

    public void updateSideTiles() {
        World world = GameController.getWorld();

        int posX = Math.round(getPosition().x);
        int posY = Math.round(getPosition().y);
        int posZ = Math.round(getPosition().z);

        sideTile = world.getTileAt(posX, posY, posZ - 1);
        upSideTile = world.getTileAt(posX, posY + 1, posZ - 1);
    }

    @Override
    public void render() {
        Camera activeCamera = Tartar2.activeScene.getActiveCamera();

        Vector2 tilePosition = getIsometricPosition();
        Vector3 cameraPosition = activeCamera.getPosition();
        float cameraZoom = activeCamera.getZoom();

        if (isTintColorApplied()) {
            Tartar2.raylib.textures.DrawTextureEx(
                    TextureAssetManager.STAIRS_TILE_TEXTURE,
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
                    TextureAssetManager.STAIRS_TILE_TEXTURE,
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
