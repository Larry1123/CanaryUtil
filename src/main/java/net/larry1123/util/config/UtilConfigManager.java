package net.larry1123.util.config;

import net.canarymod.api.world.World;
import net.canarymod.config.Configuration;
import net.canarymod.plugin.Plugin;
import net.larry1123.elec.util.config.ConfigBase;
import net.larry1123.elec.util.config.ConfigFile;
import net.visualillusionsent.utils.PropertiesFile;

import java.io.File;
import java.util.HashMap;

public class UtilConfigManager {

    private static final UtilConfigManager config = new UtilConfigManager();

    private static HashMap<ConfigBase, HashMap<String, PropertiesFile>> plugin_cfg_cache = new HashMap<ConfigBase, HashMap<String, PropertiesFile>>();

    private final String pluginName = "CanaryUtil";
    private BungeeCordConfig bungeecordConfig;
    private LoggerConfig loggerConfig;
    private UtilCommandsConfig utilCommandsConfig;

    private UtilConfigManager() {
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
        if (bungeecordConfig == null) { bungeecordConfig = new BungeeCordConfig(pluginName); }
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
        if (loggerConfig == null) { loggerConfig = new LoggerConfig(pluginName); }
        return loggerConfig;
    }

    /**
     * Reloads the Config of the Logger Config Manager
     */
    public void reloadLoggerConfig() {
        getLoggerConfig().reload();
    }

    public UtilCommandsConfig getUtilCommandsConfig() {
        if (utilCommandsConfig == null) { utilCommandsConfig = new UtilCommandsConfig(pluginName); }
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

    @Deprecated
    public PropertiesFile getPluginPropertiesFile(String plugin) {
        return new PropertiesFile("config" + File.separatorChar + plugin + File.separatorChar + plugin + ".cfg");
    }

    public PropertiesFile getPluginPropertiesFile(Plugin plugin, String module) {
        return Configuration.getPluginConfig(plugin, module);
    }

    @Deprecated
    public PropertiesFile getPluginPropertiesFile(String plugin, String module) {
        return new PropertiesFile("config" + File.separatorChar + plugin + File.separatorChar + plugin + "." + module + ".cfg");
    }

    public PropertiesFile getPluginPropertiesFile(Plugin plugin, World world) {
        return Configuration.getPluginConfig(plugin, world);
    }

    @Deprecated
    public PropertiesFile getPluginPropertiesFile(String plugin, World world) {
        return new PropertiesFile("config" + File.separatorChar + plugin + File.separatorChar + "worlds" + File.separatorChar + world.getFqName() + File.separatorChar + plugin + ".cfg");
    }

    public PropertiesFile getPluginPropertiesFile(Plugin plugin, String module, World world) {
        return Configuration.getPluginConfig(plugin, module, world);
    }

    @Deprecated
    public PropertiesFile getPluginPropertiesFile(String plugin, String module, World world) {
        return new PropertiesFile("config" + File.separatorChar + plugin + File.separatorChar + "worlds" + File.separatorChar + world.getFqName() + File.separatorChar + plugin + "." + module + ".cfg");
    }

}
