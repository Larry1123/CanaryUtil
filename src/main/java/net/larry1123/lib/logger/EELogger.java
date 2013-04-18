/**
 * @author ElecEntertainment
 * @team Larry1123, Joshtmathews, Sinzo, Xalbec
 * @lastedit Apr 18, 2013 2:13:43 AM
 */

package net.larry1123.lib.logger;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.FileHandler;
import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import net.canarymod.logger.Logman;

public class EELogger {

    public static class EECLogger extends Logman {

	final class UtilFilter implements Filter {

	    private final LinkedList<LoggerLevel> allowed = new LinkedList<LoggerLevel>();

	    public void addLogLevel(String name) {
		allowed.add(getLoggerLevel(name));
	    }

	    @Override
	    public boolean isLoggable(LogRecord rec) {

		Level level = rec.getLevel();

		if (allowed.contains(level)) {
		    return true;
		} else {
		    return false;
		}
	    }

	    public void removeLogLevel(String name) {
		allowed.remove(getLoggerLevel(name));
	    }

	}

	private final class UtilsLogFormat extends SimpleFormatter {

	    private final SimpleDateFormat dateform = new SimpleDateFormat(
		    "dd-MM-yyyy HH:mm:ss");
	    private final String linesep = System.getProperty("line.separator");

	    @Override
	    public final String format(LogRecord rec) {

		Level level = rec.getLevel();
		String msg = rec.getMessage();

		StringBuilder message = new StringBuilder();
		message.append(dateform.format(rec.getMillis()));

		if (level instanceof LoggerLevel) {
		    LoggerLevel handle = (LoggerLevel) level;
		    if (!handle.getPrefix().equals("")) {
			message.append(" [" + handle.getName() + "] ["
				+ handle.getPrefix() + "] " + msg);
		    } else {
			message.append(" [" + handle.getName() + "] " + msg);
		    }
		} else {
		    message.append(new StringBuilder("[")
		    .append(level.getName()).append("] ")
		    .append(rec.getMessage()).toString());
		}

		message.append(linesep);
		if (rec.getThrown() != null) {
		    StringWriter stringwriter = new StringWriter();
		    rec.getThrown().printStackTrace(
			    new PrintWriter(stringwriter));
		    message.append(stringwriter.toString());
		}
		return message.toString();
	    }
	}

	private static final HashMap<String, FileHandler> fileHandlers = new HashMap<String, FileHandler>();

	public EECLogger(String name) {
	    super(name);
	}

	public String addLoggerLevel(String errorName) {
	    return LoggerLevels.addLoggerLevel(errorName);
	}

	public String addLoggerLevel(String errorName, String prefix) {
	    return LoggerLevels.addLoggerLevel(errorName, prefix);
	}

	public String addLoggerLevelWFile(String errorName, String pathName) {
	    String name = LoggerLevels.addLoggerLevel(errorName);

	    int place = pathName.lastIndexOf('/');
	    if (place != -1) {
		File logDir = new File(pathName.substring(place,
			pathName.length()));
		if (!logDir.exists()) {
		    logDir.mkdirs();
		}
	    }

	    try {
		FileHandler handler = gethandler(pathName + ".log");
		UtilFilter filter = (UtilFilter) handler.getFilter();
		filter.addLogLevel(name);

	    } catch (SecurityException e) {
		EELogger.log.logCustom(EELogger.LoggerError,
			"SecurityException");
	    } catch (IOException e) {
		EELogger.log.logCustom(EELogger.LoggerError, "IOException");
	    }
	    return name;
	}

