package tt2.world;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector3;
import tt2.common.*;
import tt2.entity.*;
import tt2.world.tile.DefaultTile;
import tt2.world.tile.StairsTile;
import tt2.world.tile.Tile;

import java.util.*;

public class PathCalculator {
    private IsometricDirection[][][] pathToPlayerCubic;
    private IsometricDirection[][][] pathToPlayerFlat;

    public PathCalculator() {
        pathToPlayerCubic = new IsometricDirection[16][16][16];
        fillDirectionArray(pathToPlayerCubic, IsometricDirection.NONE);

        pathToPlayerFlat = new IsometricDirection[16][16][16];
        fillDirectionArray(pathToPlayerFlat, IsometricDirection.NONE);
    }

    private void fillDirectionArray(IsometricDirection[][][] array, IsometricDirection value) {
        for (IsometricDirection[][] collum: array)
            for (IsometricDirection[] row: collum)
                Arrays.fill(row, value);
    }

    public void reCalculateCubicPaths(World world, Player player) {
        Vector3 playerPos = player.getIntermediatePosition();

        int posX = Math.round(playerPos.x);
        int posY = Math.round(playerPos.y);
        int posZ = Math.round(playerPos.z);

        // Todo implement
    }

    public void reCalculateFlatPaths(World world, Player player) {
        Vector3 playerPos = player.getIntermediatePosition();

        int playerPosX = Math.round(playerPos.x);
        int playerPosY = Math.round(playerPos.y);
        int playerPosZ = Math.round(playerPos.z);

        fillDirectionArray(pathToPlayerFlat, IsometricDirection.NONE);

        boolean[][] visisted = new boolean[16][16];
        for (boolean[] row: visisted)
            Arrays.fill(row, false);

        Queue<Tile> queue = new LinkedList<>();

        Tile startTile = world.getTileAt(playerPosX, playerPosY, playerPosZ);
        Tile startGroundTile = world.getTileAt(playerPosX, playerPosY - 1, playerPosZ);
        if(startGroundTile != null && startTile == null) {
            queue.add(startGroundTile);
            visisted[playerPosX][playerPosZ] = true;
        }

        while(!queue.isEmpty()) {
            Tile tile = queue.remove();

            Vector3 tilePosition = tile.getPosition();

            int tilePosX = Math.round(tilePosition.x);
            int tilePosY = Math.round(tilePosition.y) + 1;
            int tilePosZ = Math.round(tilePosition.z);

            {   // LeftUp
                Tile dirTile = world.getTileAt(tilePosX - 1, tilePosY, tilePosZ);
                Tile dirTileGround = world.getTileAt(tilePosX - 1, tilePosY - 1, tilePosZ);

                if(!visisted[tilePosX - 1][tilePosZ] && dirTileGround != null && dirTile == null) {
                    queue.add(dirTileGround);
                    pathToPlayerFlat[tilePosX - 1][tilePosY][tilePosZ] = IsometricDirection.RIGHT_DOWN;
                    visisted[tilePosX - 1][tilePosZ] = true;
                }
            }

            {   // RightUp
                Tile dirTile = world.getTileAt(tilePosX, tilePosY, tilePosZ - 1);
                Tile dirTileGround = world.getTileAt(tilePosX, tilePosY - 1, tilePosZ  - 1);

                if(!visisted[tilePosX][tilePosZ  - 1] && dirTileGround != null && dirTile == null) {
                    queue.add(dirTileGround);
                    pathToPlayerFlat[tilePosX][tilePosY][tilePosZ  - 1] = IsometricDirection.LEFT_DOWN;
                    visisted[tilePosX][tilePosZ  - 1] = true;
                }
            }

            {   // RightDown
                Tile dirTile = world.getTileAt(tilePosX + 1, tilePosY, tilePosZ);
                Tile dirTileGround = world.getTileAt(tilePosX + 1, tilePosY - 1, tilePosZ);

                if(!visisted[tilePosX + 1][tilePosZ] && dirTileGround != null && dirTile == null) {
                    queue.add(dirTileGround);
                    pathToPlayerFlat[tilePosX + 1][tilePosY][tilePosZ] = IsometricDirection.LEFT_UP;
                    visisted[tilePosX + 1][tilePosZ] = true;
                }
            }

            {   // LeftDown
                Tile dirTile = world.getTileAt(tilePosX, tilePosY, tilePosZ + 1);
                Tile dirTileGround = world.getTileAt(tilePosX, tilePosY - 1, tilePosZ + 1);

                if(!visisted[tilePosX][tilePosZ + 1] && dirTileGround != null && dirTile == null) {
                    queue.add(dirTileGround);
                    pathToPlayerFlat[tilePosX][tilePosY][tilePosZ + 1] = IsometricDirection.RIGHT_UP;
                    visisted[tilePosX][tilePosZ + 1] = true;
                }
            }
        }
    }

    public IsometricDirection getFlatDirectionAt(int x, int y, int z) {
        if(x < 0 || x > 15)
            return IsometricDirection.NONE;

        if(y < 0 || y > 15)
            return IsometricDirection.NONE;

        if(z < 0 || z > 15)
            return IsometricDirection.NONE;

        return pathToPlayerFlat[x][y][z];
    }

    public IsometricDirection getCubicDirectionAt(int x, int y, int z) {
        if(x < 0 || x > 15)
            return IsometricDirection.NONE;

        if(y < 0 || y > 15)
            return IsometricDirection.NONE;

        if(z < 0 || z > 15)
            return IsometricDirection.NONE;

        return pathToPlayerCubic[x][y][z];
    }
}
