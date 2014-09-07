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

import net.canarymod.chat.MessageReceiver;

import java.util.List;

public interface CommandExecute {

    /**
     * The Method that is used to process the use of a command
     *
     * @param caller     Who used the command.
     * @param parameters What was contained in the command
     */
    public void execute(MessageReceiver caller, String[] parameters);

    /**
     * Called when a AutoComplete is asked for
     *
     * @param messageReceiver the {@link net.canarymod.chat.MessageReceiver} using tabComplete
     * @param args            the current arguments of the command
     *
     * @return list of possible completions
     */
    public List<String> tabComplete(MessageReceiver messageReceiver, String[] args);

}
