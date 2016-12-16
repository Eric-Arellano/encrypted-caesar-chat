package encryptors;

public class VigenereCipher implements Encryptable, Decryptable {

	private final CaesarCipher caesarCipher;

	public VigenereCipher() {
		caesarCipher = new CaesarCipher();
	}

	public String encryptMessage(String message, String key) {
		StringBuilder messageBuilder = new StringBuilder();
		int keyIndex = 0;
		for (int messageIndex = 0; messageIndex < message.length(); messageIndex++ ) {
			// get current values
			String currentLetter = getCurrentLetter(message, messageIndex);
			String currentKey = getCurrentKey(key, keyIndex);

			// append encryption
			String encryptedLetter = caesarCipher.encryptMessage(currentLetter, currentKey);
			messageBuilder.append(encryptedLetter);

			// update keyIndex
			if (keyIndex < key.length() - 1) {
				keyIndex++;
			} else {
				keyIndex = 0;
			}
//			keyIndex = updateKeyIndex(keyIndex, key.length());
		}
		return messageBuilder.toString();
	}

	private String getCurrentLetter(String message, int messageIndex) {
		return message.substring(messageIndex, messageIndex + 1);
	}

	private String getCurrentKey(String key, int keyIndex) {
		return key.substring(keyIndex, keyIndex + 1);
	}

	private int updateKeyIndex(int keyIndex, int keyLength) {
		if (keyIndex < keyLength - 1) {
			return keyIndex++;
		} else {
			return 0;
		}
	}

	public String decryptMessage(String encryptedMessage, String key) {
		StringBuilder messageBuilder = new StringBuilder();
		int keyIndex = 0;
		for (int messageIndex = 0; messageIndex < encryptedMessage.length(); messageIndex++ ) {
			// get current values
			String substring = encryptedMessage.substring(messageIndex, messageIndex + 1);
			String currentKey = key.substring(keyIndex, keyIndex + 1);

			// append encryption
			String encryption = caesarCipher.decryptMessage(substring, currentKey);
			messageBuilder.append(encryption);

			// update keyIndex
			if (keyIndex < key.length() - 1) {
				keyIndex++;
			} else {
				keyIndex = 0;
			}
		}
		return messageBuilder.toString();
	}

}