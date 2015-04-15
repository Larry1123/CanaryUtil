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
package net.larry1123.util.task;

import net.canarymod.tasks.ServerTask;
import net.canarymod.tasks.ServerTaskManager;
import net.canarymod.tasks.TaskOwner;
import net.larry1123.elec.util.factorys.EELoggerFactory;
import net.larry1123.elec.util.factorys.FactoryManager;
import net.larry1123.elec.util.logger.FileManager;
import net.larry1123.util.CanaryUtil;
import net.larry1123.util.api.task.TaskHandler;
import net.larry1123.util.config.LoggerConfig;
import net.larry1123.util.config.UtilConfigManager;
import org.apache.commons.lang3.time.DateUtils;

import java.io.IOException;

public class FileSplittingUpdater implements TaskHandler {

    public class FileSplitTask extends ServerTask {

        protected FileSplitTask(TaskOwner owner, long delay) {
            this(owner, delay, true);
        }

        protected FileSplitTask(TaskOwner owner, long delay, boolean continuous) {
            super(owner, delay, continuous);
        }

        @Override
        public void run() {
            if (isSplitting()) {
                if (isNotCurrent()) {
                    try {
                        getFileManager().updateFileHandlers();
                    }
                    catch (IOException e) {
                        getPlugin().getLogger("Log Updater").error("Unable to update all or any log files", e);
                    }
                }
            }
            else {
                if (hasCurrentSplit()) {
                    getLoggerConfig().setCurrentSplit(0);
                }
            }
        }

    }

    /**
     * Current Updater
     */
    protected FileSplitTask task = null;
    protected final CanaryUtil plugin;

    public FileSplittingUpdater(CanaryUtil plugin) {
        this.plugin = plugin;
    }

    /**
     * Starts the updater polling if the config will allow
     */
    @Override
    public boolean startTask() {
        if (isSplitting()) {
            if (task == null) {
                task = new FileSplitTask(getPlugin(), DateUtils.MILLIS_PER_HOUR);
                ServerTaskManager.addTask(task);
            }
            return true;
        }
        return false;
    }

    /**
     * Stops the updater polling
     */
    @Override
    public void endTask() {
        if (task != null) {
            ServerTaskManager.removeTask(task);
            task = null;
        }
    }

    /**
     * Will start the updater if the config allows or stops the updater if running and needed to be
     */
    @Override
    public boolean reloadTask() {
        endTask();
        return startTask();
    }

    protected boolean isSplitting() {
        return getFileManager().isSplitting();
    }

    protected boolean hasCurrentSplit() {
        return getFileManager().hasCurrentSplit();
    }

    protected boolean isNotCurrent() {
        return getFileManager().isNotCurrent();
    }

    protected EELoggerFactory getLoggerFactory() {
        return FactoryManager.getFactoryManager().getEELoggerFactory();
    }

    protected FileManager getFileManager() {
        return getLoggerFactory().getFileManager();
    }

    protected LoggerConfig getLoggerConfig() {
        return UtilConfigManager.getConfig().getLoggerConfig();
    }

    protected CanaryUtil getPlugin() {
        return plugin;
    }

}
