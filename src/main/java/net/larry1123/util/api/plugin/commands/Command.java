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

import net.visualillusionsent.utils.LocaleHelper;

public interface Command extends CommandExecute {

    /**
     * The Command's info pack
     *
     * @return The CommandData holding the commands data
     */
    public CommandData getCommandData();

    /**
     * @return localehelper for translating meta info
     */
    public LocaleHelper getTranslator();

    /**
     * Tells if this Command will override a command that is registered
     *
     * @return If this Command will override an other command
     */
    public boolean isForced();

    /**
     * If the Command has been registered or not
     *
     * @return true if command is registered, false otherwise
     */
    public boolean isLoaded();

    /**
     * Sets if the command is registered or not
     *
     * @param loadedness
     */
    public void setLoaded(boolean loadedness);

}
