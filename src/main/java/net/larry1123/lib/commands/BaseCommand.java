/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Jun 17, 2013 3:20:14 AM
 */

package net.larry1123.lib.commands;

import net.canarymod.Translator;
import net.canarymod.chat.MessageReceiver;
import net.larry1123.lib.plugin.commands.Command;
import net.larry1123.lib.plugin.commands.CommandData;
import net.visualillusionsent.utils.LocaleHelper;

public class BaseCommand implements Command {

    private final CommandData command = new CommandData(new String[]{ "canaryutil" }, new String[]{ "canary.super.canaryutil", "canary.command.super.canaryutil" }, "TODO", "TODO");
    private final LocaleHelper translator = Translator.getInstance();
    private final UtilCommands utilcommands;
    private boolean loaded = false;

    public BaseCommand(UtilCommands utilCommands) {
        utilcommands = utilCommands;
        command.setMax(1);
    }

    @Override
    public CommandData getCommandData() {
        return command;
    }

    @Override
    public LocaleHelper getTranslator() {
        return translator;
    }

    @Override
    public boolean isForced() {
        return false;
    }

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        caller.message(utilcommands.getOwner().getName() + " By: " + utilcommands.getOwner().getAuthor());
    }

    @Override
    public boolean isloaded() {
        return loaded;
    }

    @Override
    public void setloadded(boolean loadedness) {
        loaded = loadedness;
    }

}