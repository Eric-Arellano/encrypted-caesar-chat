package userInterface;

import encryptors.Decryptable;
import encryptors.Encryptable;

import static encryptors.CipherChooser.chooseDecryptionCipher;
import static encryptors.CipherChooser.chooseEncryptionCipher;
import static userInterface.UserInputHelper.getValidIntInput;
import static userInterface.UserInputHelper.getValidStringInput;

class ConsoleApp implements Launchable {

	private boolean quitApp;
	private EncryptMode encryptMode;

	public ConsoleApp() {
		quitApp = false;
	}

	public void launchApp() {
		welcomeUser();
		do {
			chooseEncryptOrDecrypt();
			if (!quitApp) {
				String message = requestMessage();
				String key = requestKey();
				String translatedMessage = translateMessage(message, key);
				printNewMessage(translatedMessage);
			}
		} while (!quitApp);
		closeApp();
	}

	private void welcomeUser() {
		System.out.println("Welcome, this app allows you to encrypt or decrypt a String message using" +
				" Caesar and/or Vigenere ciphers. Just supply your message and the key!");
	}

	private void closeApp() {
		System.out.println("This app is closing. Come back again to share some secrets.");
	}

	// -----------------------------------------------------------------------------------
	// Get user request
	// -----------------------------------------------------------------------------------

	private void chooseEncryptOrDecrypt() {
		promptEncryptMode();
		listenForEncryptMode();
	}

	private void promptEncryptMode() {
		String instruction = "\nWould you like to encrypt or decrypt your message? Please input \"1\", " +
				"\"2\" or \"0\" to quit.";
		String menuOptions = "\n1)\tEncrypt message\n2)\tDecrypt message";
		System.out.println(instruction + menuOptions);
	}

	private void listenForEncryptMode() {
		final int RANGE_LOWER_BOUND = 0;
		final int RANGE_UPPER_BOUND = 2;
		int userInput = getValidIntInput(RANGE_LOWER_BOUND, RANGE_UPPER_BOUND);
		switch (userInput) {
			case 1:
				encryptMode = EncryptMode.ENCRYPT;
				break;
			case 2:
				encryptMode = EncryptMode.DECRYPT;
				break;
			case 0:
				quitApp = true;
				break;
		}
	}

	private String requestMessage() {
		promptMessage();
		return listenForMessage();
	}

	private void promptMessage() {
		String prompt = String.format("Please type your %sed message (use lowercase and/or uppercase " +
				"letters, but no spaces or special characters): ", EncryptMode.getInverseMode(encryptMode));
		System.out.println(prompt);
	}

	private String listenForMessage() {
		return getValidStringInput();
	}

	private String requestKey() {
		promptKey();
		return listenForKey();
	}

	private void promptKey() {
		String prompt = String.format("Please type your %sion key (use a lowercase letter or letters)" +
				": ", encryptMode);
		System.out.println(prompt);
	}

	private String listenForKey() {
		return getValidStringInput();
	}

	// -----------------------------------------------------------------------------------
	// Convert message
	// -----------------------------------------------------------------------------------

	private String translateMessage(String message, String key) {
		if (isEncryption()) {
			Encryptable encryptor = chooseEncryptionCipher(key);
			return encryptor.encryptMessage(message, key);
		} else {
			Decryptable decryptor = chooseDecryptionCipher(key);
			return decryptor.decryptMessage(message, key);
		}
	}

	private boolean isEncryption() {
		return encryptMode.equals(EncryptMode.ENCRYPT);
	}

	private void printNewMessage(String message) {
		String newMessage = String.format("Your new %sed message is: \n\n%s\n\n", encryptMode, message);
		System.out.println(newMessage);
	}

	private enum EncryptMode {
		ENCRYPT, DECRYPT;

		@Override
		public String toString() {
			return name().toLowerCase();
		}

		public static String getInverseMode(EncryptMode currentMode) {
			if (currentMode.equals(ENCRYPT)) {
				return DECRYPT.toString();
			} else {
				return ENCRYPT.toString();
			}
		}
	}

}
