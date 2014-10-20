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
package net.larry1123.util.api.plugin.commands;

import java.util.UUID;

public class CommandData {

    protected final String[] aliases;
    protected final String[] permissions;
    protected final String description;
    protected final String toolTip;
    protected final UUID commandID = UUID.randomUUID();
    protected final int version;
    protected String parent = "";
    protected String helpLookup = "";
    protected String[] searchTerms = {" "};
    protected int min = 1;
    protected int max = -1;

    public CommandData(String[] aliases, String[] permissions, String description, String toolTip) {
        this.aliases = aliases;
        this.permissions = permissions;
        this.description = description;
        this.toolTip = toolTip;
        this.version = 1;
    }

    public CommandData(String[] aliases, String[] permissions, String description, String toolTip, int version) {
        this.aliases = aliases;
        this.permissions = permissions;
        this.description = description;
        this.toolTip = toolTip;
        this.version = version;
    }

    /**
     * Sets the Parent to a string
     *
     * @param parent Parent chain as String
     */
    public void setParent(String parent) {
        this.parent = parent;
    }

    /**
     * The parent command, for creating sub-command structures
     *
     * @return String of the Parent chain
     */
    public String getParent() {
        return parent;
    }

    /**
     * Sets the Parent to another command by CommandData UID + Parent Chain
     *
     * @param parent CommandData to set as parent
     */
    public void setParent(CommandData parent) {
        if (parent.getParent().equals("")) {
            setParent("" + parent.getCommandUID());
        }
        else {
            setParent(parent.getParent() + "." + parent.getCommandUID());
        }
    }

    /**
     * Explicitly define a name with which the command will be registered
     * at the help system. If this is empty (default), all aliases will be registered.
     * Otherwise only this name will be registered. <br>
     * Use it for registering sub-command helps to avoid name conflicts
     *
     * @return
     */
    public String getHelpLookup() {
        return helpLookup;
    }

    /**
     * xplicitly define a name with which the command will be registered
     * at the help system. If this is empty (default), all aliases will be registered.
     * Otherwise only this name will be registered. <br>
     * Use it for registering sub-command helps to avoid name conflicts
     *
     * @param helpLookup
     */
    public void setHelpLookup(String helpLookup) {
        this.helpLookup = helpLookup;
    }

    /**
     * Specifies specific terms for looking up this command in help search
     *
     * @return
     */
    public String[] getSearchTerms() {
        return searchTerms;
    }

    /**
     * Set specific terms for looking up this command in help search
     *
     * @param searchTerms String[] holding the Terms
     */
    public void setSearchTerms(String[] searchTerms) {
        this.searchTerms = searchTerms;
    }

    /**
     * Min amount of parameters
     *
     * @return
     */
    public int getMin() {
        return min;
    }

    /**
     * Set the min amount of parameters
     *
     * @param min Min amount of parameters
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * The max amounts of parameters.
     *
     * @return
     */
    public int getMax() {
        return max;
    }

    /**
     * Set the max amounts of parameters.
     *
     * @param max The max amounts of parameters. -1 for infinite amount
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * What does this command do?
     * This will be displayed in a help context.
     * Note: This string will be pushed through the translator that is attached to this command.
     * If it finds a respective translation, it will output that instead
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * A list of permissions to use this command.
     * If you specify more than one, only one of them is needed to execute the command
     *
     * @return
     */
    public String[] getPermissions() {
        return permissions;
    }

    /**
     * The command's names
     *
     * @return
     */
    public String[] getAliases() {
        return aliases;
    }

    /**
     * The tip to display when command parsing failed.
     * This may also be displayed when help for this command
     * was specifically requested
     *
     * @return
     */
    public String getToolTip() {
        return toolTip;
    }

    /**
     * TODO
     *
     * @return TODO
     */
    public int getVersion() {
        return version;
    }

    /**
     * Get the UUID of the Command
     *
     * @return The UUID for this Command
     */
    public UUID getCommandUID() {
        return commandID;
    }

}
