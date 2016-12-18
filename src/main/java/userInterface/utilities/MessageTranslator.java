package userInterface.utilities;

import encryptors.Decryptable;
import encryptors.Encryptable;

import static encryptors.CipherChooser.chooseDecryptionCipher;
import static encryptors.CipherChooser.chooseEncryptionCipher;

public class MessageTranslator {

	public static String translateMessage(EncryptMode mode, String message, String key) {
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

	public enum EncryptMode {
		ENCRYPT, DECRYPT, INVALID;

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
