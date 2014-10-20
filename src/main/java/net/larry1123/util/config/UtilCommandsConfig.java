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
import net.visualillusionsent.utils.PropertiesFile;

import java.util.ArrayList;

/**
 * @author Larry1123
 * @since 1/26/14 - 4:58 AM
 */
public class UtilCommandsConfig implements ConfigBase {

    protected ConfigFile configManager;
    protected Plugin plugin;
    @ConfigField(name = "Repair-Aliases", comments = "")
    protected ArrayList<String> repairAliases = new ArrayList<String>();
    @ConfigField(name = "Repair-Enabled", comments = "")
    private boolean repairEnable = false;

    public UtilCommandsConfig() {
        repairAliases.add("repair");
    }

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
        ArrayList<String> temp = repairAliases;
        configManager.reload();
        if (!temp.containsAll(repairAliases) && !repairAliases.containsAll(temp)) {
            // TODO Reload Command
        }
    }

    @Override
    public PropertiesFile getPropertiesFile() {
        return plugin == null ? null : plugin.getModuleConfig("Commands");
    }
}