	public String addLoggerLevelWFile(String errorName, String prefix, String pathName) {
	    String name = LoggerLevels.addLoggerLevel(errorName, prefix);

	    File logDir = new File(pathName.substring(
		    pathName.lastIndexOf('/'), pathName.length()));
	    if (!logDir.exists()) {
		logDir.mkdirs();
	    }

	    try {
		FileHandler handler = gethandler(pathName
			+ ".log");
		UtilFilter filter = (UtilFilter) handler.getFilter();
		filter.addLogLevel(name);
	    } catch (SecurityException e) {
		EELogger.log.logCustom(EELogger.LoggerError,
			"SecurityException");
	    } catch (IOException e) {
		EELogger.log.logCustom(EELogger.LoggerError, "IOException");
	    }
	    return name;
	}

	private FileHandler gethandler(String pathName)
		throws SecurityException, IOException {

	    if (!fileHandlers.containsKey(pathName)) {
		FileHandler handler = new FileHandler(pathName, true);

		UtilsLogFormat lf = new UtilsLogFormat();
		handler.setFilter(new UtilFilter());
		handler.setLevel(Level.ALL);
		handler.setFormatter(lf);
		handler.setEncoding("UTF-8");

		fileHandlers.put(pathName, handler);
	    }
	    return fileHandlers.get(pathName);

	}

	public LoggerLevel getLoggerLevel(String name) {
	    return LoggerLevels.getLoggerLevel(name);
	}

	@Override
	public void log(LogRecord logRecord) {
	    Level level = logRecord.getLevel();
	    String msg = logRecord.getMessage();
	    if (level instanceof LoggerLevel) {
		LoggerLevel handle = (LoggerLevel) level;
		if (!handle.getPrefix().isEmpty()) {
		    logRecord
		    .setMessage(" [" + handle.getPrefix() + "] " + msg);
		} else {
		    logRecord.setMessage(" " + msg);
		}
	    } else {
		logRecord.setMessage(new StringBuilder(" ").append(
			logRecord.getMessage()).toString());
	    }
	    super.log(logRecord);
	}

	public void logCustom(LoggerLevel lvl, String msg) {
	    log(lvl, msg);
	}

	public void logCustom(LoggerLevel lvl, String msg, Throwable thrown) {
	    log(lvl, msg, thrown);
	}

	public void logCustom(String lvl, String msg) {
	    log(LoggerLevels.getLoggerLevel(lvl), msg);
	}

	public void logCustom(String lvl, String msg, Throwable thrown) {
	    log(LoggerLevels.getLoggerLevel(lvl), msg, thrown);
	}

	public void removeLoggerLevel(String name) {
	    LoggerLevels.removeLoggerLevel(name);
	    if (fileHandlers.containsKey(name)) {
		removeHandler(fileHandlers.remove(name));
	    }
	}

    }

    public static final EECLogger log;
    public static final String logPath = "PluginLogs/";
    private final static String LoggerError = addLoggerLevel(
	    "ElecEntertainmentLogger", "FileHandler");
    private final static HashMap<String, EECLogger> loggers = new HashMap<String, EECLogger>();

    static {
	log = new EECLogger("ElecEntertainmentLogger");
	log.setParent(Logger.getLogger("Minecraft-Server"));
	log.setLevel(Level.ALL);

	File logDir = new File(logPath);
	if (!logDir.exists()) {
	    logDir.mkdirs();
	}
    }

    public static String addLoggerLevel(String errorName) {
	return LoggerLevels.addLoggerLevel(errorName);
    }

    public static String addLoggerLevel(String errorName, String prefix) {
	return LoggerLevels.addLoggerLevel(errorName, prefix);
    }

    public static EECLogger getLogger(String name) {
	if (!loggers.containsKey(name)) {
	    EECLogger logman = new EECLogger(name);
	    logman.setParent(Logger.getLogger("Minecraft-Server"));

	    loggers.put(name, logman);
	}
	return loggers.get(name);
    }

    public static LoggerLevel getLoggerLevel(String name) {
	return LoggerLevels.getLoggerLevel(name);
    }

    public static void removeLoggerLevel(String name) {
	LoggerLevels.removeLoggerLevel(name);
    }

}
