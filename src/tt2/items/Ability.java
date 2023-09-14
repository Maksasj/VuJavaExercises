package tt2.items;

import com.raylib.java.core.Color;
import tt2.common.IRenderable;
import tt2.common.ITickable;
import tt2.common.VisibilityLevel;

public abstract class Ability implements IRenderable, ITickable {
    @Override
    public void resetRenderingFlags() {

    }

    @Override
    public void setApplyTint(boolean applyTint) {

    }

    @Override
    public VisibilityLevel getVisibilityLevel() {
        return null;
    }

    @Override
    public void setVisibilityLevel(VisibilityLevel visibilityLevel) {

    }

    @Override
    public void setTintColor(Color color) {

    }

    @Override
    public Color getTintColor() {
        return null;
    }

    @Override
    public void doVisibilityPostProcessing() {

    }
}
