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
package net.larry1123.util.commands.bungeecord;

import net.canarymod.Translator;
import net.canarymod.chat.MessageReceiver;
import net.larry1123.util.api.plugin.commands.Command;
import net.larry1123.util.api.plugin.commands.CommandData;
import net.larry1123.util.commands.UtilCommands;
import net.visualillusionsent.utils.LocaleHelper;

import java.util.List;

public class BungeeCordCommand implements Command {

    private final String[] aliases = new String[] {"bungeecord", "cord"};

    private final CommandData command;
    private final LocaleHelper translator = Translator.getInstance();
    private final UtilCommands utilcommands;
    private final Command parent;
    private boolean loaded = false;

    public BungeeCordCommand(UtilCommands utilCommands, Command parent) {
        utilcommands = utilCommands;
        this.parent = parent;
        command = new CommandData(aliases, new String[] {"canary.super.canaryutil.bungeecord", "canary.command.super.canaryutil.bungeecord"}, "TODO", "/" + parent.getCommandData().getAliases()[0] + " " + aliases[0] + " <set|reload>");
        command.setParent(parent.getCommandData());
        command.setMax(1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        caller.message("/" + parent.getCommandData().getAliases()[0] + " " + aliases[0] + " <set|reload>");
    }

    @Override
    public List<String> tabComplete(MessageReceiver messageReceiver, String[] args) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandData getCommandData() {
        return command;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocaleHelper getTranslator() {
        return translator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isForced() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLoaded() {
        return loaded;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLoaded(boolean loadedness) {
        loaded = loadedness;
    }

}
