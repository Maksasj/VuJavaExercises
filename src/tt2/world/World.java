/**
 * @author
 * Maksim Jaroslavcevas radioboos@gmail.com
*/

package tt2.world;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector3;
import tt2.common.*;
import tt2.entity.*;
import tt2.world.tile.DefaultTile;
import tt2.world.tile.PortalTile;
import tt2.world.tile.StairsTile;
import tt2.world.tile.Tile;

import javax.sound.sampled.Port;
import java.util.*;

public class World extends CommonRenderingMaster implements IRenderable, ITickable, IStepable {
    protected final Tile[][][] tiles;
    private final List<Entity> entities;
    private final Stack<Entity> entitiesToAdd; // Array used for storing all entities that are waiting to be spawned
    private ArrayList<GameObject> renderables;
    private PathCalculator pathCalculator;

    private final Player player;

    public World(Player player) {
        tiles = new Tile[16][16][16];

        entities = new ArrayList<Entity>();
        entitiesToAdd = new Stack<>();

        renderables = new ArrayList<GameObject>();

        pathCalculator = new PathCalculator();

        addEntity(player);
        this.player = player;
    }

    public void addTile(Tile tile) {
        Vector3 position = tile.getPosition();

        int posX = Math.round(position.x);
        int posY = Math.round(position.y);
        int posZ = Math.round(position.z);

        addTileAt(tile, posX, posY, posZ);
    }

    public void addTileAt(Tile tile, int x, int y, int z) {
        tiles[x][y][z] = tile;
    }

