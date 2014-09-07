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
import net.larry1123.elec.util.factorys.FactoryManager;
import net.larry1123.elec.util.logger.EELogger;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;

/**
 * To be Refactored to work with log4j and still use the EELogger
 *
 * @author Larry1123
 * @since 11/8/13 - 2:26 AM
 */
public class EELogman extends Logman {

    private final EELogger logger;

    /**
     * NOT to be used!!
     *
     * @param name N/A
     */
    public EELogman(String name) {
        super(name);
        logger = FactoryManager.getFactoryManager().getEELoggerFactory().getLogger(name);
    }

    /**
     * Makes a wrapper for an EELogger to work as an Logman
     *
     * @param logger The logger to br wrapped
     */
    public EELogman(EELogger logger) {
        super(logger.getName());
        this.logger = logger;
    }

    /**
     * {@inheritDoc}
     */
    public void logInfo(String message) {
        getLogger().logInfo(message);
    }

    /**
     * {@inheritDoc}
     */
    public void logWarning(String message) {
        getLogger().logWarning(message);
    }

    /**
     * {@inheritDoc}
     */
    public void logSevere(String message) {
        getLogger().logSevere(message);
    }

    /**
     * {@inheritDoc}
     */
    public void logDebug(String message) {
        getLogger().logDebug(message);
    }

    /**
     * {@inheritDoc}
     */
    public void logDerp(String message) {
        getLogger().logDerp(message);
    }

    /**
     * {@inheritDoc}
     */
    public void logPluginDebug(String message) {
        getLogger().logPluginDebug(message);
    }

    /**
     * {@inheritDoc}
     */
    public void logStacktrace(String message, Throwable thrown) {
        getLogger().logStackTrace(message, thrown);
    }

    private EELogger getLogger() {
        return logger;
    }

}
