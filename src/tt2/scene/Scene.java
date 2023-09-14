package tt2.scene;

import tt2.common.*;
import tt2.common.camera.Camera;

public abstract class Scene extends CommonRenderingMaster implements ITickable, IStepable {
    abstract public Camera getActiveCamera();
}
