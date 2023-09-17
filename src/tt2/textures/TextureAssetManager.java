package tt2.textures;

import com.raylib.java.textures.*;
import tt2.Tartar2;
import tt2.common.IAssetManager;

import java.util.ArrayList;
import java.util.List;

public class TextureAssetManager implements IAssetManager {
    public static Texture2D BASE_TILE_TEXTURE;
    public static Texture2D PORTAL_TILE_TEXTURE;
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

    public static Texture2D UFO_ENJOYERS_LOGO;
    public static Texture2D TARTAR2_LOGO;

    public static Texture2D PLAY_BUTTON;
    public static Texture2D QUIT_BUTTON;

    public static List<Texture2D> allTextures;

    public void load() {
        allTextures = new ArrayList<>();

        BASE_TILE_TEXTURE = addTexture("assets/textures/baseTile.png");
        PORTAL_TILE_TEXTURE = addTexture("assets/textures/portalTile.png");

        PLAYER_TEXTURE = addTexture("assets/textures/player.png");
        SKELETON_TEXTURE = addTexture("assets/textures/skeleton.png");
        ARROW_TEXTURE = addTexture("assets/textures/arrow.png");

        SELECTION_FRAME_ICON = addTexture("assets/textures/selectionFrameIcon.png");
        MOVE_ABILITY_ICON = addTexture("assets/textures/moveAbilityIcon.png");
        SWORD_ATTACK_ABILITY_ICON = addTexture("assets/textures/swordAttackAbilityIcon.png");
        BOW_ATTACK_ABILITY_ICON = addTexture("assets/textures/bowAttackAbilityIcon.png");

        // Todo group all these tiles into tile atlas
        STAIRS_TILE0_TEXTURE = addTexture("assets/textures/stairsTile0.png");
        STAIRS_TILE1_TEXTURE = addTexture("assets/textures/stairsTile1.png");
        STAIRS_TILE2_TEXTURE = addTexture("assets/textures/stairsTile2.png");
        STAIRS_TILE3_TEXTURE = addTexture("assets/textures/stairsTile3.png");

        UFO_ENJOYERS_LOGO = addTexture("assets/textures/ufoEnjoyersLogo.png");
        TARTAR2_LOGO = addTexture("assets/textures/tartar2Logo.png");

        PLAY_BUTTON = addTexture("assets/textures/playButton.png");
        QUIT_BUTTON = addTexture("assets/textures/quitButton.png");
    }

    public Texture2D addTexture(String path) {
        Texture2D texture = rTextures.LoadTexture(path);
        allTextures.add(texture);
        return texture;
    }

    public void unload() {
        for(Texture2D texture : allTextures)
            Tartar2.raylib.textures.UnloadTexture(texture);
    }
}
