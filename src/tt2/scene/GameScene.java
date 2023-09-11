package tt2.scene;

import com.raylib.java.raymath.Vector2;
import tt2.common.Camera;
import tt2.world.World;

public class GameScene extends Scene {
    private final World gameWorld;
    private final Camera camera;

    public GameScene() {
        gameWorld = new World();
        camera = new Camera(new Vector2(0.0f, 0.0f));
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
        gameWorld.tick();
    }

    @Override
    public Camera getActiveCamera() {
        return camera;
    }
}
