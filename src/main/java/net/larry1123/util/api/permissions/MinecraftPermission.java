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
 * @since 1/26/14 - 8:22 AM
 */
public class MinecraftPermission {

    public final Permission Help = createPermission("canary.command.help");
    public final Permission OtherHelp = createPermission("canary.command.?");
    public final Permission ME = createPermission("canary.command.me");
    public final Permission Tell = createPermission("canary.command.tell");
    public final Permission Msg = createPermission("canary.command.msg");
    public final Permission W = createPermission("canary.command.w");
    public final Permission Achievement = createPermission("canary.command.achievement");
    public final Permission BlockData = createPermission("canary.command.blockdata");
    public final Permission Clear = createPermission("canary.command.clear");
    public final Permission Clone = createPermission("canary.command.clone");
    public final Permission Debug = createPermission("canary.command.debug");
    public final Permission DefaultGameMode = createPermission("canary.command.defaultgamemode");
    public final Permission Difficulty = createPermission("canary.command.difficulty");
    public final Permission Effect = createPermission("canary.command.effect");
    public final Permission Fill = createPermission("canary.command.fill");
    public final Permission Enchant = createPermission("canary.command.enchant");
    public final Permission GameMode = createPermission("canary.command.gamemode");
    public final Permission GameRule = createPermission("canary.command.gamerule");
    public final Permission Give = createPermission("canary.command.give");
    public final Permission Kill = createPermission("canary.command.kill");
    public final Permission Particle = createPermission("canary.command.particle");
    public final Permission PlaySound = createPermission("canary.command.playsound");
    public final Permission Say = createPermission("canary.command.say");
    public final Permission ScoreBoard = createPermission("canary.command.scoreboard");
    public final Permission Seed = createPermission("canary.command.seed");
    public final Permission SetBlock = createPermission("canary.command.setblock");
    public final Permission SetWorldSpawn = createPermission("canary.command.setworldspawn");
    public final Permission SpawnPoint = createPermission("canary.command.spawnpoint");
    public final Permission SpreadPlayers = createPermission("canary.command.spreadplayers");
    public final Permission Summon = createPermission("canary.command.summon");
    public final Permission TellRaw = createPermission("canary.command.tellraw");
    public final Permission TestForBlock = createPermission("canary.command.testforblock");
    public final Permission Time = createPermission("canary.command.time");
    public final Permission ToggleDownFall = createPermission("canary.command.toggledownfall");
    public final Permission Tp = createPermission("canary.command.tp");
    public final Permission Weather = createPermission("canary.command.weather.");
    public final Permission Xp = createPermission("canary.command.xp.");
    public final Permission Ban = createPermission("canary.command.ban");
    public final Permission BanIp = createPermission("canary.command.ban-ip");
    public final Permission BanList = createPermission("canary.command.banlist");
    public final Permission DeOp = createPermission("canary.command.deop");
    public final Permission Kick = createPermission("canary.command.kick");
    public final Permission PList = createPermission("canary.command.list");
    public final Permission Op = createPermission("canary.command.op");
    public final Permission Pardon = createPermission("canary.command.pardon");
    public final Permission PardonIp = createPermission("canary.command.pardon-ip");
    public final Permission SaveAll = createPermission("canary.command.save-all");
    public final Permission SaveOff = createPermission("canary.command.save-off");
    public final Permission SaveOn = createPermission("canary.command.save-on");
    public final Permission SetIdleTimeOut = createPermission("canary.command.setidletimeout");
    public final Permission Stop = createPermission("canary.command.stop");
    public final Permission WhiteList = createPermission("canary.command.whitelist ");
    public final Permission TestFor = createPermission("canary.command.testfor");
    private final PermissionTracker tracker;
    private final ArrayList<Permission> permissions = new ArrayList<Permission>();

    public MinecraftPermission(PermissionTracker tracker) {
        this.tracker = tracker;
        if (!this.tracker.doesRootExist("canary")) {
            try {
                this.tracker.createRootPerm("canary");
            }
            catch (PermissionCreationError permissionCreationError) {
                getLoggerFactory().getSubLogger("MinecraftPermission", getLoggerFactory().getLogger("CanaryUtil")).error("Complete Derpness Happened that should never have been able to have happened", permissionCreationError);
            }
        }
    }

    public ArrayList<Permission> getAllMinecraftPermissions() {
        return Lists.newArrayList(permissions);
    }

    protected Permission createPermission(String perm) {
        try {
            Permission ret = tracker.getPerm(perm);
            permissions.add(ret);
            return ret;
        }
        catch (PermissionCreationError permissionCreationError) {
            getLoggerFactory().getSubLogger("MinecraftPermission", getLoggerFactory().getLogger("CanaryUtil")).error("Failed to Create Permission", permissionCreationError);
        }
        return null;
    }

    private EELoggerFactory getLoggerFactory() {
        return FactoryManager.getFactoryManager().getEELoggerFactory();
    }

}
