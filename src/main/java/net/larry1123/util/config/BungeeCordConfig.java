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
package net.larry1123.util.config;

import net.canarymod.plugin.Plugin;
import net.larry1123.elec.util.config.ConfigBase;
import net.larry1123.elec.util.config.ConfigField;
import net.larry1123.elec.util.config.ConfigFile;
import net.larry1123.util.CanaryUtil;
import net.visualillusionsent.utils.PropertiesFile;

public class BungeeCordConfig implements ConfigBase {

    protected ConfigFile configManager;
    protected Plugin plugin;

    @ConfigField(name = "BungeeCord-enabled", comments = "This sets if the Util will try to talk to a BungeeCord server or not")
    protected boolean BungeeCord_enabled = false;

    @ConfigField(name = "BungeeCord-pollTime", comments = "This sets how many ticks between when the Util will send packets to BungeeCord")
    protected long BungeeCord_pollTime = 1000;

    @ConfigField(name = "BungeeCord-ServerName", comments = "This is only used if BungeeCord is disabled, and as a default if no players have yet connected")
    protected String BungeeCord_ServerName = "Server";

    BungeeCordConfig() {}

    public void postInt(Plugin plugin) {
        if (configManager == null) {
            this.plugin = plugin;
            configManager = UtilConfigManager.getConfig().getPluginConfig(this);
        }
    }

    /**
     * Will update everything with any changes in Config file
     */
    void reload() {
        configManager.reload();
        CanaryUtil.getCustomPacket().reloadBungeeCord();
    }

    /**
     * Gets the state of BungeeCord use.
     *
     * @return If BungeeCord functions are enabled
     */
    public boolean isEnabled() {
        return configManager != null && BungeeCord_enabled;
    }

    /**
     * Gets how long to wait between Polling a BungeeCord Server
     *
     * @return wait time for polling
     */
    public long getPollTime() {
        return BungeeCord_pollTime;
    }

    /**
     * Sets the wait between Polling a BungeeCord Server
     *
     * @param time the number of ms to wait
     */
    public void setPollTime(long time) {
        BungeeCord_pollTime = time;
        if (configManager != null) {
            configManager.save(); // Time to Save
        }
        CanaryUtil.getCustomPacket().reloadBungeeCord();
    }

    /**
     * Gets what the Name of this Server is.
     *
     * @return Gets the Server's Name
     */
    public String getServerName() {
        return BungeeCord_ServerName;
    }

    /**
     * Sets this Server's name
     * Mainly for use with out BungeeCord Running
     *
     * @param name Name of this server
     */
    public void setServerName(String name) {
        BungeeCord_ServerName = name;
        if (configManager != null) {
            configManager.save(); // Time to Save
        }
        CanaryUtil.getCustomPacket().reloadBungeeCord();
    }

    /**
     * Will Enable or disable BungeeCord Functions
     *
     * @param state true to start, false to stop
     */
    public void setIsEnabled(boolean state) {
        BungeeCord_enabled = state;
        if (configManager != null) {
            configManager.save(); // Time to Save
        }
        CanaryUtil.getCustomPacket().reloadBungeeCord();
    }

    @Override
    public PropertiesFile getPropertiesFile() {
        return plugin == null ? null : plugin.getModuleConfig("BungeeCord");
    }
}
