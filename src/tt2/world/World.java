package tt2.world;

import com.raylib.java.raymath.Vector2;
import tt2.common.IRenderable;
import tt2.common.IStepable;
import tt2.common.ITickable;
import tt2.world.tile.DefaultTile;
import tt2.world.tile.Tile;

public class World implements IRenderable, ITickable, IStepable {
    private Tile[][] tiles;

    public World() {
        tiles = new Tile[16][16];

        for(int x = 0; x < 16; ++x) {
            for(int y = 0; y < 16; ++y) {
                tiles[x][y] = new DefaultTile(new Vector2(x, y));
            }
        }
    }

    @Override
    public void render() {
        for(Tile[] tileRow : tiles) {
            for(Tile tile : tileRow) {
                tile.render();
            }
        }
    }

    @Override
    public void step() {

    }

    @Override
    public void tick() {

    }
}
