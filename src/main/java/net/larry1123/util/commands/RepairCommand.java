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

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.blocks.CommandBlock;
import net.canarymod.chat.MessageReceiver;
import net.larry1123.util.api.permissions.PermissionsManager;
import net.larry1123.util.api.permissions.UtilPermission;
import net.larry1123.util.api.plugin.commands.Command;
import net.larry1123.util.api.plugin.commands.CommandData;
import net.visualillusionsent.utils.LocaleHelper;

import java.util.ArrayList;
import java.util.List;

import static net.larry1123.util.api.inventory.item.Repair.*;

/**
 * @author Larry1123
 * @since 10/31/2014 - 11:25 PM
 */
public class RepairCommand implements Command {

    private final CommandData command;
    private final UtilCommands utilcommands;
    private boolean loaded = false;

    public RepairCommand(UtilCommands utilCommands, String[] aliases) {
        utilcommands = utilCommands;
        command = new CommandData(aliases, //
                new String[] {getUtilPermissions().REPAIR.getPermissionString()}, //
                aliases[0] + "[Player] [all]", //
                aliases[0] + "[Player] [all]", //
                2);
    }

    @Override
    public CommandData getCommandData() {
        return command;
    }

    @Override
    public LocaleHelper getTranslator() {
        return Translator.getInstance();
    }

    @Override
    public boolean isForced() {
        return false;
    }

    @Override
    public boolean isLoaded() {
        return loaded;
    }

    @Override
    public void setLoaded(boolean loadedness) {
        loaded = loadedness;
    }

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        Player targetPlayer;
        Player buyingPlayer;
        boolean free;
        boolean all;
        if (caller instanceof Server) {
            if (parameters.length < 1) {
                caller.notice("Must provide at lest the name of the player");
                return;
            }
            free = true;
            targetPlayer = Canary.getServer().matchPlayer(parameters[0]);
            buyingPlayer = null;
            all = parameters.length >= 2;
        }
        else if (caller instanceof Player || caller instanceof CommandBlock) {
            // Find target and if they want all items that can be repaired fixed up.
            if (parameters.length > 1) {
                if (parameters[0].equals("all")) {
                    if (caller instanceof CommandBlock) {
                        caller.notice("Must say what player to repair all for!");
                        return;
                    }
                    targetPlayer = (Player) caller;
                    all = hasRepairAllPermission(caller);
                    if (hasRepairFreePermission(caller)) {
                        buyingPlayer = null;
                        free = true;
                    }
                    else {
                        buyingPlayer = targetPlayer;
                        free = hasRepairFreePermission(buyingPlayer);
                    }
                }
                else {
                    targetPlayer = Canary.getServer().matchPlayer(parameters[0]);
                    all = parameters.length >= 2 && hasRepairAllPermission(caller);
                    if (caller instanceof Player) {
                        buyingPlayer = (Player) caller;
                        free = hasRepairFreePermission(caller);
                    }
                    else {
                        if (hasRepairFreePermission(caller)) {
                            buyingPlayer = null;
                            free = true;
                        }
                        else {
                            buyingPlayer = targetPlayer;
                            free = hasRepairFreePermission(buyingPlayer);
                        }
                    }
                }
            }
            else {
                if (caller instanceof Player) {
                    targetPlayer = (Player) caller;
                    all = false;
                    if (hasRepairFreePermission(caller)) {
                        buyingPlayer = null;
                        free = true;
                    }
                    else {
                        buyingPlayer = targetPlayer;
                        free = hasRepairFreePermission(buyingPlayer);
                    }
                }
                else {
                    caller.notice("A Command Block has no items to repair!");
                    return;
                }
            }
        }
        else {
            // what the hell is using a command!
            return;
        }
        if (targetPlayer == null) {
            caller.notice("Target Player not found!");
            return;
        }
        // caller is player; caller is not target; can repair for other?
        else if (caller instanceof Player && targetPlayer != caller && !hasRepairOtherPermission(caller)) {
            caller.notice("You do not have permission to repair for others!");
            return;
        }
        ArrayList<Item> itemsToRepair = new ArrayList<Item>();
        if (all) {
            for (Item item : targetPlayer.getInventory().getContents()) {
                if (canRepairItem(item)) {
                    itemsToRepair.add(item);
                }
            }
        }
        else {
            Item item = targetPlayer.getItemHeld();
            if (canRepairItem(item)) {
                itemsToRepair.add(item);
            }
        }
        if (itemsToRepair.size() == 0) {
            caller.notice("No items require repair.");
            return;
        }
        int itemsRepaired = 0;
        for (Item item : itemsToRepair) {
            if (item == null) continue;
            if (!free) {
                int repairCost = item.getRepairCost() * 2;
                if (buyingPlayer.getLevel() >= repairCost) {
                    buyingPlayer.removeLevel(repairCost);
                }
                else {
                    if (!(caller instanceof CommandBlock) || caller != buyingPlayer) {
                        caller.notice("Paying player lacks the needed XP to repair " + item.getDisplayName() + "!");
                    }
                    buyingPlayer.notice("You lack the needed XP to repair " + item.getDisplayName() + "!");
                    break;
                }
            }
            repairItem(item);
            targetPlayer.message(item.getDisplayName() + " has been repaired.");
            itemsRepaired++;
        }
        String message = itemsRepaired + ", items have been repaired.";
        if (itemsToRepair.size() != itemsRepaired) {
            message += " " + (itemsToRepair.size() - itemsRepaired) + " items have not been repaired.";
        }
        if (caller != targetPlayer) {
            targetPlayer.message(message);
        }
        caller.message(message);
    }

    @Override
    public List<String> tabComplete(MessageReceiver messageReceiver, String[] args) {
        return null;
    }

    protected boolean hasRepairFreePermission(MessageReceiver caller) {return getUtilPermissions().REPAIR$FREE.hasPermission(caller);}

    protected boolean hasRepairAllPermission(MessageReceiver caller) {return getUtilPermissions().REPAIR$ALL.hasPermission(caller);}

    protected boolean hasRepairOtherPermission(MessageReceiver caller) {return getUtilPermissions().REPAIR$OTHER.hasPermission(caller);}

    protected UtilPermission getUtilPermissions() {
        return PermissionsManager.getManager().getUtilPermissions();
    }

}
