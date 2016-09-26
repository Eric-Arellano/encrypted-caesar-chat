public class VinereCipher {

	private static String messageString;
	private static String fullKey;

	private char currentKey;
	private int keyIndex;

	private char currentCharacter;
	private int messageIndex;

	public VinereCipher(String message, String key) {
		messageString = message;
		fullKey = key;

		updateCurrentKey();
		keyIndex = 0;

		updateCurrentCharacter();
		messageIndex = 0;
	}

	public String encryptMessage(){
		String encryptedMessage = "";
		while (messageIndex < messageString.length()) {
			encryptedMessage += encryptCurrentCharacter();
//			System.out.println("before key: " + currentKey);
			System.out.println("before char index: " + messageIndex);
			System.out.println("before char: " + currentCharacter);

			updateCurrentKey();
			updateCurrentCharacter();
//			System.out.println("after key: " + currentKey);
			System.out.println("after char index: " + messageIndex);
			System.out.println("after char: " + currentCharacter);

			System.out.println();
		}
		return encryptedMessage;
	}

	private String encryptCurrentCharacter(){
		int key = (int)(currentKey);
		char encryptedCharacter = currentCharacter;
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
			keyIndex++;
		}
		else {
			keyIndex = 0;
			currentKey = fullKey.charAt(keyIndex);
			keyIndex++;
		}
	}

	/**
	 * Move substring index up one and update value.
	 */
	private void updateCurrentCharacter() {
		currentCharacter = messageString.charAt(messageIndex);
		messageIndex++;
	}

}
