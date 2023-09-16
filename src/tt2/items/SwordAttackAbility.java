package tt2.items;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.raymath.Vector3;
import com.raylib.java.textures.Texture2D;
import tt2.Tartar2;
import tt2.common.GameController;
import tt2.common.camera.Camera;
import tt2.common.camera.CameraController;
import tt2.entity.Player;
import tt2.textures.TextureAssetManager;
import tt2.world.World;
import tt2.world.tile.Tile;
import tt2.world.tile.TileDensity;

public class SwordAttackAbility extends Ability {
    private Player player;
    private Tile[] groundTiles;
    private Tile[] wallTiles;

    public SwordAttackAbility(Player player) {
        super();

        groundTiles = new Tile[4];
        wallTiles = new Tile[4];

        this.player = player;
    }

    @Override
    public void render() {

    }

    private void updateAvailableTilesTint() {
        for(int i = 0; i < 4; ++i) {
            Tile tile = groundTiles[i];

            if(tile != null) {
                tile.setTintColor(new Color(255, 125, 125, 255));
                tile.setApplyTint(true);
            }
        }

        for(int i = 0; i < 4; ++i) {
            Tile tile = wallTiles[i];

            if((tile != null) && (tile.getTileDensity() == TileDensity.HOLLOW)) {
                tile.setTintColor(new Color(255, 125, 125, 255));
                tile.setApplyTint(true);
            }
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

        World.getNeighbourGroundTiles(world, playerX, playerY, playerZ, groundTiles);
        World.getNeighbourTiles(world, playerX, playerY, playerZ, wallTiles);
    }

    public boolean checkDirection(Tile hoveringTile, Tile groundTile, Tile wallTile) {
        boolean wallTileIsHollow = false;

        if(wallTile == null)
            wallTileIsHollow = true;
        else
            wallTileIsHollow = wallTile.getTileDensity() == TileDensity.HOLLOW;

        return (hoveringTile == groundTile) && wallTileIsHollow;
    }

    public int getDamage() {
        return player.getMeleeDamage();
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

            int playerX = Math.round(player.getPosition().x);
            int playerY = Math.round(player.getPosition().y);
            int playerZ = Math.round(player.getPosition().z);

            World world = GameController.getWorld();

            if(checkDirection(hoveringTile, groundTiles[0], wallTiles[0])) {
                world.dealDamageToEntitiesAt(playerX - 1, playerY, playerZ, getDamage());
            } else if (checkDirection(hoveringTile, groundTiles[1], wallTiles[1])) {
                world.dealDamageToEntitiesAt(playerX + 1, playerY, playerZ, getDamage());
            } else if (checkDirection(hoveringTile, groundTiles[2], wallTiles[2])) {
                world.dealDamageToEntitiesAt(playerX, playerY, playerZ - 1, getDamage());
            } else if (checkDirection(hoveringTile, groundTiles[3], wallTiles[3])) {
                world.dealDamageToEntitiesAt(playerX, playerY, playerZ + 1, getDamage());
            } else {
                invokeStep = false;
            }

            // Tartar2.raylib.core.IsMouseButtonPressed(0) insures that this part will be invoked only once per click
            if(invokeStep)
                Tartar2.activeScene.step();
        }
    }

    @Override
    public void doRenderingPreProcessing() {
        updateAvailableTilesTint();
    }

    @Override
    public boolean isBlocked() {
        return false;
    }

    @Override
    public Texture2D getIconTexture() {
        return TextureAssetManager.SWORD_ATTACK_ABILITY_ICON;
    }
}
