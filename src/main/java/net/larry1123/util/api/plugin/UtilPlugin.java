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
package net.larry1123.util.api.plugin;

import net.canarymod.logger.Logman;
import net.canarymod.plugin.Plugin;
import net.larry1123.elec.util.factorys.FactoryManager;
import net.larry1123.elec.util.logger.EELogger;

public abstract class UtilPlugin extends Plugin {

    protected final FactoryManager factoryManager = FactoryManager.getFactoryManager();
    private final EELogger logger = factoryManager.getEELoggerFactory().getLogger(getName());
    private final EELogman loggerWrap = new EELogman(logger, super.getLogman());
    protected final String defultLoggerPath = getLogger().path;

    /**
     * Retrieves a SubLogger
     *
     * @param name Name to name the subLogger
     *
     * @return The SubLogger
     */
    public EELogger getSubLogger(String name) {
        return factoryManager.getEELoggerFactory().getSubLogger(name, getLogger());
    }

    /**
     * Logs that this Plugin failed to start
     */
    public void enableFailed() {
        getLogger().logSevere("Plugin Could not be Enabled!");
    }

    /**
     * Logs that this Plugin failed to start and why
     *
     * @param reason The Reason that the plugin failed to start
     */
    public void enableFailed(String reason) {
        getLogger().logSevere("Plugin Could not be Enabled, because" + reason);
    }

    /**
     * Gets the EELogger of the current Plugin
     *
     * @return This Plugins EELogger
     */
    public EELogger getLogger() {
        return logger;
    }

    /**
     * {@inheritDoc}
     * <p/>
     * The Logman returned is a wrapper of EELogger
     */
    @Override
    public Logman getLogman() {
        return loggerWrap;
    }

}
