package pl.edu.pw.elka.pjastrz2.mbi.utils;

/**
 * MBI Project Android-like Log class
 * 
 * @author piotr.jastrzebski@gmail.com
 * @author marcin@nazimek.pl
 * 
 */
public class Log {

	/**
	 * Change to false in order to turn off the DEBUG console dumping
	 */
	private static final boolean DEBUG = false;

	/**
	 * Change to false in order to turn off the ERROR console dumping
	 */
	private static final boolean ERROR = false;

	public static void d(String text) {
		if (DEBUG) {
			System.out.println("DEBUG: " + text);
		}
	}

	public static void e(String text) {
		if (DEBUG || ERROR) {
			System.err.println("ERROR: " + text);
		}
	}

	public static void i(String text) {
		if (DEBUG) {
			System.out.println("INFO: " + text);
		}
	}

	public static void w(String text) {
		if (DEBUG) {
			System.out.println("WARN: " + text);
		}
	}

	/**
	 * Like in Android - should never be called :)
	 * 
	 * @param text
	 */
	public static void wtf(String text) {
		if (DEBUG) {
			System.out.println("WTF: " + text);
		}
	}

}
