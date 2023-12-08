package com.tartar_mouse_edition.game;

import static com.raylib.Jaylib.RAYWHITE;
import static com.raylib.Jaylib.WHITE;
import static com.raylib.Raylib.*;

import com.raylib.Jaylib;
import com.raylib.Raylib;
import com.tartar_mouse_edition.game.common.Pair2D;
import com.tartar_mouse_edition.game.level.Level;
import com.tartar_mouse_edition.game.rat.Rat;
import com.tartar_mouse_edition.game.rat.RatController;
import javafx.scene.input.MouseButton;

import java.util.Random;

public class TartarMEGame implements Runnable {
    public static GameState state;
    public static Level level;
    public static Rat rat;
    public static Cheese cheese;

    private static RenderTexture levelTarget;
    private static RenderTexture entitiesTarget;
    private static int animationTimer;

    private static Texture titleTexture;
    private static Texture starTexture;
    private static Texture levelCompleteTexture;

    private static float titleOpacity = 1.0f;
    private static float levelCompleteOpacity = 0.0f;
    private static boolean restartLevel = true;

    public static void main() {
        // SetTraceLogLevel(LOG_NONE);
        state = GameState.SPLASH_SCREEN;
        animationTimer = 0;

        InitWindow(800, 800, "Tartar: Mouse Edition");
        SetTargetFPS(60);
        Camera3D camera = new Camera3D()
            ._position(new Vector3().x(0).y(8).z(8))
            .target(new Vector3().x(0).y(0).z(0))
            .up(new Vector3().x(0).y(1).z(0))
            .fovy(45).projection(CAMERA_PERSPECTIVE);

        levelTarget = LoadRenderTexture(800, 800);
        entitiesTarget = LoadRenderTexture(800, 800);

        titleTexture = LoadTexture("src/main/resources/title.png");
        starTexture = LoadTexture("src/main/resources/star.png");
        levelCompleteTexture = LoadTexture("src/main/resources/levelCompleteScreen.png");

        Shader shader = LoadShader("src/main/resources/shader/shader.vs", "src/main/resources/shader/shader.fs");

        while (!WindowShouldClose()) {
            if(restartLevel) {
                loadLevel();
                restartLevel = false;
            }

            rat.tick();
            rat.act(camera);

            if(rat.isMoved()) {
                updateMiniMap();
                rat.markMoved(false);
            }

            if(rat.getGridPos().equal(cheese.getGridPos()) && state != GameState.LEVEL_FINISHED) {
                state = GameState.LEVEL_FINISHED;
                forceStop();
            }

            BeginDrawing();
            BeginTextureMode(levelTarget);
            ClearBackground(new Jaylib.Color(0, 0, 0, 0));

            BeginMode3D(camera);
            level.render(camera);
            EndMode3D();
            EndTextureMode();

            BeginTextureMode(entitiesTarget);
            ClearBackground(new Jaylib.Color(0, 0, 0, 0));

            BeginMode3D(camera);
            rat.render(camera);
            cheese.render(camera);
            EndMode3D();
            EndTextureMode();

            ClearBackground(RAYWHITE);

            Raylib.BeginShaderMode(shader);
                DrawTextureEx(levelTarget.texture(), new Jaylib.Vector2(0, 0), 0, 1, RAYWHITE);
                DrawTextureEx(entitiesTarget.texture(), new Jaylib.Vector2(0, 0), 0, 1, RAYWHITE);
            Raylib.EndShaderMode();

            if(IsKeyPressed(KEY_SPACE) && state == GameState.SPLASH_SCREEN) {
                state = GameState.GAME;
            }

            if(IsKeyPressed(KEY_SPACE) && state == GameState.LEVEL_FINISHED) {
                state = GameState.SPLASH_SCREEN;
                restartLevel = true;
                titleOpacity = 0;
            }

            switch (state) {
                case GAME -> renderGame();
                case SPLASH_SCREEN -> renderSplashScreen();
                case LEVEL_FINISHED -> renderLevelFinished();
            }

            EndDrawing();
        }

        CloseWindow();
    }

    public static void renderGame() {
        titleOpacity -= 0.02;
        if(titleOpacity <= 0) {
            titleOpacity = 0;
        }

        DrawRectangle(0, 0, 800, 800, new Jaylib.Color(0, 0, 0, (int) (150 * titleOpacity)));
        DrawTexture(titleTexture, 0, 0, new Jaylib.Color(255, 255, 255, (int) (255 * titleOpacity)));

        level.render();
    }

    public static void renderSplashScreen() {
        titleOpacity += 0.02;
        if(titleOpacity >= 1) {
            titleOpacity = 1;
        }

        DrawRectangle(0, 0, 800, 800, new Jaylib.Color(0, 0, 0, (int) (150 * titleOpacity)));
        DrawTexture(titleTexture, 0, 0, new Jaylib.Color(255, 255, 255, (int) (255 * titleOpacity)));
    }

    public static void renderLevelFinished() {
        levelCompleteOpacity += 0.02;

        if(levelCompleteOpacity >= 1) {
            levelCompleteOpacity = 1;
        }

        DrawRectangle(0, 0, 800, 800, new Jaylib.Color(0, 0, 0, (int) (150 * levelCompleteOpacity)));
        DrawTexture(levelCompleteTexture, 0, 0, new Jaylib.Color(255, 255, 255, (int) (255 * levelCompleteOpacity)));
    }

    public static void loadLevel() {
        level = new Level();

        var random = new Random();
        var map = level.getMap();
        var maze = map.getMaze();

        Pair2D startPos = null;
        Pair2D endPos = null;

        while(true) {
            var start = new Pair2D(random.nextInt(0, 23), random.nextInt(0, 23));
            var finish = new Pair2D(random.nextInt(0, 23), random.nextInt(0, 23));

            if(start.equal(finish)) continue;

            if(maze.isSolidAt(start.x, start.y) == 1) continue;
            if(maze.isSolidAt(finish.x, finish.y) == 1) continue;

            if(maze.pathExist(start.x, start.y, finish.x, finish.y)) {
                startPos = start;
                endPos = finish;
                break;
            }
        }

        rat = new Rat(new Jaylib.Vector3(startPos.x, 0.0f, startPos.y));
        cheese = new Cheese(new Jaylib.Vector3(endPos.x, 0.0f, endPos.y));

        RatController.setActiveRat(rat);
        RatController.setActiveLevel(level);
        RatController.setActiveCheese(cheese);

        updateMiniMap();
    }

    public static void submitCode(String text) {
        if(state != GameState.GAME) {
            state = GameState.GAME;
        }

        RatController.simulate(text);
    }

    public static void forceStop() {
        RatController.forceStop();
    }

    public static void restart() {
        RatController.forceStop();

        restartLevel = true;
        state = GameState.SPLASH_SCREEN;
        titleOpacity = 1.0f;
    }

    public static void updateMiniMap() {
        var level = RatController.getActiveLevel();
        var rat = RatController.getActiveRat();
        var cheese = RatController.getActiveCheese();

        var map = level.getMap();

        var ratPos = new Pair2D((int) rat.position.x(), (int) rat.position.z());
        var cheesePos = new Pair2D((int) cheese.position.x(), (int) cheese.position.z());

        map.updateMiniMap(ratPos, cheesePos);
    }

    @Override
    public void run() {
        main();
    }
}
