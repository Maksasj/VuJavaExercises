package com.tartar_mouse_edition.game;

import com.raylib.Jaylib;
import com.raylib.Raylib;
import com.tartar_mouse_edition.common.Utils;
import com.tartar_mouse_edition.game.common.IActor;

import static com.raylib.Raylib.*;
import static com.raylib.Raylib.GetMouseX;

public class CameraActorEntity extends SmoothEntity implements IActor {
    private boolean mauseHold;
    private float mauseX;
    private float prevMauseX;
    private float zoom;

    public CameraActorEntity(Vector3 position) {
        super(position);

        mauseHold = false;
        mauseX = 0;
        zoom = 1.0f;
    }

    @Override
    public void act(Raylib.Camera3D camera) {
        var position = getPosition();

        camera.target(position);

        var camPos = new Jaylib.Vector3(
            (float) (position.x() - zoom * 7*Math.sin(mauseX)),
                zoom * 5.0f,
            (float) (position.z() - zoom * 7*Math.cos(mauseX)));

        // camPos.x(camPos.x() * zoom);
        // camPos.y(camPos.y() * zoom);
        // camPos.z(camPos.z() * zoom);

        camera._position(camPos);

        if(IsMouseButtonPressed(MOUSE_BUTTON_LEFT)) {
            mauseHold = true;

            prevMauseX = GetMouseX();
        }

        if(IsMouseButtonReleased(MOUSE_BUTTON_LEFT)) {
            mauseHold = false;
        }

        if(GetMouseWheelMove() != 0) {
            zoom -= GetMouseWheelMove() * 0.1f;
            zoom = Utils.clamp(zoom, 0.5f, 3.0f);
        }

        if(mauseHold) {
            mauseX += (prevMauseX - GetMouseX())*0.01f;
            prevMauseX = GetMouseX();
        }
    }
}
