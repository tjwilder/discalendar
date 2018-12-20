package com.github.khalory;

import java.io.PrintStream;

/**
 * A class to handle logging of all types of messages
 */
class Logger {

	// Aligned logger messages
	private static final String DEBUG_PREFIX = "[DEBUG] ";
	private static final String INFO_PREFIX  = "[INFO]  ";
	private static final String ERROR_PREFIX = "[ERROR] ";

	protected static void debug(String message) {
		if (Config.logDebug())
			log(DEBUG_PREFIX + message, System.out);
	}

	protected static void info(String message) {
		if (Config.logInfo())
			log(INFO_PREFIX + message, System.out);
	}

	protected static void error(String message) {
		if (Config.logError())
			log(ERROR_PREFIX + message, System.err);
	}

    protected static void error(String message, Exception e) {
        message += "\n" + e.getMessage() + "\nStack trace:";
        for (StackTraceElement stackPiece : e.getStackTrace()) {
            message += "\n" + stackPiece.toString();
        }
        log(message, System.err);
    }

    protected static void log(String message, PrintStream ps) {
		// If MainRunner is not being debugged,
		// report all log messages to Discord
        if (!MainRunner.DEBUG) {
			BotUtils.report(message);
        }

		ps.println(message);
    }
}

