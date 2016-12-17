package encryptors;

import static encryptors.ConversionUtilities.*;

public class CaesarCipher implements Encryptable, Decryptable {

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


	// -----------------------------------------------------------------------------------
	// Convert key
	// -----------------------------------------------------------------------------------

	private int convertKey(String key) {
		char shift = key.charAt(0);
		shift = Character.toLowerCase(shift);
		shift = convertCharDownFromASCII(shift, Case.LOWERCASE);
		shift = shiftKey(shift);
		return (int) shift;
	}

	// -----------------------------------------------------------------------------------
	// Encrypt/decrypt chars
	// -----------------------------------------------------------------------------------

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
		if (Character.isLowerCase(letter)) {
			return encryptLowerCaseChar(letter, shift);
		} else if (Character.isUpperCase(letter)) {
			return encryptUpperCaseChar(letter, shift);
		} else {
			return letter; //TODO: improve this control flow, what should else really do?
		}
	}

	private char decryptChar(char letter, int shift) {
		if (Character.isLowerCase(letter)) {
			return decryptLowerCaseChar(letter, shift);
		} else if (Character.isUpperCase(letter)) {
			return decryptUpperCaseChar(letter, shift);
		} else {
			return letter; //TODO: improve this control flow, what should else really do?
		}
	}

	private char encryptLowerCaseChar(char letter, int shift) {
		char encryptedLetter = convertCharDownFromASCII(letter, Case.LOWERCASE);
		encryptedLetter = shiftChar(encryptedLetter, shift, ShiftCharType.ENCRYPT);
		encryptedLetter = convertCharBackToASCII(encryptedLetter, Case.LOWERCASE);
		return encryptedLetter;
	}

	private char encryptUpperCaseChar(char letter, int shift) {
		char encryptedLetter = convertCharDownFromASCII(letter, Case.UPPERCASE);
		encryptedLetter = shiftChar(encryptedLetter, shift, ShiftCharType.ENCRYPT);
		encryptedLetter = convertCharBackToASCII(encryptedLetter, Case.UPPERCASE);
		return encryptedLetter;
	}

	private char decryptLowerCaseChar(char letter, int shift) {
		char decryptedLetter = convertCharDownFromASCII(letter, Case.LOWERCASE);
		decryptedLetter = shiftChar(decryptedLetter, shift, ShiftCharType.DECRYPT);
		decryptedLetter = convertCharBackToASCII(decryptedLetter, Case.LOWERCASE);
		return decryptedLetter;
	}

	private char decryptUpperCaseChar(char letter, int shift) {
		char decryptedLetter = convertCharDownFromASCII(letter, Case.UPPERCASE);
		decryptedLetter = shiftChar(decryptedLetter, shift, ShiftCharType.DECRYPT);
		decryptedLetter = convertCharBackToASCII(decryptedLetter, Case.UPPERCASE);
		return decryptedLetter;
	}

}
