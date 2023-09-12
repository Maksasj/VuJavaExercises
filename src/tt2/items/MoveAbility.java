package tt2.items;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import tt2.Tartar2;
import tt2.common.GameController;
import tt2.common.camera.Camera;
import tt2.common.camera.CameraController;
import tt2.entity.Player;
import tt2.world.World;
import tt2.world.tile.Tile;

import javax.swing.plaf.synth.SynthTextAreaUI;

public class MoveAbility extends Ability {
    private Player player;

    private Tile[] tiles;


    public MoveAbility(Player player) {
        super();

        tiles = new Tile[4];

        this.player = player;
    }

    @Override
    public void render() {
        for(Tile tile : tiles) {
            if(tile != null) {
                tile.setTintColor(Color.RED);
                tile.submitApplyTintColorFlag(true);
            }
        }
    }

    private Tile getPlayerHoveringTile() {
        World world = GameController.getWorld();

        Camera cameraPos = Tartar2.activeScene.getActiveCamera();
        Vector2 mousePos = CameraController.getMousePosition();
        mousePos.x -= cameraPos.getPosition().x;
        mousePos.y -= cameraPos.getPosition().z;

        float i_x = 1.0f;
        float i_y = 0.5f;
        float j_x = -1.0f;
        float j_y = 0.5f;

        float a = i_x * 0.5f * 32.0f * 3.0f;
        float b = j_x * 0.5f * 32.0f * 3.0f;
        float c = i_y * 0.5f * 32.0f * 3.0f;
        float d = j_y * 0.5f * 32.0f * 3.0f;

        float det = (1 / (a * d - b * c));

        int worldPosX = (int) (mousePos.x * (det * d) + mousePos.y * (det * -b));
        int worldPosZ = (int) (mousePos.x * (det * -c) + mousePos.y * (det * a));

        return world.getTileAt(worldPosX, 0, worldPosZ);
    }

    public void updateNeighbourTiles() {
        World world = GameController.getWorld();

        int playerX = (int) player.getPosition().x;
        int playerY = (int) player.getPosition().y;
        int playerZ = (int) player.getPosition().z;

        tiles[0] = world.getTileAt(playerX - 1, playerY - 1, playerZ);
        tiles[1] = world.getTileAt(playerX + 1, playerY - 1, playerZ);
        tiles[2] = world.getTileAt(playerX, playerY - 1, playerZ - 1);
        tiles[3] = world.getTileAt(playerX, playerY - 1, playerZ + 1);
    }

    @Override
    public void tick() {
        Tile hoveringTile = getPlayerHoveringTile();

        updateNeighbourTiles();

        if(hoveringTile == null)
            return;

        hoveringTile.submitYOffset(7.0f);

        if(Tartar2.raylib.core.IsMouseButtonPressed(0)) {
            if(hoveringTile == tiles[0]) {
                System.out.println("Player tried to move");
            } else if (hoveringTile == tiles[1]) {
                System.out.println("Player tried to move");
            } else if (hoveringTile == tiles[2]) {
                System.out.println("Player tried to move");
            } else if (hoveringTile == tiles[3]) {
                System.out.println("Player tried to move");
            }
        }
    }
}
