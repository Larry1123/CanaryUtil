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

import java.util.ArrayList;

/**
 * @author Larry1123
 * @since 1/26/14 - 7:30 AM
 */
public class CanaryPermission {

    // Player permissions
    public final Permission MOTD;
    public final Permission HELP;
    public final Permission PLAYER$LIST;
    public final Permission COMPASS;
    public final Permission GETPOS;
    public final Permission MESSAGE;
    public final Permission EMOTE;
    public final Permission TIME;
    public final Permission SPAWNPOINT;
    // Mod permissions
    public final Permission BAN;
    public final Permission UNBAN;
    public final Permission IPBAN;
    public final Permission KICK;
    public final Permission MUTE;
    public final Permission PLAYER$INFO;
    public final Permission ACHIEVEMENT;
    public final Permission ACHIEVEMENT$OTHER;
    public final Permission BROADCAST;
    public final Permission GOD;
    public final Permission GOD$OTHER;
    public final Permission CLEAR;
    public final Permission CLEAR$OTHER;
    public final Permission TESTFOR;
    public final Permission TESTFORBLOCK;
    public final Permission MESSAGERAW;
    // Admin permissions
    public final Permission STOP;
    public final Permission RELOAD;
    public final Permission UPTIME;
    public final Permission SYSINFO;
    public final Permission RESERVELIST;
    public final Permission WHITELIST;
    public final Permission GIVE;
    public final Permission GIVE$OTHER;
    public final Permission KILL;
    public final Permission KILL$OTHER;
    public final Permission SUMMON;
    public final Permission DEFAULTGAMEMODE;
    public final Permission SETWORLDSPAWN;
    public final Permission DIFFICULTY;
    public final Permission EFFECT;
    public final Permission EFFECT$OTHER;
    public final Permission ENCHANT;
    public final Permission ENCHANT$OTHER;
    public final Permission GAMEMODE;
    public final Permission GAMEMODE$OTHER;
    public final Permission GAMERULE;
    public final Permission PLAYSOUND;
    public final Permission PLAYSOUND$OTHER;
    public final Permission SAVE$ALL;
    public final Permission SAVE$OFF;
    public final Permission SAVE$ON;
    public final Permission SCOREBOARD;
    public final Permission SETBLOCK;
    public final Permission SPREADPLAYERS;
    public final Permission TOGGLEDOWNFALL;
    public final Permission WEATHER;
    public final Permission XP;
    public final Permission XP$OTHER;
    public final Permission SPAWNPOINT$OTHER;
    public final Permission CANARYMOD;
    // GroupMod
    public final Permission GROUPMOD;
    public final Permission GROUPMOD$LIST;
    public final Permission GROUPMOD$CHECK;
    public final Permission GROUPMOD$ADD;
    public final Permission GROUPMOD$REMOVE;
    public final Permission GROUPMOD$RENAME;
    public final Permission GROUPMOD$PREFIX;
    public final Permission GROUPMOD$PARENT;
    public final Permission GROUPMOD$PERMISSIONS;
    public final Permission GROUPMOD$PERMISSIONS$ADD;
    public final Permission GROUPMOD$PERMISSIONS$REMOVE;
    public final Permission GROUPMOD$PERMISSIONS$CHECK;
    public final Permission GROUPMOD$PERMISSIONS$LIST;
    public final Permission GROUPMOD$PERMISSIONS$FLUSH;
    // PlayerMod
    public final Permission PLAYERMOD;
    public final Permission PLAYERMOD$ADD;
    public final Permission PLAYERMOD$REMOVE;
    public final Permission PLAYERMOD$PREFIX;
    public final Permission PLAYERMOD$GROUP;
    public final Permission PLAYERMOD$GROUP$SET;
    public final Permission PLAYERMOD$GROUP$ADD;
    public final Permission PLAYERMOD$GROUP$LIST;
    public final Permission PLAYERMOD$GROUP$CHECK;
    public final Permission PLAYERMOD$GROUP$REMOVE;
    public final Permission PLAYERMOD$PERMISSIONS;
    public final Permission PLAYERMOD$PERMISSIONS$ADD;
    public final Permission PLAYERMOD$PERMISSIONS$REMOVE;
    public final Permission PLAYERMOD$PERMISSIONS$CHECK;
    public final Permission PLAYERMOD$PERMISSIONS$LIST;
    // Kit permissions
    public final Permission KIT;
    public final Permission KIT$OTHER;
    public final Permission KIT$GROUP;
    public final Permission KIT$PRIVATE;
    // "teleport" permissions
    public final Permission HOME;
    public final Permission HOME$OTHER;
    public final Permission HOME$SET;
    public final Permission SPAWN;
    public final Permission TELEPORT;
    public final Permission TELEPORT$OTHER;
    // "warp" permissions
    public final Permission WARP$LIST;
    public final Permission WARP$SET;
    public final Permission WARP$SET$ADMIN;
    public final Permission WarpSetPublic;
    public final Permission WARP$SET$GROUP;
    public final Permission WARP$SET$PRIVATE;
    public final Permission WARP$USE;
    public final Permission WarpAdmin;
    // "plugin" permissions
    public final Permission PLUGIN$ENABLE;
    public final Permission PLUGIN$DISABLE;
    public final Permission PLUGIN$RELOAD;
    public final Permission PLUGIN$LIST;
    // World permissions
    public final Permission WORLD$CREATE;
    public final Permission WORLD$DELETE;
    public final Permission WORLD$LOAD;
    // Mob permissions
    public final Permission MOB;
    public final Permission MOB$CLEAR;
    public final Permission MOB$COUNT;
    // other permissions
    public final Permission CommandBlock;
    public final Permission SpawnBuild;
    public final Permission WorldBuild;
    public final Permission Administrator;
    public final Permission CanFly;

