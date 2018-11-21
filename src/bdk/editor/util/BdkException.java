package bdk.editor.util;

public class BdkException {

	/*
	 * Throws an Exception with the specified message
	 */
	public static void throwWithMessage(String message) {
		try {
			throw new Exception(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
