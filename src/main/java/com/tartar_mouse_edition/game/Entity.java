package com.tartar_mouse_edition.game;

import com.raylib.Jaylib;
import com.raylib.Raylib;
import com.tartar_mouse_edition.game.common.ITickable;

public abstract class Entity {
    protected Raylib.Vector3 position;

    public Entity(Raylib.Vector3 position) {
        this.position = position;
    }

    public Raylib.Vector3 getPosition() {
        return position;
    }

    public void setPosition(Raylib.Vector3 position) {
        this.position = position;
    }
}
