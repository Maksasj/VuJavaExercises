package com.tartar_mouse_edition.game.level;

import com.raylib.Jaylib;
import com.raylib.Raylib;
import com.tartar_mouse_edition.game.common.IRendarable2D;

import static com.raylib.Jaylib.RAYWHITE;
import static com.raylib.Raylib.*;

public class Map implements IRendarable2D {
    private Raylib.Image image;
    private Raylib.Texture texture;

    public Map() {
        this.image = LoadImage("src/main/resources/map.png");
        this.texture = LoadTextureFromImage(image);
    }

    public Mesh genMesh() {
        return GenMeshCubicmap(image, new Jaylib.Vector3( 1.0f, 1.0f,  1.0f));
    }

    @Override
    public void render() {
        DrawTextureEx(texture, new Jaylib.Vector2(5.0f, 5.0f), 0, 6, RAYWHITE);
    }
}
