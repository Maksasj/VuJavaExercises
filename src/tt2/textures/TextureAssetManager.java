package tt2.textures;

import com.raylib.java.shapes.Rectangle;
import com.raylib.java.textures.*;
import tt2.Tartar2;
import tt2.common.IAssetManager;

import java.util.ArrayList;
import java.util.List;

public class TextureAssetManager implements IAssetManager {
    public static List<Texture> allTextures;

    public static Texture tileTextures;
    public static Texture mobsTexture;
    public static Texture uiTexture;
    public static Texture backGroundTexture;

    public void load() {
        allTextures = new ArrayList<>();

        backGroundTexture = addTexture(new Texture(
            "assets/textures/backgroundTexture.png",
            new Rectangle[]{
                new Rectangle(0, 0, 432, 240),
                new Rectangle(0, 240, 432, 240),
                new Rectangle(0, 480, 432, 240)
            }
        ));

        tileTextures = addTexture(new Texture(
            "assets/textures/tilesTexture.png",
            new Rectangle[]{
                new Rectangle(0, 0, 32, 32),    // 0 Default tile (Red)
                new Rectangle(32, 0, 32, 32),   // 1 Default tile (Blue)
                new Rectangle(64, 0, 32, 32),   // 2 Default tile (Yellow)
                new Rectangle(0, 64, 32, 32),   // 3 Portal
                new Rectangle(0, 32, 32, 32),   // 4 Stairs left-up
                new Rectangle(32, 32, 32, 32),  // 5 Stairs right-up
                new Rectangle(64, 32, 32, 32),  // 6 Stairs right-down
                new Rectangle(96, 32, 32, 32),  // 7 Stairs left-down
            }
        ));

        mobsTexture = addTexture(new Texture(
            "assets/textures/mobsTexture.png",
            new Rectangle[]{
                new Rectangle(0, 0, 32, 32),    // 0 Player left up
                new Rectangle(0, 32, 32, 32),   // 1 Player right up
                new Rectangle(0, 64, 32, 32),   // 2 Player right down
                new Rectangle(0, 96, 32, 32),   // 3 Player left down

                new Rectangle(32, 0, 32, 32),   // 4 Skeleton left up
                new Rectangle(32, 32, 32, 32),  // 5 Skeleton right up
                new Rectangle(32, 64, 32, 32),  // 6 Skeleton right down
                new Rectangle(32, 96, 32, 32),  // 7 Skeleton left down

                new Rectangle(64, 0, 16, 16),     // 8 Bullet left up
                new Rectangle(64, 16, 16, 16),    // 9 Bullet right up
                new Rectangle(64, 32, 16, 16),    // 10 Bullet right down
                new Rectangle(64, 48, 16, 16),    // 11 Bullet left down
            }
        ));

        uiTexture = addTexture(new Texture(
            "assets/textures/uiTexture.png",
            new Rectangle[]{
                new Rectangle(0, 0, 256, 64),   // 0 Tartar 2 logo
                new Rectangle(0, 64, 48, 16),   // 1 Play button
                new Rectangle(48, 64, 48, 16),  // 2 Quit button
                new Rectangle(0, 80, 32, 32),   // 3 Selection frame icon
                new Rectangle(32, 80, 32, 32),  // 4 Walk ability icon
                new Rectangle(64, 80, 32, 32),  // 5 Melee Attack ability icon
                new Rectangle(96, 80, 32, 32),  // 6 Ranged Attack ability icon
                new Rectangle(256, 0, 96, 96),  // 7 Ufo enjoyers logo
                new Rectangle(352, 0, 8, 8),    // 8 Empty heart icon
                new Rectangle(352, 8, 8, 8),    // 9 Full heart icon
            }
        ));
    }

    public Texture addTexture(Texture texture) {
        allTextures.add(texture);
        return texture;
    }

    public void unload() {
        for(Texture texture : allTextures)
            texture.unload();
    }
}
