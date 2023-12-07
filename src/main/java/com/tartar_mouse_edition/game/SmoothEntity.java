package com.tartar_mouse_edition.game;

import com.raylib.Jaylib;
import com.raylib.Raylib;
import com.tartar_mouse_edition.game.common.ITickable;

public class SmoothEntity extends Entity implements ITickable {
    private Raylib.Vector3 smoothPosition;
    public SmoothEntity(Raylib.Vector3 position) {
        super(position);

        this.smoothPosition = new Jaylib.Vector3(position.x(), position.y(), position.z());
    }

    @Override
    public void tick() {
        smoothPosition.x(smoothPosition.x() + (position.x() - smoothPosition.x()) / 10.0f);
        smoothPosition.y(smoothPosition.y() + (position.y() - smoothPosition.y()) / 10.0f);
        smoothPosition.z(smoothPosition.z() + (position.z() - smoothPosition.z()) / 10.0f);
    }

    @Override
    public Raylib.Vector3 getPosition() {
        return smoothPosition;
    }
}
