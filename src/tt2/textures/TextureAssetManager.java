package tt2.textures;

import com.raylib.java.textures.*;
import tt2.Tartar2;
import tt2.common.IAssetManager;

public class TextureAssetManager implements IAssetManager {
    public static Texture2D BASE_TILE_TEXTURE;
    public static Texture2D PLAYER_TEXTURE;
    public static Texture2D SKELETON_TEXTURE;

    public void load() {
        BASE_TILE_TEXTURE = rTextures.LoadTexture("assets/textures/baseTile.png");
        PLAYER_TEXTURE = rTextures.LoadTexture("assets/textures/player.png");
        SKELETON_TEXTURE = rTextures.LoadTexture("assets/textures/skeleton.png");
    }

    public void unload() {
        Tartar2.raylib.textures.UnloadTexture(BASE_TILE_TEXTURE);
        Tartar2.raylib.textures.UnloadTexture(PLAYER_TEXTURE);
        Tartar2.raylib.textures.UnloadTexture(SKELETON_TEXTURE);
    }
}
