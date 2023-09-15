package tt2.textures;

import com.raylib.java.textures.*;
import tt2.Tartar2;
import tt2.common.IAssetManager;

public class TextureAssetManager implements IAssetManager {
    public static Texture2D BASE_TILE_TEXTURE;
    public static Texture2D STAIRS_TILE0_TEXTURE;
    public static Texture2D STAIRS_TILE1_TEXTURE;
    public static Texture2D STAIRS_TILE2_TEXTURE;
    public static Texture2D STAIRS_TILE3_TEXTURE;
    public static Texture2D PLAYER_TEXTURE;
    public static Texture2D SKELETON_TEXTURE;
    public static Texture2D ARROW_TEXTURE;

    public static Texture2D SELECTION_FRAME_ICON;
    public static Texture2D MOVE_ABILITY_ICON;
    public static Texture2D SWORD_ATTACK_ABILITY_ICON;
    public static Texture2D BOW_ATTACK_ABILITY_ICON;

    public void load() {
        BASE_TILE_TEXTURE = rTextures.LoadTexture("assets/textures/baseTile.png");
        PLAYER_TEXTURE = rTextures.LoadTexture("assets/textures/player.png");
        SKELETON_TEXTURE = rTextures.LoadTexture("assets/textures/skeleton.png");
        ARROW_TEXTURE = rTextures.LoadTexture("assets/textures/arrow.png");

        SELECTION_FRAME_ICON = rTextures.LoadTexture("assets/textures/selectionFrameIcon.png");
        MOVE_ABILITY_ICON = rTextures.LoadTexture("assets/textures/moveAbilityIcon.png");
        SWORD_ATTACK_ABILITY_ICON = rTextures.LoadTexture("assets/textures/swordAttackAbilityIcon.png");
        BOW_ATTACK_ABILITY_ICON = rTextures.LoadTexture("assets/textures/bowAttackAbilityIcon.png");

        // Todo group all these tiles into tile atlas
        STAIRS_TILE0_TEXTURE = rTextures.LoadTexture("assets/textures/stairsTile0.png");
        STAIRS_TILE1_TEXTURE = rTextures.LoadTexture("assets/textures/stairsTile1.png");
        STAIRS_TILE2_TEXTURE = rTextures.LoadTexture("assets/textures/stairsTile2.png");
        STAIRS_TILE3_TEXTURE = rTextures.LoadTexture("assets/textures/stairsTile3.png");
    }

    public void unload() {
        Tartar2.raylib.textures.UnloadTexture(BASE_TILE_TEXTURE);
        Tartar2.raylib.textures.UnloadTexture(PLAYER_TEXTURE);
        Tartar2.raylib.textures.UnloadTexture(SKELETON_TEXTURE);
        Tartar2.raylib.textures.UnloadTexture(ARROW_TEXTURE);

        Tartar2.raylib.textures.UnloadTexture(SELECTION_FRAME_ICON);
        Tartar2.raylib.textures.UnloadTexture(MOVE_ABILITY_ICON);
        Tartar2.raylib.textures.UnloadTexture(SWORD_ATTACK_ABILITY_ICON);
        Tartar2.raylib.textures.UnloadTexture(BOW_ATTACK_ABILITY_ICON);

        Tartar2.raylib.textures.UnloadTexture(STAIRS_TILE0_TEXTURE);
        Tartar2.raylib.textures.UnloadTexture(STAIRS_TILE1_TEXTURE);
        Tartar2.raylib.textures.UnloadTexture(STAIRS_TILE2_TEXTURE);
        Tartar2.raylib.textures.UnloadTexture(STAIRS_TILE3_TEXTURE);
    }
}
