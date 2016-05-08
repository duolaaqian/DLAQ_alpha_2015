package org.webframe.support.util;

import java.util.Date;

public class SystemLogUtils {

	public static boolean	enableSystemLog	= true;

	public static long		currentTime			= new Date().getTime();

	private SystemLogUtils() {
	}

	public static void println(Object msg) {
		if (enableSystemLog) {
			System.out.println(timeMsg("[INFO]  ", msg));
		}
	}

	public static void errorPrintln(Object msg) {
		if (enableSystemLog) {
			System.err.println("[ERROR] " + msg);
		}
	}

	public static void rootPrintln(Object msg) {
		println("<=======================" + msg + "=======================>");
	}

	public static void secondPrintln(Object msg) {
		println("  " + msg);
	}

	public static void thirdPrintln(Object msg) {
		println("    " + msg);
	}

	private static String timeMsg(String level, Object msg) {
		long time = new Date().getTime();
		try {
			String format = (time - currentTime) + "";
			String timeStr = StringUtils.formatBySpace(format, 5, true);
			return level + timeStr + " ms " + msg;
		} finally {
			currentTime = time;
		}
	}
}
