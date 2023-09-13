package tt2.entity;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.raymath.Vector3;
import tt2.Tartar2;
import tt2.common.GameController;
import tt2.common.Utils;
import tt2.textures.TextureAssetManager;
import tt2.world.World;
import tt2.world.tile.Tile;

import static tt2.world.tile.Tile.DEFAULT_TILE_SCALE;

public class Skeleton extends Entity {
    private Tile[] tiles;

    public Skeleton(Vector3 pos) {
        super(pos);

        tiles = new Tile[4];
    }

    @Override
    public void step() {
        updateNeighbourTiles();

        int turn = Utils.getRandomInRange(0, 4);
        Tile tile = tiles[turn];

        if(tile != null) {
            if(turn == 0)
                movePosition(new Vector3(-1.0f, 0.0f, 0.0f));
            else if (turn == 1)
                movePosition(new Vector3(1.0f, 0.0f, 0.0f));
            else if (turn == 2)
                movePosition(new Vector3(0.0f, 0.0f, -1.0f));
            else if (turn == 3)
                movePosition(new Vector3(0.0f, 0.0f, 1.0f));
        }
    }

    public void updateNeighbourTiles() {
        World world = GameController.getWorld();

        int playerX = Math.round(getPosition().x);
        int playerY = Math.round(getPosition().y);
        int playerZ = Math.round(getPosition().z);

        tiles[0] = world.getTileAt(playerX - 1, playerY - 1, playerZ);
        tiles[1] = world.getTileAt(playerX + 1, playerY - 1, playerZ);
        tiles[2] = world.getTileAt(playerX, playerY - 1, playerZ - 1);
        tiles[3] = world.getTileAt(playerX, playerY - 1, playerZ + 1);
    }

    @Override
    public void render() {
        Vector2 tilePosition = getIsometricPosition();
        Vector3 cameraPosition = Tartar2.activeScene.getActiveCamera().getPosition();

        Tartar2.raylib.textures.DrawTextureEx(
                TextureAssetManager.SKELETON_TEXTURE,
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
