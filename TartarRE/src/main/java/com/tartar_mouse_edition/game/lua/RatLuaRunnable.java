/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.tartar_mouse_edition.game.lua;

import com.tartar_mouse_edition.game.rat.RatLuaInterface;
import com.tartar_mouse_edition.idea.TartarMEIDEA;
import com.tartar_mouse_edition.idea.components.logMessage.LogLevel;
import com.tartar_mouse_edition.idea.components.logMessage.LogMessage;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LoadState;
import org.luaj.vm2.compiler.LuaC;
import org.luaj.vm2.lib.*;
import org.luaj.vm2.lib.jse.*;

import static com.tartar_mouse_edition.idea.components.logMessage.LogLevel.ERROR;

public class RatLuaRunnable implements Runnable {
    private Globals gl;
    private String code;

    public RatLuaRunnable(String code) {
        this.code = code;

        this.gl = new Globals();
        this.gl.load(new JseBaseLib());
        this.gl.load(new PackageLib());
        this.gl.load(new Bit32Lib());
        this.gl.load(new TableLib());
        this.gl.load(new StringLib());
        this.gl.load(new CoroutineLib());
        this.gl.load(new JseMathLib());
        this.gl.load(new JseIoLib());
        this.gl.load(new JseOsLib());
        this.gl.load(new RatDebugLib());
        this.gl.load(new LuajavaLib());
        LoadState.install(gl);
        LuaC.install(gl);
    }

    public void forceStop() {
        ((RatDebugLib) this.gl.debuglib).stop();
    }

    @Override
    public void run() {
        try {
            TartarMEIDEA.controller.logMessage(new LogMessage(LogLevel.SYSTEM, "Started simulation"));

            // var gl = JsePlatform.standardGlobals();
            var instance = CoerceJavaToLua.coerce(new RatLuaInterface());
            gl.set("rat", instance);

            var chunk = gl.load(code);
            chunk.call();

            var t = gl.get("path");
            t.call();

            TartarMEIDEA.controller.logMessage(new LogMessage(LogLevel.SYSTEM, "Simulation complete"));
        } catch (RuntimeException ex) {

        } catch (Exception ex) {
            TartarMEIDEA.controller.logMessage(new LogMessage(ERROR, "Failed to load lua code"));
        }
    }
}
