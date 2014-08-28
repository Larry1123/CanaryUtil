package net.larry1123.util.config;

import net.larry1123.elec.util.config.ConfigBase;
import net.larry1123.elec.util.config.ConfigField;
import net.larry1123.elec.util.config.ConfigFile;
import net.visualillusionsent.utils.PropertiesFile;

import java.util.ArrayList;

/**
 * @author Larry1123
 * @since 1/26/14 - 4:58 AM
 */
public class UtilCommandsConfig implements ConfigBase {

    private final ConfigFile configManager;
    private PropertiesFile propertiesFile;

    @ConfigField(name = "Repair-Enabled", comments = "")
    private boolean repairEnable = false;

    @ConfigField(name = "Repair-Aliases", comments = "")
    private ArrayList<String> repairAliases = new ArrayList<String>();

    public UtilCommandsConfig(String plugin) {
        this.propertiesFile = UtilConfigManager.getConfig().getPluginPropertiesFile(plugin, "Commands");
        configManager = UtilConfigManager.getConfig().getPluginConfig(this);
    }

    /**
     * Will update everything with any changes in Config file
     */
    void reload() {
        ArrayList<String> temp = repairAliases;
        configManager.reload();
        if (!temp.containsAll(repairAliases) && !repairAliases.containsAll(temp)) {
            // TODO Reload Command
        }
    }

    @Override
    public PropertiesFile getPropertiesFile() {
        return propertiesFile;
    }
}
