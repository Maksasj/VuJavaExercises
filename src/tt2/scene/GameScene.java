package tt2.scene;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.raymath.Vector3;
import tt2.Tartar2;
import tt2.common.GameController;
import tt2.common.camera.Camera;
import tt2.common.camera.CameraController;
import tt2.world.World;

public class GameScene extends Scene {
    private World gameWorld;
    private final Camera camera;
    private final CameraController cameraController;
    private final GameController gameController;

    public GameScene() {
        super();

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
        Tartar2.raylib.core.ClearBackground(Color.DARKGRAY);

        gameController.doRenderingPreProcessing();
        gameWorld.doRenderingPreProcessing();
        gameWorld.render();

        gameController.render();
    }

    @Override
    public void step() {
        gameController.step();
        gameWorld.step();
    }

    @Override
    public void tick() {
        super.tick();

        cameraController.moveCamera(camera);
        cameraController.zoomCamera(camera);

        gameWorld.tick();
        gameController.tick();
    }

    @Override
    public Camera getActiveCamera() {
        return camera;
    }

    public World getWorld() {
        return gameWorld;
    }

    public void nextLevel() {
        // For now we just create new world
        gameWorld = new World(gameController.getPlayer());
    }
}
