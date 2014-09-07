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
import net.larry1123.elec.util.logger.FileManager;
import net.larry1123.elec.util.logger.FileSplits;
import net.larry1123.util.config.LoggerConfig;
import net.larry1123.util.config.UtilConfigManager;
import org.apache.commons.lang3.time.DateUtils;

import static net.larry1123.util.CanaryUtil.getPlugin;

public class FileSpliterUpdater extends ServerTask {

    private static final LoggerConfig config = UtilConfigManager.getConfig().getLoggerConfig();
    /**
     * Current Updater
     */
    private static FileSpliterUpdater ticksystem = null;

    private FileSpliterUpdater(TaskOwner owner, long delay) {
        this(owner, delay, true);
    }

    private FileSpliterUpdater(TaskOwner owner, long delay, boolean continuous) {
        super(owner, delay, continuous);
    }

    /**
     * Starts the updater polling if the config will allow
     */
    public static void startUpdater() {
        if (isSplitng() && (getPlugin() != null)) {
            if (ticksystem == null) {
                ticksystem = new FileSpliterUpdater(getPlugin(), DateUtils.MILLIS_PER_HOUR);
                ServerTaskManager.addTask(ticksystem);
            }
        }
    }

    /**
     * Stops the updater polling
     */
    public static void endUpdater() {
        if (ticksystem != null) {
            ServerTaskManager.removeTask(ticksystem);
            ticksystem = null;
        }
    }

    /**
     * Will start the updater if the config allows or stops the updater if running and needed to be
     */
    public static void reloadUpdater() {
        if (isSplitng()) {
            endUpdater();
            startUpdater();
        }
        else {
            endUpdater();
        }
    }

    private static boolean isSplitng() {
        return !config.getSplit().getValue().toLowerCase().equals(FileSplits.NONE.getValue().toLowerCase());
    }

    private static boolean hasCurrentSplit() {
        return !(config.getCurrentSplit().equals(null) || config.getCurrentSplit().equals(""));
    }

    private static boolean isNotCurrent() {
        if (hasCurrentSplit()) {
            return !config.getCurrentSplit().equals(FileManager.dateTime());
        }
        else {
            return !false;
        }
    }

    @Override
    public void run() {
        if (isSplitng()) {
            if (isNotCurrent()) {
                FileManager.updateFileHandlers();
            }
        }
        else {
            if (hasCurrentSplit()) {
                config.setCurrentSplit("");
            }
        }
    }

}
