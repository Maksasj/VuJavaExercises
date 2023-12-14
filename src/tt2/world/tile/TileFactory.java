/**
 * @author
 * Maksim Jaroslavcevas radioboos@gmail.com
*/

package tt2.world.tile;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.raylib.java.raymath.Vector3;
import tt2.common.CommonFactory;
import tt2.common.IsometricRotation;

public class TileFactory extends CommonFactory {
    static public Tile createTile(JsonObject obj, Vector3 defaultPosition) {
        Tile tile = null;

        String type = obj.get("tile").getAsString();

        JsonElement posElement = obj.get("pos");
        Vector3 position = defaultPosition;

        if(posElement != null)
            position = getVector3(posElement.getAsJsonObject());

        if(type.contentEquals("DefaultTile")) {
            tile = new DefaultTile(position);
        } else if(type.contentEquals("StairsTile")) {
            IsometricRotation tileRotation = IsometricRotation.fromString(obj.get("rot").getAsString());
            tile = new StairsTile(position, tileRotation);
        } else if(type.contentEquals("PortalTile")) {
            tile = new PortalTile(position);
        } else if(type.contentEquals("null")) {
            tile = null;
        }

        return tile;
    }
}
