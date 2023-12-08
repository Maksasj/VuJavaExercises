package com.tartar_mouse_edition.game;

import static com.raylib.Jaylib.RAYWHITE;
import static com.raylib.Raylib.*;

import com.raylib.Jaylib;
import com.tartar_mouse_edition.game.common.Pair2D;
import com.tartar_mouse_edition.game.level.Level;
import com.tartar_mouse_edition.game.rat.Rat;
import com.tartar_mouse_edition.game.rat.RatController;

import java.util.Random;

public class TartarMEGame implements Runnable {
    /*
    -- write your code there

    function path()
        while(true) do
            rat:rotate_left()

            if(not rat:look()) then
                rat:rotate_right()
            end

            if(not rat:look()) then
                rat:rotate_right()
            end

            rat:walk()
        end
    end
    */

    public static void main() {
        SetTraceLogLevel(LOG_NONE);

        InitWindow(800, 800, "Tartar: Mouse Edition");
        SetTargetFPS(60);
        Camera3D camera = new Camera3D()
            ._position(new Vector3().x(0).y(8).z(8))
            .target(new Vector3().x(0).y(0).z(0))
            .up(new Vector3().x(0).y(1).z(0))
            .fovy(45).projection(CAMERA_PERSPECTIVE);

        Level level = new Level();

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

        Rat rat = new Rat(new Jaylib.Vector3(startPos.x, 0.0f, startPos.y));
        Cheese cheese = new Cheese(new Jaylib.Vector3(endPos.x, 0.0f, endPos.y));

        RatController.setActiveRat(rat);
        RatController.setActiveLevel(level);
        RatController.setActiveCheese(cheese);

        updateMiniMap();

        while (!WindowShouldClose()) {
            rat.tick();
            rat.act(camera);

            if(rat.isMoved()) {
                updateMiniMap();
                rat.markMoved(false);
            }

            BeginDrawing();
                ClearBackground(RAYWHITE);

                BeginMode3D(camera);
                    level.render(camera);
                    rat.render(camera);
                    cheese.render(camera);
                EndMode3D();

                level.render();
            EndDrawing();
        }

        CloseWindow();
    }

    public static void submitCode(String text) {
        RatController.simulate(text);
    }

    public static void forceStop() {
        RatController.forceStop();
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
