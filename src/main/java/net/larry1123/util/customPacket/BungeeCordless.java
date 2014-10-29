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

import net.canarymod.Canary;
import net.canarymod.api.OfflinePlayer;
import net.canarymod.api.entity.living.humanoid.Player;
import net.larry1123.util.api.abstracts.BungeeCord;
import net.larry1123.util.api.abstracts.RemoteServer;
import net.larry1123.util.config.UtilConfigManager;

import java.util.LinkedList;

public class BungeeCordless implements BungeeCord {

    public BungeeCordless() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Deprecated
    public String getRealPlayerIp(Player player) {
        return player.getIP();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getServerPlayerCount(RemoteServer server) {
        // TODO not sure about this one yet
        return Canary.getServer().getNumPlayersOnline();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LinkedList<OfflinePlayer> getServerPlayerList(RemoteServer server) {
        return new LinkedList<OfflinePlayer>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LinkedList<RemoteServer> getServerList() {
        LinkedList<RemoteServer> remoteServerLinkedList = new LinkedList<RemoteServer>();
        remoteServerLinkedList.add(getCurrentServer());
        return remoteServerLinkedList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RemoteServer getCurrentServer() {
        return RemoteServer.getServer(UtilConfigManager.getConfig().getBungeeCordConfig().getServerName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sendPlayerToServer(Player player, RemoteServer server) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sendMessageToServer(RemoteServer server, String subCnannel, String data) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sendMessageToServerAsAllPlayers(RemoteServer server, String subCnannel, String data) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean sendMessageToServerAsPlayer(RemoteServer server, String subCnannel, String data, Player player) {
        return false;
    }

}
