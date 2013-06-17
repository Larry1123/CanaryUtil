/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Jun 17, 2013 3:21:39 AM
 */

package net.larry1123.lib.commands.bungeecord;

import net.canarymod.Translator;
import net.canarymod.chat.MessageReceiver;
import net.larry1123.lib.commands.UtilCommands;
import net.larry1123.lib.config.UtilConfig;
import net.larry1123.lib.plugin.commands.Command;
import net.larry1123.lib.plugin.commands.CommandData;
import net.visualillusionsent.utils.LocaleHelper;

public class BungeeCordSetCommand implements Command {

    private static UtilConfig config = UtilConfig.getConfig();

    private final CommandData command = new CommandData(new String[]{ "bungeecord", "cord" }, new String[]{ "canary.super.canaryutil.bungeecord.set", "canary.command.super.canaryutil.bungeecord.set" }, "TODO set", "TODO set");
    private final LocaleHelper translator = Translator.getInstance();
    private final UtilCommands utilcommands;
    private boolean loaded = false;

    public BungeeCordSetCommand(UtilCommands utilCommands) {
        utilcommands = utilCommands;
        command.setParent(utilcommands.bungeecordCommand.getCommandData());
        command.setMin(2);
    }

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        if (parameters[2].toLowerCase().equals("enabled")) {
            if (parameters.length > 2) {
                config.getBungeeCordConfig().setIsEnabled(Boolean.parseBoolean(parameters[3]));
            } else {
                config.getBungeeCordConfig().setIsEnabled(!config.getBungeeCordConfig().isEnabled());
            }
        }
        if (parameters[2].toLowerCase().equals("time") || parameters[2].toLowerCase().equals("polltime")) {
            if (parameters.length > 2) {
                config.getBungeeCordConfig().setPollTime(Integer.parseInt(parameters[3]));
            } else {
                caller.message("");
            }
        }
        if (parameters[2].toLowerCase().equals("server")) {
            if (parameters.length > 2) {
                config.getBungeeCordConfig().setServerName(parameters[3]);
            } else {
                caller.message("");
            }
        }
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
    public boolean isloaded() {
        return loaded;
    }

    @Override
    public void setloadded(boolean loadedness) {
        loaded = loadedness;
    }

}
