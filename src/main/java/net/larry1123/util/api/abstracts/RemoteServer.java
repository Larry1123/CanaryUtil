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

    public boolean isCurrentServer() {
        return this.equals(getBungeeCordInterface().getCurrentServer());
    }

    public boolean isServerOnline() {
        return getBungeeCordInterface().getServerList().contains(this);
    }

    public int getPlayerCount() {
        return getBungeeCordInterface().getServerPlayerCount(this);
    }

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
     * @return true if the packet was sent, false if the packet was not sent
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
     * @param subChannel What channel to send over
     * @param data       What data to pass
     *
     * @return true if the packet was sent, false if the packet was not sent
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
     * @param subChannel
     * @param data
     *
     * @return
     */
    public boolean sendMessageToServerAsAllPlayers(String subChannel, String data) {
        return getBungeeCordInterface().sendMessageToServerAsAllPlayers(this, subChannel, data);
    }

    /**
     * Will return true if the packet was sent, false if we know that the server is not online at this time.
     * Will also return false if you are trying to send to the current server;
     * Will return false if the player is not connected to the BungeeCord Server
     *
     * @param subChannel
     * @param data
     * @param player
     *
     * @return
     */
    public boolean sendMessageToServerAsPlayer(String subChannel, String data, Player player) {
        return getBungeeCordInterface().sendMessageToServerAsPlayer(this, subChannel, data, player);
    }

    private BungeeCord getBungeeCordInterface() {
        return CanaryUtil.getCustomPacket().getBungeeCord();
    }

}
