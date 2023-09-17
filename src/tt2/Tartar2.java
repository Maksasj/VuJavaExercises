package tt2;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import tt2.common.Settings;
import tt2.scene.GameScene;
import tt2.scene.MainMenuScene;
import tt2.scene.Scene;
import tt2.scene.SplashScene;
import tt2.textures.TextureAssetManager;

public class Tartar2 {
    public static Raylib raylib;

    public static TextureAssetManager textureAssetManager;
    public static GameScene gameScene;
    public static MainMenuScene mainMenuScene;
    public static SplashScene splashScene;
    public static Scene activeScene;

    public static void preinit() {
        raylib = new Raylib();
    }

    public static void init() {
        raylib.core.InitWindow(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT, "Tartar 2");

        textureAssetManager = new TextureAssetManager();
    }

    public static void load() {
        textureAssetManager.load();

        gameScene = new GameScene();
        mainMenuScene = new MainMenuScene();
        splashScene = new SplashScene();

        activeScene = splashScene;
    }

    public static void run() {
        while (!raylib.core.WindowShouldClose()) {
            // Tick part
            activeScene.tick();

            // Rendering part
            raylib.core.BeginDrawing();
                raylib.core.ClearBackground(Color.DARKGRAY);
                activeScene.render();
            raylib.core.EndDrawing();
        }
    }

    public static void unload() {
        textureAssetManager.unload();
    }

    public static void setActiveScene(Scene scene) {
        activeScene = scene;
    }

    public static void main(String[] args) {
        preinit();
        init();
        load();
        run();
        unload();
    }
}