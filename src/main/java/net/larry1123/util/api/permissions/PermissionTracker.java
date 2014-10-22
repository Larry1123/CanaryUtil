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
    protected final HashMap<PermissionNode, Permission> Permissions = new HashMap<PermissionNode, Permission>();

    /**
     * TODO
     *
     * @param server This is for BungeeCord stuffs
     */
    public PermissionTracker(RemoteServer server) {
        remoteServer = server;
    }

    /**
     * Will return the Root Perm for the given Path
     * Will return null if there is no root perm made for the given path
     */
    public Permission getRootByPath(String path) {
        return getPermissions().get(new PermissionNode(path).getRoot());

    }

    /**
     * Will get a perm or create one under parent or root.
     * If one is not found it will create a perm for the given path
     * Will not create a root Perm
     */
    public Permission getPerm(String path) {
        return getPerm(new PermissionNode(path));
    }

    public Permission getPerm(PermissionNode node) {
        PermissionNode temp = node;
        do {
            if (!getPermissions().containsKey(temp)) {
                getPermissions().put(temp, new Permission(this, temp));
            }
        }
        while ((temp = temp.getParent()) != null);
        return getPermissions().get(node);
    }

    /**
     * Removes the given Permission path from the tracker if is it tracked.
     *
     * @param perm The full path for the Permission to remove
     */
    public void removePerm(String perm) {
        removePerm(new PermissionNode(perm));
    }

    /**
     * Removes the given Permission from the tracker if it is tracked
     *
     * @param perm The Permission object to remove
     */
    public void removePerm(Permission perm) {
        removePerm(perm.permissionNode);
    }

    public void removePerm(PermissionNode node) {
        if (getPermissions().containsKey(node)) {
            getPermissions().remove(node);
        }
    }

    public RemoteServer getRemoteServer() {
        return remoteServer;
    }

    protected HashMap<PermissionNode, Permission> getPermissions() {
        return Permissions;
    }

}
