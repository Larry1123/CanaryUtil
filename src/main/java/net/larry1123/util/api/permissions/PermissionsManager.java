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
    private final HashMap<RemoteServer, CanaryPermission> canaryPermissionHashMap = new HashMap<RemoteServer, CanaryPermission>();
    private final HashMap<RemoteServer, UtilPermission> utilPermissionHashMap = new HashMap<RemoteServer, UtilPermission>();

    private PermissionsManager() {}

    public static PermissionsManager getManager() {
        return globalManager;
    }

    /**
     * Gets the PermissionTracker for the local server
     */
    public PermissionTracker getPermissionTracker() {
        return getPermissionTracker(getLocalRemoteServer());
    }

    public PermissionTracker getPermissionTracker(RemoteServer remoteServer) {
        if (!Trackers.containsKey(remoteServer)) {
            Trackers.put(remoteServer, new PermissionTracker(remoteServer));
        }
        return Trackers.get(remoteServer);
    }

    public CanaryPermission getCanaryPermissions() {
        return getCanaryPermissions(getLocalRemoteServer());
    }

    public CanaryPermission getCanaryPermissions(RemoteServer remoteServer) {
        if (!canaryPermissionHashMap.containsKey(remoteServer)) {
            canaryPermissionHashMap.put(remoteServer, new CanaryPermission(getPermissionTracker(remoteServer)));
        }
        return canaryPermissionHashMap.get(remoteServer);
    }

    public UtilPermission getUtilPermissions() {
        return getUtilPermissions(getLocalRemoteServer());
    }

    public UtilPermission getUtilPermissions(RemoteServer remoteServer) {
        if (!utilPermissionHashMap.containsKey(remoteServer)) {
            utilPermissionHashMap.put(remoteServer, new UtilPermission(getPermissionTracker(remoteServer)));
        }
        return utilPermissionHashMap.get(remoteServer);
    }

    @Deprecated
    public MinecraftPermission getMinecraftPermission() {
        return new MinecraftPermission(getPermissionTracker());
    }

    @Deprecated
    public MinecraftPermission getMinecraftPermission(RemoteServer remoteServer) {
        return new MinecraftPermission(getPermissionTracker(remoteServer));
    }

    protected RemoteServer getLocalRemoteServer() {return CanaryUtil.getCustomPacket().getBungeeCord().getCurrentServer();}

}
