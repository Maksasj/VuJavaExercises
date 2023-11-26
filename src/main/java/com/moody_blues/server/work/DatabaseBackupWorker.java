package com.moody_blues.server.work;

import com.moody_blues.server.MoodyBluesServer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class DatabaseBackupWorker implements Runnable {
    @Override
    public void run() {
        while(MoodyBluesServer.isRunnning()) {
            try {
                Thread.sleep(10000);

                var file = new File("database.txt");
                file.createNewFile();

                var out = new ObjectOutputStream(new FileOutputStream(file, false));

                out.writeObject(MoodyBluesServer.database);

                out.close();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }

        try {
            var out = new ObjectOutputStream(new FileOutputStream(new File("database.txt")));

            out.writeObject(MoodyBluesServer.database);

            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
