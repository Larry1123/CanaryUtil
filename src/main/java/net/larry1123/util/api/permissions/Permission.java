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

import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.blocks.CommandBlock;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.user.Group;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * This is not a New Permission system this is only a tracker of used Permission Nodes
 */
public class Permission {

    protected final PermissionTracker permissionTracker;

    // The full node path for this Permission
    protected final String path;
    // If this is a Root Perm or not
    protected final boolean root;
    // Should all ways be set even if it is the root
    protected final Permission rootPerm;

    /**
     * Used only for creating a root perm
     *
     * @param tracker What tracker is tracking this root
     * @param path    The root path
     */
    public Permission(PermissionTracker tracker, String path) {
        permissionTracker = tracker;
        this.path = path;
        root = true;
        rootPerm = this;
    }

    public Permission(PermissionTracker tracker, Permission parentPerm, String subPath) {
        permissionTracker = tracker;
        this.path = parentPerm.getPermissionString() + "." + subPath;
        rootPerm = parentPerm.getRootPerm();
        root = false;
    }

    /**
     * Get the string value for this {@link net.larry1123.util.api.permissions.Permission}
     *
     * @return returns the string value
     */
    public String getPermissionString() {
        return path;
    }

    /**
     * Will return if this Permission is a root perm
     *
     * @return true if it is a root perm, false if it is not
     */
    public boolean isRootPermission() {
        return root;
    }

    /**
     * Will Return the Parent of this Permission.
     * Returns null if this Permission is a root.
     * Also Returns null if something has gone derp and we can't find a parent
     *
     * @return
     */
    public Permission getParentPerm() {
        if (isRootPermission()) {
            return null;
        }
        Permission ret = null;
        for (Permission perm : rootPerm.getChildPermissions()) {
            // Lets not return that something is it's own parent
            if (perm != this) {
                ret = getParentByPath(getPermissionString());
            }
        }
        return ret;
    }

    public Permission getRootPerm() {
        return rootPerm;
    }

    /**
     * Tells if this Permission has any Child Permission
     *
     * @return {@code true} if it has sub Permissions; {@code false} if it does not have any sub Permissions
     */
    public boolean isParent() {
        return getChildPermissions().size() != 0;
    }

    public boolean isParentOf(Permission permission) {
        return getChildPermissions().contains(permission);
    }

    /**
     * Returns a list of child Permissions
     *
     * @return returns all Permissions that's path contain this Permission's path
     */
    public LinkedList<Permission> getChildPermissions() {
        LinkedList<Permission> ret = new LinkedList<Permission>();
        for (String perm : getPermissions().keySet()) {
            if (perm.startsWith(getPermissionString())) {
                Permission permission = getPermissions().get(perm);
                // Would hope that Permissions.get(perm) will not be null ever but to be safe
                if (permission != this && permission != null) {
                    ret.add(permission);
                }
            }
        }
        return ret;
    }

    /**
     * Checks if the player has this permission
     *
     * @param player The {@link net.canarymod.api.entity.living.humanoid.Player} to check if it has Permission of
     *
     * @return {@code true} if the given {@link net.canarymod.api.entity.living.humanoid.Player} has this Permission
     */
    public boolean hasPermission(Player player) {
        return player.isAdmin() || player.canIgnoreRestrictions() || player.hasPermission(getPermissionString());
    }

    /**
     * Gives the player this permission with the fieldValue true
     *
     * @param player What player to give this permission to
     */
    public void givePermission(Player player) {
        givePermission(player, true);
    }

    /**
     * Gives the player this permission with the given fieldValue
     *
     * @param player What player to give this permission to
     * @param value  pass false if this perm should take and not give
     */
    public void givePermission(Player player, boolean value) {
        player.getPermissionProvider().addPermission(getPermissionString(), value);
    }

    /**
     * Checks to see if the passed {@link net.canarymod.user.Group} has this permission
     *
     * @param group What to check if it has Permission of
     *
     * @return {@code true} if the given {@link net.canarymod.user.Group} has this Permission
     */
    public boolean hasPermission(Group group) {
        return group.isAdministratorGroup() || group.canIgnorerestrictions() || group.hasPermission(getPermissionString());
    }

    /**
     * Checks to see if the passed {@link net.canarymod.chat.MessageReceiver} has this permission
     *
     * @param caller What to check if it has Permission of
     *
     * @return {@code true} if the given {@link net.canarymod.chat.MessageReceiver} has this Permission
     */
    public boolean hasPermission(MessageReceiver caller) {
        if (caller instanceof Server) { return true; }
        if (caller instanceof Player) { return hasPermission(caller); }
        if (caller instanceof CommandBlock) { return hasPermission(((CommandBlock) caller).getGroup()); }
        return caller.hasPermission(getPermissionString());
    }

    /**
     * Gives the group this permission with the fieldValue true
     *
     * @param group What group to give this permission to
     */
    public void givePermission(Group group) {
        givePermission(group, true);
    }

    /**
     * Gives the group this permission with the given fieldValue
     *
     * @param group What group to give this permission to
     * @param value pass false if this perm should take and not give
     */
    public void givePermission(Group group, boolean value) {
        group.getPermissionProvider().addPermission(getPermissionString(), value);
    }

    /**
     * Gets the {@link net.larry1123.util.api.permissions.PermissionTracker} that is tracking this {@link net.larry1123.util.api.permissions.Permission}
     *
     * @return Returns the tracking {@link net.larry1123.util.api.permissions.PermissionTracker}
     */
    public PermissionTracker getTrackingPermissionTracker() {
        return permissionTracker;
    }

    /**
     * TODO doc this
     *
     * @param path The path to get the parent of
     *
     * @return Returns the {@link net.larry1123.util.api.permissions.Permission}
     */
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
        return permissionTracker.getPermissions();
    }

    /**
     * Returns the Permission String
     *
     * @return provides the string value for this Permission
     */
    @Override
    public String toString() {
        return getPermissionString();
    }

}

