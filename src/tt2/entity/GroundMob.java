package tt2.entity;

import com.raylib.java.raymath.Vector3;
import tt2.common.GameController;
import tt2.world.World;
import tt2.world.tile.Tile;

public abstract class GroundMob extends Mob {
    private final Tile[] groundTiles;
    private final Tile[] sideTiles;

    public GroundMob(Vector3 pos, Statblock statblock) {
        super(pos, statblock);

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
