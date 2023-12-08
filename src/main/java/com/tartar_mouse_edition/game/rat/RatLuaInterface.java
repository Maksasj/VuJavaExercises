package com.tartar_mouse_edition.game.rat;

import com.raylib.Jaylib;
import com.tartar_mouse_edition.game.TartarMEGame;
import com.tartar_mouse_edition.game.common.Pair2D;
import com.tartar_mouse_edition.game.common.Rotation;
import com.tartar_mouse_edition.idea.TartarMEIDEA;
import com.tartar_mouse_edition.idea.components.logMessage.LogMessage;

import static com.tartar_mouse_edition.idea.components.logMessage.LogLevel.RAT;

public class RatLuaInterface {
    private static void waitm(long milis) {
        try {
            Thread.sleep(milis);
        } catch (Exception ex) {

        }
    }

    public static void walk() {
        TartarMEIDEA.controller.logMessage(new LogMessage(RAT, "walks forward"));

        var rat = RatController.getActiveRat();
        var level = RatController.getActiveLevel();

        var rotation = rat.getRotation();
        var position = rat.getPosition();

        var maze =level.getMap().getMaze();
        var pos = new Pair2D(Math.round(position.x()), Math.round(position.z()));

        switch (rotation) {
            case X_PLUS -> {
                if(maze.isInBounds(pos.x + 1, pos.y) && (maze.isSolidAt(pos.x + 1, pos.y) == 0)) {
                    rat.movePosition(new Jaylib.Vector3(1.0f, 0.0f, 0.0f));
                }
            }
            case X_MINUS -> {
                if(maze.isInBounds(pos.x - 1, pos.y) && (maze.isSolidAt(pos.x - 1, pos.y) == 0)) {
                    rat.movePosition(new Jaylib.Vector3(-1.0f, 0.0f, 0.0f));
                }
            }
            case Z_PLUS -> {
                if(maze.isInBounds(pos.x, pos.y + 1) && (maze.isSolidAt(pos.x, pos.y + 1) == 0)) {
                    rat.movePosition(new Jaylib.Vector3(0.0f, 0.0f, 1.0f));
                }
            }
            case Z_MINUS -> {
                if(maze.isInBounds(pos.x, pos.y - 1) && (maze.isSolidAt(pos.x, pos.y - 1) == 0)) {
                    rat.movePosition(new Jaylib.Vector3(0.0f, 0.0f, -1.0f));
                }
            }
        }

        waitm(400);

        rat.markMoved(true);
    }

    public static void rotate_left() {
        TartarMEIDEA.controller.logMessage(new LogMessage(RAT, "rotates left"));

        var rat = RatController.getActiveRat();
        var rotation = rat.getRotation();

        switch (rotation) {
            case X_PLUS ->  rat.rotate(Rotation.Z_MINUS);
            case X_MINUS -> rat.rotate(Rotation.Z_PLUS);
            case Z_PLUS ->  rat.rotate(Rotation.X_PLUS);
            case Z_MINUS -> rat.rotate(Rotation.X_MINUS);
        }

        waitm(200);

        rat.markMoved(true);
    }

    public static void rotate_right() {
        TartarMEIDEA.controller.logMessage(new LogMessage(RAT, "rotates right"));

        var rat = RatController.getActiveRat();
        var rotation = rat.getRotation();

        switch (rotation) {
            case X_PLUS ->  rat.rotate(Rotation.Z_PLUS);
            case X_MINUS -> rat.rotate(Rotation.Z_MINUS);
            case Z_PLUS ->  rat.rotate(Rotation.X_MINUS);
            case Z_MINUS -> rat.rotate(Rotation.X_PLUS);
        }

        waitm(200);

        rat.markMoved(true);
    }

    public static boolean look() {
        TartarMEIDEA.controller.logMessage(new LogMessage(RAT, "looks forward"));

        var rat = RatController.getActiveRat();
        var level = RatController.getActiveLevel();

        var rotation = rat.getRotation();
        var position = rat.getPosition();

        var maze =level.getMap().getMaze();
        var pos = new Pair2D(Math.round(position.x()), Math.round(position.z()));

        switch (rotation) {
            case X_PLUS -> {
                if(maze.isInBounds(pos.x + 1, pos.y) && (maze.isSolidAt(pos.x + 1, pos.y) == 0)) {
                    return true;
                }
            }
            case X_MINUS -> {
                if(maze.isInBounds(pos.x - 1, pos.y) && (maze.isSolidAt(pos.x - 1, pos.y) == 0)) {
                    return true;
                }
            }
            case Z_PLUS -> {
                if(maze.isInBounds(pos.x, pos.y + 1) && (maze.isSolidAt(pos.x, pos.y + 1) == 0)) {
                    return true;
                }
            }
            case Z_MINUS -> {
                if(maze.isInBounds(pos.x, pos.y - 1) && (maze.isSolidAt(pos.x, pos.y - 1) == 0)) {
                    return true;
                }
            }
        }

        waitm(200);

        rat.markMoved(true);

        return false;
    }
}
