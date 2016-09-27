public class CaesarCipher implements Encryptable {

	public String encryptMessage(String message, String shift) {

		char[] encryptedChars = message.toCharArray();
		int shiftNumber = convertKey(shift);

		for (int index = 0; index < message.length(); index++) {
			char letter = encryptedChars[index];
			encryptedChars[index] = encryptChar(letter, shiftNumber);
		}
		return new String(encryptedChars);

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
		value = (value + shift) % 26;
		value = value + 65;
		return (char)(value);
	}


}
