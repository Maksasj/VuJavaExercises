/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.tartar_mouse_edition.game.level;

import com.raylib.Jaylib;
import com.raylib.Raylib;
import com.tartar_mouse_edition.game.common.IRendarable2D;
import com.tartar_mouse_edition.game.common.IRendarable3D;

import java.util.concurrent.TimeUnit;

import static com.raylib.Jaylib.RAYWHITE;
import static com.raylib.Raylib.*;
import static com.raylib.Raylib.LoadTexture;

public class Level implements IRendarable3D, IRendarable2D {
    private Map map;
    private Raylib.Model model;
    private Vector3 position;
    private long startTimestamp;
    private long endTimestamp;

    public Level() {
        this.map = new Map(new MazeGenerator());
        this.model = LoadModelFromMesh(map.genMesh());

        var texture = LoadTexture("src/main/resources/cubicmap_atlas.png");
        model.materials()
            .maps()
            .texture(texture);

        this.position = new Jaylib.Vector3( 0.0f, 0.0f,  0.0f);
        this.startTimestamp = System.currentTimeMillis();
    }

    public void levelFinished() {
        endTimestamp = System.currentTimeMillis();
    }

    public String getCompleteTime() {
        final long l = endTimestamp - startTimestamp;

        return formatTime(l);
    }

    public String getEclapsedTimeString() {
        var time = System.currentTimeMillis();

        final long l = time - startTimestamp;

        return formatTime(l);
    }

    public String formatTime(final long l) {
        final long hr = TimeUnit.MILLISECONDS.toHours(l);
        final long min = TimeUnit.MILLISECONDS.toMinutes(l - TimeUnit.HOURS.toMillis(hr));
        final long sec = TimeUnit.MILLISECONDS.toSeconds(l - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min));

        return String.format("%02d:%02d", min, sec);
    }

    public Map getMap() {
        return map;
    }

    @Override
    public void render() {
        map.render();
    }

    @Override
    public void render(Raylib.Camera3D camera) {
        DrawModel(model, position, 1.0f, RAYWHITE);
    }
}
