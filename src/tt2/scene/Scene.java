package tt2.scene;

import tt2.common.Camera;
import tt2.common.IRenderable;
import tt2.common.IStepable;
import tt2.common.ITickable;

public abstract class Scene implements IRenderable, ITickable, IStepable {
    abstract public Camera getActiveCamera();
}
