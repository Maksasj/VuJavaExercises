package tt2.scene;

import com.raylib.java.raymath.Vector2;
import com.raylib.java.raymath.Vector3;
import tt2.common.GameController;
import tt2.common.camera.Camera;
import tt2.common.camera.CameraController;
import tt2.world.World;

public class GameScene extends Scene {
    private final World gameWorld;
    private final Camera camera;
    private final CameraController cameraController;
    private final GameController gameController;

    public GameScene() {
        gameController = new GameController();

        gameWorld = new World(gameController.getPlayer());
        camera = new Camera(new Vector3(0.0f, 0.0f, 0.0f));
        cameraController = new CameraController();
    }

    public GameController getGameController() {
        return gameController;
    }

    @Override
    public void render() {
        gameController.render();
        gameWorld.render();
    }

    @Override
    public void step() {
        gameController.step();
        gameWorld.step();
    }

    @Override
    public void tick() {
        cameraController.moveCamera(camera);
        cameraController.zoomCamera(camera);

        gameController.tick();
        gameWorld.tick();
    }

    @Override
    public Camera getActiveCamera() {
        return camera;
    }

    public World getWorld() {
        return gameWorld;
    }
}
