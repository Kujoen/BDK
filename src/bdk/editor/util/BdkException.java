package bdk.editor.util;

public class BdkException {

	public static void throwWithMessage(String message) {
		try {
			throw new Exception(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		throw new IllegalArgumentException();
	}
	
}
