package com.tartar_mouse_edition.game.level;

import com.tartar_mouse_edition.game.common.Pair2D;

import java.util.*;

public class Maze {
    private final int size;
    private final int[][] maze;

    public Maze(int size, int[][] maze) {
        this.size = size;
        this.maze = maze;
    }

    public int isSolidAt(int i, int j) {
        return maze[i][j];
    }

    public int getSize() {
        return size;
    }

    private boolean isInBounds(int x, int y) {
        return (x >= 0) && (y >= 0) && (x < size) && (y < size);
    }

    private boolean pointExist(List<Pair2D> points, Pair2D point) {
        for(var p : points) {
            if(p.equal(point))
                return true;
        }

        return false;
    }

    public boolean pathExist(int iS, int jS, int iF, int jF) {
        var starP = new Pair2D(iS, jS);
        var starF = new Pair2D(iF, jF);

        var st = new Stack<Pair2D>();
        st.push(starP);

        var visited = new ArrayList<Pair2D>();
        visited.add(starP);

        while(!st.isEmpty()) {
            var p = st.pop();

            if(p.equal(starF))
                return true;

            if(isInBounds(p.x - 1, p.y) && (maze[p.x - 1][p.y] == 0) && !pointExist(visited, new Pair2D(p.x - 1, p.y))) {
                visited.add(new Pair2D(p.x - 1, p.y));
                st.push(new Pair2D(p.x - 1, p.y));
            }

            if(isInBounds(p.x + 1, p.y) && (maze[p.x + 1][p.y] == 0) && !pointExist(visited, new Pair2D(p.x + 1, p.y))) {
                visited.add(new Pair2D(p.x + 1, p.y));
                st.push(new Pair2D(p.x + 1, p.y));
            }

            if(isInBounds(p.x, p.y + 1) && (maze[p.x][p.y + 1] == 0) && !pointExist(visited, new Pair2D(p.x, p.y + 1))) {
                visited.add(new Pair2D(p.x, p.y + 1));
                st.push(new Pair2D(p.x, p.y + 1));
            }

            if(isInBounds(p.x, p.y - 1) && (maze[p.x][p.y - 1] == 0) && !pointExist(visited, new Pair2D(p.x, p.y - 1))) {
                visited.add(new Pair2D(p.x, p.y - 1));
                st.push(new Pair2D(p.x, p.y - 1));
            }
        }

        return false;
    }
}
