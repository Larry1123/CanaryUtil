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

import net.larry1123.util.CanaryUtil;
import net.larry1123.util.api.abstracts.RemoteServer;

import java.util.HashMap;

/**
 * @author Larry1123
 * @since 1/26/14 - 6:34 AM
 */
public class PermissionsManager {

    private static final PermissionsManager globalManager = new PermissionsManager();

    private final HashMap<RemoteServer, PermissionTracker> Trackers = new HashMap<RemoteServer, PermissionTracker>();

    public static PermissionsManager getManager() {
        return globalManager;
    }

    /**
     * Gets the PermissionTracker for the local server
     *
     * @return
     */
    public PermissionTracker getPermissionTracker() {
        return getPermissionTracker(CanaryUtil.getCustomPacket().getBungeeCord().getCurrentServer());
    }

    public PermissionTracker getPermissionTracker(RemoteServer remoteServer) {
        if (!Trackers.containsKey(remoteServer)) {
            Trackers.put(remoteServer, new PermissionTracker(remoteServer));
        }
        return Trackers.get(remoteServer);
    }

    public CanaryPermission getCanaryPermissions() {
        return new CanaryPermission(getPermissionTracker());
    }

    public CanaryPermission getCanaryPermissions(RemoteServer remoteServer) {
        return new CanaryPermission(getPermissionTracker(remoteServer));
    }

    public MinecraftPermission getMinecraftPermission() {
        return new MinecraftPermission(getPermissionTracker());
    }

    public MinecraftPermission getMinecraftPermission(RemoteServer remoteServer) {
        return new MinecraftPermission(getPermissionTracker(remoteServer));
    }

}
