package tt2.world.tile;

import com.google.gson.JsonObject;
import com.raylib.java.raymath.Vector3;
import tt2.common.CommonFactory;
import tt2.common.IsometricRotation;

public class TileFactory extends CommonFactory {
    static public Tile createTile(JsonObject obj) {
        Tile tile = null;

        String type = obj.get("type").getAsString();
        Vector3 position = getVector3(obj.get("pos").getAsJsonObject());

        if(type.contentEquals("DefaultTile")) {
            tile = new DefaultTile(position);
        } else if(type.contentEquals("StairsTile")) {
            IsometricRotation tileRotation = IsometricRotation.fromString(obj.get("rot").getAsString());
            tile = new StairsTile(position, tileRotation);
        } else if(type.contentEquals("PortalTile")) {
            tile = new PortalTile(position);
        }

        return tile;
    }
}
