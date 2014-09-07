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

import net.canarymod.commandsys.Command;

import java.lang.annotation.Annotation;

public class CommandCommand implements Command {

    public final String[] aliases;
    public final String[] permissions;
    public final String description;
    public final String toolTip;
    public final String parent;
    public final String helpLookup;
    public final String[] searchTerms;
    public final int min;
    public final int max;

    CommandCommand(CommandData data) {
        String[] aliases = new String[data.getAliases().length + 1];
        int index = 0;
        for (String alias : data.getAliases()) {
            aliases[index++] = alias;
        }
        aliases[index] = "" + data.getCommandUID();
        this.aliases = aliases;
        this.permissions = data.getPermissions();
        this.description = data.getDescription();
        this.toolTip = data.getToolTip();
        this.parent = data.getParent();
        this.helpLookup = data.getHelpLookup();
        this.searchTerms = data.getSearchTerms();
        this.min = data.getMin();
        this.max = data.getMax();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends Annotation> annotationType() {
        return Command.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] aliases() {
        return this.aliases;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] permissions() {
        return this.permissions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String description() {
        return this.description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toolTip() {
        return this.toolTip;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String parent() {
        return this.parent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String helpLookup() {
        return this.helpLookup;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] searchTerms() {
        return this.searchTerms;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int min() {
        return this.min;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int max() {
        return this.max;
    }

    @Override
    public String tabCompleteMethod() {
        return null;
    }

}
