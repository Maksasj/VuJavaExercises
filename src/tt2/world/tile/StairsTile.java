package tt2.world.tile;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.raymath.Vector3;
import com.raylib.java.textures.Texture2D;
import tt2.Tartar2;
import tt2.common.GameController;
import tt2.common.IsometricRotation;
import tt2.common.VisibilityLevel;
import tt2.common.camera.Camera;
import tt2.entity.Player;
import tt2.scene.GameScene;
import tt2.textures.TextureAssetManager;
import tt2.world.World;

public class StairsTile extends RotatableTile {
    private Tile sideTile;
    private Tile upSideTile;

    public StairsTile(Vector3 position, IsometricRotation tileRotation) {
        super(position, TileDensity.HOLLOW, tileRotation);

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

        IsometricRotation rotation = getIsometricRotation();

        if((playerX == selfX) && (playerY == selfY) && (playerZ == selfZ)) {
            switch(rotation) {
                case LEFT_UP -> player.movePosition(new Vector3(-1.0f, 1.0f, 0.0f));
                case RIGHT_UP -> player.movePosition(new Vector3(0.0f, 1.0f, -1.0f));
                case RIGHT_DOWN -> player.movePosition(new Vector3(1.0f, 1.0f, 0.0f));
                case LEFT_DOWN -> player.movePosition(new Vector3(0.0f, 1.0f, 1.0f));
            }
        }
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

        IsometricRotation rotation = getIsometricRotation();

        switch (rotation) {
            case LEFT_UP -> {
                sideTile = world.getTileAt(posX - 1, posY, posZ);
                upSideTile = world.getTileAt(posX - 1, posY + 1, posZ);
            }
            case RIGHT_UP -> {
                sideTile = world.getTileAt(posX, posY, posZ - 1);
                upSideTile = world.getTileAt(posX, posY + 1, posZ - 1);
            }
            case RIGHT_DOWN -> {
                sideTile = world.getTileAt(posX + 1, posY, posZ);
                upSideTile = world.getTileAt(posX + 1, posY + 1, posZ);
            }
            case LEFT_DOWN -> {
                sideTile = world.getTileAt(posX, posY, posZ + 1);
                upSideTile = world.getTileAt(posX, posY + 1, posZ + 1);
            }
        }
    }

    private Texture2D getTexture() {
        IsometricRotation rotation = getIsometricRotation();

        return switch (rotation) {
            case LEFT_UP -> TextureAssetManager.STAIRS_TILE1_TEXTURE;
            case RIGHT_UP -> TextureAssetManager.STAIRS_TILE0_TEXTURE;
            case RIGHT_DOWN -> TextureAssetManager.STAIRS_TILE2_TEXTURE;
            case LEFT_DOWN -> TextureAssetManager.STAIRS_TILE3_TEXTURE;
        };
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

        Tartar2.raylib.textures.DrawTextureEx(getTexture(), texturePos, 0, cameraZoom, tintColor);
    }
}
