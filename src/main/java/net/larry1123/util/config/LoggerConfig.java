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

import net.canarymod.config.Configuration;
import net.canarymod.plugin.Plugin;
import net.larry1123.elec.util.config.ConfigBase;
import net.larry1123.elec.util.config.ConfigField;
import net.larry1123.elec.util.config.ConfigFile;
import net.larry1123.elec.util.logger.FileSplits;
import net.larry1123.elec.util.logger.LoggerSettings;
import net.larry1123.util.task.FileSpliterUpdater;
import net.visualillusionsent.utils.PropertiesFile;

import java.io.File;

public class LoggerConfig implements ConfigBase, LoggerSettings {

    protected ConfigFile configManager;
    protected Plugin plugin;

    @ConfigField(name = "Logger-Split", comments = {"If left blank it will default no spliting", "None|Hour|Day|Week|Month"})
    protected String logSplit = "None";

    @ConfigField(name = "Logger-CurrentSplit", comments = "Do not change this, used to keep track of splits over reloads and restarts")
    protected String currentSplit = "";

    @ConfigField(name = "Paste-Enabled", comments = "Allows plugins to post errors to https://paste.larry1123.net/")
    protected boolean pasteSend = true;

    public LoggerConfig() {}

    public void postInt(Plugin plugin) {
        if (configManager == null) {
            this.plugin = plugin;
            configManager = UtilConfigManager.getConfig().getPluginConfig(this);
            configManager.save();
        }
    }

    /**
     * Will update everything with any changes in Config file
     */
    void reload() {
        if (configManager != null) {
            configManager.reload();
            FileSpliterUpdater.reloadUpdater();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLoggerPath() {
        return "logs" + File.separatorChar;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLoggerPath(String path) {}

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPastingAllowed() {
        return configManager != null && pasteSend;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPastingAllowed(boolean state) {}

    /**
     * {@inheritDoc}
     */
    @Override
    public FileSplits getSplit() {
        return configManager == null ? FileSplits.NONE : FileSplits.getFromString(logSplit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFileSplit(FileSplits fileSplit) {
        // TODO
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCurrentSplit() {
        return configManager == null ? "" : currentSplit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCurrentSplit(String current) {
        boolean change = !currentSplit.equals(current);
        currentSplit = current;
        if (configManager != null) {
            configManager.save(); // Time to Save
        }
        if (change) {
            FileSpliterUpdater.reloadUpdater();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFileType() {
        return "log";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFileType(String type) {}

    @Override
    public PropertiesFile getPropertiesFile() {
        return plugin == null ? null : Configuration.getPluginConfig(plugin, "Logger");
    }

}