    protected void createDefaultWalls() {
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
            for(int y = 1; y < 16; ++y) {
                tiles[0][y][z] = new DefaultTile(new Vector3(0.0f, y, z));
                tiles[15][y][z] = new DefaultTile(new Vector3(15.0f, y, z));

                tiles[z][y][15] = new DefaultTile(new Vector3(z, y,15.0f));
                tiles[z][y][0] = new DefaultTile(new Vector3(z, y,0.0f));
            }
        }
    }

    public void dealDamageToEntitiesAt(int x, int y, int z, int damageValue, Mob damageDealer) {
        for(Entity entity : entities) {
            if(!(entity instanceof Mob mob))
                continue;

            if(mob == damageDealer)
                continue;

            Vector3 position = entity.getIntermediatePosition();

            int posX = Math.round(position.x);
            int posY = Math.round(position.y);
            int posZ = Math.round(position.z);

            if((posX == x) && (posY == y) && (posZ == z)) {
                mob.takeDamage(damageValue);

                addEntity(new TextPopupEntity(
                        new Vector3(posX, posY, posZ),
                        Integer.toString(damageValue),
                        new Color(255, 0, 0, 255),
                        2.0f,
                        1.5f
                ));
            }

            if(mob.isDead())
                mob.markAsDeleted();
        }
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

    public PathCalculator getPathCalculator() {
        return pathCalculator;
    }

    private void setVisibilityLevelAroundPlayer(int playerX, int playerY, int playerZ) {
        int xBound = Utils.clamp(playerX + 5, 0, 16);
        int zBound = Utils.clamp(playerZ + 5, 0, 16);

        // Make some block that are in player view radius go semi transparent
        for (int y = playerY; y < 16; ++y) {
            for (int z = playerZ; z < zBound; ++z) {
                for (int x = playerX; x < xBound; ++x) {
                    if (tiles[x][y][z] != null)
                        tiles[x][y][z].setVisibilityLevel(VisibilityLevel.SEMI_VISIBLE);
                }
            }
        }
    }

    private void addAllTilesToRenderables(ArrayList<GameObject> renderables) {
        for (int y = 0; y < 16; ++y) {
            for (int z = 0; z < 16; ++z) {
                for (int x = 0; x < 16; ++x) {
                    if (tiles[x][y][z] != null) {
                        renderables.add(tiles[x][y][z]);
                    }
                }
            }
        }
    }

    private void setAllUpperTilesTransparent(int playerX, int playerY, int playerZ) {
        {
            int yBound = Utils.clamp(playerY + 2, 0, 15);

            for (int y = yBound; y < 16; ++y) {
                for (int z = 0; z < 16; ++z) {
                    for (int x = 0; x < 16; ++x) {
                        if (tiles[x][y][z] != null)
                            tiles[x][y][z].setVisibilityLevel(VisibilityLevel.TRANSPARENT);
                    }
                }
            }
        }

        {
            int yLowBound = Utils.clamp(playerY + 1, 0, 15);
            int yUpBound = Math.min(yLowBound + 1, 16);

            for (int y = yLowBound; y < yUpBound; ++y) {
                for (int z = 1; z < 15; ++z) {
                    for (int x = 1; x < 15; ++x) {
                        if (tiles[x][y][z] != null)
                            tiles[x][y][z].setVisibilityLevel(VisibilityLevel.TRANSPARENT);
                    }
                }
            }
        }
    }

    @Override
    public void render() {
        // Rendering
        for(GameObject object : renderables) {
            IRenderable renderable = (IRenderable) object;
            VisibilityLevel visibilityLevel = renderable.getVisibilityLevel();

            if(visibilityLevel == VisibilityLevel.VISIBLE) {
                renderable.render();
            } else if(visibilityLevel == VisibilityLevel.SEMI_VISIBLE) {
                renderable.setTintColor(new Color(255, 255, 255, 125));
                renderable.setApplyTint(true);
                renderable.render();
            }

            renderable.resetRenderingFlags();
        }
    }

    // Needed for debuging
    private void visualizeFlatPaths() {
        for (int x = 0; x < 16; ++x) {
            for (int y = 0; y < 16; ++y) {
                for (int z = 0; z < 16; ++z) {
                    Tile groundTile = getTileAt(x, y, z);

                    if (groundTile == null)
                        continue;

                    Vector3 position = groundTile.getPosition();

                    int posX = Math.round(position.x);
                    int posY = Math.round(position.y) + 1;
                    int posZ = Math.round(position.z);

                    IsometricDirection dir = pathCalculator.getFlatDirectionAt(posX, posY, posZ);
                    if(dir == IsometricDirection.NONE) {
                        groundTile.setApplyTint(true);
                        groundTile.setTintColor(new Color(125, 255, 125, 255));
                    }
                }
            }
        }
    }

    @Override
    public void doRenderingPreProcessing() {
        renderables.clear();

        // visualizeFlatPaths(); // needed for debuging

        // Adding all tile to renderables
        addAllTilesToRenderables(renderables);

        // Let's make all tiles semi transparent that block player view;
        int playerX = Math.round(player.getPosition().x);
        int playerY = Math.round(player.getPosition().y);
        int playerZ = Math.round(player.getPosition().z);
        setVisibilityLevelAroundPlayer(playerX, playerY, playerZ);

        // Make all block that are above player invisible
        // Z and X from 1 to 15, since we want to have this nice border
        setAllUpperTilesTransparent(playerX, playerY, playerZ);

        // Adding all regular entities to renderables list

        // Todo we need to filter entities that needs to be not rendered
        for(Entity entity : entities) {
            if(entity instanceof TextPopupEntity) {
                renderables.add(entity);
            } else {
                if(entity.getIntermediatePosition().y <= playerY) {
                    renderables.add(entity);
                }
            }
        }

        // renderables.addAll(entities);

        // Now we do visibility post-processing
        for(GameObject object : renderables)
            ((IRenderable) object).doRenderingPreProcessing();

        // Sorting all renderables
        renderables.sort(new GameObjectSorterByPerspective());
    }

    @Override
    public void step() {
        pathCalculator.reCalculateFlatPaths(this, player);
        pathCalculator.reCalculateCubicPaths(this, player);

        for(Entity entity : entities) {
            entity.step();
        }

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

    private void spawnAllWaitingEntities() {
        while (!entitiesToAdd.isEmpty()) {
            Entity entity = entitiesToAdd.pop();
            entities.add(entity);
        }
    }

    private void deleteAllWaitingEntities() {
        // There we check if any entity need to be deleted !
        List<Entity> toDelete = new ArrayList<Entity>();

        for(Entity entity : entities)
            if(entity != null && entity.isDeleted())
                toDelete.add(entity);

        entities.removeAll(toDelete);
    }

    @Override
    public void tick() {
        spawnAllWaitingEntities();

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

        deleteAllWaitingEntities();
    }

    public void addEntity(Entity entity) {
        entitiesToAdd.push(entity);
    }
}
