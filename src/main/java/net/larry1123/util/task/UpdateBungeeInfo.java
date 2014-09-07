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
package net.larry1123.util.task;

import net.canarymod.Canary;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.tasks.ServerTask;
import net.canarymod.tasks.ServerTaskManager;
import net.canarymod.tasks.TaskOwner;
import net.larry1123.util.CanaryUtil;
import net.larry1123.util.api.abstracts.RemoteServer;
import net.larry1123.util.api.plugin.hooks.bungeecord.BungeeCordPollHook;
import net.larry1123.util.config.UtilConfigManager;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.LinkedList;

import static net.larry1123.util.CanaryUtil.getPlugin;

public class UpdateBungeeInfo extends ServerTask {

    /**
     * ConfigManager
     */
    private static final UtilConfigManager config = UtilConfigManager.getConfig();
    /**
     * Current Updater
     */
    private static UpdateBungeeInfo tickSystem;

    private UpdateBungeeInfo(TaskOwner owner, long delay) {
        this(owner, delay, true);
    }

    private UpdateBungeeInfo(TaskOwner owner, long delay, boolean continuous) {
        super(owner, delay, continuous);
    }

    /**
     * Starts the updater polling if the config will allow
     */
    public static void startUpdater() {
        if (config.getBungeeCordConfig().isEnabled() && getPlugin() != null) {
            if (tickSystem == null) {
                tickSystem = new UpdateBungeeInfo(getPlugin(), config.getBungeeCordConfig().getPollTime());
                ServerTaskManager.addTask(tickSystem);
            }
        }
    }

    /**
     * Stops the updater polling
     */
    public static void endUpdater() {
        if (tickSystem != null) {
            ServerTaskManager.removeTask(tickSystem);
            tickSystem = null;
        }
    }

    /**
     * Will start the updater if the config allows or stops the updater if running and needed to be
     */
    public static void reloadUpdater() {
        if (config.getBungeeCordConfig().isEnabled()) {
            if (tickSystem != null) {
                ServerTaskManager.removeTask(tickSystem);
            }
            startUpdater();
        }
        else {
            if (tickSystem != null) {
                ServerTaskManager.removeTask(tickSystem);
                tickSystem = null;
            }
        }
    }

    /**
     * Sends Polls for BungeeCord Info over players
     */
    @Override
    public void run() {

        // Update Player IPs
        updateIPs();

        for (Player player : Canary.getServer().getPlayerList()) {

            // Update Server List
            updateServerList(player);

            @SuppressWarnings("unchecked")
            LinkedList<RemoteServer> servers = (LinkedList<RemoteServer>) CanaryUtil.getCustomPacket().getBungeeCord().getServerList().clone();
            servers.add(RemoteServer.getALLServerObject());
            for (RemoteServer server : servers) {
                // Update playerList for each server
                updatePlayerList(player, server);

                // Update Playercount for each server
                updatePlayerCount(player, server);

            }

            // Update Current Server's name
            updateCurrentServer(player);

            // Call BungeeCordPollHook
            new BungeeCordPollHook(player).call();
        }
    }

    private void updateIPs() {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        for (Player player : Canary.getServer().getPlayerList()) {
            b = new ByteArrayOutputStream();
            out = new DataOutputStream(b);
            try {
                out.writeUTF("IP");
            }
            catch (IOException e) {
                // Can't happen man
            }
            Canary.channels().sendCustomPayloadToPlayer("BungeeCord", b.toByteArray(), player);
        }
    }

    private void updateServerList(Player player) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        b = new ByteArrayOutputStream();
        out = new DataOutputStream(b);
        try {
            out.writeUTF("GetServers");
        }
        catch (IOException e) {
            // Can't happen man
        }
        Canary.channels().sendCustomPayloadToPlayer("BungeeCord", b.toByteArray(), player);
    }

    private void updatePlayerList(Player player, RemoteServer server) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        b = new ByteArrayOutputStream();
        out = new DataOutputStream(b);
        try {
            out.writeUTF("PlayerList");
            out.writeUTF(server.getServerName());
        }
        catch (IOException e) {
            // Can't happen man
        }
        Canary.channels().sendCustomPayloadToPlayer("BungeeCord", b.toByteArray(), player);
    }

    private void updatePlayerCount(Player player, RemoteServer server) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        b = new ByteArrayOutputStream();
        out = new DataOutputStream(b);
        try {
            out.writeUTF("PlayerCount");
            out.writeUTF(server.getServerName());
        }
        catch (IOException e) {
            // Can't happen man
        }
        Canary.channels().sendCustomPayloadToPlayer("BungeeCord", b.toByteArray(), player);
    }

    private void updateCurrentServer(Player player) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        b = new ByteArrayOutputStream();
        out = new DataOutputStream(b);
        try {
            out.writeUTF("GetServer");
        }
        catch (IOException e) {
            // Can't happen man
        }
        Canary.channels().sendCustomPayloadToPlayer("BungeeCord", b.toByteArray(), player);
    }

}
