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

import java.util.ArrayList;

/**
 * @author Larry1123
 * @since 2/7/14 - 3:44 AM
 */
public class UtilPermission {

    public final Permission REPAIR;
    public final Permission REPAIR$ALL;
    public final Permission REPAIR$FREE;
    public final Permission REPAIR$OTHER;

    private final PermissionTracker tracker;
    private final ArrayList<Permission> permissions = new ArrayList<Permission>();


    public UtilPermission(PermissionTracker tracker) {
        this.tracker = tracker;

        REPAIR = createPermission("canaryUtil.commands.repair");
        REPAIR$ALL = createPermission("canaryUtil.commands.repair.all");
        REPAIR$FREE = createPermission("canaryUtil.commands.repair.free");
        REPAIR$OTHER = createPermission("canaryUtil.commands.repair.other");
    }

    public ArrayList<Permission> getAllUtilPermissions() {
        return Lists.newArrayList(permissions);
    }

    private Permission createPermission(String perm) {
        Permission ret = tracker.getPerm(perm);
        permissions.add(ret);
        return ret;
    }

}
