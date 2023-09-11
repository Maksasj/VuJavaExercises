package tt2.common.camera;

import com.raylib.java.raymath.Vector2;
import com.raylib.java.raymath.Vector3;
import com.raylib.java.shapes.Rectangle;
import tt2.Tartar2;
import tt2.common.Settings;

import java.lang.Math;

public class CameraController {
    private final Rectangle interactionZone;
    private final Vector2 screenCenter;

    private Vector2 mousePreviousPosition;

    public CameraController() {
        int interactionZoneSize = 70;

        interactionZone = new Rectangle(interactionZoneSize, interactionZoneSize, Settings.WINDOW_WIDTH - interactionZoneSize * 2, Settings.WINDOW_HEIGHT - interactionZoneSize * 2);
        screenCenter = new Vector2((float) Settings.WINDOW_WIDTH / 2, (float) Settings.WINDOW_HEIGHT / 2);

        mousePreviousPosition = getMousePosition();
    }

    public static Vector2 getMousePosition() {
        return new Vector2(Tartar2.raylib.core.GetMouseX(), Tartar2.raylib.core.GetMouseY());
    }

    public void moveCamera(Camera camera) {
        Vector2 mousePosition = new Vector2(Tartar2.raylib.core.GetMouseX(), Tartar2.raylib.core.GetMouseY());

        if(Settings.ENABLE_INTERACTION_ZONE_SLIDING) {
            // If mouse is not in the interaction zone lets move camera
            if(!Tartar2.raylib.shapes.CheckCollisionPointRec(mousePosition, interactionZone)) {
                Vector2 delta = new Vector2(screenCenter.x - mousePosition.x, screenCenter.y - mousePosition.y);

                float length = (float) Math.sqrt(delta.x * delta.x + delta.y * delta.y);

                delta.x /= length;
                delta.y /= length;

                camera.move(new Vector3(delta.x, 0.0f, delta.y));
            }
        }

        if(!Tartar2.raylib.core.IsMouseButtonUp(0)) {
            Vector2 delta = new Vector2(mousePosition.x - mousePreviousPosition.x, mousePosition.y - mousePreviousPosition.y);

            camera.move(new Vector3(delta.x, 0.0f, delta.y));
        }

        mousePreviousPosition = mousePosition;
    }
}
