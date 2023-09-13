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

public abstract class GroundEntity extends Entity {
    private final Tile[] groundTiles;
    private final Tile[] sideTiles;

    public GroundEntity(Vector3 pos) {
        super(pos);

        groundTiles = new Tile[4];
        sideTiles = new Tile[4];
    }

    @Override
    public void step() {
        updateNeighbourTiles();
    }

    public Tile[] getGroundTiles() {
        return groundTiles;
    }

    public Tile[] getSideTiles() {
        return sideTiles;
    }

    public void updateNeighbourTiles() {
        World world = GameController.getWorld();

        int playerX = Math.round(getPosition().x);
        int playerY = Math.round(getPosition().y);
        int playerZ = Math.round(getPosition().z);

        World.getNeighbourGroundTiles(world, playerX, playerY, playerZ, groundTiles);
        World.getNeighbourTiles(world, playerX, playerY, playerZ, sideTiles);
    }
}
