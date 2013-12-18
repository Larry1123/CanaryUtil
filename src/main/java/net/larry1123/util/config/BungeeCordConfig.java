package net.larry1123.util.config;

import net.larry1123.util.CanaryUtil;

public class BungeeCordConfig implements ConfigBase {

    private final boolean enableDefult = false;
    private final long pollTimeDefult = 1000;
    private final String serverNameDefult = "Server";

    private final ConfigFile configManager;

    @ConfigField(comments = {"This sets if the Util will try to talk to a BungeeCord server or not", "Test"})
    private boolean BungeeCord_enabled = enableDefult;

    @ConfigField(comments = "This sets how many ticks between when the Util will send packets to BungeeCord")
    private long BungeeCord_pollTime = pollTimeDefult;

    @ConfigField(comments = "This is only used if BungeeCord is disabled, and as a default if no players have yet connected")
    private String BungeeCord_ServerName = serverNameDefult;

    BungeeCordConfig(String plugin) {
        configManager = UtilConfigManager.getConfig().getPluginConfig(this, plugin, "BungeeCord");
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
        return BungeeCord_enabled;
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
     * Gets what the Name of this Server is.
     *
     * @return Gets the Server's Name
     */
    public String getServerName() {
        return BungeeCord_ServerName;
    }

    /**
     * Will Enable or disable BungeeCord Functions
     *
     * @param state true to start, false to stop
     */
    public void setIsEnabled(boolean state) {
        BungeeCord_enabled = state;
        configManager.save(); // Time to Save
        CanaryUtil.getCustomPacket().reloadBungeeCord();
    }

    /**
     * Sets the wait between Polling a BungeeCord Server
     *
     * @param time the number of ms to wait
     */
    public void setPollTime(long time) {
        BungeeCord_pollTime = time;
        configManager.save(); // Time to Save
        CanaryUtil.getCustomPacket().reloadBungeeCord();
    }

    /**
     * Sets this Server's name
     * Mainly for use with out BungeeCord Running
     *
     * @param name Name of this server
     */
    public void setServerName(String name) {
        BungeeCord_ServerName = name;
        configManager.save(); // Time to Save
        CanaryUtil.getCustomPacket().reloadBungeeCord();
    }

}
