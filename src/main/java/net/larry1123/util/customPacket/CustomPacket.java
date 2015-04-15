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

import net.larry1123.util.CanaryUtil;
import net.larry1123.util.api.abstracts.BungeeCord;
import net.larry1123.util.config.BungeeCordConfig;
import net.larry1123.util.config.UtilConfigManager;
import net.larry1123.util.task.UpdateBungeeInfo;

public class CustomPacket {

    protected BungeeCord bungeeCord;
    protected final CanaryUtil plugin;
    protected final UpdateBungeeInfo updateBungeeInfo;

    public CustomPacket(CanaryUtil plugin) {
        this.plugin = plugin;
        updateBungeeInfo = new UpdateBungeeInfo(this.plugin);
        if (getBungeeCordConfig().isEnabled()) {
            setBungeeCord(new BungeeCordHandler(getPlugin()));
            updateBungeeInfo.startTask();
        }
        else {
            setBungeeCord(new BungeeCordless());
        }
    }

    /**
     * Gets the Currently running BungeeCord manager, may be the online or offline version
     *
     * @return Current BungeeCord Manager
     */
    public BungeeCord getBungeeCord() {
        return bungeeCord;
    }

    /**
     * Will start BungeeCord functions if the config allows or stops BungeeCord functions if running if needed to be
     */
    public void reloadBungeeCord() {
        if (getBungeeCordConfig().isEnabled()) {
            if (getBungeeCord() != null && getBungeeCord() instanceof BungeeCordHandler) {
                ((BungeeCordHandler) getBungeeCord()).unregChannelListener();
            }
            setBungeeCord(new BungeeCordHandler(getPlugin()));
            updateBungeeInfo.reloadTask();
        }
        else {
            if (getBungeeCord() == null || getBungeeCord() instanceof BungeeCordHandler) {
                setBungeeCord(new BungeeCordless());
            }
            updateBungeeInfo.endTask();
        }
    }

    protected void setBungeeCord(BungeeCord bungeeCord) {
        this.bungeeCord = bungeeCord;
    }

    protected BungeeCordConfig getBungeeCordConfig() {
        return UtilConfigManager.getConfig().getBungeeCordConfig();
    }

    protected CanaryUtil getPlugin() {
        return plugin;
    }

}
