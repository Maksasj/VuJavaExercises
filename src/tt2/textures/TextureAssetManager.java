package tt2.textures;

import com.raylib.java.textures.*;
import tt2.Tartar2;
import tt2.common.IAssetManager;

public class TextureAssetManager implements IAssetManager {
    public static Texture2D BASE_TILE_TEXTURE;

    public void load() {
        BASE_TILE_TEXTURE = rTextures.LoadTexture("assets/textures/baseTile.png");
    }

    public void unload() {
        Tartar2.raylib.textures.UnloadTexture(BASE_TILE_TEXTURE);
    }
}
