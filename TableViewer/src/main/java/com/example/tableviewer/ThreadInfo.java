/**
 * @author
 * Maksim Jaroslavcevas 2 grupe radioboos@gmail.com
*/

package com.example.tableviewer;

public class ThreadInfo {
    private Thread thread;
    private FileLoaderWorker worker;

    public ThreadInfo(Thread thread, FileLoaderWorker worker) {
        this.thread = thread;
        this.worker = worker;
    }

    public Thread getThread() {
        return thread;
    }

    public FileLoaderWorker getWorker() {
        return worker;
    }
}
