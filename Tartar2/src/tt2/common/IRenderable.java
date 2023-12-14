/**
 * @author
 * Maksim Jaroslavcevas radioboos@gmail.com
*/

package tt2.common;

import com.raylib.java.core.Color;

public interface IRenderable {
    public void render();
    public void resetRenderingFlags();

    public VisibilityLevel getVisibilityLevel();
    public void setVisibilityLevel(VisibilityLevel visibilityLevel);
    public void doRenderingPreProcessing();

    public void setTintColor(Color color);
    public void setApplyTint(boolean applyTint);
    public Color getTintColor();
}
