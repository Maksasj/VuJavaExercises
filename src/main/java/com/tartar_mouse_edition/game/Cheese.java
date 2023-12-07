package com.tartar_mouse_edition.game;

import com.raylib.Jaylib;
import com.tartar_mouse_edition.game.common.IActor;
import com.tartar_mouse_edition.game.common.IRendarable3D;
import com.tartar_mouse_edition.game.common.IRotatable;
import com.tartar_mouse_edition.game.common.Rotation;

import static com.raylib.Jaylib.RAYWHITE;
import static com.raylib.Raylib.*;

public class Cheese extends Entity implements IRendarable3D, IRotatable {
    private Model model;
    private Rotation rotation;

    public Cheese(Vector3 pos) {
        super(pos);

        this.model = LoadModel("src/main/resources/cheese/cheese.obj");                 // Load model

        Texture cheeseTexture = LoadTexture("src/main/resources/cheese/cheese.png"); // Load model texture
        this.model.materials()
            .maps()
            .texture(cheeseTexture);

        this.rotation = Rotation.X_PLUS;
    }

    @Override
    public void render(Camera3D camera) {
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
