public class VinereCipher {

	private static String fullMessage;
	private static String fullKey;

	private char currentKey;
	private int keyIndex;

	private char currentChar;
	private int charIndex;

	public VinereCipher(String message, String key) {
		fullMessage = message;
		fullKey = key;

		keyIndex = 0;
		currentKey = fullKey.charAt(keyIndex);
		charIndex = 0;
		currentChar = fullMessage.charAt(charIndex);
	}

	public String encryptMessage(){
		String encryptedMessage = "";
		while (charIndex < fullMessage.length()) {
			encryptedMessage += encryptCurrentChar();
//			System.out.println("before key: " + currentKey);
			System.out.println("before char index: " + charIndex);
			System.out.println("before char: " + currentChar);

			updateCurrentKey();
			updateCurrentChar();
//			System.out.println("after key: " + currentKey);
			System.out.println("after char index: " + charIndex);
			System.out.println("after char: " + currentChar);

			System.out.println();
		}
		return encryptedMessage;
	}

	private String encryptCurrentChar(){
		int key = (int)(currentKey);
		char encryptedCharacter = currentChar;
		// shift down to alphabet
		encryptedCharacter = (char)(encryptedCharacter - 65);
		// add key
		encryptedCharacter = (char)((encryptedCharacter + key) % 26);
		// convert to String
		encryptedCharacter = (char)(encryptedCharacter + 65);
		return Character.toString(encryptedCharacter);
	}

	/**
	 * Move the current key used up by one from full key. Once Full Key used, repeat.
	 */
	private void updateCurrentKey() {
		if (keyIndex < fullKey.length()) {
			currentKey = fullKey.charAt(keyIndex);
		}
		else {
			keyIndex = 0;
			currentKey = fullKey.charAt(keyIndex);
		}
		keyIndex++;
	}

	/**
	 * Move substring index up one and update value.
	 */
	private void updateCurrentChar() {
		currentChar = fullMessage.charAt(charIndex);
		charIndex++;
	}

}
