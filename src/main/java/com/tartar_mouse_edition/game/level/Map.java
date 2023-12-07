package com.tartar_mouse_edition.game.level;

import com.raylib.Jaylib;
import com.raylib.Raylib;
import com.tartar_mouse_edition.game.common.IRendarable2D;

import static com.raylib.Jaylib.RAYWHITE;
import static com.raylib.Raylib.*;

public class Map implements IRendarable2D {
    private Raylib.Image image;
    private Raylib.Texture texture;
    private Maze maze;

    public Map(MazeGenerator mazeGenerator) {
        this.image = LoadImage("src/main/resources/map.png");
        this.maze = mazeGenerator.getMaze();

        for(int i = 0; i < maze.getSize(); ++i) {
            for(int j = 0; j < maze.getSize(); ++j) {
                if(maze.isSolidAt(i, j) == 0)
                    ImageDrawPixel(image, i, j, new Jaylib.Color(0, 0, 0, 255));
                else
                    ImageDrawPixel(image, i, j, new Jaylib.Color(255, 255, 255, 255));
            }
        }

        this.texture = LoadTextureFromImage(image);
    }

    public Mesh genMesh() {
        return GenMeshCubicmap(image, new Jaylib.Vector3( 1.0f, 1.0f,  1.0f));
    }

    public Maze getMaze() {
        return maze;
    }

    @Override
    public void render() {
        DrawTextureEx(texture, new Jaylib.Vector2(5.0f, 5.0f), 0, 6, RAYWHITE);
    }
}
