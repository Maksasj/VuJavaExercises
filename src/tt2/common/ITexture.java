/**
 * @author
 * Maksim Jaroslavcevas radioboos@gmail.com
*/

package tt2.common;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;

public interface ITexture {
    public void render(Vector2 position, float scale, Color tintColor);
    public void unload();
    public float getWidth();
    public float getHeight();
}
