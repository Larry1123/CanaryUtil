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

import java.io.Serializable;

/**
 * @author Larry1123
 * @since 10/22/2014 - 7:27 AM
 */
public class PermissionNode implements Comparable<PermissionNode>, Serializable, Cloneable {

    protected final String fullPath;

    public PermissionNode(String fullPath) {
        this.fullPath = fullPath;
    }

    public PermissionNode(Permission permission) {
        fullPath = permission.getPermissionString();
    }

    public PermissionNode(PermissionNode permissionNode) {
        fullPath = permissionNode.getFullPath();
    }

    /**
     * Gets the full path of the permission
     */
    public String getFullPath() {
        return fullPath;
    }

    /**
     * Gets just the name of the permission
     */
    public String getName() {
        return isRoot() ? getFullPath() : isWildCard() ? getParent().getName() + ".*" : getFullPath().substring(getIndexOfLastDot() + 1);
    }

    /**
     * Checks to see if it is a root permission or not
     */
    public boolean isRoot() {
        // TODO look farther into this
        return !getFullPath().contains(".");
    }

    /**
     * Checks to see if this Node is a WildCard permission
     */
    public boolean isWildCard() {
        return getFullPath().substring(getIndexOfLastDot() + 1).equals("*");
    }

    /**
     * Checks if the passed permission is a child permission of this one
     * <code>canary.derp.herp</code>
     * This is a child of
     * <code>canary.derp</code>
     * and that would be the parent of
     * <code>canary.*</code>
     */
    public boolean isParentOf(PermissionNode permissionNode) {
        return permissionNode.getFullPath().contains((isWildCard() ? isRoot() ? "" : getParent().getFullPath() + "." : getFullPath() + "."));
    }

    public boolean isChildOf(PermissionNode permissionNode) {
        return permissionNode.isParentOf(this);
    }

    /**
     * Will Return the Parent of this Permission.
     * Returns null if this Permission is a root.
     */
    public PermissionNode getParent() {
        return isRoot() ? null : new PermissionNode(getFullPath().substring(0, getIndexOfLastDot()));
    }

    /**
     * Will return the root
     * <code>canary.derp.herp</code>
     * would return
     * <code>canary</code>
     */
    public PermissionNode getRoot() {
        PermissionNode ret = new PermissionNode(this);
        while (ret.getParent() != null) {
            ret = ret.getParent();
        }
        return ret;
    }

    /**
     * Checks to see if the passed permission has the same parent as the current permissions
     */
    public boolean isSiblingOf(PermissionNode permissionNode) {
        if (isRoot() || permissionNode.isRoot()) { return isRoot() && permissionNode.isRoot(); }
        return getParent().equals(permissionNode.getParent());
    }

    /**
     * Returns a {@link PermissionNode} of the lower common Node
     * <code>canary.command.warp.use</code>
     * and
     * <code>canary.command.give.other</code>
     * would have
     * <code>canary.command</code>
     * returned
     */
    public PermissionNode getLowestCommonNode(PermissionNode permissionNode) {
        // Remove Root so we only have stuff with dots
        if (isRoot()) {
            return permissionNode.isRoot() ? equals(permissionNode) ? new PermissionNode(getName()) : null : null;
        }
        // Hey if we can save some time lets do so
        if (isSiblingOf(permissionNode)) { return getParent(); }
        PermissionNode ret = null;
        int dotLocation = 0;
        int min = Math.min(getFullPath().length(), permissionNode.getFullPath().length());
        while ((dotLocation = getIndexOfNextDot(dotLocation)) != -1 && min - dotLocation > 0) {
            if (getToIndex(dotLocation).equals(permissionNode.getToIndex(dotLocation))) {
                ret = new PermissionNode(getToIndex(dotLocation));
                continue;
            }
            break;
        }
        return ret;
    }

    @Override
    public int compareTo(PermissionNode permissionNode) {
        return getFullPath().compareTo(permissionNode.getFullPath());
    }

    @Override
    public int hashCode() {
        return getFullPath().hashCode();
    }

    @Override
    public boolean equals(Object object) {
        return (object instanceof PermissionNode && (((PermissionNode) object).getFullPath().equals(getFullPath()))) || object.equals(getFullPath());
    }

    @Override
    public PermissionNode clone() throws CloneNotSupportedException {
        try {
            return (PermissionNode) super.clone();
        }
        catch (CloneNotSupportedException e) {
            return new PermissionNode(this);
        }
    }

    @Override
    public String toString() {
        return getFullPath();
    }

    protected String getToIndex(int index) {
        return getFullPath().substring(0, index);
    }

    protected int getIndexOfLastDot() {
        return isRoot() ? -1 : getFullPath().lastIndexOf('.');
    }

    protected int getIndexOfNextDot(int lastDot) {
        return isRoot() ? -1 : getFullPath().indexOf('.', lastDot + 1);
    }

}
