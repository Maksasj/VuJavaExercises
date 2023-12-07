package com.tartar_mouse_edition.game;

import com.raylib.Jaylib;
import com.raylib.Raylib;
import com.tartar_mouse_edition.game.common.IActor;
import com.tartar_mouse_edition.game.common.IRendarable3D;
import com.tartar_mouse_edition.game.common.IRotatable;
import com.tartar_mouse_edition.game.common.Rotation;

import static com.raylib.Jaylib.RAYWHITE;
import static com.raylib.Raylib.*;

public class Rat extends CameraActorEntity implements IRendarable3D, IActor, IRotatable {
    private Raylib.Model model;
    private Rotation rotation;

    public Rat(Vector3 pos) {
        super(pos);

        this.model = LoadModel("src/main/resources/rat/rat.obj");                 // Load model

        Raylib.Texture radTexture = LoadTexture("src/main/resources/rat/rat.png"); // Load model texture
        this.model.materials()
            .maps()
            .texture(radTexture);

        this.rotation = Rotation.X_PLUS;
    }

    @Override
    public void render(Raylib.Camera3D camera) {
        switch (rotation) {
            case X_PLUS -> {
                DrawModelEx(
                    model,
                    position,
                    new Jaylib.Vector3(0.0f, 1.0f, 0.0f),
                    0,
                    new Jaylib.Vector3(0.5f, 0.5f, 0.5f),
                    RAYWHITE);
            }
            case X_MINUS -> {
                DrawModelEx(
                    model,
                    position,
                    new Jaylib.Vector3(0.0f, 1.0f, 0.0f),
                    180,
                    new Jaylib.Vector3(0.5f, 0.5f, 0.5f),
                    RAYWHITE);
            }
            case Z_PLUS -> {
                DrawModelEx(
                    model,
                    position,
                    new Jaylib.Vector3(0.0f, 1.0f, 0.0f),
                    90,
                    new Jaylib.Vector3(0.5f, 0.5f, 0.5f),
                    RAYWHITE);
            }
            case Z_MINUS -> {
                DrawModelEx(
                    model,
                    position,
                    new Jaylib.Vector3(0.0f, 1.0f, 0.0f),
                    270,
                    new Jaylib.Vector3(0.5f, 0.5f, 0.5f),
                    RAYWHITE);
            }
        }


    }

    @Override
    public void rotate(Rotation rotation) {
        this.rotation = rotation;
    }
}
