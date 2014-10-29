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
import net.larry1123.util.api.task.TaskHandler;
import net.larry1123.util.config.BungeeCordConfig;
import net.larry1123.util.config.UtilConfigManager;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.LinkedList;

public class UpdateBungeeInfo implements TaskHandler {

    public class BungeeCordTask extends ServerTask {

        public BungeeCordTask(TaskOwner owner, long delay) {
            this(owner, delay, true);
        }

        public BungeeCordTask(TaskOwner owner, long delay, boolean continuous) {
            super(owner, delay, continuous);
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
                LinkedList<RemoteServer> servers = CanaryUtil.getCustomPacket().getBungeeCord().getServerList();
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

    }

    /**
     * Current Updater
     */
    protected BungeeCordTask task;
    protected final CanaryUtil plugin;
    protected ByteArrayOutputStream b;
    protected DataOutputStream out;

    public UpdateBungeeInfo(CanaryUtil plugin) {
        this.plugin = plugin;
    }

    /**
     * Starts the updater polling if the config will allow
     */
    public boolean startUpdater() {
        if (getBungeeCordConfig().isEnabled() && getPlugin() != null) {
            if (task == null) {
                task = new BungeeCordTask(getPlugin(), getBungeeCordConfig().getPollTime());
                ServerTaskManager.addTask(task);
            }
            return true;
        }
        return false;
    }

    /**
     * Stops the updater polling
     */
    public void endUpdater() {
        if (task != null) {
            ServerTaskManager.removeTask(task);
            task = null;
        }
    }

    /**
     * Will start the updater if the config allows or stops the updater if running and needed to be
     */
    public boolean reloadUpdater() {
        if (getBungeeCordConfig().isEnabled()) {
            endUpdater();
            return startUpdater();
        }
        else {
            endUpdater();
            return false;
        }
    }

    protected void updateIPs() {
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

    protected void updateServerList(Player player) {
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

    protected void updatePlayerList(Player player, RemoteServer server) {
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

    protected void updatePlayerCount(Player player, RemoteServer server) {
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

    protected void updateCurrentServer(Player player) {
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

    protected BungeeCordConfig getBungeeCordConfig() {
        return UtilConfigManager.getConfig().getBungeeCordConfig();
    }

    protected CanaryUtil getPlugin() {
        return plugin;
    }

}
