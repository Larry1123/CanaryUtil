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

import com.google.common.collect.Lists;
import net.canarymod.Canary;
import net.canarymod.api.OfflinePlayer;
import net.canarymod.api.entity.living.humanoid.Player;
import net.larry1123.util.CanaryUtil;
import net.larry1123.util.api.abstracts.BungeeCord;
import net.larry1123.util.api.abstracts.RemoteServer;
import net.larry1123.util.config.UtilConfigManager;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class BungeeCordHandler implements BungeeCord {

    private final BungeeCordListener lis;
    private final RemoteServer all = RemoteServer.getALLServerObject();

    private final HashMap<RemoteServer, Integer> serverPlayerCount = new HashMap<RemoteServer, Integer>();
    private final HashMap<RemoteServer, LinkedList<OfflinePlayer>> playerList = new HashMap<RemoteServer, LinkedList<OfflinePlayer>>();
    private LinkedList<RemoteServer> serverList = new LinkedList<RemoteServer>();
    private RemoteServer currentServer = RemoteServer.getServer(UtilConfigManager.getConfig().getBungeeCordConfig().getServerName());

    protected final CanaryUtil plugin;

    public BungeeCordHandler(CanaryUtil plugin) {
        this.plugin = plugin;
        lis = new BungeeCordListener(this);
        Canary.channels().registerListener(getPlugin(), "BungeeCord", lis);
    }

    /**
     * For use from the Listener only
     *
     * @param server Server to update
     * @param players Number of Players
     * @param liss The Object of the Listener
     */
    void setPlayerCountForServer(RemoteServer server, int players, BungeeCordListener liss) {
        if (lis == liss) {
            if (currentServer == server) {
                return;
            }
            serverPlayerCount.put(server, players);
        }
    }

    /**
     * For use from the Listener only
     * This Method handles the server name ALL, when ALL is handled it will remove from all other Server list missing players
     *
     * @param server Server to update
     * @param players List of Players on Given Server
     * @param liss The Object of the Listener
     */
    void setPlayerList(RemoteServer server, LinkedList<String> players, BungeeCordListener liss) {
        if (lis == liss) {
            LinkedList<OfflinePlayer> serverplayers = new LinkedList<OfflinePlayer>();
            for (String playerr : players) {
                serverplayers.add(Canary.getServer().getOfflinePlayer(playerr));
            }
            playerList.put(server, serverplayers);

            if (server == all) {
                // Removes players from the list for an other server if they are not in the list for all
                for (RemoteServer key : playerList.keySet()) {
                    if (key != all) {
                        for (OfflinePlayer player : playerList.get(key)) {
                            if (!playerList.get(all).contains(player)) {
                                playerList.get(key).remove(player);
                            }
                        }
                    }
                }

            }
            else {

                // Adds players that are known to be on other servers to the all list!
                for (OfflinePlayer player : playerList.get(server)) {
                    if (playerList.get(all) != null) {
                        if (!playerList.get(all).contains(player)) {
                            playerList.get(all).add(player);
                        }
                    }
                }
            }
        }
    }

    /**
     * For use from the Listener only
     *
     * @param servers String LinkedList of Server Names
     * @param liss The Object of the Listener
     */
    void setServerList(LinkedList<RemoteServer> servers, BungeeCordListener liss) {
        if (lis == liss) {
            serverList = servers;
            for (RemoteServer server : serverList) {
                if (!serverPlayerCount.containsKey(server)) {
                    serverPlayerCount.remove(server);
                }
            }
        }
    }

    /**
     * For use from the Listener only
     *
     * @param server Server to update
     * @param liss The Object of the Listener
     */
    void setCurrentServerName(RemoteServer server, BungeeCordListener liss) {
        if (lis == liss) {
            // Only update if changed!
            if (!currentServer.equals(server)) {
                UtilConfigManager.getConfig().getBungeeCordConfig().setServerName((currentServer = server).getServerName());
            }
        }
    }

    public void unregChannelListener() {
        Canary.channels().unregisterListeners(getPlugin());
    }

    /**
     * Gets the Real IP of a Player
     * This will work with both BungeeCord Enabled or disabled
     * This will also work if the Player is not connected to the BungeeCord Server
     *
     * @param player Player Class
     *
     * @return The IP of the Player
     */
    @Override
    @Deprecated
    public String getRealPlayerIp(Player player) {
        return player.getIP();
    }

    /**
     * Gets the last known amount of players on the given Server
     * Will return -1 if the server is Offline
     *
     * @param server What Server to check
     *
     * @return Player Count for given Server
     */
    @Override
    public int getServerPlayerCount(RemoteServer server) {
        if (currentServer != server) {
            Integer count = serverPlayerCount.get(server);
            if (count != null) {
                return count;
            }
            else {
                return -1;
            }
        }
        else {
            return Canary.getServer().getNumPlayersOnline();
        }
    }

    /**
     * Gets a OfflinePlayer LinkedList of Players for the given Server or a empty List
     *
     * @param server What Server to check
     *
     * @return LinkedList of OfflinePlayers for the given Server
     */
    @Override
    public LinkedList<OfflinePlayer> getServerPlayerList(RemoteServer server) {
        LinkedList<OfflinePlayer> ren = new LinkedList<OfflinePlayer>();
        if (playerList.get(server) != null) {
            ren = playerList.get(server);
        }
        return ren;
    }

    /**
     * Gets a LinkedList<String> of the last known list of currently Online Servers
     *
     * @return List of Online Servers
     */
    @Override
    public LinkedList<RemoteServer> getServerList() {
        return Lists.newLinkedList(serverList);
    }

    /**
     * Gets the {@link net.larry1123.util.api.abstracts.RemoteServer} for this server
     *
     * @return The {@link net.larry1123.util.api.abstracts.RemoteServer} for this Server
     */
    @Override
    public RemoteServer getCurrentServer() {
        return currentServer;
    }

    /**
     * This will disconnect the Player from this server and send them to a different server if it is online
     * Will return true if the packet was sent false if we know that the server is not online at this time.
     * Will also return false if you are trying to send to the current server;
     * Will return false if no players are online or if no players that are online are connected to the BungeeCord Server
     *
     * @param player Player Object of who to send to a new server
     * @param server What server to send to
     *
     * @return true if the packet was sent, false if the packet was not sent
     */
    @Override
    public boolean sendPlayerToServer(Player player, RemoteServer server) {
        if (!server.isServerOnline() || server.isCurrentServer() || server == all) {
            return false;
        }
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            // May add check of if this server is known to be online before trying to send packet
            out.writeUTF(server.getServerName());
        }
        catch (IOException e) {
            return false;
            // Can't happen man
            // But lets return just in case it does
        }
        return Canary.channels().sendCustomPayloadToPlayer("BungeeCord", b.toByteArray(), player);
    }

    /**
     * Will send a CustomPayload Packet to the given server as any connected player
     * Will return true if the packet was sent false if we know that the server is not online at this time.
     * Will also return false if you are trying to send to the current server;
     * Will return false if no players are online or if no players that are online are connected to the BungeeCord Server
     *
     * @param server What server to send to.
     * @param subCnannel What channel to send over
     * @param data What data to pass
     *
     * @return true if the packet was sent, false if the packet was not sent
     */
    @Override
    public boolean sendMessageToServer(RemoteServer server, String subCnannel, String data) {
        if (!server.isServerOnline() || server.isCurrentServer()) {
            return false;
        }
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Forward");
            out.writeUTF(server.getServerName());
            out.writeShort(data.length());
            out.writeUTF(data);
        }
        catch (IOException e) {
            return false;
            // Can't happen man
            // But lets return just in case it does
        }
        boolean once = false;
        for (Player player : Canary.getServer().getPlayerList()) {
            if (!once) {
                once = Canary.channels().sendCustomPayloadToPlayer("BungeeCord", b.toByteArray(), player);
            }
        }
        return once;
    }

    /**
     * Will send a CustomPayload Packet to the given server as All Connected Players
     * Will return true if any packet was sent, false if we know that the server is not online at this time.
     * Will also return false if you are trying to send to the current server;
     * Will return false if no players are online or if no players that are online are connected to the BungeeCord Server
     *
     * @param server
     * @param subCnannel
     * @param data
     */
    @Override
    public boolean sendMessageToServerAsAllPlayers(RemoteServer server, String subCnannel, String data) {
        if (!server.isServerOnline() || server.isCurrentServer()) {
            return false;
        }
        if (Canary.getServer().getNumPlayersOnline() == 0) {
            return false;
        }
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Forward");
            // May add check of if this server is known to be there before trying to send packet
            out.writeUTF(server.getServerName());
            out.writeShort(data.length());
            out.writeUTF(data);
        }
        catch (IOException e) {
            return false;
            // Can't happen man
            // But lets return just in case it does
        }
        boolean ret = false;
        for (Player player : Canary.getServer().getPlayerList()) {
            if (Canary.channels().sendCustomPayloadToPlayer("BungeeCord", b.toByteArray(), player)) {
                ret = true;
            }
        }
        return ret;
    }

    /**
     * Will return true if the packet was sent, false if we know that the server is not online at this time.
     * Will also return false if you are trying to send to the current server;
     * Will return false if the player is not connected to the BungeeCord Server
     *
     * @param server
     * @param subCnannel
     * @param data
     * @param player
     */
    @Override
    public boolean sendMessageToServerAsPlayer(RemoteServer server, String subCnannel, String data, Player player) {
        if (!server.isServerOnline() || server.isCurrentServer()) {
            return false;
        }
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Forward");
            // May add check of if this server is known to be there before trying to send packet
            out.writeUTF(server.getServerName());
            out.writeShort(data.length());
            out.writeUTF(data);
        }
        catch (IOException e) {
            return false;
            // Can't happen man
            // But lets return just in case it does
        }
        return Canary.channels().sendCustomPayloadToPlayer("BungeeCord", b.toByteArray(), player);
    }

    protected CanaryUtil getPlugin() {
        return plugin;
    }

}
