/**
 * @author
 * Maksim Jaroslavcevas radioboos@gmail.com
*/

package tt2.items;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.raymath.Vector3;
import com.raylib.java.textures.Texture2D;
import org.lwjgl.system.CallbackI;
import tt2.Tartar2;
import tt2.common.GameController;
import tt2.common.ITexture;
import tt2.common.camera.Camera;
import tt2.common.camera.CameraController;
import tt2.entity.ArrowEntity;
import tt2.entity.Player;
import tt2.entity.PlayerArrowEntity;
import tt2.textures.SubTexture;
import tt2.textures.TextureAssetManager;
import tt2.world.World;
import tt2.world.tile.Tile;
import tt2.world.tile.TileDensity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BowAttackAbility extends Ability {
    private Player player;
    private Tile[] groundTilesLeftUp;
    private Tile[] groundTilesRightUp;
    private Tile[] groundTilesRightDown;
    private Tile[] groundTilesLeftDown;

    private Tile[] wallTilesLeftUp;
    private Tile[] wallTilesRightUp;
    private Tile[] wallTilesRightDown;
    private Tile[] wallTilesLeftDown;

    private int bowRange;

    private ArrowEntity arrowEntity;


    public BowAttackAbility(Player player) {
        super();

        bowRange = 3;

        groundTilesLeftUp = new Tile[bowRange];
        groundTilesRightUp = new Tile[bowRange];
        groundTilesRightDown = new Tile[bowRange];
        groundTilesLeftDown = new Tile[bowRange];

        wallTilesLeftUp = new Tile[bowRange];
        wallTilesRightUp = new Tile[bowRange];
        wallTilesRightDown = new Tile[bowRange];
        wallTilesLeftDown = new Tile[bowRange];

        arrowEntity = null;

        this.player = player;
    }

    @Override
    public void render() {

    }

    private List<Tile> getListOfAllTiles() {
        List<Tile> tiles = new ArrayList<Tile>();

        tiles.addAll(Arrays.asList(groundTilesLeftUp));
        tiles.addAll(Arrays.asList(groundTilesRightUp));
        tiles.addAll(Arrays.asList(groundTilesRightDown));
        tiles.addAll(Arrays.asList(groundTilesLeftDown));

        tiles.addAll(Arrays.asList(wallTilesLeftUp));
        tiles.addAll(Arrays.asList(wallTilesRightUp));
        tiles.addAll(Arrays.asList(wallTilesRightDown));
        tiles.addAll(Arrays.asList(wallTilesLeftDown));

        return tiles;
    }

    private void updateAvailableTilesTint() {
        List<Tile> tiles = getListOfAllTiles();

        for(Tile tile : tiles) {
            if(tile == null)
                continue;

            tile.setTintColor(new Color(255, 125, 125, 255));
            tile.setApplyTint(true);
        }
    }

    private Tile getPlayerHoveringTile() {
        World world = GameController.getWorld();

        Camera camera = Tartar2.activeScene.getActiveCamera();
        float cameraZoom = camera.getZoom();

        int playerY = Math.round(player.getPosition().y);

        Vector2 mousePos = CameraController.getMousePosition();
        mousePos.x -= camera.getPosition().x;
        mousePos.y -= camera.getPosition().z - (playerY - 1)*48.0f;

        float i_x = 1.0f;
        float i_y = 0.5f;
        float j_x = -1.0f;
        float j_y = 0.5f;

        float a = i_x * 0.5f * 32.0f * cameraZoom;
        float b = j_x * 0.5f * 32.0f * cameraZoom;
        float c = i_y * 0.5f * 32.0f * cameraZoom;
        float d = j_y * 0.5f * 32.0f * cameraZoom;

        float det = (1 / (a * d - b * c));

        int worldPosX = (int) (mousePos.x * (det * d) + mousePos.y * (det * -b));
        int worldPosZ = (int) (mousePos.x * (det * -c) + mousePos.y * (det * a));

        return world.getTileAt(worldPosX, playerY - 1, worldPosZ);
    }

    public void updateNeighbourTiles() {
        World world = GameController.getWorld();

        int playerX = Math.round(player.getPosition().x);
        int playerY = Math.round(player.getPosition().y);
        int playerZ = Math.round(player.getPosition().z);

        for(int i = 0; i < bowRange ; ++i) {
            groundTilesLeftUp[i] = world.getTileAt(playerX - i - 2, playerY - 1, playerZ);
            groundTilesLeftDown[i] = world.getTileAt(playerX, playerY - 1, playerZ + i + 2);
            groundTilesRightUp[i] = world.getTileAt(playerX, playerY - 1, playerZ - i - 2);
            groundTilesRightDown[i] = world.getTileAt(playerX + i + 2, playerY - 1, playerZ);

            wallTilesLeftUp[i] = world.getTileAt(playerX - i - 2, playerY, playerZ);
            wallTilesLeftDown[i] = world.getTileAt(playerX, playerY, playerZ + i + 2);
            wallTilesRightUp[i] = world.getTileAt(playerX, playerY, playerZ - i - 2);
            wallTilesRightDown[i] = world.getTileAt(playerX + i + 2, playerY, playerZ);
        }
    }

    public boolean checkTileRow(Tile hoveringTile, Tile[] tileRow) {
        for(Tile tile : tileRow)
            if(hoveringTile == tile)
                return true;

        return false;
    }

    public int getDirectionTileDistance(Tile hoveringTile, Tile[] tileRow) {
        for(int i = 0; i < tileRow.length; ++i)
            if(hoveringTile == tileRow[i])
                return i;

        return 0;
    }

    @Override
    public void tick() {
        Tile hoveringTile = getPlayerHoveringTile();

        updateNeighbourTiles();

        if(hoveringTile == null)
            return;

        hoveringTile.submitYOffset(7.0f);

        if(Tartar2.raylib.core.IsMouseButtonPressed(0)) {
            boolean arrowIsShoot = true;

            Vector3 arrowDirection = new Vector3(0.0f, 0.0f, 0.0f);
            Tile[] tileDirectionRow = null;

            if(checkTileRow(hoveringTile, groundTilesLeftUp)) {
                arrowDirection = new Vector3(-1.0f, 0.0f, 0.0f);
                tileDirectionRow = groundTilesLeftUp;
            } else if(checkTileRow(hoveringTile, groundTilesRightUp)) {
                arrowDirection = new Vector3(0.0f, 0.0f, -1.0f);
                tileDirectionRow = groundTilesRightUp;
            } else if(checkTileRow(hoveringTile, groundTilesRightDown)) {
                arrowDirection = new Vector3(1.0f, 0.0f, 0.0f);
                tileDirectionRow = groundTilesRightDown;
            } else if(checkTileRow(hoveringTile, groundTilesLeftDown)) {
                arrowDirection = new Vector3(0.0f, 0.0f, 1.0f);
                tileDirectionRow = groundTilesLeftDown;
            } else {
                arrowIsShoot = false;
            }

            // Tartar2.raylib.core.IsMouseButtonPressed(0) insures that this part will be invoked only once per click
            if(arrowIsShoot) {
                // Tartar2.activeScene.step();

                Vector3 playerPos = player.getPosition();
                Vector3 arrowStartPos = new Vector3(playerPos.x, playerPos.y, playerPos.z);
                float arrowSpeed = 0.08f;

                int directionalDistance = getDirectionTileDistance(hoveringTile, tileDirectionRow) + 2;

                arrowEntity = new PlayerArrowEntity(
                        arrowStartPos,
                        arrowDirection,
                        arrowSpeed,
                        (float )directionalDistance,
                        player
                );

                World world = GameController.getWorld();
                world.addEntity(arrowEntity);
            }
        }
    }

    @Override
    public void doRenderingPreProcessing() {
        updateAvailableTilesTint();
    }

    @Override
    public boolean isBlocked() {
        return arrowEntity != null && !arrowEntity.isDeleted();
    }

    @Override
    public ITexture getIconTexture() {
        return TextureAssetManager.uiTexture.getSubTexture(6);
    }
}
