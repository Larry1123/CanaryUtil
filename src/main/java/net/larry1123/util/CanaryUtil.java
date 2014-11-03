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
package net.larry1123.util;

import net.canarymod.commandsys.CommandOwner;
import net.canarymod.tasks.ServerTaskManager;
import net.canarymod.tasks.TaskOwner;
import net.larry1123.elec.util.factorys.FactoryManager;
import net.larry1123.util.api.plugin.UtilPlugin;
import net.larry1123.util.api.plugin.commands.Commands;
import net.larry1123.util.commands.UtilCommands;
import net.larry1123.util.config.LoggerConfig;
import net.larry1123.util.config.UtilConfigManager;
import net.larry1123.util.customPacket.CustomPacket;
import net.larry1123.util.task.FileSpliterUpdater;

public class CanaryUtil extends UtilPlugin implements TaskOwner, CommandOwner {

    private static final Commands commands = new Commands();

    static {
        FactoryManager.getFactoryManager().getEELoggerFactory().setLoggerSettings(new LoggerConfig());
    }

    private static CanaryUtil plugin;
    private static CustomPacket customPacket;
    private UtilCommands commandsManager;
    protected FileSpliterUpdater fileSpliterUpdater;

    public CanaryUtil() {
        plugin = this;
        fileSpliterUpdater = new FileSpliterUpdater(this);
    }

    /**
     * Warning may return Null if the Util is not enabled yet!!!!
     *
     * @return CustomPacket Manager
     */
    public static CustomPacket getCustomPacket() {
        return customPacket;
    }

    public static CanaryUtil getPlugin() {
        return plugin;
    }

    /**
     * @return Command Manager
     */
    public static Commands commands() {
        return commands;
    }

    /**
     * Plugin Enable Method
     */
    @Override
    public boolean enable() {
        // Start checker for splitting logging files
        getFileSpliterUpdater().startUpdater();
        // Make the custom packet manager object
        customPacket = new CustomPacket(this);
        // Start up the Command manager
        commandsManager = new UtilCommands(this);
        // Give that ConfigManager something to think about!
        UtilConfigManager.getConfig().setPlugin(this);
        getEELoggerFactory().setLoggerSettings(UtilConfigManager.getConfig().getLoggerConfig());
        // Log that the Plugin was Enabled
        enabled();
        // Hey everything worked lets return true so Canary knows that too
        return true;
    }

    /**
     * Plugin Disable Method
     */
    @Override
    public void disable() {
        // Stop Tasks!
        ServerTaskManager.removeTasks(this);
        // Log that the plugin was Disabled
        getLogger().info("Plugin Disabled");
    }

    public FileSpliterUpdater getFileSpliterUpdater() {
        return fileSpliterUpdater;
    }

    public UtilCommands getCommandsManager() {
        return commandsManager;
    }

}
