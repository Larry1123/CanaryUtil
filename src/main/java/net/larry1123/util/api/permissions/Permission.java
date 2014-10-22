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
public class Permission implements Comparable<Permission> {

    protected final PermissionTracker permissionTracker;

    protected final PermissionNode permissionNode;

    /**
     * Used only for creating a root perm
     *
     * @param tracker What tracker is tracking this root
     * @param node    The Node for this Permission
     */
    public Permission(PermissionTracker tracker, PermissionNode node) {
        permissionTracker = tracker;
        permissionNode = node;
    }

    /**
     * Get the string value for this {@link net.larry1123.util.api.permissions.Permission}
     *
     * @return returns the string value
     */
    public String getPermissionString() {
        return getPermissionNode().getFullPath();
    }

    /**
     * Will return if this Permission is a root perm
     *
     * @return true if it is a root perm, false if it is not
     */
    public boolean isRootPermission() {
        return getPermissionNode().isRoot();
    }

    /**
     * Will Return the Parent of this Permission.
     * Returns null if this Permission is a root.
     * Also Returns null if something has gone derp and we can't find a parent
     */
    public Permission getParentPerm() {
        return isRootPermission() ? null : getTrackingPermissionTracker().getPerm(getPermissionNode().getParent());
    }

    public Permission getRootPerm() {
        return getTrackingPermissionTracker().getPerm(getPermissionNode().getRoot());
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
        return toPermissionNode().isParentOf(permission.toPermissionNode());
    }

    /**
     * Returns a list of child Permissions
     *
     * @return returns all Permissions that's path contain this Permission's path
     */
    public LinkedList<Permission> getChildPermissions() {
        LinkedList<Permission> ret = new LinkedList<Permission>();
        for (Permission perm : getPermissions().values()) {
            if (isParentOf(perm)) {
                if (perm != this && perm != null) {
                    ret.add(perm);
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

    protected HashMap<PermissionNode, Permission> getPermissions() {
        return permissionTracker.getPermissions();
    }

    public PermissionNode toPermissionNode() {
        return new PermissionNode(getPermissionNode());
    }

    protected PermissionNode getPermissionNode() {
        return permissionNode;
    }

    @Override
    public int hashCode() {
        return getPermissionNode().hashCode();
    }

    @Override
    public String toString() {
        return getPermissionString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Permission permission) {
        return toPermissionNode().compareTo(permission.toPermissionNode());
    }

}

