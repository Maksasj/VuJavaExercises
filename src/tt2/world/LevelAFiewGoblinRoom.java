/**
 * @author
 * Maksim Jaroslavcevas radioboos@gmail.com
*/

package tt2.world;

import com.raylib.java.raymath.Vector3;
import tt2.common.IsometricRotation;
import tt2.entity.Player;
import tt2.entity.Goblin;
import tt2.world.tile.DefaultTile;
import tt2.world.tile.PortalTile;
import tt2.world.tile.StairsTile;

public class LevelAFiewGoblinRoom extends World {

    public LevelAFiewGoblinRoom(Player player) {
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
                for(int i = 0; i < 16; ++i) {
                    tiles[4][3][i] = new DefaultTile(new Vector3(4.0f, 3.0f, i));
                    tiles[9][3][i] = new DefaultTile(new Vector3(9.0f, 3.0f, i));
                }

                for(int i = 0; i < 16; ++i) {
                    tiles[i][3][4] = new DefaultTile(new Vector3(i, 3.0f, 4.0f));
                    tiles[i][3][9] = new DefaultTile(new Vector3(i, 3.0f, 9.0f));
                }

                tiles[4][3][10] = null;
                tiles[4][3][3] = null;
                tiles[4][3][5] = null;

                tiles[9][3][10] = null;
                tiles[9][3][3] = null;
                tiles[9][3][5] = null;

                tiles[10][3][9] = null;
                tiles[3][3][9] = null;
                tiles[5][3][9] = null;

                tiles[9][3][4] = null;

                addEntity(new Goblin(new Vector3(12.0f, 3.0f, 8.0f)));
                addEntity(new Goblin(new Vector3(2.0f, 3.0f, 8.0f)));

                addEntity(new Goblin(new Vector3(12.0f, 3.0f, 12.0f)));
                addEntity(new Goblin(new Vector3(2.0f, 3.0f, 12.0f)));

                addEntity(new Goblin(new Vector3(7.0f, 3.0f, 2.0f)));
            }
        }

        {
            for(int i = 0; i < 16; ++i) {
                tiles[4][1][i] = new DefaultTile(new Vector3(4.0f, 1.0f, i));
                tiles[9][1][i] = new DefaultTile(new Vector3(9.0f, 1.0f, i));
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

            addEntity(new Goblin(new Vector3(12.0f, 1.0f, 12.0f)));
            addEntity(new Goblin(new Vector3(2.0f, 1.0f, 12.0f)));

            addEntity(new Goblin(new Vector3(7.0f, 1.0f, 2.0f)));
        }
    }
}
