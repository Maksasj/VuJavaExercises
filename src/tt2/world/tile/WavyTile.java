package tt2.world.tile;

import com.raylib.java.core.Color;
import com.raylib.java.core.rCore;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.raymath.Vector3;
import tt2.Tartar2;
import tt2.textures.TextureAssetManager;

public class WavyTile extends Tile {
    private float wavyRate;
    private final float waveAmplitude;
    private final float waveSpeed;
    public WavyTile(Vector3 position) {
        super(position);

        wavyRate = 0.0f;
        waveAmplitude = 32.0f;
        waveSpeed = 2.0f;
    }

    @Override
    public void tick() {
        wavyRate = (float) Math.sin(waveSpeed * rCore.GetTime() + getPosition().x / 2.0f);
    }

    @Override
    public void render() {
        Vector2 tilePosition = getIsometricPosition();
        Vector3 cameraPosition = Tartar2.activeScene.getActiveCamera().getPosition();

        Tartar2.raylib.textures.DrawTextureEx(
            TextureAssetManager.BASE_TILE_TEXTURE,
            new Vector2(
                tilePosition.x * DEFAULT_TILE_SCALE * 32.0f - DEFAULT_TILE_SCALE * 16 + cameraPosition.x,
                tilePosition.y * DEFAULT_TILE_SCALE * 32.0f + cameraPosition.z + wavyRate * waveAmplitude
            ),
            0,
            3,
            Color.WHITE
        );
    }
}
