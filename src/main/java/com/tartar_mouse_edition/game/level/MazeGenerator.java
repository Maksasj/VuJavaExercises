/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.tartar_mouse_edition.game.level;

import com.tartar_mouse_edition.game.level.Maze;

import java.util.Arrays;
import java.util.Collections;

public class MazeGenerator {
    private final int size = 24;
    private final int[][] maze = new int[size][size];

    public MazeGenerator() {
        for(int[] row : maze) {
            Arrays.fill(row, 1);
        }

        generateMaze(1, 1);

        for(int i = 0; i < size; ++i) {
            maze[i][size - 1] = 1;
            maze[size - 1][i] = 1;
            maze[0][i] = 1;
            maze[i][0] = 1;
        }
    }

    private void generateMaze(int x, int y) {
        int[][] dirs = {{-2, 0}, {0, 2}, {2, 0}, {0, -2}};
        Collections.shuffle(Arrays.asList(dirs));

        maze[x][y] = 0;

        for (int[] dir : dirs) {
            int nextX = x + dir[0];
            int nextY = y + dir[1];

            if (isInBounds(nextX, nextY) && maze[nextX][nextY] == 1) {
                maze[x + dir[0] / 2][y + dir[1] / 2] = 0;
                generateMaze(nextX, nextY);
            }
        }
    }

    private boolean isInBounds(int x, int y) {
        return (x >= 0) && (y >= 0) && (x < size) && (y < size);
    }

    public Maze getMaze() {
        return new Maze(size, maze);
    }
}