    private final PermissionTracker tracker;
    private final ArrayList<Permission> permissions = new ArrayList<Permission>();

    public CanaryPermission(PermissionTracker tracker) {
        this.tracker = tracker;

        // Player permissions
        MOTD = createPermission(CanaryCommandPermissions.MOTD);
        HELP = createPermission(CanaryCommandPermissions.HELP);
        PLAYER$LIST = createPermission(CanaryCommandPermissions.PLAYER$LIST);
        COMPASS = createPermission(CanaryCommandPermissions.COMPASS);
        GETPOS = createPermission(CanaryCommandPermissions.GETPOS);
        MESSAGE = createPermission(CanaryCommandPermissions.MESSAGE);
        EMOTE = createPermission(CanaryCommandPermissions.EMOTE);
        TIME = createPermission(CanaryCommandPermissions.TIME);
        SPAWNPOINT = createPermission(CanaryCommandPermissions.SPAWNPOINT);
        // Mod permissions
        BAN = createPermission(CanaryCommandPermissions.BAN);
        UNBAN = createPermission(CanaryCommandPermissions.UNBAN);
        IPBAN = createPermission(CanaryCommandPermissions.IPBAN);
        KICK = createPermission(CanaryCommandPermissions.KICK);
        MUTE = createPermission(CanaryCommandPermissions.MUTE);
        PLAYER$INFO = createPermission(CanaryCommandPermissions.PLAYER$INFO);
        ACHIEVEMENT = createPermission(CanaryCommandPermissions.ACHIEVEMENT);
        ACHIEVEMENT$OTHER = createPermission(CanaryCommandPermissions.ACHIEVEMENT$OTHER);
        BROADCAST = createPermission(CanaryCommandPermissions.BROADCAST);
        GOD = createPermission(CanaryCommandPermissions.GOD);
        GOD$OTHER = createPermission(CanaryCommandPermissions.GOD$OTHER);
        CLEAR = createPermission(CanaryCommandPermissions.CLEAR);
        CLEAR$OTHER = createPermission(CanaryCommandPermissions.CLEAR$OTHER);
        TESTFOR = createPermission(CanaryCommandPermissions.TESTFOR);
        TESTFORBLOCK = createPermission(CanaryCommandPermissions.TESTFORBLOCK);
        MESSAGERAW = createPermission(CanaryCommandPermissions.MESSAGERAW);
        // Admin permissions
        STOP = createPermission(CanaryCommandPermissions.STOP);
        RELOAD = createPermission(CanaryCommandPermissions.RELOAD);
        UPTIME = createPermission(CanaryCommandPermissions.UPTIME);
        SYSINFO = createPermission(CanaryCommandPermissions.SYSINFO);
        RESERVELIST = createPermission(CanaryCommandPermissions.RESERVELIST);
        WHITELIST = createPermission(CanaryCommandPermissions.WHITELIST);
        GIVE = createPermission(CanaryCommandPermissions.GIVE);
        GIVE$OTHER = createPermission(CanaryCommandPermissions.GIVE$OTHER);
        KILL = createPermission(CanaryCommandPermissions.KILL);
        KILL$OTHER = createPermission(CanaryCommandPermissions.KILL$OTHER);
        SUMMON = createPermission(CanaryCommandPermissions.SUMMON);
        DEFAULTGAMEMODE = createPermission(CanaryCommandPermissions.DEFAULTGAMEMODE);
        SETWORLDSPAWN = createPermission(CanaryCommandPermissions.SETWORLDSPAWN);
        DIFFICULTY = createPermission(CanaryCommandPermissions.DIFFICULTY);
        EFFECT = createPermission(CanaryCommandPermissions.EFFECT);
        EFFECT$OTHER = createPermission(CanaryCommandPermissions.EFFECT$OTHER);
        ENCHANT = createPermission(CanaryCommandPermissions.ENCHANT);
        ENCHANT$OTHER = createPermission(CanaryCommandPermissions.ENCHANT$OTHER);
        GAMEMODE = createPermission(CanaryCommandPermissions.GAMEMODE);
        GAMEMODE$OTHER = createPermission(CanaryCommandPermissions.GAMEMODE$OTHER);
        GAMERULE = createPermission(CanaryCommandPermissions.GAMERULE);
        PLAYSOUND = createPermission(CanaryCommandPermissions.PLAYSOUND);
        PLAYSOUND$OTHER = createPermission(CanaryCommandPermissions.PLAYSOUND$OTHER);
        SAVE$ALL = createPermission(CanaryCommandPermissions.SAVE$ALL);
        SAVE$OFF = createPermission(CanaryCommandPermissions.SAVE$OFF);
        SAVE$ON = createPermission(CanaryCommandPermissions.SAVE$ON);
        SCOREBOARD = createPermission(CanaryCommandPermissions.SCOREBOARD);
        SETBLOCK = createPermission(CanaryCommandPermissions.SETBLOCK);
        SPREADPLAYERS = createPermission(CanaryCommandPermissions.SPREADPLAYERS);
        TOGGLEDOWNFALL = createPermission(CanaryCommandPermissions.TOGGLEDOWNFALL);
        WEATHER = createPermission(CanaryCommandPermissions.WEATHER);
        XP = createPermission(CanaryCommandPermissions.XP);
        XP$OTHER = createPermission(CanaryCommandPermissions.XP$OTHER);
        SPAWNPOINT$OTHER = createPermission(CanaryCommandPermissions.SPAWNPOINT$OTHER);
        // GroupMod
        CANARYMOD = createPermission(CanaryCommandPermissions.CANARYMOD);
        GROUPMOD = createPermission(CanaryCommandPermissions.GROUPMOD);
        GROUPMOD$LIST = createPermission(CanaryCommandPermissions.GROUPMOD$LIST);
        GROUPMOD$CHECK = createPermission(CanaryCommandPermissions.GROUPMOD$CHECK);
        GROUPMOD$ADD = createPermission(CanaryCommandPermissions.GROUPMOD$ADD);
        GROUPMOD$REMOVE = createPermission(CanaryCommandPermissions.GROUPMOD$REMOVE);
        GROUPMOD$RENAME = createPermission(CanaryCommandPermissions.GROUPMOD$RENAME);
        GROUPMOD$PREFIX = createPermission(CanaryCommandPermissions.GROUPMOD$PREFIX);
        GROUPMOD$PARENT = createPermission(CanaryCommandPermissions.GROUPMOD$PARENT);
        GROUPMOD$PERMISSIONS = createPermission(CanaryCommandPermissions.GROUPMOD$PERMISSIONS);
        GROUPMOD$PERMISSIONS$ADD = createPermission(CanaryCommandPermissions.GROUPMOD$PERMISSIONS$ADD);
        GROUPMOD$PERMISSIONS$REMOVE = createPermission(CanaryCommandPermissions.GROUPMOD$PERMISSIONS$REMOVE);
        GROUPMOD$PERMISSIONS$CHECK = createPermission(CanaryCommandPermissions.GROUPMOD$PERMISSIONS$CHECK);
        GROUPMOD$PERMISSIONS$LIST = createPermission(CanaryCommandPermissions.GROUPMOD$PERMISSIONS$LIST);
        GROUPMOD$PERMISSIONS$FLUSH = createPermission(CanaryCommandPermissions.GROUPMOD$PERMISSIONS$FLUSH);
        // PlayerMod
        PLAYERMOD = createPermission(CanaryCommandPermissions.PLAYERMOD);
        PLAYERMOD$ADD = createPermission(CanaryCommandPermissions.PLAYERMOD$ADD);
        PLAYERMOD$REMOVE = createPermission(CanaryCommandPermissions.GROUPMOD$REMOVE);
        PLAYERMOD$PREFIX = createPermission(CanaryCommandPermissions.PLAYERMOD$PREFIX);
        PLAYERMOD$GROUP = createPermission(CanaryCommandPermissions.PLAYERMOD$GROUP);
        PLAYERMOD$GROUP$SET = createPermission(CanaryCommandPermissions.PLAYERMOD$GROUP$SET);
        PLAYERMOD$GROUP$ADD = createPermission(CanaryCommandPermissions.PLAYERMOD$GROUP$ADD);
        PLAYERMOD$GROUP$LIST = createPermission(CanaryCommandPermissions.PLAYERMOD$GROUP$LIST);
        PLAYERMOD$GROUP$CHECK = createPermission(CanaryCommandPermissions.PLAYERMOD$GROUP$CHECK);
        PLAYERMOD$GROUP$REMOVE = createPermission(CanaryCommandPermissions.PLAYERMOD$GROUP$REMOVE);
        PLAYERMOD$PERMISSIONS = createPermission(CanaryCommandPermissions.PLAYERMOD$PERMISSIONS);
        PLAYERMOD$PERMISSIONS$ADD = createPermission(CanaryCommandPermissions.PLAYERMOD$PERMISSIONS$ADD);
        PLAYERMOD$PERMISSIONS$REMOVE = createPermission(CanaryCommandPermissions.PLAYERMOD$PERMISSIONS$REMOVE);
        PLAYERMOD$PERMISSIONS$CHECK = createPermission(CanaryCommandPermissions.PLAYERMOD$PERMISSIONS$CHECK);
        PLAYERMOD$PERMISSIONS$LIST = createPermission(CanaryCommandPermissions.PLAYERMOD$PERMISSIONS$LIST);
        // Kit permissions
        KIT = createPermission(CanaryCommandPermissions.KIT);
        KIT$OTHER = createPermission(CanaryCommandPermissions.KIT$OTHER);
        KIT$GROUP = createPermission(CanaryCommandPermissions.KIT$GROUP);
        KIT$PRIVATE = createPermission(CanaryCommandPermissions.KIT$PRIVATE);
        // "teleport" permissions
        HOME = createPermission(CanaryCommandPermissions.HOME);
        HOME$OTHER = createPermission(CanaryCommandPermissions.HOME$OTHER);
        HOME$SET = createPermission(CanaryCommandPermissions.HOME$SET);
        SPAWN = createPermission(CanaryCommandPermissions.SPAWN);
        TELEPORT = createPermission(CanaryCommandPermissions.TELEPORT);
        TELEPORT$OTHER = createPermission(CanaryCommandPermissions.TELEPORT$OTHER);
        // "warp" permissions
        WARP$LIST = createPermission(CanaryCommandPermissions.WARP$LIST);
        WARP$SET = createPermission(CanaryCommandPermissions.WARP$SET);
        WARP$SET$ADMIN = createPermission(CanaryCommandPermissions.WARP$SET$ADMIN);
        WarpSetPublic = createPermission("canary.command.warp.set.public");
        WARP$SET$GROUP = createPermission(CanaryCommandPermissions.WARP$SET$GROUP);
        WARP$SET$PRIVATE = createPermission(CanaryCommandPermissions.WARP$SET$PRIVATE);
        WARP$USE = createPermission(CanaryCommandPermissions.WARP$USE);
        WarpAdmin = createPermission("canary.command.warp.admin");
        // "plugin" permissions
        PLUGIN$ENABLE = createPermission(CanaryCommandPermissions.PLUGIN$ENABLE);
        PLUGIN$DISABLE = createPermission(CanaryCommandPermissions.PLUGIN$DISABLE);
        PLUGIN$RELOAD = createPermission(CanaryCommandPermissions.PLUGIN$RELOAD);
        PLUGIN$LIST = createPermission(CanaryCommandPermissions.PLUGIN$LIST);
        // World permissions
        WORLD$CREATE = createPermission(CanaryCommandPermissions.WORLD$CREATE);
        WORLD$DELETE = createPermission(CanaryCommandPermissions.WORLD$DELETE);
        WORLD$LOAD = createPermission(CanaryCommandPermissions.WORLD$LOAD);
        // Mob permissions
        MOB = createPermission(CanaryCommandPermissions.MOB);
        MOB$CLEAR = createPermission(CanaryCommandPermissions.MOB$CLEAR);
        MOB$COUNT = createPermission(CanaryCommandPermissions.MOB$COUNT);
        // other permissions
        CommandBlock = createPermission("canary.world.commandblock");
        SpawnBuild = createPermission("canary.world.spawnbuild");
        WorldBuild = createPermission("canary.world.build");
        Administrator = createPermission("canary.super.administrator");
        CanFly = createPermission("canary.player.canFly");
    }

    public ArrayList<Permission> getAllCanaryPermissions() {
        return Lists.newArrayList(permissions);
    }

    protected Permission createPermission(String perm) {
        Permission ret = tracker.getPerm(perm);
        permissions.add(ret);
        return ret;
    }

}
