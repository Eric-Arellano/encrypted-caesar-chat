package userInterface;

import encryptors.Decryptable;
import encryptors.Encryptable;

import static encryptors.CipherChooser.chooseDecryptionCipher;
import static encryptors.CipherChooser.chooseEncryptionCipher;
import static userInterface.UserInputHelper.InvalidInputException;
import static userInterface.UserInputHelper.isNotValidLetter;

public class CommandLineApp {

	String[] args;

	public CommandLineApp(String[] args) {
		this.args = args;
	}

	public void launchCommandLineApp() {
		EncryptMode mode = parseMode();
		String message = parseMessage();
		String key = parseKey();
		System.out.println(translateMessage(mode, message, key));
	}

	private EncryptMode parseMode() {
		EncryptMode mode = EncryptMode.INVALID;
		try {
			String modeValue = args[0];
			if (isEncryptionInput(modeValue)) {
				mode = EncryptMode.ENCRYPT;
			} else if (isDecryptionInput(modeValue)) {
				mode = EncryptMode.DECRYPT;
			} else {
				throw new InvalidInputException("Invalid mode input.");
			}
		} catch (InvalidInputException invalidInputException) {
			System.out.println("Invalid mode input.");
		}
		return mode;
	}

	private boolean isEncryptionInput(String modeValue) {
		return modeValue.equalsIgnoreCase("e") ||
				modeValue.equalsIgnoreCase("encrypt") ||
				modeValue.equalsIgnoreCase("encryption") ||
				modeValue.equalsIgnoreCase("-e");
	}

	private boolean isDecryptionInput(String modeValue) {
		return modeValue.equalsIgnoreCase("d") ||
				modeValue.equalsIgnoreCase("decrypt") ||
				modeValue.equalsIgnoreCase("decryption") ||
				modeValue.equalsIgnoreCase("-d");
	}

	private String parseMessage() {
		String message = "";
		try {
			message = args[1];
			if (isNotValidLetter(message)) {
				throw new InvalidInputException("Invalid message.");
			}
		} catch (InvalidInputException invalidInputException) {
			System.out.println("Invalid message");
		}
		return message;
	}

	private String parseKey() {
		String key = "";
		try {
			key = args[2];
			if (isNotValidLetter(key)) {
				throw new InvalidInputException("Invalid message.");
			}
		} catch (InvalidInputException invalidInputException) {
			System.out.println("Invalid message");
		}
		return key;
	}

	private static String translateMessage(EncryptMode mode, String message, String key) {
		if (isEncryption(mode)) {
			Encryptable encryptor = chooseEncryptionCipher(key);
			return encryptor.encryptMessage(message, key);
		} else {
			Decryptable decryptor = chooseDecryptionCipher(key);
			return decryptor.decryptMessage(message, key);
		}
	}

	private static boolean isEncryption(EncryptMode mode) {
		return mode.equals(EncryptMode.ENCRYPT);
	}

	private enum EncryptMode {
		ENCRYPT, DECRYPT, INVALID;

		@Override
		public String toString() {
			return name().toLowerCase();
		}
	}

}
