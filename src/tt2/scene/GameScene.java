package tt2.scene;

import tt2.world.World;

public class GameScene extends Scene {
    private World gameWorld;

    public GameScene() {
        gameWorld = new World();
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
}
