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
package net.larry1123.util.api.abstracts;

import net.canarymod.api.OfflinePlayer;
import net.canarymod.api.entity.living.humanoid.Player;
import net.larry1123.util.CanaryUtil;
import net.larry1123.util.customPacket.BungeeCord;

import java.util.ArrayList;
import java.util.LinkedList;

public class RemoteServer {

    private static ArrayList<RemoteServer> servers = new ArrayList<RemoteServer>();

    private String serverName;

    private RemoteServer(String name) {
        serverName = name;
    }

    public static RemoteServer getServer(String name) {
        for (RemoteServer server : servers) {
            if (server.serverName.equals(name)) {
                return server;
            }
        }
        RemoteServer ret = new RemoteServer(name);
        servers.add(ret);
        return ret;
    }

    public static RemoteServer getALLServerObject() {
        return getServer("ALL");
    }

    public String getServerName() {
        return serverName;
    }

    /**
     * Tells if this {@link RemoteServer} is the local server
     *
     * @return {@code true} if this is the local server, {@code false} if this is not the local server
     */
    public boolean isCurrentServer() {
        return this.equals(getBungeeCordInterface().getCurrentServer());
    }

    /**
     * Tells if the server is currently online
     *
     * @return {@code true} this {@link RemoteServer} is online, {@code false} this {@link RemoteServer} is offline
     */
    public boolean isServerOnline() {
        return getBungeeCordInterface().getServerList().contains(this);
    }

    /**
     * Gets the last known amount of players on the given Server
     * Will return -1 if the server is Offline
     *
     * @return Player Count for given Server
     */
    public int getPlayerCount() {
        return getBungeeCordInterface().getServerPlayerCount(this);
    }

    /**
     * Gets a OfflinePlayer LinkedList of Players for the given Server or a empty List
     *
     * @return LinkedList of OfflinePlayers for the given Server
     */
    public LinkedList<OfflinePlayer> getServerPlayerList() {
        return getBungeeCordInterface().getServerPlayerList(this);
    }

    /**
     * This will disconnect the Player from this server and send them to a different server if it is online
     * Will return true if the packet was sent false if we know that the server is not online at this time.
     * Will also return false if you are trying to send to the current server;
     * Will return false if no players are online or if no players that are online are connected to the BungeeCord Server
     *
     * @param player Player Object of who to send to a new server
     *
     * @return {@code true} if the packet was sent, {@code false} if the packet was not sent
     */
    public boolean sendPlayerToServer(Player player) {
        return getBungeeCordInterface().sendPlayerToServer(player, this);
    }

    /**
     * Will send a CustomPayload Packet to this server as any connected player
     * Will return true if the packet was sent false if we know that the server is not online at this time.
     * Will also return false if you are trying to send to the current server;
     * Will return false if no players are online or if no players that are online are connected to the BungeeCord Server
     *
     * @param subChannel What channel to send data over
     * @param data       What data to pass
     *
     * @return {@code true} if the packet was sent, {@code false} if the packet was not sent
     */
    public boolean sendMessageToServer(String subChannel, String data) {
        return getBungeeCordInterface().sendMessageToServer(this, subChannel, data);
    }

    /**
     * Will send a CustomPayload Packet to this server as All Connected Players
     * Will return true if any packet was sent, false if we know that the server is not online at this time.
     * Will also return false if you are trying to send to the current server;
     * Will return false if no players are online or if no players that are online are connected to the BungeeCord Server
     *
     * @param subChannel What channel to send data over
     * @param data       What data to pass
     *
     * @return {@code true} if the packet was sent, {@code false} if the packet was not sent
     */
    public boolean sendMessageToServerAsAllPlayers(String subChannel, String data) {
        return getBungeeCordInterface().sendMessageToServerAsAllPlayers(this, subChannel, data);
    }

    /**
     * Will return true if the packet was sent, false if we know that the server is not online at this time.
     * Will also return false if you are trying to send to the current server;
     * Will return false if the player is not connected to the BungeeCord Server
     *
     * @param subChannel What channel to send data over
     * @param data       What data to pass
     * @param player     What player to send this data over with
     *
     * @return {@code true} if the packet was sent, {@code false} if the packet was not sent
     */
    public boolean sendMessageToServerAsPlayer(String subChannel, String data, Player player) {
        return getBungeeCordInterface().sendMessageToServerAsPlayer(this, subChannel, data, player);
    }

    protected BungeeCord getBungeeCordInterface() {
        return CanaryUtil.getCustomPacket().getBungeeCord();
    }

}
