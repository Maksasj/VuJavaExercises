package tt2.world;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.raylib.java.raymath.Vector3;
import tt2.common.CommonFactory;
import tt2.common.IAssetManager;
import tt2.entity.Entity;
import tt2.entity.EntityFactory;
import tt2.entity.Player;
import tt2.world.tile.Tile;
import tt2.world.tile.TileFactory;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class LevelAssetManager extends CommonFactory implements IAssetManager {
    private Gson gson;

    public JsonObject LEVELS[];

    public LevelAssetManager() {
        gson = new Gson();

        LEVELS = new JsonObject[4];
        Arrays.fill(LEVELS, null);
    }

    @Override
    public void load() {
        try {
            LEVELS[0] = gson.fromJson(new FileReader("assets/levels/level0.json"), JsonObject.class);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public World loadLevel(Player player, JsonObject levelJson) {
        World world = new World(player);

        String name = levelJson.get("name").getAsString();

        JsonArray tiles = levelJson.get("tiles").getAsJsonArray();
        JsonArray entities = levelJson.get("entities").getAsJsonArray();

        for(JsonElement tileElement : tiles) {
            JsonObject tmpJson = tileElement.getAsJsonObject();

            String type = tmpJson.get("type").getAsString();
            if(type.contentEquals("tile")) {
                Tile tile = TileFactory.createTile(tmpJson, new Vector3(0.0f, 0.0f, 0.0f));

                if(tile != null)
                    world.addTile(tile);
            } else if(type.contentEquals("fill")) {
                Vector3 startPos = getVector3(tmpJson.get("startPos").getAsJsonObject());
                Vector3 endPos = getVector3(tmpJson.get("endPos").getAsJsonObject());

                int xStart = Math.round(startPos.x);
                int yStart = Math.round(startPos.y);
                int zStart = Math.round(startPos.z);

                int xEnd = Math.round(endPos.x);
                int yEnd = Math.round(endPos.y);
                int zEnd = Math.round(endPos.z);

                for(int x = xStart; x < xEnd; ++x) {
                    for(int y = yStart; y < yEnd; ++y) {
                        for(int z = zStart; z < zEnd; ++z) {
                            Tile tile = TileFactory.createTile(tmpJson, new Vector3(x, y, z));
                            world.addTileAt(tile, x, y, z);
                        }
                    }
                }
            }
        }

        for(JsonElement entityElement : entities) {
            JsonObject entityJson = entityElement.getAsJsonObject();

            Entity entity = EntityFactory.createEntity(entityJson);

            if(entity != null)
                world.addEntity(entity);
        }

        return world;
    }

    @Override
    public void unload() {

    }
}
