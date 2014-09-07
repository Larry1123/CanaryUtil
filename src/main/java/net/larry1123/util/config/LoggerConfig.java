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

import net.larry1123.elec.util.config.ConfigBase;
import net.larry1123.elec.util.config.ConfigField;
import net.larry1123.elec.util.config.ConfigFile;
import net.larry1123.elec.util.logger.FileSplits;
import net.larry1123.elec.util.logger.LoggerSettings;
import net.larry1123.util.task.FileSpliterUpdater;
import net.visualillusionsent.utils.PropertiesFile;

import java.util.logging.Logger;

public class LoggerConfig implements ConfigBase, LoggerSettings {

    private final ConfigFile configManager;
    private PropertiesFile propertiesFile;

    @ConfigField(name = "Logger-Path", comments = "This defines where the log files will be placed.")
    private String logger_Path = "pluginlogs/";

    @ConfigField(name = "Logger-Split", comments = {"If left blank it will default no spliting", "None|Hour|Day|Week|Month"})
    private String logSplit = "None";

    @ConfigField(name = "Logger-FileType", comments = "The FileType with out the leading '.'")
    private String logFileType = "log";

    @ConfigField(name = "Logger-CurrentSplit", comments = "Do not change this, used to keep track of splits over reloads and restarts")
    private String currentSplit = "";

    @ConfigField(name = "Paste-Enabled", comments = "Allows plugins to post errors to https://paste.larry1123.net/")
    private boolean pasteSend = true;

    public LoggerConfig(String plugin) {
        this.propertiesFile = UtilConfigManager.getConfig().getPluginPropertiesFile(plugin, "Logger");
        configManager = UtilConfigManager.getConfig().getPluginConfig(this);
    }

    /**
     * Will update everything with any changes in Config file
     */
    void reload() {
        configManager.reload();
        FileSpliterUpdater.reloadUpdater();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLoggerPath() {
        return logger_Path;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLoggerPath(String path) {
        logger_Path = path;
        configManager.save(); // Time to Save
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPastingAllowed() {
        return pasteSend;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPastingAllowed(boolean state) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FileSplits getSplit() {
        return FileSplits.getFromString(logSplit);
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
        return currentSplit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCurrentSplit(String current) {
        boolean change = !currentSplit.equals(current);
        currentSplit = current;
        configManager.save(); // Time to Save
        if (change) {
            FileSpliterUpdater.reloadUpdater();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFileType() {
        if (logFileType.startsWith(".")) {
            logFileType = logFileType.substring(2);
        }
        if (!logFileType.equals("")) {
            return this.logFileType;
        }
        else {
            return "log";
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFileType(String type) {
        // TODO add command to change setting
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Logger getParentLogger() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setParentLogger(Logger logger) {}

    @Override
    public PropertiesFile getPropertiesFile() {
        return propertiesFile;
    }
}
