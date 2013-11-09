package net.larry1123.util.task;

import net.canarymod.tasks.ServerTask;
import net.canarymod.tasks.ServerTaskManager;
import net.canarymod.tasks.TaskOwner;
import net.larry1123.util.config.LoggerConfig;
import net.larry1123.util.config.UtilConfigManager;
import net.larry1123.util.logger.FileManager;
import net.larry1123.util.logger.FileSplits;
import org.apache.commons.lang3.time.DateUtils;

import static net.larry1123.util.CanaryUtil.getPlugin;

public class FileSpliterUpdater extends ServerTask {

    private static final LoggerConfig config = UtilConfigManager.getConfig().getLoggerConfig();
    /**
     * Current Updater
     */
    private static FileSpliterUpdater ticksystem = null;

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
        } else {
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
        } else {
            return !false;
        }
    }

    private FileSpliterUpdater(TaskOwner owner, long delay) {
        this(owner, delay, true);
    }

    private FileSpliterUpdater(TaskOwner owner, long delay, boolean continuous) {
        super(owner, delay, continuous);
    }

    @Override
    public void run() {
        if (isSplitng()) {
            if (isNotCurrent()) {
                FileManager.updateFileHandlers();
            }
        } else {
            if (hasCurrentSplit()) {
                config.setCurrentSplit("");
            }
        }
    }

}
