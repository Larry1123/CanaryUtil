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

    // Player permissions
    public final Permission MOTD = createPermission(CanaryCommandPermissions.MOTD);
    public final Permission HELP = createPermission(CanaryCommandPermissions.HELP);
    public final Permission PLAYER$LIST = createPermission(CanaryCommandPermissions.PLAYER$LIST);
    public final Permission COMPASS = createPermission(CanaryCommandPermissions.COMPASS);
    public final Permission GETPOS = createPermission(CanaryCommandPermissions.GETPOS);
    public final Permission MESSAGE = createPermission(CanaryCommandPermissions.MESSAGE);
    public final Permission EMOTE = createPermission(CanaryCommandPermissions.EMOTE);
    public final Permission TIME = createPermission(CanaryCommandPermissions.TIME);
    public final Permission SPAWNPOINT = createPermission(CanaryCommandPermissions.SPAWNPOINT);
    // Mod permissions
    public final Permission BAN = createPermission(CanaryCommandPermissions.BAN);
    public final Permission UNBAN = createPermission(CanaryCommandPermissions.UNBAN);
    public final Permission IPBAN = createPermission(CanaryCommandPermissions.IPBAN);
    public final Permission KICK = createPermission(CanaryCommandPermissions.KICK);
    public final Permission MUTE = createPermission(CanaryCommandPermissions.MUTE);
    public final Permission PLAYER$INFO = createPermission(CanaryCommandPermissions.PLAYER$INFO);
    public final Permission ACHIEVEMENT = createPermission(CanaryCommandPermissions.ACHIEVEMENT);
    public final Permission ACHIEVEMENT$OTHER = createPermission(CanaryCommandPermissions.ACHIEVEMENT$OTHER);
    public final Permission BROADCAST = createPermission(CanaryCommandPermissions.BROADCAST);
    public final Permission GOD = createPermission(CanaryCommandPermissions.GOD);
    public final Permission GOD$OTHER = createPermission(CanaryCommandPermissions.GOD$OTHER);
    public final Permission CLEAR = createPermission(CanaryCommandPermissions.CLEAR);
    public final Permission CLEAR$OTHER = createPermission(CanaryCommandPermissions.CLEAR$OTHER);
    public final Permission TESTFOR = createPermission(CanaryCommandPermissions.TESTFOR);
    public final Permission TESTFORBLOCK = createPermission(CanaryCommandPermissions.TESTFORBLOCK);
    public final Permission MESSAGERAW = createPermission(CanaryCommandPermissions.MESSAGERAW);
    // Admin permissions
    public final Permission STOP = createPermission(CanaryCommandPermissions.STOP);
    public final Permission RELOAD = createPermission(CanaryCommandPermissions.RELOAD);
    public final Permission UPTIME = createPermission(CanaryCommandPermissions.UPTIME);
    public final Permission SYSINFO = createPermission(CanaryCommandPermissions.SYSINFO);
    public final Permission RESERVELIST = createPermission(CanaryCommandPermissions.RESERVELIST);
    public final Permission WHITELIST = createPermission(CanaryCommandPermissions.WHITELIST);
    public final Permission GIVE = createPermission(CanaryCommandPermissions.GIVE);
    public final Permission GIVE$OTHER = createPermission(CanaryCommandPermissions.GIVE$OTHER);
    public final Permission KILL = createPermission(CanaryCommandPermissions.KILL);
    public final Permission KILL$OTHER = createPermission(CanaryCommandPermissions.KILL$OTHER);
    public final Permission SUMMON = createPermission(CanaryCommandPermissions.SUMMON);
    public final Permission DEFAULTGAMEMODE = createPermission(CanaryCommandPermissions.DEFAULTGAMEMODE);
    public final Permission SETWORLDSPAWN = createPermission(CanaryCommandPermissions.SETWORLDSPAWN);
    public final Permission DIFFICULTY = createPermission(CanaryCommandPermissions.DIFFICULTY);
    public final Permission EFFECT = createPermission(CanaryCommandPermissions.EFFECT);
    public final Permission EFFECT$OTHER = createPermission(CanaryCommandPermissions.EFFECT$OTHER);
    public final Permission ENCHANT = createPermission(CanaryCommandPermissions.ENCHANT);
    public final Permission ENCHANT$OTHER = createPermission(CanaryCommandPermissions.ENCHANT$OTHER);
    public final Permission GAMEMODE = createPermission(CanaryCommandPermissions.GAMEMODE);
    public final Permission GAMEMODE$OTHER = createPermission(CanaryCommandPermissions.GAMEMODE$OTHER);
    public final Permission GAMERULE = createPermission(CanaryCommandPermissions.GAMERULE);
    public final Permission PLAYSOUND = createPermission(CanaryCommandPermissions.PLAYSOUND);
    public final Permission PLAYSOUND$OTHER = createPermission(CanaryCommandPermissions.PLAYSOUND$OTHER);
    public final Permission SAVE$ALL = createPermission(CanaryCommandPermissions.SAVE$ALL);
    public final Permission SAVE$OFF = createPermission(CanaryCommandPermissions.SAVE$OFF);
    public final Permission SAVE$ON = createPermission(CanaryCommandPermissions.SAVE$ON);
    public final Permission SCOREBOARD = createPermission(CanaryCommandPermissions.SCOREBOARD);
    public final Permission SETBLOCK = createPermission(CanaryCommandPermissions.SETBLOCK);
    public final Permission SPREADPLAYERS = createPermission(CanaryCommandPermissions.SPREADPLAYERS);
    public final Permission TOGGLEDOWNFALL = createPermission(CanaryCommandPermissions.TOGGLEDOWNFALL);
    public final Permission WEATHER = createPermission(CanaryCommandPermissions.WEATHER);
    public final Permission XP = createPermission(CanaryCommandPermissions.XP);
    public final Permission XP$OTHER = createPermission(CanaryCommandPermissions.XP$OTHER);
    public final Permission SPAWNPOINT$OTHER = createPermission(CanaryCommandPermissions.SPAWNPOINT$OTHER);
    public final Permission CANARYMOD = createPermission(CanaryCommandPermissions.CANARYMOD);
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
    // Kit permissions
    public final Permission KIT = createPermission(CanaryCommandPermissions.KIT);
    public final Permission KIT$OTHER = createPermission(CanaryCommandPermissions.KIT$OTHER);
    public final Permission KIT$GROUP = createPermission(CanaryCommandPermissions.KIT$GROUP);
    public final Permission KIT$PRIVATE = createPermission(CanaryCommandPermissions.KIT$PRIVATE);
    // "teleport" permissions
    public final Permission HOME = createPermission(CanaryCommandPermissions.HOME);
    public final Permission HOME$OTHER = createPermission(CanaryCommandPermissions.HOME$OTHER);
    public final Permission HOME$SET = createPermission(CanaryCommandPermissions.HOME$SET);
    public final Permission SPAWN = createPermission(CanaryCommandPermissions.SPAWN);
    public final Permission TELEPORT = createPermission(CanaryCommandPermissions.TELEPORT);
    public final Permission TELEPORT$OTHER = createPermission(CanaryCommandPermissions.TELEPORT$OTHER);
    // "warp" permissions
    public final Permission WARP$LIST = createPermission(CanaryCommandPermissions.WARP$LIST);
    public final Permission WARP$SET = createPermission(CanaryCommandPermissions.WARP$SET);
    public final Permission WARP$SET$ADMIN = createPermission(CanaryCommandPermissions.WARP$SET$ADMIN);
    public final Permission WarpSetPublic = createPermission("canary.command.warp.set.public");
    public final Permission WARP$SET$GROUP = createPermission(CanaryCommandPermissions.WARP$SET$GROUP);
    public final Permission WARP$SET$PRIVATE = createPermission(CanaryCommandPermissions.WARP$SET$PRIVATE);
    public final Permission WARP$USE = createPermission(CanaryCommandPermissions.WARP$USE);
    public final Permission WarpAdmin = createPermission("canary.command.warp.admin");
    // "plugin" permissions
    public final Permission PLUGIN$ENABLE = createPermission(CanaryCommandPermissions.PLUGIN$ENABLE);
    public final Permission PLUGIN$DISABLE = createPermission(CanaryCommandPermissions.PLUGIN$DISABLE);
    public final Permission PLUGIN$RELOAD = createPermission(CanaryCommandPermissions.PLUGIN$RELOAD);
    public final Permission PLUGIN$LIST = createPermission(CanaryCommandPermissions.PLUGIN$LIST);
    // World permissions
    public final Permission WORLD$CREATE = createPermission(CanaryCommandPermissions.WORLD$CREATE);
    public final Permission WORLD$DELETE = createPermission(CanaryCommandPermissions.WORLD$DELETE);
    public final Permission WORLD$LOAD = createPermission(CanaryCommandPermissions.WORLD$LOAD);
    // Mob permissions
    public final Permission MOB = createPermission(CanaryCommandPermissions.MOB);
    public final Permission MOB$CLEAR = createPermission(CanaryCommandPermissions.MOB$CLEAR);
    public final Permission MOB$COUNT = createPermission(CanaryCommandPermissions.MOB$COUNT);

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
    }

    public ArrayList<Permission> getAllCanaryPermissions() {
        return Lists.newArrayList(permissions);
    }

    protected Permission createPermission(String perm) {
        Permission ret = tracker.getPerm(perm);
        permissions.add(ret);
        return ret;
    }

    private EELoggerFactory getLoggerFactory() {
        return FactoryManager.getFactoryManager().getEELoggerFactory();
    }

}
