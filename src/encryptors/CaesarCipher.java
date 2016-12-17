package encryptors;

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

	private int convertKey(String key) {
		int shift = key.charAt(0);
		if (Character.isLowerCase(shift)) {
            shift = convertCharDownFromASCII((char) shift, Case.LOWERCASE);
        }
        else if (Character.isUpperCase(shift)) {
		    shift = convertCharDownFromASCII((char) shift, Case.UPPERCASE);
        }
		shift = shiftKey((char) shift);
		return shift;
	}

	private int shiftKey(char letter) {
		return ShiftCharType.CONVERT_KEY.shiftChar(letter, 1);
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
		int value = convertCharDownFromASCII(letter, Case.LOWERCASE);
		value = shiftChar((char) value, shift, ShiftCharType.ENCRYPT);
		value = convertCharBackToASCII((char) value, Case.LOWERCASE);
		return (char)(value);
	}

	private char encryptUpperCaseChar(char letter, int shift) {
        int value = convertCharDownFromASCII(letter, Case.UPPERCASE);
		value = shiftChar((char) value, shift, ShiftCharType.ENCRYPT);
		value = convertCharBackToASCII((char) value, Case.UPPERCASE);
		return (char)(value);
	}

	private char decryptLowerCaseChar(char letter, int shift) {
        int value = convertCharDownFromASCII(letter, Case.LOWERCASE);
		value = shiftChar((char) value, shift, ShiftCharType.DECRYPT);
        value = convertCharBackToASCII((char) value, Case.LOWERCASE);
		return (char)(value);
	}

	private char decryptUpperCaseChar(char letter, int shift) {
        int value = convertCharDownFromASCII(letter, Case.UPPERCASE);
		value = shiftChar((char) value, shift, ShiftCharType.DECRYPT);
        value = convertCharBackToASCII((char) value, Case.UPPERCASE);
		return (char)(value);
	}

	private int convertCharDownFromASCII(char letter, Case CASE) {
	    return (int) letter - CASE.asciiShift();
    }

    private int convertCharBackToASCII(char letter, Case CASE) {
	    return (int) letter + CASE.asciiShift();
    }

    private int shiftChar(char letter, int shift, ShiftCharType encryptOrDecrypt) {
		return encryptOrDecrypt.shiftChar(letter, shift);
	}

    private enum Case {
	    UPPERCASE(65), LOWERCASE(97);

	    private final int asciiShiftValue;

	    Case(int asciiShiftValue) {
	        this.asciiShiftValue = asciiShiftValue;
        }

        public int asciiShift() {
	        return asciiShiftValue;
        }
    }

    private enum ShiftCharType {
		ENCRYPT {
			int shiftChar(char letter, int shift) {
				return Math.floorMod((letter + shift), ALPHABET_SIZE);
			}
		},
		DECRYPT {
			int shiftChar(char letter, int shift) {
				return Math.floorMod((letter - shift), ALPHABET_SIZE);
			}
		},
		CONVERT_KEY {
			int shiftChar(char letter, int shift) {
				return Math.floorMod((letter), ALPHABET_SIZE) + 1;
			}
		};

		private static final int ALPHABET_SIZE = 26;

		abstract int shiftChar(char letter, int shift);
	}


}
