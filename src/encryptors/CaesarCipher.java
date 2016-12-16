package encryptors;

public class CaesarCipher implements Encryptable, Decryptable {

	private final int ASCII_SHIFT_FOR_LOWER_CASE = 97;
	private final int ALPHABET_SIZE = 26;

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
		shift = (shift - ASCII_SHIFT_FOR_LOWER_CASE) % ALPHABET_SIZE;
		shift++; // increment in order to produce shift
		return shift;
	}

	private char encryptChar(char letter, int shift) {
		int value = letter;
		value = value - ASCII_SHIFT_FOR_LOWER_CASE;
		value = Math.floorMod((value + shift), ALPHABET_SIZE);
		value = value + ASCII_SHIFT_FOR_LOWER_CASE;
		return (char)(value);
	}

	private char decryptChar(char letter, int shift) {
		int value = letter;
		value = value - ASCII_SHIFT_FOR_LOWER_CASE;
		value = Math.floorMod((value - shift), ALPHABET_SIZE);
		value = value + ASCII_SHIFT_FOR_LOWER_CASE;
		return (char)(value);
	}


}
