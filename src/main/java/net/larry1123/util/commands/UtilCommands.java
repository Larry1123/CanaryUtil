package net.larry1123.util.commands;

import net.canarymod.commandsys.CommandDependencyException;
import net.larry1123.elec.util.factorys.FactoryManager;
import net.larry1123.util.CanaryUtil;
import net.larry1123.util.api.plugin.commands.Command;
import net.larry1123.util.commands.bungeecord.BungeeCordCommand;
import net.larry1123.util.commands.bungeecord.BungeeCordReloadCommand;
import net.larry1123.util.commands.bungeecord.BungeeCordSetCommand;

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
            FactoryManager.getFactoryManager().getEELoggerFactory().getLogger("CanaryUtil").logCustom("Commands", "Failed to add command: " + command.getCommandData().getAliases()[0], e);
            command.setLoaded(false);
        }
    }

    public void reloadUtilCommandRepair() {
        // TODO
    }

}
