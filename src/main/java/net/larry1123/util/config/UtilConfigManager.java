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

import net.canarymod.api.world.World;
import net.canarymod.config.Configuration;
import net.canarymod.plugin.Plugin;
import net.larry1123.elec.util.config.ConfigBase;
import net.larry1123.elec.util.config.ConfigFile;
import net.larry1123.util.CanaryUtil;
import net.visualillusionsent.utils.PropertiesFile;

public class UtilConfigManager {

    private static final UtilConfigManager config = new UtilConfigManager();

    private BungeeCordConfig bungeecordConfig;
    private LoggerConfig loggerConfig;
    private UtilCommandsConfig utilCommandsConfig;
    protected CanaryUtil plugin;

    private UtilConfigManager() {}

    public void setPlugin(CanaryUtil plugin) {
        if (getPlugin() == null) {
            this.plugin = plugin;
            getLoggerConfig().postInt(getPlugin());
            getBungeeCordConfig().postInt(getPlugin());
            getUtilCommandsConfig().postInt(getPlugin());
        }
    }

    public CanaryUtil getPlugin() {
        return plugin;
    }

    /**
     * Gets the Config Manager
     *
     * @return Config Manager
     */
    public static UtilConfigManager getConfig() {
        return config;
    }

    /**
     * Gets the Config for BungeeCord
     *
     * @return Config Manager for BungeeCord
     */
    public BungeeCordConfig getBungeeCordConfig() {
        if (bungeecordConfig == null) {
            bungeecordConfig = new BungeeCordConfig();
        }
        return bungeecordConfig;
    }

    /**
     * Reloads the Config of the BungeeCord Config Manager
     */
    public void reloadBungeeCordConfig() {
        getBungeeCordConfig().reload();
    }

    /**
     * Gets the Config for the Logger
     *
     * @return Config Manager for Logger
     */
    public LoggerConfig getLoggerConfig() {
        if (loggerConfig == null) {
            loggerConfig = new LoggerConfig();
        }
        return loggerConfig;
    }

    /**
     * Reloads the Config of the Logger Config Manager
     */
    public void reloadLoggerConfig() {
        getLoggerConfig().reload();
    }

    public UtilCommandsConfig getUtilCommandsConfig() {
        if (utilCommandsConfig == null) { utilCommandsConfig = new UtilCommandsConfig(); }
        return utilCommandsConfig;
    }

    /**
     * TODO
     *
     * @param config What {@link ConfigBase} to base the wrapper's logic about
     */
    public ConfigFile getPluginConfig(ConfigBase config) {
        return new ConfigFile(config);
    }

    public PropertiesFile getPluginPropertiesFile(Plugin plugin) {
        return Configuration.getPluginConfig(plugin);
    }

    public PropertiesFile getPluginPropertiesFile(Plugin plugin, String module) {
        return Configuration.getPluginConfig(plugin, module);
    }

    public PropertiesFile getPluginPropertiesFile(Plugin plugin, World world) {
        return Configuration.getPluginConfig(plugin, world);
    }

    public PropertiesFile getPluginPropertiesFile(Plugin plugin, String module, World world) {
        return Configuration.getPluginConfig(plugin, module, world);
    }

}
