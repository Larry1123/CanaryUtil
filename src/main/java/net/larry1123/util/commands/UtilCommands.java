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

import java.util.LinkedList;

public class UtilCommands {


    protected final CanaryUtil plugin;

    public UtilCommands(CanaryUtil plugin) {
        this.plugin = plugin;
        LinkedList<Command> commands = new LinkedList<Command>();
        Command baseCommand;
        Command bungeeCordBase;
        {
            // canaryutil
            commands.add(baseCommand = new BaseCommand(this));
            { // SubCommands BaseCommand
                // canaryutil help
                commands.add(new VersionCommand(this, baseCommand));
                // canaryutil bungeeCord
                commands.add(bungeeCordBase = new BungeeCordCommand(this, baseCommand));
                { // SubCommands of BungeeCordCommand
                    // canaryutil bungeeCord reload
                    commands.add(new BungeeCordReloadCommand(this, bungeeCordBase));
                    // canaryutil bungeeCord set
                    commands.add(new BungeeCordSetCommand(this, bungeeCordBase));
                }
            }
            commands.add(new RepairCommand(this, new String[] { "repair" }));
        }
        for (Command command : commands) {
            regCommand(command);
        }
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

    public CanaryUtil getPlugin() {
        return plugin;
    }

}
