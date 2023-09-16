package tt2.items;

import com.raylib.java.core.Color;
import com.raylib.java.textures.Texture2D;
import tt2.common.*;

public abstract class Ability implements IRenderable, ITickable, IIconable, IBlockable {
    private boolean blocked;

    public Ability() {
        blocked = false;
    }

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
}
