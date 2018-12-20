package com.github.khalory;

// Handles general configuration options
class Config {
	
	// What's the current log level?
	public static final LogLevel LOG_LEVEL = LogLevel.Debug;

	// Pretty sure there's an easier way to do this enum
	// comparison but I can't look it up right now
	public static boolean logDebug() {
		return LOG_LEVEL == LogLevel.Debug;
	}

	public static boolean logInfo() {
		return LOG_LEVEL == LogLevel.Debug
			|| LOG_LEVEL == LogLevel.Info;
	}

	public static boolean logError() {
		return LOG_LEVEL == LogLevel.Debug
			|| LOG_LEVEL == LogLevel.Info
			|| LOG_LEVEL == LogLevel.Error;
	}


	// TODO: Eventually load config from separate files for
	// dev, prod, etc.
}

// Possible LogLevels, None for no output
enum LogLevel {
	Debug,
	Info,
	Error,
	None;
}
