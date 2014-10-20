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
package net.larry1123.util.commands;

import net.canarymod.commandsys.CommandDependencyException;
import net.canarymod.commandsys.DuplicateCommandException;
import net.larry1123.elec.util.factorys.FactoryManager;
import net.larry1123.util.CanaryUtil;
import net.larry1123.util.api.plugin.commands.Command;
import net.larry1123.util.commands.bungeecord.BungeeCordCommand;
import net.larry1123.util.commands.bungeecord.BungeeCordReloadCommand;
import net.larry1123.util.commands.bungeecord.BungeeCordSetCommand;
import org.slf4j.MarkerFactory;

import static net.larry1123.util.CanaryUtil.getPlugin;

public class UtilCommands {

    public final Command baseCommand;
    public final Command versionCommand;
    public final Command bungeecordCommand;
    public final Command bungeecordReloadCommand;
    public final Command bungeecordSetCommand;

    public UtilCommands() {
        {
            // canaryutil
            baseCommand = new BaseCommand(this);
            { // SubCommands BaseCommand
                // canaryutil help
                versionCommand = new VersionCommand(this);
                // canaryutil bungeecord
                bungeecordCommand = new BungeeCordCommand(this);
                { // SubCommands of BungeeCordCommand
                    // canaryutil bungeecord reload
                    bungeecordReloadCommand = new BungeeCordReloadCommand(this);
                    // canaryutil bungeecord set
                    bungeecordSetCommand = new BungeeCordSetCommand(this);
                }
            }
        }
        regCommand(baseCommand);
        regCommand(versionCommand);
        regCommand(bungeecordCommand);
        regCommand(bungeecordReloadCommand);
        regCommand(bungeecordSetCommand);
    }

    private void regCommand(Command command) {
        try {
            CanaryUtil.commands().registerCommand(command, getPlugin());
            command.setLoaded(true);
        }
        catch (CommandDependencyException e) {
            FactoryManager.getFactoryManager().getEELoggerFactory().getLogger("CanaryUtil").error(MarkerFactory.getMarker("Commands"), "Failed to add command: " + command.getCommandData().getAliases()[0], e);
            command.setLoaded(false);
        }
        catch (DuplicateCommandException e) {
            FactoryManager.getFactoryManager().getEELoggerFactory().getLogger("CanaryUtil").error(MarkerFactory.getMarker("Commands"), "Failed to add command: " + command.getCommandData().getAliases()[0], e);
            command.setLoaded(false);
        }
    }

    public void reloadUtilCommandRepair() {
        // TODO
    }

}
