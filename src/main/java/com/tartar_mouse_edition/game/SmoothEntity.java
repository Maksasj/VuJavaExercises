package com.tartar_mouse_edition.game;

import com.raylib.Jaylib;
import com.raylib.Raylib;
import com.tartar_mouse_edition.game.common.ITickable;
import com.tartar_mouse_edition.game.common.Pair2D;

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

    public void movePosition(Raylib.Vector3 move) {
        setPosition(new Jaylib.Vector3(
        position.x() + move.x(),
        position.y() + move.y(),
        position.z() + move.z()
        ));
    }

    @Override
    public Raylib.Vector3 getPosition() {
        return smoothPosition;
    }
}
