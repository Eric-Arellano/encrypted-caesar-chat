public class CaesarCipher {

	private static String messageString;
	private static char key;

	private char[] messageChars;

	CaesarCipher(String message, char key) {
		this.messageString = message;
		this.key = key;
		this.messageChars = messageString.toCharArray();
	}

	public String encryptMessage() {
		char[] encryptedChars = messageChars;

		// shift down to letters
		for (char element : encryptedChars) {
			element = (char)(element - 65);
		}
		// add key
		for (char element : encryptedChars) {
			element = (char)((element + key) % 26);
		}
		String encryptedMessage = encryptedChars.toString();
		return encryptedMessage;


	}


}
