package encryptors;

public class CipherChooser {

	public static Encryptable chooseEncryptionCipher(String key) {
		int keyLength = key.length();
		if (keyLength == 1) {
			return new CaesarCipher();
		} else {
			return new VigenereCipher();
		}
	}

	public static Decryptable chooseDecryptionCipher(String key) {
		int keyLength = key.length();
		if (keyLength == 1) {
			return new CaesarCipher();
		} else {
			return new VigenereCipher();
		}
	}

}
