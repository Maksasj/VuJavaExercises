package tt2.scene;

import com.raylib.java.raymath.Vector2;
import com.raylib.java.raymath.Vector3;
import tt2.common.camera.Camera;
import tt2.common.camera.CameraController;
import tt2.world.World;

public class GameScene extends Scene {
    private final World gameWorld;
    private final Camera camera;
    private final CameraController cameraController;

    public GameScene() {
        gameWorld = new World();
        camera = new Camera(new Vector3(0.0f, 0.0f, 0.0f));
        cameraController = new CameraController();
    }

    @Override
    public void render() {
        gameWorld.render();
    }

    @Override
    public void step() {
        gameWorld.step();
    }

    @Override
    public void tick() {
        cameraController.moveCamera(camera);

        gameWorld.tick();
    }

    @Override
    public Camera getActiveCamera() {
        return camera;
    }
}
