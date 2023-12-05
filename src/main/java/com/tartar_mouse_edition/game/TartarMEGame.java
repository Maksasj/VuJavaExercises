package com.tartar_mouse_edition.game;

import static com.raylib.Jaylib.RAYWHITE;
import static com.raylib.Jaylib.VIOLET;
import static com.raylib.Raylib.*;

import org.luaj.vm2.*;
import org.luaj.vm2.lib.jse.*;
// import org.luaj.vm2.lib.jse.JsePlatform;

public class TartarMEGame implements Runnable {
    public static void main() {
        InitWindow(800, 800, "Tartar: Mouse Edition");
        SetTargetFPS(60);
        Camera3D camera = new Camera3D()
                ._position(new Vector3().x(18).y(16).z(18))
                .target(new Vector3())
                .up(new Vector3().x(0).y(1).z(0))
                .fovy(45).projection(CAMERA_PERSPECTIVE);

        while (!WindowShouldClose()) {
            UpdateCamera(camera, CAMERA_ORBITAL);
            BeginDrawing();
            ClearBackground(RAYWHITE);
            BeginMode3D(camera);
            DrawGrid(20, 1.0f);
            EndMode3D();
            DrawText("Hello world", 190, 200, 20, VIOLET);
            DrawFPS(20, 20);
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
