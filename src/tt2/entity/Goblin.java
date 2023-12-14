/**
 * @author
 * Maksim Jaroslavcevas radioboos@gmail.com
*/

package tt2.entity;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.raymath.Vector3;
import tt2.Tartar2;
import tt2.common.*;
import tt2.common.camera.Camera;
import tt2.textures.TextureAssetManager;
import tt2.world.World;

public class Goblin extends GroundMob {
    private IsometricRotation rotation;

    public Goblin(Vector3 pos) {
        super(pos, new Statblock(2, 1, 1, 1));

        rotation = IsometricRotation.LEFT_UP;
    }
    public void setRotation(IsometricRotation rotation) {
        this.rotation = rotation;
    }

    @Override
    public void step() {
        super.step();

        if(isDead())
            return;

        World world = GameController.getWorld();

        Vector3 position = getPosition();

        int posX = Math.round(position.x);
        int posY = Math.round(position.y);
        int posZ = Math.round(position.z);

        IsometricDirection directionToGo = world.getPathCalculator().getFlatDirectionAt(posX, posY, posZ);
        setRotation(directionToGo.toIsometricRotation());

        switch (directionToGo) {
            case LEFT_UP -> movePosition(new Vector3(-1.0f, 0.0f, 0.0f));
            case RIGHT_UP -> movePosition(new Vector3(0.0f, 0.0f, -1.0f));
            case RIGHT_DOWN -> movePosition(new Vector3(1.0f, 0.0f, 0.0f));
            case LEFT_DOWN -> movePosition(new Vector3(0.0f, 0.0f, 1.0f));
            default -> {}
        }

        Player player = Tartar2.gameScene.getGameController().getPlayer();
        if(player == null)
            return;

        Vector3 playerPos = player.getIntermediatePosition();
        Vector3 selfPos = getPosition();

        int playerPosX = Math.round(playerPos.x);
        int playerPosY = Math.round(playerPos.y);
        int playerPosZ = Math.round(playerPos.z);

        int selfPosX = Math.round(selfPos.x);
        int selfPosY = Math.round(selfPos.y);
        int selfPosZ = Math.round(selfPos.z);

        IsometricDirection attackDirection = IsometricDirection.NONE;
        if(playerPosY != selfPosY)
            return;

        if((playerPosX + 1) == selfPosX && playerPosZ == selfPosZ) {
            attackDirection = IsometricDirection.RIGHT_DOWN;
            world.dealDamageToEntitiesAt(playerPosX, playerPosY, selfPosZ, getMeleeDamage(), this);
        } else if((playerPosX - 1) == selfPosX && playerPosZ == selfPosZ) {
            attackDirection = IsometricDirection.LEFT_UP;
            world.dealDamageToEntitiesAt(playerPosX, playerPosY, selfPosZ, getMeleeDamage(), this);
        } else if(playerPosX == selfPosX && playerPosZ == (selfPosZ + 1)) {
            attackDirection = IsometricDirection.LEFT_DOWN;
            world.dealDamageToEntitiesAt(playerPosX, playerPosY, selfPosZ, getMeleeDamage(), this);
        } else if(playerPosX == selfPosX && playerPosZ == (selfPosZ - 1)) {
            attackDirection = IsometricDirection.RIGHT_UP;
            world.dealDamageToEntitiesAt(playerPosX, playerPosY, selfPosZ, getMeleeDamage(), this);
        }
    }

    private ITexture getTexture() {
        return switch (rotation) {
            case LEFT_UP -> TextureAssetManager.mobsTexture.getSubTexture(4);
            case RIGHT_UP -> TextureAssetManager.mobsTexture.getSubTexture(5);
            case RIGHT_DOWN -> TextureAssetManager.mobsTexture.getSubTexture(6);
            case LEFT_DOWN -> TextureAssetManager.mobsTexture.getSubTexture(7);
        };
    }

    @Override
    public void render() {
        if(isDeleted())
            return;

        Camera activeCamera = Tartar2.activeScene.getActiveCamera();

        Vector2 tilePosition = getIsometricPosition();
        Vector3 cameraPosition = Tartar2.activeScene.getActiveCamera().getPosition();
        float cameraZoom = activeCamera.getZoom();

        Vector2 texturePos = new Vector2(
                tilePosition.x * cameraZoom * 32.0f - cameraZoom * 16 + cameraPosition.x,
                tilePosition.y * cameraZoom * 32.0f + cameraPosition.z
        );

        getTexture().render(texturePos, cameraZoom, Color.WHITE);
    }
}
