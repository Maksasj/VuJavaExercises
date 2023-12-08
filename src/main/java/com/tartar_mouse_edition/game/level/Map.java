package com.tartar_mouse_edition.game.level;

import com.raylib.Jaylib;
import com.raylib.Raylib;
import com.tartar_mouse_edition.game.common.IRendarable2D;
import com.tartar_mouse_edition.game.common.Pair2D;

import static com.raylib.Jaylib.RAYWHITE;
import static com.raylib.Raylib.*;

public class Map implements IRendarable2D {
    private Raylib.Image image;
    private Maze maze;

    private Raylib.Image miniMapImage;
    private Raylib.Texture miniMapTexture;

    public Map(MazeGenerator mazeGenerator) {
        this.image = LoadImage("src/main/resources/map.png");
        this.miniMapImage = LoadImage("src/main/resources/map.png");
        this.maze = mazeGenerator.getMaze();

        updateMap();
        updateMiniMap(new Pair2D(0, 0), new Pair2D(0, 0));
    }

    public void updateMap() {
        for(int i = 0; i < maze.getSize(); ++i) {
            for(int j = 0; j < maze.getSize(); ++j) {
                if(maze.isSolidAt(i, j) == 0)
                    ImageDrawPixel(image, i, j, new Jaylib.Color(0, 0, 0, 255));
                else
                    ImageDrawPixel(image, i, j, new Jaylib.Color(255, 255, 255, 255));
            }
        }
    }

    synchronized public void updateMiniMap(Pair2D mouse, Pair2D cheese) {
        for(int i = 1; i < maze.getSize() - 1; ++i) {
            for(int j = 1; j < maze.getSize() - 1; ++j) {
                if(maze.isSolidAt(i, j) == 0)
                    ImageDrawPixel(miniMapImage, i, j, new Jaylib.Color(255, 255, 255, 255));
                else
                    ImageDrawPixel(miniMapImage, i, j, new Jaylib.Color(0, 0, 0, 255));

            }
        }

        for(int i = 0; i < maze.getSize(); ++i) {
            ImageDrawPixel(miniMapImage, i, 0, new Jaylib.Color(79, 135, 200, 255));
            ImageDrawPixel(miniMapImage, i, maze.getSize() - 1, new Jaylib.Color(79, 135, 200, 255));

            ImageDrawPixel(miniMapImage, 0, i, new Jaylib.Color(79, 135, 200, 255));
            ImageDrawPixel(miniMapImage, maze.getSize() - 1, i, new Jaylib.Color(79, 135, 200, 255));
        }

        ImageDrawPixel(miniMapImage, mouse.x, mouse.y, new Jaylib.Color(113, 200, 79, 255));
        ImageDrawPixel(miniMapImage, cheese.x, cheese.y, new Jaylib.Color(200, 170, 79, 255));

        this.miniMapTexture = LoadTextureFromImage(miniMapImage);
    }

    public Mesh genMesh() {
        return GenMeshCubicmap(image, new Jaylib.Vector3( 1.0f, 0.3f,  1.0f));
    }

    public Maze getMaze() {
        return maze;
    }

    @Override
    public void render() {
        DrawTextureEx(miniMapTexture, new Jaylib.Vector2(5.0f, 5.0f), 0, 6, RAYWHITE);
    }
}
