package tt2.world;

import com.raylib.java.raymath.Vector2;
import com.raylib.java.raymath.Vector3;
import tt2.common.IRenderable;
import tt2.common.IStepable;
import tt2.common.ITickable;
import tt2.world.tile.DefaultTile;
import tt2.world.tile.Tile;
import tt2.world.tile.WavyTile;

public class World implements IRenderable, ITickable, IStepable {
    private Tile[][][] tiles;

    public World() {
        tiles = new Tile[16][16][16];

        for(int x = 0; x < 16; ++x) {
            for(int y = 0; y < 1; ++y) {
                for(int z = 0; z < 16; ++z) {
                    tiles[x][y][z] = new DefaultTile(new Vector3(x, y, z));
                }
            }
        }
    }

    @Override
    public void render() {
        for (int y = 15; y >= 0; --y) {
            for (int z = 0; z < 16; ++z) {
                for (int x = 0; x < 16; ++x) {
                    if (tiles[x][y][z] != null) {
                        tiles[x][y][z].render();
                    }
                }
            }
        }
    }

    @Override
    public void step() {
        for (int y = 15; y >= 0; --y) {
            for (int z = 0; z < 16; ++z) {
                for (int x = 0; x < 16; ++x) {
                    if (tiles[x][y][z] != null) {
                        tiles[x][y][z].step();
                    }
                }
            }
        }
    }

    @Override
    public void tick() {
        for (int y = 15; y >= 0; --y) {
            for (int z = 0; z < 16; ++z) {
                for (int x = 0; x < 16; ++x) {
                    if (tiles[x][y][z] != null) {
                        tiles[x][y][z].tick();
                    }
                }
            }
        }
    }
}
