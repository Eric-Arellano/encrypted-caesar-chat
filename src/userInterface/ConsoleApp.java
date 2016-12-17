package userInterface;

import encryptors.Decryptable;
import encryptors.Encryptable;

import static encryptors.CipherChooser.chooseDecryptionCipher;
import static encryptors.CipherChooser.chooseEncryptionCipher;

public class ConsoleApp {

	private boolean quitApp;
	private EncryptMode encryptMode;

	public ConsoleApp() {
		quitApp = false;
	}

	public void launchConsoleApp() {
		welcomeUser();
		do {
			chooseEncryptOrDecrypt();
			String message = requestMessage();
			String key = requestKey();
			String translatedMessage = translateMessage(message, key);
			printNewMessage(translatedMessage);
		} while (!quitApp);
		closeApp();
	}

	private void welcomeUser() {
		System.out.println("Welcome, this app allows you to encrypt or decrypt a String message using" +
				" Caesar and/or Vigenere ciphers. Just supply your message and the key!");
	}

	private void closeApp() {
		System.out.println("This app is closing. Come back again for sharing your secrets.");
	}

	// -----------------------------------------------------------------------------------
	// Get user request
	// -----------------------------------------------------------------------------------

	private void chooseEncryptOrDecrypt() {
		promptEncryptMode();
		listenForEncryptMode();
	}

	private void promptEncryptMode() {
		System.out.println("Would you like to encrypt or decrypt your message?");
	}

	private void listenForEncryptMode() {
		final int RANGE_LOWER_BOUND = 1;
		final int RANGE_UPPER_BOUND = 2;
		int userInput = getValidIntInput(RANGE_LOWER_BOUND, RANGE_UPPER_BOUND);
		switch (userInput) {
			case 1:
				encryptMode = EncryptMode.ENCRYPT;
				break;
			case 2:
				encryptMode = EncryptMode.DECRYPT;
				break;
		}
	}

	private String requestMessage() {
		promptMessage();
		return listenForMessage();
	}

	private void promptMessage() {
		String prompt = String.format("Please type your %sd message: ", encryptMode);
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
		String prompt = String.format("Please type your %sion key: ", encryptMode);
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
		String newMessage = String.format("Your new %sed message is: %s", encryptMode, message);
		System.out.println(newMessage);
	}

	private enum EncryptMode {
		ENCRYPT, DECRYPT;

		@Override
		public String toString() {
			return name().toLowerCase();
		}
	}

	// -----------------------------------------------------------------------------------
	// User input helper
	// -----------------------------------------------------------------------------------

	private class UserInputHelper {

	}

	private static int getValidIntInput(int RANGE_LOWER_BOUND, int RANGE_UPPER_BOUND) {
		// keep asking until within inclusive range
	}

	private static String getValidStringInput() {
		// only upper and lower case letters; no numbers, special characters, or spaces
	}

}
