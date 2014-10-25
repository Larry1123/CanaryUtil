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
package net.larry1123.util.api.plugin;

import com.google.common.io.Files;
import net.canarymod.commandsys.CommandDependencyException;
import net.canarymod.logger.Logman;
import net.canarymod.plugin.Plugin;
import net.larry1123.elec.util.factorys.EELoggerFactory;
import net.larry1123.elec.util.factorys.FactoryManager;
import net.larry1123.elec.util.logger.EELogger;
import net.larry1123.util.CanaryUtil;
import net.larry1123.util.api.plugin.commands.Command;

import java.io.File;
import java.io.IOException;

public abstract class UtilPlugin extends Plugin {

    private final File pluginDataFolder = new File("pluginData" + File.separatorChar + getName());

    {
        try {
            Files.createParentDirs(getPluginDataFolder());
        }
        catch (IOException e) {
            getLogger().error("Unable to create plugin data folder!", e);
        }
    }

    /**
     * @param name Name to name the subLogger
     *
     * @return The SubLogger
     *
     * @deprecated {@link UtilPlugin#getLogger(String)}
     * <p/>
     * Retrieves a SubLogger
     */
    @Deprecated
    public EELogger getSubLogger(String name) {
        return getLogger(name);
    }

    /**
     * Logs that this Plugin failed to start
     */
    public void enableFailed() {
        getLogger().error("Plugin Could not be Enabled!");
    }

    /**
     * Logs that this Plugin failed to start and why
     *
     * @param reason The Reason that the plugin failed to start
     */
    public void enableFailed(String reason) {
        getLogger().error("Plugin Could not be Enabled, because " + reason);
    }

    /**
     * Logs that the plugin is enabled
     */
    public void enabled() {
        getLogger().info("Plugin Enabled");
    }

    /**
     * {@inheritDoc}
     * <p/>
     * The Logman returned is a wrapper of EELogger
     */
    @Deprecated
    @Override
    public Logman getLogman() {
        return null; // TODO replace
    }

    public File getPluginDataFolder() {
        return pluginDataFolder;
    }

    public void registerCommand(Command command) throws CommandDependencyException {
        CanaryUtil.commands().registerCommand(command, this);
        command.setLoaded(true);
    }

    public FactoryManager getEEFactoryManager() {
        return FactoryManager.getFactoryManager();
    }

    public EELoggerFactory getEELoggerFactory() {
        return getEEFactoryManager().getEELoggerFactory();
    }

    /**
     * Get the Logger for this plugin
     *
     * @return The Logger for this pluign
     */
    public EELogger getLogger() {
        return getEELoggerFactory().getLogger(getName());
    }

    /**
     * Get a sub logger of this plugin
     *
     * @param sub Name of the sub logger you want
     *
     * @return The sub logger you wanted
     */
    public EELogger getLogger(String sub) {
        return getEELoggerFactory().getSubLogger(getLogger(), sub);
    }

}
