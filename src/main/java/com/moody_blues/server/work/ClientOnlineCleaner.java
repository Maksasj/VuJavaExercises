package com.moody_blues.server.work;

import com.moody_blues.server.ClientInstance;
import com.moody_blues.server.MoodyBluesServer;

import java.util.ArrayList;

public class ClientOnlineCleaner implements Runnable{
    @Override
    public void run() {
        while(MoodyBluesServer.isRunnning()) {
            var toRemove = new ArrayList<ClientInstance>();

            for(var client : MoodyBluesServer.connectedClients)
                if(!client.online())
                    toRemove.add(client);

            MoodyBluesServer.connectedClients.removeAll(toRemove);
        }
    }
}
