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
package net.larry1123.util.customPacket;

import net.larry1123.util.config.UtilConfigManager;
import net.larry1123.util.task.UpdateBungeeInfo;

public class CustomPacket {

    /**
     * Config Manager
     */
    private static final UtilConfigManager config = UtilConfigManager.getConfig();
    /**
     * The currently running BungeeCord manager
     */
    private BungeeCord bungeecord;

    public CustomPacket() {
        if (config.getBungeeCordConfig().isEnabled()) {
            bungeecord = new BungeeCord();
            UpdateBungeeInfo.startUpdater();
        }
        else {
            new BungeeCordless();
        }
    }

    /**
     * Gets the Currently running BungeeCord manager, may be the online or offline version
     *
     * @return Current BungeeCord Manager
     */
    public BungeeCord getBungeeCord() {
        return bungeecord;
    }

    /**
     * Will start BungeeCord functions if the config allows or stops BungeeCord functions if running if needed to be
     */
    public void reloadBungeeCord() {
        if (config.getBungeeCordConfig().isEnabled()) {
            if (bungeecord instanceof BungeeCordless) {
                bungeecord = new BungeeCord();
            }
            UpdateBungeeInfo.reloadUpdater();
        }
        else {
            if (!(bungeecord instanceof BungeeCordless)) {
                new BungeeCordless();
            }
            UpdateBungeeInfo.endUpdater();
        }
    }

}
