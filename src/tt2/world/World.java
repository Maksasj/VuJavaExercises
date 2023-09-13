package tt2.world;

import com.raylib.java.raymath.Vector3;
import tt2.common.IRenderable;
import tt2.common.IStepable;
import tt2.common.ITickable;
import tt2.entity.Entity;
import tt2.entity.Player;
import tt2.entity.Skeleton;
import tt2.world.tile.DefaultTile;
import tt2.world.tile.Tile;

import java.util.ArrayList;
import java.util.List;

public class World implements IRenderable, ITickable, IStepable {
    private Tile[][][] tiles;
    private List<Entity> entities;

    public World(Player player) {
        tiles = new Tile[16][16][16];
        entities = new ArrayList<Entity>();

        for(int x = 0; x < 16; ++x) {
            for(int y = 0; y < 1; ++y) {
                for(int z = 0; z < 16; ++z) {
                    tiles[x][y][z] = new DefaultTile(new Vector3(x, y, z));
                }
            }
        }

        entities.add(player);
        entities.add(new Skeleton(new Vector3(12.0f, 1.0f, 4.0f)));
    }

    public Tile getTileAt(int x, int y, int z) {
        if(x < 0 || x > 15)
            return null;

        if(y < 0 || y > 15)
            return null;

        if(z < 0 || z > 15)
            return null;

        return tiles[x][y][z];
    }

    @Override
    public void render() {
        for (int y = 0; y < 16; ++y) {
            for (int z = 0; z < 16; ++z) {
                for (int x = 0; x < 16; ++x) {
                    if (tiles[x][y][z] != null) {
                        tiles[x][y][z].render();

                        tiles[x][y][z].submitApplyTintColorFlag(false);
                        tiles[x][y][z].resetYOffset();
                    }
                }
            }
        }

        for(Entity entity : entities) {
            entity.render();
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

        for(Entity entity : entities) {
            entity.step();
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

        for(Entity entity : entities) {
            entity.tick();
        }
    }
}
