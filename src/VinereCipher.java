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
			updateCurrentKey();
			updateCurrentCharacter();
		}
		return encryptedMessage;
	}

	private String encryptCurrentCharacter(){
		int key = (int)(currentKey);
		// shift down to alphabet
		currentCharacter = (char)(currentCharacter - 65);
		// add key
		currentCharacter = (char)((currentCharacter + key) % 26);
		// convert to String
		currentCharacter = (char)(currentCharacter + 65);
		return Character.toString(currentCharacter);
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
