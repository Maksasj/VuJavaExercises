/**
 * @author
 * Maksim Jaroslavcevas radioboos@gmail.com
*/

package tt2.textures;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.textures.Texture2D;
import com.raylib.java.textures.rTextures;
import tt2.Tartar2;
import tt2.common.ITexture;

import java.util.ArrayList;
import java.util.List;

public class Texture implements ITexture {
    private Texture2D texture;
    private String path;
    private List<SubTexture> subTextures;

    public Texture(String path, Rectangle[] subRegions) {
        this.path = path;
        texture = rTextures.LoadTexture(path);

        subTextures = new ArrayList<>();

        for(int i = 0; i < subRegions.length; ++i) {
            subTextures.add(new SubTexture(texture, subRegions[i]));
        }
    }

    public SubTexture getSubTexture(int i) {
        return subTextures.get(i);
    }

    public void unload() {
        Tartar2.raylib.textures.UnloadTexture(texture);
    }

    @Override
    public float getWidth() {
        return texture.width;
    }

    @Override
    public float getHeight() {
        return texture.height;
    }

    @Override
    public void render(Vector2 position, float scale, Color tintColor) {
        Tartar2.raylib.textures.DrawTextureEx(
                texture,
                position,
                0,
                scale,
                tintColor
        );
    }
}
