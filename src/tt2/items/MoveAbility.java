package tt2.items;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.raymath.Vector3;
import tt2.Tartar2;
import tt2.common.GameController;
import tt2.common.camera.Camera;
import tt2.common.camera.CameraController;
import tt2.entity.Player;
import tt2.world.World;
import tt2.world.tile.Tile;

public class MoveAbility extends Ability {
    private Player player;
    private Tile[] groundTiles;
    private Tile[] wallTiles;

    public MoveAbility(Player player) {
        super();

        groundTiles = new Tile[4];
        wallTiles = new Tile[4];

        this.player = player;
    }

    @Override
    public void render() {
        for(int i = 0; i < 4; ++i) {
            Tile tile = groundTiles[i];

            if(tile != null && wallTiles[i] == null) {
                tile.setTintColor(new Color(255, 125, 125, 255));
                tile.submitApplyTintColorFlag(true);
            }
        }
    }

    private Tile getPlayerHoveringTile() {
        World world = GameController.getWorld();

        Camera camera = Tartar2.activeScene.getActiveCamera();
        float cameraZoom = camera.getZoom();

        Vector2 mousePos = CameraController.getMousePosition();
        mousePos.x -= camera.getPosition().x;
        mousePos.y -= camera.getPosition().z;

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

        return world.getTileAt(worldPosX, 0, worldPosZ);
    }

    public void updateNeighbourTiles() {
        World world = GameController.getWorld();

        int playerX = Math.round(player.getPosition().x);
        int playerY = Math.round(player.getPosition().y);
        int playerZ = Math.round(player.getPosition().z);

        World.getNeighbourGroundTiles(world, playerX, playerY, playerZ, groundTiles);
        World.getNeighbourTiles(world, playerX, playerY, playerZ, wallTiles);
    }

    public boolean checkDirection(Tile hoveringTile, Tile groundTile, Tile sideTile) {
        return (hoveringTile == groundTile) && (sideTile == null);
    }

    @Override
    public void tick() {
        Tile hoveringTile = getPlayerHoveringTile();

        updateNeighbourTiles();

        if(hoveringTile == null)
            return;

        hoveringTile.submitYOffset(7.0f);

        if(Tartar2.raylib.core.IsMouseButtonPressed(0)) {
            boolean invokeStep = true;

            if(checkDirection(hoveringTile, groundTiles[0], wallTiles[0])) {
                player.movePosition(new Vector3(-1.0f, 0.0f, 0.0f));
            } else if (checkDirection(hoveringTile, groundTiles[1], wallTiles[1])) {
                player.movePosition(new Vector3(1.0f, 0.0f, 0.0f));
            } else if (checkDirection(hoveringTile, groundTiles[2], wallTiles[2])) {
                player.movePosition(new Vector3(0.0f, 0.0f, -1.0f));
            } else if (checkDirection(hoveringTile, groundTiles[3], wallTiles[3])) {
                player.movePosition(new Vector3(0.0f, 0.0f, 1.0f));
            } else {
                invokeStep = false;
            }

            // Tartar2.raylib.core.IsMouseButtonPressed(0) insures that this part will be invoked only once per click
            if(invokeStep)
                Tartar2.activeScene.step();
        }
    }
}
