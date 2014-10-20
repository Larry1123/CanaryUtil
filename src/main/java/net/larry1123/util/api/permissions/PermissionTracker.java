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

import net.larry1123.util.api.abstracts.RemoteServer;

import java.util.HashMap;

public class PermissionTracker {

    protected final RemoteServer remoteServer;
    final HashMap<String, Permission> Permissions = new HashMap<String, Permission>();

    /**
     * TODO
     *
     * @param server This is for BungeeCord stuffs
     */
    public PermissionTracker(RemoteServer server) {
        remoteServer = server;
    }

    /**
     * Makes Root Perm or returns a all ready created one for the given path
     * This will be the only way to create a root perm.
     * All subPerms may only be made under a root perm.
     *
     * @param path
     *
     * @return
     */
    public Permission createRootPerm(String path) throws PermissionCreationError {
        Permission parent = getParentByPath(path);
        if (parent == null) {
            if (!getPermissions().containsKey(path)) {
                getPermissions().put(path, new Permission(this, path));
            }
            return getPermissions().get(path);
        }
        else {
            if (parent.getRootPerm().getPermissionString().equals(path)) {
                return parent.getRootPerm();
            }
            throw new PermissionCreationError("Can not create root Perm given path has a Root as it is.");
        }
    }

    /**
     * Will return the Root Perm for the given Path
     * Will return null if there is no root perm made for the given path
     *
     * @param path
     *
     * @return
     */
    public Permission getRootByPath(String path) {
        Permission parent = getParentByPath(path);
        if (parent != null) {
            return parent.getRootPerm();
        }
        else {
            return null;
        }
    }

    /**
     * Will get a perm or create one under parent or root.
     * If one is not found it will create a perm for the given path
     * Will not create a root Perm
     *
     * @param path
     *
     * @return
     *
     * @throws PermissionCreationError
     */
    public Permission getPerm(String path) throws PermissionCreationError {
        if (!Permissions.containsKey(path)) {
            Permission rootPerm = null;
            for (String permPath : getPermissions().keySet()) {
                if (path.contains(permPath)) {
                    // We found there there is a root or parent
                    Permission perm = getPermissions().get(permPath);
                    // But we only care if it is a root
                    if (perm.isRootPermission()) {
                        rootPerm = perm;
                        break;
                        // No need to keep going if we found it
                    }
                }
            }
            if (rootPerm == null) {
                // No root to create Perm from!!!!!
                throw new PermissionCreationError("No root permission found for permission");
            }
            Permission parentPerm = getParentByPath(path);
            if (parentPerm == null) {
                // No Parent to create Perm from!!!!!
                throw new PermissionCreationError("No parent permission found for permission");
            }
            String subPath = path.substring(path.lastIndexOf(parentPerm.getPermissionString()) + 1);
            getPermissions().put(path, new Permission(this, parentPerm, subPath));
        }
        return getPermissions().get(path);
    }

    /**
     * Will return a subPermission of the given parent Permission
     * Will fail if the Root or Parent permission was removed.
     *
     * @param parentPerm
     * @param subPath
     *
     * @return
     *
     * @throws PermissionCreationError
     */
    public Permission getPerm(Permission parentPerm, String subPath) throws PermissionCreationError {
        String path = parentPerm.getPermissionString() + "." + subPath;
        if (!getPermissions().containsKey(path)) {
            getPerm(path);
        }
        return getPermissions().get(path);
    }

    /**
     * Removes the given Permission path from the tracker if is it tracked.
     *
     * @param perm The full path for the Permission to remove
     */
    public void removePerm(String perm) {
        if (getPermissions().get(perm) == null) { return; }
        removePerm(getPermissions().get(perm));
    }

    /**
     * Removes the given Permission from the tracker if it is tracked
     *
     * @param perm The Permission object to remove
     */
    public void removePerm(Permission perm) {
        if (getPermissions().containsValue(perm)) {
            if (perm.isRootPermission()) {
                for (Permission subPerm : perm.getChildPermissions()) {
                    removePerm(subPerm);
                }
            }
            getPermissions().remove(perm.getPermissionString());
        }
    }

    /**
     * Will find out if a root has been made for this tracker yet
     *
     * @param root what root to check
     *
     * @return {@code true} if the root exist; {@code false} if the root does not exist
     */
    public boolean doesRootExist(String root) {
        return getRootByPath(root) != null;
    }

    public RemoteServer getRemoteServer() {
        return remoteServer;
    }

    protected Permission getParentByPath(String path) {
        Permission ret = null;
        for (Permission perm : getPermissions().values()) {
            // Lets weed out childs of this permission since they can not be the parent
            if (!perm.getPermissionString().contains(path)) {
                // Now lets see everything that is in the parent chain
                if (path.contains(perm.getPermissionString())) {
                    if (ret != null && ret != perm) {
                        if (perm.getPermissionString().contains(ret.getPermissionString())) {
                            ret = perm;
                        }
                    }
                    else {
                        ret = perm;
                    }
                }
            }
        }
        return ret;
    }

    protected HashMap<String, Permission> getPermissions() {
        return Permissions;
    }

}
