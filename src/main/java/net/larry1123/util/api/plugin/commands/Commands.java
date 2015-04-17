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
package net.larry1123.util.api.plugin.commands;

import net.canarymod.Canary;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandDependencyException;
import net.canarymod.commandsys.CommandOwner;
import net.visualillusionsent.utils.LocaleHelper;

import java.util.Arrays;
import java.util.List;

public class Commands {

    /**
     * Will Register a Command with different parts
     *
     * @param data Replacement for @Command
     * @param owner Command owner
     * @param translator localehelper for translating meta info
     * @param execute Class holding method to execute Command when called
     * @param force If Command will override an other command
     *
     * @throws CommandDependencyException
     */
    public void registerCommand(final CommandData data, CommandOwner owner, LocaleHelper translator, final CommandExecute execute, boolean force) throws CommandDependencyException {
        CanaryCommand ccommand = new CanaryCommand(new CommandCommand(data), owner, translator) {

            @Override
            protected boolean hasTabComplete() {
                return true;
            }

            @Override
            protected List<String> tabComplete(MessageReceiver messageReceiver, String[] args) {
                if (meta.version() == 1) {
                    return execute.tabComplete(messageReceiver, args);
                }
                else {
                    return execute.tabComplete(messageReceiver, Arrays.copyOfRange(args, 1, args.length));
                }
            }

            @Override
            protected void execute(MessageReceiver caller, String[] parameters) {
                execute.execute(caller, parameters);
            }

        };
        Canary.commands().registerCommand(ccommand, owner, force);
    }

    /**
     * Will Register a Command that has all given data in one Command Class
     *
     * @param command Command pack
     * @param owner Command owner
     *
     * @throws CommandDependencyException
     */
    public void registerCommand(Command command, CommandOwner owner) throws CommandDependencyException {
        registerCommand(command.getCommandData(), owner, command.getTranslator(), command, command.isForced());
        command.setLoaded(true);
    }

    /**
     * Will unregister a given Command
     *
     * @param data The Command's data pack
     *
     * @return true if the command was removed, false otherwise.
     */
    public boolean unregisterCommand(CommandData data) {
        if (!data.getParent().equals("")) {
            return Canary.commands().unregisterCommand(data.getParent() + "." + data.getCommandUID());
        }
        else {
            return Canary.commands().unregisterCommand("" + data.getCommandUID());
        }
    }

    /**
     * Will unregister a given Command
     *
     * @param command The Command pack to remove
     *
     * @return true if the command was removed, false otherwise.
     */
    public boolean unregisterCommand(Command command) {
        if (unregisterCommand(command.getCommandData())) {
            command.setLoaded(true);
            return true;
        }
        else {
            return false;
        }
    }

}
