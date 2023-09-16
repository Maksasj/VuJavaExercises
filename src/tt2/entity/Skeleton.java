package tt2.entity;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.raymath.Vector3;
import tt2.Tartar2;
import tt2.common.GameController;
import tt2.common.IsometricDirection;
import tt2.common.Utils;
import tt2.common.camera.Camera;
import tt2.textures.TextureAssetManager;
import tt2.world.World;
import tt2.world.tile.Tile;

public class Skeleton extends GroundMob {
    public Skeleton(Vector3 pos) {
        super(pos, new Statblock(2, 1, 1, 1));
    }

    @Override
    public void step() {
        super.step();

        World world = GameController.getWorld();

        Vector3 position = getPosition();

        int posX = Math.round(position.x);
        int posY = Math.round(position.y);
        int posZ = Math.round(position.z);

        IsometricDirection directionToGo = world.getPathCalculator().getFlatDirectionAt(posX, posY, posZ);

        switch (directionToGo) {
            case LEFT_UP -> movePosition(new Vector3(-1.0f, 0.0f, 0.0f));
            case RIGHT_UP ->  movePosition(new Vector3(0.0f, 0.0f, -1.0f));
            case RIGHT_DOWN -> movePosition(new Vector3(1.0f, 0.0f, 0.0f));
            case LEFT_DOWN -> movePosition(new Vector3(0.0f, 0.0f, 1.0f));
            default -> {}
        }

        // int turn = Utils.getRandomInRange(0, 4);
        // Tile groundTile = getGroundTiles()[turn];
        // Tile sideTile = getSideTiles()[turn];

        // We check is there any ground tile, and also check is there no any wall
        // if(groundTile == null || sideTile != null)
        //     return;
//
        // if(turn == 0)
        //     movePosition(new Vector3(-1.0f, 0.0f, 0.0f));
        // else if (turn == 1)
        //     movePosition(new Vector3(1.0f, 0.0f, 0.0f));
        // else if (turn == 2)
        //     movePosition(new Vector3(0.0f, 0.0f, -1.0f));
        // else if (turn == 3)
        //     movePosition(new Vector3(0.0f, 0.0f, 1.0f));
    }

    @Override
    public void render() {
        Camera activeCamera = Tartar2.activeScene.getActiveCamera();

        Vector2 tilePosition = getIsometricPosition();
        Vector3 cameraPosition = Tartar2.activeScene.getActiveCamera().getPosition();
        float cameraZoom = activeCamera.getZoom();

        Tartar2.raylib.textures.DrawTextureEx(
                TextureAssetManager.SKELETON_TEXTURE,
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
