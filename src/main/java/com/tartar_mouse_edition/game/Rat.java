package com.tartar_mouse_edition.game;

import com.raylib.Jaylib;
import com.raylib.Raylib;
import com.tartar_mouse_edition.game.common.IActor;
import com.tartar_mouse_edition.game.common.IRendarable3D;

import static com.raylib.Jaylib.RAYWHITE;
import static com.raylib.Raylib.*;

public class Rat extends CameraActorEntity implements IRendarable3D, IActor {
    private Raylib.Model model;

    public Rat() {
        super(new Jaylib.Vector3( 0.0f, 0.0f,  0.0f));

        this.model = LoadModel("src/main/resources/rat/rat.obj");                 // Load model

        Raylib.Texture radTexture = LoadTexture("src/main/resources/rat/rat.png"); // Load model texture
        this.model.materials()
            .maps()
            .texture(radTexture);

    }

    @Override
    public void render(Raylib.Camera3D camera) {
        var position = getPosition();
        var pos = new Jaylib.Vector3(position.x(), position.y(), position.z() + 0.4f);

        DrawModel(model, pos, 0.5f, RAYWHITE);
    }
}
