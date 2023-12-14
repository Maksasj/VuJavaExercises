/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.tartar_mouse_edition.game.rat;

import com.raylib.Jaylib;
import com.tartar_mouse_edition.game.Cheese;
import com.tartar_mouse_edition.game.TartarMEGame;
import com.tartar_mouse_edition.game.level.Level;
import com.tartar_mouse_edition.game.lua.RatDebugLib;
import com.tartar_mouse_edition.game.lua.RatLuaRunnable;
import com.tartar_mouse_edition.idea.TartarMEIDEA;
import com.tartar_mouse_edition.idea.components.logMessage.LogLevel;
import com.tartar_mouse_edition.idea.components.logMessage.LogMessage;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LoadState;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.compiler.LuaC;
import org.luaj.vm2.lib.*;
import org.luaj.vm2.lib.jse.*;

import static com.tartar_mouse_edition.idea.components.logMessage.LogLevel.*;

public class RatController {
    private static Rat activeRat;
    private static Level activeLevel;
    private static Cheese activeCheese;

    private static Thread simulationThread;
    private static RatLuaRunnable simulationRunnable;


    public synchronized static void setActiveRat(Rat rat) {
        RatController.activeRat = rat;
    }

    public synchronized static void setActiveLevel(Level activeLevel) {
        RatController.activeLevel = activeLevel;
    }

    public static void setActiveCheese(Cheese cheese) {
        RatController.activeCheese = cheese;
    }

    public synchronized static Rat getActiveRat() {
        return activeRat;
    }

    public synchronized static Level getActiveLevel() {
        return activeLevel;
    }

    public synchronized static void simulate(String code) {
        var runnable = new RatLuaRunnable(code);
        var thread = new Thread(runnable);

        if(simulationThread != null) {
            try {
                simulationRunnable.forceStop();
                simulationThread.interrupt();
                TartarMEIDEA.controller.logMessage(new LogMessage(WARNING, "Stopped previous simulation"));
            } catch (Exception ex) {
                ex.printStackTrace();
                TartarMEIDEA.controller.logMessage(new LogMessage(ERROR, "Failed to stop simulation"));
                return;
            }
        }

        simulationThread = thread;
        simulationRunnable = runnable;
        simulationThread.start();
    }

    public synchronized static Cheese getActiveCheese() {
        return activeCheese;
    }

    public synchronized static void forceStop() {
        try {
            simulationRunnable.forceStop();
            simulationThread.interrupt();
            TartarMEIDEA.controller.logMessage(new LogMessage(WARNING, "Stopped simulation"));
        } catch (Exception ex) {
            TartarMEIDEA.controller.logMessage(new LogMessage(ERROR, "Failed to stop simulation"));
        }
    }
}
