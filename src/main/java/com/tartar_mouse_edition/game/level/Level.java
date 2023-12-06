package com.tartar_mouse_edition.game.level;

import com.raylib.Jaylib;
import com.raylib.Raylib;
import com.sun.prism.Texture;
import com.tartar_mouse_edition.game.common.IRendarable2D;
import com.tartar_mouse_edition.game.common.IRendarable3D;

import static com.raylib.Jaylib.RAYWHITE;
import static com.raylib.Raylib.*;
import static com.raylib.Raylib.LoadTexture;

public class Level implements IRendarable3D, IRendarable2D {
    private Map map;
    private Raylib.Model model;
    private Vector3 position;

    public Level() {
        this.map = new Map();
        this.model = LoadModelFromMesh(map.genMesh());

        var texture = LoadTexture("src/main/resources/cubicmap_atlas.png");
        model.materials()
            .maps()
            .texture(texture);

        this.position = new Jaylib.Vector3( -12.0f, 0.0f,  -12.0f);
    }

    @Override
    public void render() {
        map.render();
    }

    @Override
    public void render(Raylib.Camera3D camera) {
        DrawModel(model, position, 1.0f, RAYWHITE);
    }
}
