package com.tartar_mouse_edition.game;

import static com.raylib.Jaylib.RAYWHITE;
import static com.raylib.Raylib.*;

import com.tartar_mouse_edition.game.level.Level;
import org.luaj.vm2.*;
import org.luaj.vm2.lib.jse.*;

public class TartarMEGame implements Runnable {
    public static void main() {
        InitWindow(800, 800, "Tartar: Mouse Edition");
        SetTargetFPS(60);
        Camera3D camera = new Camera3D()
            ._position(new Vector3().x(0).y(8).z(8))
            .target(new Vector3().x(0).y(0).z(0))
            .up(new Vector3().x(0).y(1).z(0))
            .fovy(45).projection(CAMERA_PERSPECTIVE);

        Level level = new Level();
        Rat rat = new Rat();

        while (!WindowShouldClose()) {
            rat.act(camera);

            BeginDrawing();
                ClearBackground(RAYWHITE);

                BeginMode3D(camera);
                    level.render(camera);
                    rat.render(camera);
                EndMode3D();

                level.render();
            EndDrawing();
        }

        CloseWindow();
    }

    public static void test() {
        LuaValue globals = JsePlatform.standardGlobals();
        Globals gl = JsePlatform.standardGlobals();
        gl.load("x = 10 \n\t function TestF(v1, v2)\n\t  x = x + 10\n\t print('Test function, v1: ' .. x, 'v2: ' .. v2)\n\treturn v2\nend").call();
        gl.get("TestF").call( LuaValue.valueOf("ARG1"), LuaValue.valueOf("ARG2"));
        gl.get("TestF").call( LuaValue.valueOf("ARG1"), LuaValue.valueOf("ARG2"));
        LuaValue lv = gl.get("TestF").call( LuaValue.valueOf("ARG1"), LuaValue.valueOf("ARG2"));
        System.out.println(lv.toString());
    }

    @Override
    public void run() {
        test();
        main();
    }
}
