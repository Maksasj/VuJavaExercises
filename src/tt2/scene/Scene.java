package tt2.scene;

import com.raylib.java.core.rCore;
import tt2.common.*;
import tt2.common.camera.Camera;

public abstract class Scene extends CommonRenderingMaster implements ITickable, IStepable {
    private float timer;

    public Scene() {
        timer = 0;
    }

    @Override
    public void tick() {
        timer += rCore.GetFrameTime();
    }

    public float getDeltaTime() {
        return rCore.GetFrameTime();
    }

    public float getSceneTimer() {
        return timer;
    }

    abstract public Camera getActiveCamera();
}
