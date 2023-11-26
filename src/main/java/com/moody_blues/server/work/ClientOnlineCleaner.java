package com.moody_blues.server.work;

import com.moody_blues.common.Logger;
import com.moody_blues.common.packet.update.OnlineUsersPacket;
import com.moody_blues.server.ClientInstance;
import com.moody_blues.server.MoodyBluesServer;

import java.util.ArrayList;

public class ClientOnlineCleaner implements Runnable{
    @Override
    public void run() {
        while(MoodyBluesServer.isRunnning()) {
            var toRemove = new ArrayList<ClientInstance>();

            try {
                Thread.sleep(1000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            for(var client : MoodyBluesServer.connectedClients) {
                if (client.getSocket().isClosed()) {
                    toRemove.add(client);

                    Logger.log("Disconnected user");
                }
            }

            MoodyBluesServer.connectedClients.removeAll(toRemove);

            if(!toRemove.isEmpty()) {
                var usernames = MoodyBluesServer.getOnlineUsersnames();
                MoodyBluesServer.sendPacketToEachClient(new OnlineUsersPacket(usernames));
            }
        }
    }
}
