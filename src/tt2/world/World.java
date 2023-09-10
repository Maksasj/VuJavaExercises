package tt2.world;

import tt2.common.IRenderable;
import tt2.world.tile.DefaultTile;
import tt2.world.tile.Tile;

public class World implements IRenderable {
    private Tile[][] tiles;

    public void World() {
        tiles = new DefaultTile[16][16];
    }

    @Override
    public void render() {
        for(Tile[] tileRow : tiles) {
            for(Tile tile : tileRow) {
                tile.render();
            }
        }
    }
}
