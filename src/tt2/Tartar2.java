package tt2;

/**
 * @file
 * Tartar2.java
 *
 * @author
 * Maksim Jaroslavcevas radioboos@gmail.com
 *
 */

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import tt2.common.Settings;
import tt2.scene.GameScene;
import tt2.scene.MainMenuScene;
import tt2.scene.Scene;
import tt2.scene.SplashScene;
import tt2.textures.TextureAssetManager;
import tt2.world.LevelAssetManager;

import java.io.FileReader;

public class Tartar2 {
    public static Raylib raylib;
    public static boolean gameRunning;
    public static TextureAssetManager textureAssetManager;
    public static LevelAssetManager levelAssetManager;
    public static GameScene gameScene;
    public static MainMenuScene mainMenuScene;
    public static SplashScene splashScene;
    public static Scene activeScene;

    public static void preinit() {
        raylib = new Raylib();
        gameRunning = true;
    }

    public static void init() {
        raylib.core.InitWindow(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT, "Tartar 2");

        textureAssetManager = new TextureAssetManager();
        levelAssetManager = new LevelAssetManager();
    }

    public static void load() {
        textureAssetManager.load();
        levelAssetManager.load();

        gameScene = new GameScene();
        mainMenuScene = new MainMenuScene();
        splashScene = new SplashScene();

        activeScene = splashScene;
        // activeScene = gameScene;
    }

    public static void run() {
        while ((!raylib.core.WindowShouldClose()) && gameRunning) {
            // Tick part
            activeScene.tick();

            // Rendering part
            raylib.core.BeginDrawing();
                activeScene.render();
            raylib.core.EndDrawing();
        }
    }

    public static void unload() {
        textureAssetManager.unload();
        levelAssetManager.unload();
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

    public static void quitGame() {
        gameRunning = false;
    }
}