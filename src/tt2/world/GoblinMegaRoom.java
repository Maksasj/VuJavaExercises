package tt2.world;

import com.raylib.java.raymath.Vector3;
import tt2.common.IsometricRotation;
import tt2.entity.Goblin;
import tt2.entity.Player;
import tt2.world.tile.DefaultTile;
import tt2.world.tile.StairsTile;

public class GoblinMegaRoom extends World {

    public GoblinMegaRoom(Player player) {
        super(player);

        createDefaultWalls();

        {
            // Floor
            for(int i = 0; i < 16; ++i) {
                for(int j = 0; j < 16; ++j) {
                    tiles[j][2][i] = new DefaultTile(new Vector3(j, 2.0f, i));
                }
            }

            {
                tiles[2][3][2] = new DefaultTile(new Vector3(2.0f, 3.0f, 2.0f));
                tiles[3][3][3] = new DefaultTile(new Vector3(3.0f, 3.0f, 3.0f));
                tiles[4][3][4] = new DefaultTile(new Vector3(4.0f, 3.0f, 4.0f));
                tiles[3][3][5] = new DefaultTile(new Vector3(3.0f, 3.0f, 5.0f));
                tiles[2][3][6] = new DefaultTile(new Vector3(2.0f, 3.0f, 6.0f));

                tiles[2][3][7] = new DefaultTile(new Vector3(2.0f, 3.0f, 7.0f));
                tiles[3][3][8] = new DefaultTile(new Vector3(3.0f, 3.0f, 8.0f));
                tiles[4][3][9] = new DefaultTile(new Vector3(4.0f, 3.0f, 9.0f));
                tiles[3][3][10] = new DefaultTile(new Vector3(3.0f, 3.0f, 10.0f));
                tiles[2][3][11] = new DefaultTile(new Vector3(2.0f, 3.0f, 11.0f));

                addEntity(new Goblin(new Vector3(12.0f, 3.0f, 8.0f)));
                addEntity(new Goblin(new Vector3(2.0f, 3.0f, 8.0f)));

                addEntity(new Goblin(new Vector3(12.0f, 3.0f, 12.0f)));
                addEntity(new Goblin(new Vector3(2.0f, 3.0f, 12.0f)));

                addEntity(new Goblin(new Vector3(7.0f, 3.0f, 2.0f)));
            }
        }

        {
            for(int i = 0; i < 16; ++i) {
                tiles[i][1][4] = new DefaultTile(new Vector3(i, 1.0f, 4.0f));
                tiles[i][1][9] = new DefaultTile(new Vector3(i, 1.0f, 9.0f));
            }

            tiles[4][1][1] = null;
            tiles[9][1][1] = null;

            {
                tiles[1][1][1] = new DefaultTile(new Vector3(1.0f, 1.0f, 1.0f));
                tiles[1][1][2] = new DefaultTile(new Vector3(1.0f, 1.0f, 2.0f));
                tiles[1][1][3] = new StairsTile(new Vector3(1.0f, 1.0f, 3.0f), IsometricRotation.RIGHT_UP);

                tiles[1][2][1] = new DefaultTile(new Vector3(1.0f, 2.0f, 1.0f));
                tiles[1][2][2] = new StairsTile(new Vector3(1.0f, 2.0f, 2.0f), IsometricRotation.RIGHT_UP);
                tiles[1][2][3] = null;
            }

            {
                tiles[14][1][1] = new DefaultTile(new Vector3(14.0f, 1.0f, 1.0f));
                tiles[14][1][2] = new DefaultTile(new Vector3(14.0f, 1.0f, 2.0f));
                tiles[14][1][3] = new StairsTile(new Vector3(14.0f, 1.0f, 3.0f), IsometricRotation.RIGHT_UP);

                tiles[14][2][1] = new DefaultTile(new Vector3(14.0f, 2.0f, 1.0f));
                tiles[14][2][2] = new StairsTile(new Vector3(14.0f, 2.0f, 2.0f), IsometricRotation.RIGHT_UP);
                tiles[14][2][3] = null;
            }

            addEntity(new Goblin(new Vector3(12.0f, 1.0f, 8.0f)));
            addEntity(new Goblin(new Vector3(2.0f, 1.0f, 8.0f)));

            addEntity(new Goblin(new Vector3(13.0f, 1.0f, 9.0f)));
            addEntity(new Goblin(new Vector3(3.0f, 1.0f, 9.0f)));

            addEntity(new Goblin(new Vector3(12.0f, 1.0f, 12.0f)));
            addEntity(new Goblin(new Vector3(2.0f, 1.0f, 12.0f)));

            addEntity(new Goblin(new Vector3(13.0f, 1.0f, 13.0f)));
            addEntity(new Goblin(new Vector3(3.0f, 1.0f, 13.0f)));

            addEntity(new Goblin(new Vector3(7.0f, 1.0f, 2.0f)));
        }
    }
}
