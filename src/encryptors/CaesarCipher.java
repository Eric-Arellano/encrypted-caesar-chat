package encryptors;

public class CaesarCipher implements Encryptable, Decryptable {

	private final int ASCII_SHIFT_FOR_LOWER_CASE = 97;
	private final int ALPHABET_SIZE = 26;

	public String encryptMessage(String message, String key) {
		char[] chars = message.toCharArray();
		int shiftNumber = convertKey(key);
		return encryptChars(chars, shiftNumber);
	}

	public String decryptMessage(String encryptedMessage, String key) {
		char[] chars = encryptedMessage.toCharArray();
		int shiftNumber = convertKey(key);
		return decryptChars(chars, shiftNumber);
	}

	private int convertKey(String key) {
		int shift = key.charAt(0);
		shift = shift - ASCII_SHIFT_FOR_LOWER_CASE;
		shift = Math.floorMod(shift, ALPHABET_SIZE);
		shift++; // increment in order to produce shift
		return shift;
	}

	private String encryptChars(char[] chars, int shiftNumber) {
		for (int index = 0; index < chars.length; index++) {
			char letter = chars[index];
			chars[index] = encryptChar(letter, shiftNumber);
		}
		return new String(chars);
	}

	private String decryptChars(char[] chars, int shiftNumber) {
		for (int index = 0; index < chars.length; index++) {
			char letter = chars[index];
			chars[index] = decryptChar(letter, shiftNumber);
		}
		return new String(chars);
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
