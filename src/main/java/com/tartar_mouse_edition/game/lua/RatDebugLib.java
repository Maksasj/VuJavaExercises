package com.tartar_mouse_edition.game.lua;

import com.tartar_mouse_edition.game.rat.RatController;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.DebugLib;

public class RatDebugLib extends DebugLib {
    private boolean stopFlag;

    public RatDebugLib() {
        this.stopFlag = false;
    }

    @Override
    public void onInstruction(int var1, Varargs var2, int var3) {
        if(stopFlag) {
            throw new RuntimeException("Thread stopped");
        }

        super.onInstruction(var1, var2, var3);
    }

    public void stop() {
        this.stopFlag = true;
    }
}
