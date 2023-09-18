package tt2.textures;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.textures.Texture2D;
import com.raylib.java.textures.rTextures;
import tt2.common.ITexture;

public class SubTexture implements ITexture {
    private Texture2D texture;
    private Rectangle subRegion;

    public SubTexture(Texture2D texture, Rectangle subRegion) {
        this.texture = texture;
        this.subRegion = subRegion;
    }

    public void render(Vector2 position, float scale, Color tintColor) {
        rTextures.DrawTexturePro(
                texture,
                subRegion,
                new com.raylib.java.shapes.Rectangle(
                        position.x, position.y,
                        getWidth() * scale,
                        getHeight() * scale),
                new Vector2(0,0),
                0,
                tintColor
        );
    }

    @Override
    public void unload() {

    }

    @Override
    public float getWidth() {
        return subRegion.width;
    }

    @Override
    public float getHeight() {
        return subRegion.height;
    }
}
