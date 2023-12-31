/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.moody_blues.client.controllers;

public abstract class CommonController {
    private boolean running;

    public CommonController() {
        running = true;
    }

    public boolean isRunning() {
        return running;
    }

    abstract public void onAnyUpdate();
}
