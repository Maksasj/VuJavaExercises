package tt2.world;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector3;
import tt2.common.IRenderable;
import tt2.common.IStepable;
import tt2.common.ITickable;
import tt2.common.Utils;
import tt2.entity.*;
import tt2.world.tile.DefaultTile;
import tt2.world.tile.Tile;

import java.util.ArrayList;
import java.util.List;

public class World implements IRenderable, ITickable, IStepable {
    private final Tile[][][] tiles;
    private final List<Entity> entities;
    private final Player player;

    public World(Player player) {
        tiles = new Tile[16][16][16];
        entities = new ArrayList<Entity>();

        for(int x = 0; x < 16; ++x) {
            for(int z = 0; z < 16; ++z) {
                tiles[x][0][z] = new DefaultTile(new Vector3(x, 0.0f, z));
            }
        }

        for(int x = 0; x < 16; ++x) {
            for(int z = 0; z < 16; ++z) {
                tiles[x][2][z] = new DefaultTile(new Vector3(x, 2.0f, z));
            }
        }

        for(int z = 0; z < 16; ++z) {
            for(int y = 1; y < 3; ++y) {
                tiles[0][y][z] = new DefaultTile(new Vector3(0.0f, y, z));
                tiles[15][y][z] = new DefaultTile(new Vector3(15.0f, y, z));

                tiles[z][y][15] = new DefaultTile(new Vector3(z, y,15.0f));
                tiles[z][y][0] = new DefaultTile(new Vector3(z, y,0.0f));
            }
        }

        entities.add(player);
        this.player = player;

        entities.add(new Skeleton(new Vector3(12.0f, 1.0f, 4.0f)));
    }

    public static void getNeighbourGroundTiles(World world, int x, int y, int z, Tile[] tiles) {
        tiles[0] = world.getTileAt(x - 1, y - 1, z);
        tiles[1] = world.getTileAt(x + 1, y - 1, z);
        tiles[2] = world.getTileAt(x, y - 1, z - 1);
        tiles[3] = world.getTileAt(x, y - 1, z + 1);
    }

    public static void getNeighbourTiles(World world, int x, int y, int z, Tile[] tiles) {
        tiles[0] = world.getTileAt(x - 1, y, z);
        tiles[1] = world.getTileAt(x + 1, y, z);
        tiles[2] = world.getTileAt(x, y, z - 1);
        tiles[3] = world.getTileAt(x, y, z + 1);
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
        // Before rendering we need to sort all renderable objects
        ArrayList<GameObject> renderables = new ArrayList<GameObject>();

        for (int y = 0; y < 16; ++y) {
            for (int z = 0; z < 16; ++z) {
                for (int x = 0; x < 16; ++x) {
                    if (tiles[x][y][z] != null) {
                        renderables.add(tiles[x][y][z]);
                    }
                }
            }
        }

        // Let's make all tiles semi transparent that block player view;
        int playerX = Math.round(player.getPosition().x);
        int playerY = Math.round(player.getPosition().y);
        int playerZ = Math.round(player.getPosition().z);

        int xBound = Utils.clamp(playerX + 5, 0, 16);
        int zBound = Utils.clamp(playerZ + 5, 0, 16);

        for (int y = playerY; y < 16; ++y) {
            for (int z = playerZ; z < zBound; ++z) {
                for (int x = playerX; x < xBound; ++x) {
                    if (tiles[x][y][z] != null) {
                        tiles[x][y][z].setTintColor(new Color(255, 255, 255, 75));
                        tiles[x][y][z].submitApplyTintColorFlag(true);
                    }
                }
            }
        }

        // Z and X from 1 to 15, since we want to have this nice border
        int yBound = Utils.clamp(playerY + 1, 0, 16);

        for (int y = yBound; y < 16; ++y) {
            for (int z = 1; z < 15; ++z) {
                for (int x = 1; x < 15; ++x) {
                    if (tiles[x][y][z] != null) {
                        tiles[x][y][z].setTintColor(new Color(255, 255, 255, 0));
                        tiles[x][y][z].submitApplyTintColorFlag(true);
                    }
                }
            }
        }

        renderables.addAll(entities);

        // Sorting
        renderables.sort(new GameObjectSorterByPerspective());

        // Rendering
        for(GameObject object : renderables) {
            ((IRenderable) object).render();
            ((IRenderable) object).resetRenderingFlags();
        }
    }

    @Override
    public void resetRenderingFlags() {

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
