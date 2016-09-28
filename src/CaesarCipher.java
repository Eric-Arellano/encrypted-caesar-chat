public class CaesarCipher implements Encryptable, Decryptable {

	public String encryptMessage(String message, String shift) {
		char[] encryptedChars = message.toCharArray();
		int shiftNumber = convertKey(shift);

		for (int index = 0; index < message.length(); index++) {
			char letter = encryptedChars[index];
			encryptedChars[index] = encryptChar(letter, shiftNumber);
		}
		return new String(encryptedChars);
	}

	public String decryptMessage(String encryptedMessage, String key) {
		char[] decryptedChars = encryptedMessage.toCharArray();
		int shiftNumber = convertKey(key);

		for (int index = 0; index < encryptedMessage.length(); index++) {
			char letter = decryptedChars[index];
			decryptedChars[index] = decryptChar(letter, shiftNumber);
		}
		return new String(decryptedChars);
	}

	private int convertKey(String key) {
		int shift = key.charAt(0);
		shift = (shift - 65) % 26;
		shift++; // increment in order to produce shift
		return shift;
	}

	private char encryptChar(char letter, int shift) {
		int value = letter;
		value = value - 65;
		value = Math.floorMod((value + shift), 26);
		value = value + 65;
		return (char)(value);
	}

	private char decryptChar(char letter, int shift) {
		int value = letter;
		value = value - 65;
		value = Math.floorMod((value - shift), 26);
		value = value + 65;
		return (char)(value);
	}


}
