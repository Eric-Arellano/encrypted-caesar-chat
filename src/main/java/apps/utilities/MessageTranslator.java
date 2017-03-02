package apps.utilities;

import encryptors.Decryptable;
import encryptors.Encryptable;

import static apps.utilities.EncryptMode.isDecryption;
import static apps.utilities.EncryptMode.isEncryption;
import static encryptors.CipherChooser.chooseDecryptionCipher;
import static encryptors.CipherChooser.chooseEncryptionCipher;

public class MessageTranslator {

	public static String translateMessage(EncryptMode mode, String message, String key) {
		if (isEncryption(mode)) {
			Encryptable encryptor = chooseEncryptionCipher(key);
			return encryptor.encryptMessage(message, key);
		} else if (isDecryption(mode)) {
			Decryptable decryptor = chooseDecryptionCipher(key);
			return decryptor.decryptMessage(message, key);
		} else {
			return "Invalid encryption or decryption mode.";
		}
	}

}
