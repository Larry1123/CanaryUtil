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

    public final Permission BlockData;
    public final Permission Clone;
    public final Permission Fill;
    public final Permission Particle;
    public final Permission Seed;
    public final Permission SetIdleTimeOut;

    private final PermissionTracker tracker;
    private final ArrayList<Permission> permissions = new ArrayList<Permission>();

    public MinecraftPermission(PermissionTracker tracker) {
        this.tracker = tracker;

        BlockData = createPermission("canary.command.blockdata");
        Clone = createPermission("canary.command.clone");
        Fill = createPermission("canary.command.fill");
        Particle = createPermission("canary.command.particle");
        Seed = createPermission("canary.command.seed");
        SetIdleTimeOut = createPermission("canary.command.setidletimeout");
    }

    public ArrayList<Permission> getAllMinecraftPermissions() {
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
