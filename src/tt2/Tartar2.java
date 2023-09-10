package tt2;

import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import tt2.textures.TextureAssetManager;

public class Tartar2 {
    public static Raylib raylib;

    public static TextureAssetManager textureAssetManager;

    public static void preinit() {
        raylib = new Raylib();
    }

    public static void init() {
        raylib.core.InitWindow(800, 600, "Tartar 2");

        textureAssetManager = new TextureAssetManager();
    }

    public static void load() {
        textureAssetManager.load();
    }

    public static void run() {
        while (!raylib.core.WindowShouldClose()) {
            raylib.core.BeginDrawing();
            raylib.core.ClearBackground(Color.WHITE);
            raylib.text.DrawText("Hello, World!", 800 - (raylib.text.MeasureText("Hello, World!", 20) / 2), 300, 20, Color.DARKGRAY);

            // Render world

            raylib.core.EndDrawing();
        }
    }

    public static void unload() {

    }

    public static void main(String[] args) {
        preinit();
        init();
        load();
        run();
        unload();
    }
}