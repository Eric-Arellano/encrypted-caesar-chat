public class VigenereCipher implements Encryptable {

	private CaesarCipher caesarCipher;

	public VigenereCipher() {
		caesarCipher = new CaesarCipher();
	}

	public String encryptMessage(String message, String key) {
		StringBuilder messageBuilder = new StringBuilder();
		int keyIndex = 0;
		for (int messageIndex = 0; messageIndex < message.length(); messageIndex++ ) {
			// get current values
			String substring = message.substring(messageIndex, messageIndex + 1);
			String currentKey = key.substring(keyIndex, keyIndex + 1);

			// append encryption
			String encryption = caesarCipher.encryptMessage(substring, currentKey);
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