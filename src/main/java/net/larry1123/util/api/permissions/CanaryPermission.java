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
import net.canarymod.commandsys.CanaryCommandPermissions;
import net.larry1123.elec.util.factorys.EELoggerFactory;
import net.larry1123.elec.util.factorys.FactoryManager;

import java.util.ArrayList;

/**
 * @author Larry1123
 * @since 1/26/14 - 7:30 AM
 */
public class CanaryPermission {

    // "super" permissions
    public final Permission Ban = createPermission(CanaryCommandPermissions.BAN);
    public final Permission IPBan = createPermission(CanaryCommandPermissions.IPBAN);
    public final Permission Kick = createPermission(CanaryCommandPermissions.KICK);
    public final Permission Mute = createPermission(CanaryCommandPermissions.MUTE);
    public final Permission Reload = createPermission(CanaryCommandPermissions.RELOAD);
    public final Permission SetSpawn = createPermission(CanaryCommandPermissions.SETWORLDSPAWN);
    public final Permission ReserveList = createPermission(CanaryCommandPermissions.RESERVELIST);
    public final Permission WhiteList = createPermission(CanaryCommandPermissions.WHITELIST);
    // GroupMod
    public final Permission GROUPMOD = createPermission(CanaryCommandPermissions.GROUPMOD);
    public final Permission GROUPMOD$LIST = createPermission(CanaryCommandPermissions.GROUPMOD$LIST);
    public final Permission GROUPMOD$CHECK = createPermission(CanaryCommandPermissions.GROUPMOD$CHECK);
    public final Permission GROUPMOD$ADD = createPermission(CanaryCommandPermissions.GROUPMOD$ADD);
    public final Permission GROUPMOD$REMOVE = createPermission(CanaryCommandPermissions.GROUPMOD$REMOVE);
    public final Permission GROUPMOD$RENAME = createPermission(CanaryCommandPermissions.GROUPMOD$RENAME);
    public final Permission GROUPMOD$PREFIX = createPermission(CanaryCommandPermissions.GROUPMOD$PREFIX);
    public final Permission GROUPMOD$PARENT = createPermission(CanaryCommandPermissions.GROUPMOD$PARENT);
    public final Permission GROUPMOD$PERMISSIONS = createPermission(CanaryCommandPermissions.GROUPMOD$PERMISSIONS);
    public final Permission GROUPMOD$PERMISSIONS$ADD = createPermission(CanaryCommandPermissions.GROUPMOD$PERMISSIONS$ADD);
    public final Permission GROUPMOD$PERMISSIONS$REMOVE = createPermission(CanaryCommandPermissions.GROUPMOD$PERMISSIONS$REMOVE);
    public final Permission GROUPMOD$PERMISSIONS$CHECK = createPermission(CanaryCommandPermissions.GROUPMOD$PERMISSIONS$CHECK);
    public final Permission GROUPMOD$PERMISSIONS$LIST = createPermission(CanaryCommandPermissions.GROUPMOD$PERMISSIONS$LIST);
    public final Permission GROUPMOD$PERMISSIONS$FLUSH = createPermission(CanaryCommandPermissions.GROUPMOD$PERMISSIONS$FLUSH);
    // PlayerMod
    public final Permission PLAYERMOD = createPermission(CanaryCommandPermissions.PLAYERMOD);
    public final Permission PLAYERMOD$ADD = createPermission(CanaryCommandPermissions.PLAYERMOD$ADD);
    public final Permission PLAYERMOD$REMOVE = createPermission(CanaryCommandPermissions.GROUPMOD$REMOVE);
    public final Permission PLAYERMOD$PREFIX = createPermission(CanaryCommandPermissions.PLAYERMOD$PREFIX);
    public final Permission PLAYERMOD$GROUP = createPermission(CanaryCommandPermissions.PLAYERMOD$GROUP);
    public final Permission PLAYERMOD$GROUP$SET = createPermission(CanaryCommandPermissions.PLAYERMOD$GROUP$SET);
    public final Permission PLAYERMOD$GROUP$ADD = createPermission(CanaryCommandPermissions.PLAYERMOD$GROUP$ADD);
    public final Permission PLAYERMOD$GROUP$LIST = createPermission(CanaryCommandPermissions.PLAYERMOD$GROUP$LIST);
    public final Permission PLAYERMOD$GROUP$CHECK = createPermission(CanaryCommandPermissions.PLAYERMOD$GROUP$CHECK);
    public final Permission PLAYERMOD$GROUP$REMOVE = createPermission(CanaryCommandPermissions.PLAYERMOD$GROUP$REMOVE);
    public final Permission PLAYERMOD$PERMISSIONS = createPermission(CanaryCommandPermissions.PLAYERMOD$PERMISSIONS);
    public final Permission PLAYERMOD$PERMISSIONS$ADD = createPermission(CanaryCommandPermissions.PLAYERMOD$PERMISSIONS$ADD);
    public final Permission PLAYERMOD$PERMISSIONS$REMOVE = createPermission(CanaryCommandPermissions.PLAYERMOD$PERMISSIONS$REMOVE);
    public final Permission PLAYERMOD$PERMISSIONS$CHECK = createPermission(CanaryCommandPermissions.PLAYERMOD$PERMISSIONS$CHECK);
    public final Permission PLAYERMOD$PERMISSIONS$LIST = createPermission(CanaryCommandPermissions.PLAYERMOD$PERMISSIONS$LIST);
    //"player" permissions
    public final Permission Compass = createPermission(CanaryCommandPermissions.COMPASS);
    public final Permission GetPos = createPermission(CanaryCommandPermissions.GETPOS);
    public final Permission Give = createPermission(CanaryCommandPermissions.GIVE);
    public final Permission GiveOther = createPermission(CanaryCommandPermissions.GIVE$OTHER);
    public final Permission Kill = createPermission(CanaryCommandPermissions.KILL);
    public final Permission Kit = createPermission(CanaryCommandPermissions.KIT);
    public final Permission KitOther = createPermission(CanaryCommandPermissions.KIT$OTHER);
    public final Permission KitGroup = createPermission(CanaryCommandPermissions.KIT$GROUP);
    public final Permission KitPrivate = createPermission(CanaryCommandPermissions.KIT$PRIVATE);
    public final Permission MobSpawn = createPermission("canary.command.player.mobspawn");
    public final Permission Message = createPermission(CanaryCommandPermissions.MESSAGE);
    public final Permission PList = createPermission(CanaryCommandPermissions.PLAYER$LIST);
    // "teleport" permissions
    public final Permission Home = createPermission(CanaryCommandPermissions.HOME);
    public final Permission HomeOther = createPermission(CanaryCommandPermissions.HOME$OTHER);
    public final Permission SetHome = createPermission(CanaryCommandPermissions.HOME$SET);
    public final Permission Spawn = createPermission(CanaryCommandPermissions.SPAWN);
    public final Permission Teleport = createPermission(CanaryCommandPermissions.TELEPORT);
    public final Permission TeleportOther = createPermission(CanaryCommandPermissions.TELEPORT$OTHER);
    // "warp" permissions
    public final Permission WarpList = createPermission(CanaryCommandPermissions.WARP$LIST);
    public final Permission WarpSet = createPermission(CanaryCommandPermissions.WARP$SET);
    public final Permission WarpSetAdmin = createPermission("canary.command.warp.admin");
    public final Permission WarpSetPublic = createPermission("canary.command.warp.set.public");
    public final Permission WarpSetGroup = createPermission("canary.command.warp.set.group");
    public final Permission WarpSetPrivate = createPermission("canary.command.warp.set.private");
    public final Permission WarpUse = createPermission(CanaryCommandPermissions.WARP$USE);
    public final Permission WarpAdmin = createPermission("canary.command.warp.admin");
    // "plugin" permissions
    public final Permission PluginEnable = createPermission(CanaryCommandPermissions.PLUGIN$ENABLE);
    public final Permission PluginDisable = createPermission(CanaryCommandPermissions.PLUGIN$DISABLE);
    public final Permission PluginReload = createPermission(CanaryCommandPermissions.RELOAD);
    public final Permission PluginList = createPermission(CanaryCommandPermissions.PLUGIN$LIST);
    //"misc" commands
    public final Permission Motd = createPermission(CanaryCommandPermissions.MOTD);
    public final Permission Help = createPermission(CanaryCommandPermissions.HELP);
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
                getLoggerFactory().getSubLogger("CanaryPermission", getLoggerFactory().getLogger("CanaryUtil")).error("Complete Derpness Happened that should never have been able to have happened", permissionCreationError);
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
            getLoggerFactory().getSubLogger("CanaryPermission", getLoggerFactory().getLogger("CanaryUtil")).error("Failed to Create Permission", permissionCreationError);
        }
        return null;
    }

    private EELoggerFactory getLoggerFactory() {
        return FactoryManager.getFactoryManager().getEELoggerFactory();
    }

}
