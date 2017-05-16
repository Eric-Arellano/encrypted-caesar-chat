package inpututilities;

public enum EncryptMode {

	ENCRYPT, DECRYPT, INVALID, QUIT_APP;

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

	public static boolean isEncryption(EncryptMode mode) {
		return mode.equals(EncryptMode.ENCRYPT);
	}

	public static boolean isDecryption(EncryptMode mode) {
		return mode.equals(EncryptMode.DECRYPT);
	}

	public static boolean isQuitApp(EncryptMode mode) {
		return mode.equals(EncryptMode.QUIT_APP);
	}

}
