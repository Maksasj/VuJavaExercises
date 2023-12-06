package com.tartar_mouse_edition.game;

import com.raylib.Raylib;

public abstract class Entity {
    private Raylib.Vector3 position;

    public Entity(Raylib.Vector3 position) {
        this.position = position;
    }

    public Raylib.Vector3 getPosition() {
        return position;
    }
}
