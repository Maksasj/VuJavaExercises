package com.tartar_mouse_edition.game.common;

import com.tartar_mouse_edition.game.level.Maze;

public class Pair2D {
    public int x;
    public int y;

    public Pair2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equal(Pair2D pair2D) {
        return (this.x == pair2D.x) && (this.y == pair2D.y);
    }
}
