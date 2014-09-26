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
 * @since 2/7/14 - 3:44 AM
 */
public class UtilPermission {

    public final Permission RepairPermission = createPermission("canaryUtil.commands.repair");
    public final Permission RepairAllPermission = createPermission("canaryUtil.commands.repair.all");
    public final Permission RepairFreePermission = createPermission("canaryUtil.commands.repair.free");
    private final PermissionTracker tracker;
    private final ArrayList<Permission> permissions = new ArrayList<Permission>();
    private final ArrayList<String> rootPerms = new ArrayList<String>();


    public UtilPermission(PermissionTracker tracker) {
        this.tracker = tracker;
        for (String root : rootPerms) {
            if (this.tracker.getRootByPath(root) == null) {
                try {
                    this.tracker.createRootPerm(root);
                }
                catch (PermissionCreationError permissionCreationError) {
                    getLoggerFactory().getSubLogger("UtilPermission", getLoggerFactory().getLogger("CanaryUtil")).logStackTrace("Complete Derpness Happened that should never have been able to have happened\"", permissionCreationError);
                }
            }
        }
    }

    public ArrayList<Permission> getAllUtilPermissions() {
        return Lists.newArrayList(permissions);
    }

    private Permission createPermission(String perm) {
        rootPerms.add("canaryUtil");
        try {
            Permission ret = tracker.getPerm(perm);
            permissions.add(ret);
            return ret;
        }
        catch (PermissionCreationError permissionCreationError) {
            getLoggerFactory().getSubLogger("UtilPermission", getLoggerFactory().getLogger("CanaryUtil")).logStackTrace("Failed to Create Permission", permissionCreationError);
        }
        return null;
    }

    private EELoggerFactory getLoggerFactory() {
        return FactoryManager.getFactoryManager().getEELoggerFactory();
    }

}
