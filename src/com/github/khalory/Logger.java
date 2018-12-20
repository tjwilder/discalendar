package com.github.khalory;

import java.io.PrintStream;

/**
 * A class to handle logging of all types of messages
 */
public class Logger {

	// Aligned logger messages
	private static final String DEBUG_PREFIX = "[DEBUG] ";
	private static final String INFO_PREFIX  = "[INFO]  ";
	private static final String ERROR_PREFIX = "[ERROR] ";

	public static void debug(String message) {
		if (Config.logDebug())
			log(DEBUG_PREFIX + message, System.out);
	}

	public static void debug(String message, Exception e) {
		if (Config.logDebug())
			log(DEBUG_PREFIX + message, e, System.out);
	}

	public static void info(String message) {
		if (Config.logInfo())
			log(INFO_PREFIX + message, System.out);
	}

	public static void error(String message) {
		if (Config.logError())
			log(ERROR_PREFIX + message, System.err);
	}

    public static void error(String message, Exception e) {
        message += "\n" + e.getMessage() + "\nStack trace:";
        for (StackTraceElement stackPiece : e.getStackTrace()) {
            message += "\n" + stackPiece.toString();
        }
        log(message, System.err);
    }

    public static void log(String message, Exception e, PrintStream ps) {
        message += "\n" + e.getMessage() + "\nStack trace:";
        for (StackTraceElement stackPiece : e.getStackTrace()) {
            message += "\n" + stackPiece.toString();
        }
        log(message, ps);
    }

    public static void log(String message, PrintStream ps) {
		// If MainRunner is not being debugged,
		// report all log messages to Discord
        if (!MainRunner.DEBUG) {
			BotUtils.report(message);
        }

		ps.println(message);
    }
}

