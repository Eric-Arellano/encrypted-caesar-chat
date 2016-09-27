public class VigenereCipher implements Encryptable {

	private CaesarCipher caesarCipher;

	public VigenereCipher() {
		caesarCipher = new CaesarCipher();
	}

	public String encryptMessage(String message, String key) {
		StringBuilder messageBuilder = new StringBuilder();
		for (int messageIndex = 0; messageIndex < message.length(); messageIndex++ ) {
			String substring = message.substring(messageIndex, messageIndex + 1);

			String encryption = caesarCipher.encryptMessage(substring, key);
			messageBuilder.append(encryption);
		}
		return messageBuilder.toString();
	}

	private int[] convertKey(String key) {
		char[] keys = key.toCharArray();
		int[] shifts = new int[keys.length];
		for (int index = 0; index < keys.length; index++) {
			shifts[index] = keys[index];
			shifts[index] = (shifts[index] - 65) % 26;
			shifts[index]++;
		}
		return shifts;
	}

}