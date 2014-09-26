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
package net.larry1123.util.api.permissions;

import com.google.common.collect.Lists;
import net.larry1123.elec.util.factorys.EELoggerFactory;
import net.larry1123.elec.util.factorys.FactoryManager;

import java.util.ArrayList;

/**
 * @author Larry1123
 * @since 1/26/14 - 7:30 AM
 */
public class CanaryPermission {

    // "super" permissions
    public final Permission Ban = createPermission("canary.command.super.ban");
    public final Permission IPBan = createPermission("canary.command.super.ipban");
    public final Permission Kick = createPermission("canary.command.super.kick");
    public final Permission Mute = createPermission("canary.command.super.mute");
    public final Permission Reload = createPermission("canary.command.super.reload");
    public final Permission SetSpawn = createPermission("canary.command.super.setspawn");
    public final Permission ReserveList = createPermission("canary.command.super.reservelist");
    public final Permission WhiteList = createPermission("canary.command.super.whitelist");
    // GroupMod
    public final Permission GroupMod = createPermission("canary.command.super.groupmod");
    public final Permission GroupModAdd = createPermission("canary.command.super.groupmod.add");
    public final Permission GroupModDelete = createPermission("canary.command.super.groupmod.delete");
    public final Permission GroupModPermissions = createPermission("canary.command.super.groupmod.permissions");
    public final Permission GroupModList = createPermission("canary.command.super.groupmod.list");
    public final Permission GroupModRename = createPermission("canary.command.super.groupmod.rename");
    public final Permission GroupModPrefix = createPermission("canary.command.super.groupmod.prefix");
    // PlayerMod
    public final Permission PlayerMod = createPermission("canary.command.super.playermod");
    public final Permission PlayerModAdd = createPermission("canary.command.super.playermod.add");
    public final Permission PlayerModRemove = createPermission("canary.command.super.playermod.remove");
    public final Permission PlayerModPermissions = createPermission("canary.command.super.playermod.permissions");
    public final Permission PlayerModPrefix = createPermission("canary.command.super.playermod.prefix");
    public final Permission PlayerModGroup = createPermission("canary.command.super.playermod.group");
    //"player" permissions
    public final Permission Compass = createPermission("canary.command.player.compass");
    public final Permission GetPos = createPermission("canary.command.player.getpos");
    public final Permission Give = createPermission("canary.command.player.give");
    public final Permission GiveOther = createPermission("canary.command.player.give.other");
    public final Permission Kill = createPermission("canary.command.player.kill");
    public final Permission Kit = createPermission("canary.command.player.kit");
    public final Permission KitOther = createPermission("canary.command.player.kit.other");
    public final Permission KitGroup = createPermission("canary.command.player.kit.group");
    public final Permission KitPrivate = createPermission("canary.command.player.kit.private");
    public final Permission MobSpawn = createPermission("canary.command.player.mobspawn");
    public final Permission Message = createPermission("canary.command.player.msg");
    public final Permission PList = createPermission("canary.command.player.list");
    // "teleport" permissions
    public final Permission Home = createPermission("canary.command.teleport.home");
    public final Permission HomeOther = createPermission("canary.command.teleport.home.other");
    public final Permission SetHome = createPermission("canary.command.teleport.sethome");
    public final Permission Spawn = createPermission("canary.command.teleport.spawn");
    public final Permission Teleport = createPermission("canary.command.teleport.self");
    public final Permission TeleportOther = createPermission("canary.command.teleport.other");
    // "warp" permissions
    public final Permission WarpList = createPermission("canary.command.warp.list");
    public final Permission WarpSet = createPermission("canary.command.warp.set");
    public final Permission WarpSetAdmin = createPermission("canary.command.warp.admin");
    public final Permission WarpSetPublic = createPermission("canary.command.warp.set.public");
    public final Permission WarpSetGroup = createPermission("canary.command.warp.set.group");
    public final Permission WarpSetPrivate = createPermission("canary.command.warp.set.private");
    public final Permission WarpUse = createPermission("canary.command.warp.use");
    public final Permission WarpAdmin = createPermission("canary.command.warp.admin");
    // "plugin" permissions
    public final Permission PluginEnable = createPermission("canary.command.plugin.enable");
    public final Permission PluginDisable = createPermission("canary.command.plugin.disable");
    public final Permission PluginReload = createPermission("canary.command.plugin.reload");
    //"misc" commands
    public final Permission Motd = createPermission("canary.command.motd");
    public final Permission Help = createPermission("canary.command.help");
    // other permissions
    public final Permission CommandBlock = createPermission("canary.world.commandblock");
    public final Permission SpawnBuild = createPermission("canary.world.spawnbuild");
    public final Permission WorldBuild = createPermission("canary.world.build");
    public final Permission Administrator = createPermission("canary.super.administrator");
    public final Permission CanFly = createPermission("canary.player.canFly");
    private final PermissionTracker tracker;
    private final ArrayList<Permission> permissions = new ArrayList<Permission>();

    public CanaryPermission(PermissionTracker tracker) {
        this.tracker = tracker;
        if (!this.tracker.doesRootExist("canary")) {
            try {
                this.tracker.createRootPerm("canary");
            }
            catch (PermissionCreationError permissionCreationError) {
                getLoggerFactory().getSubLogger("CanaryPermission", getLoggerFactory().getLogger("CanaryUtil")).logStackTrace("Complete Derpness Happened that should never have been able to have happened", permissionCreationError);
            }
        }
    }

    public ArrayList<Permission> getAllCanaryPermissions() {
        return Lists.newArrayList(permissions);
    }

    protected Permission createPermission(String perm) {
        try {
            Permission ret = tracker.getPerm(perm);
            permissions.add(ret);
            return ret;
        }
        catch (PermissionCreationError permissionCreationError) {
            getLoggerFactory().getSubLogger("CanaryPermission", getLoggerFactory().getLogger("CanaryUtil")).logStackTrace("Failed to Create Permission", permissionCreationError);
        }
        return null;
    }

    private EELoggerFactory getLoggerFactory() {
        return FactoryManager.getFactoryManager().getEELoggerFactory();
    }

}
