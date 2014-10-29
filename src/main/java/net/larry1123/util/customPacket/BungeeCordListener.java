/*
 * Copyright 2014 ElecEntertainment
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.larry1123.util.customPacket;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.channels.ChannelListener;
import net.canarymod.plugin.PluginListener;
import net.larry1123.util.api.abstracts.RemoteServer;

import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

public final class BungeeCordListener extends ChannelListener implements PluginListener {

    @Override
    public void onChannelInput(String channel, Player player, byte[] byteStream) {
        try {
            DataInput input = new DataInputStream(new ByteArrayInputStream(byteStream));
            String subChannel = input.readUTF();

            if (subChannel.startsWith("PlayerCount")) {
                String server = input.readUTF();
                int playerCount = input.readInt();
                BungeeCordHandler.setPlayerCountForServer(RemoteServer.getServer(server), playerCount, this);
            }
            if (subChannel.startsWith("PlayerList")) {
                String server = input.readUTF();
                LinkedList<String> players = new LinkedList<String>();
                String rawplayers = input.readUTF();
                Collections.addAll(players, rawplayers.split(","));
                BungeeCordHandler.setPlayerList(RemoteServer.getServer(server), players, this);
            }
            if (subChannel.startsWith("GetServer")) {
                if (subChannel.startsWith("GetServers")) {
                    String rawservers = input.readUTF();
                    LinkedList<RemoteServer> servers = new LinkedList<RemoteServer>();
                    for (String server : rawservers.split(",")) {
                        servers.add(RemoteServer.getServer(server));
                    }
                    BungeeCordHandler.setServerList(servers, this);
                }
                else {
                    String server = input.readUTF();
                    BungeeCordHandler.setCurrentServerName(RemoteServer.getServer(server), this);
                }
            }
        }
        catch (IOException error) {
            // No clue what should be done if this happens.
        }
    }

}
