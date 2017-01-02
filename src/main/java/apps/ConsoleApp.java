package apps;

import apps.utilities.EncryptMode;

import static apps.utilities.ConsoleInterfacer.*;
import static apps.utilities.EncryptMode.isQuitApp;
import static apps.utilities.MessageTranslator.translateMessage;

class ConsoleApp implements Launchable {

	public void launchApp() {
		welcomeUser();
		EncryptMode encryptMode;
		do {
			encryptMode = chooseEncryptOrDecrypt();
			if (!isQuitApp(encryptMode)) {
				String message = requestMessage(encryptMode);
				String key = requestKey(encryptMode);
				String translatedMessage = translateMessage(encryptMode, message, key);
				printNewMessage(encryptMode, translatedMessage);
			}
		} while (!isQuitApp(encryptMode));
		closeApp();
	}

	private void welcomeUser() {
		System.out.println("Welcome, this app allows you to encrypt or decrypt a String message using" +
				" Caesar and/or Vigenere ciphers. Just supply your message and the key!");
	}

	private void closeApp() {
		System.out.println("This app is closing. Come back again to share some secrets.");
	}

}
