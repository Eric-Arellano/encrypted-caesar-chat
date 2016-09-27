public class CaesarCipher implements Encryptable {

	public String encryptMessage(String message, String shift) {

		char[] encryptedChars = message.toCharArray();
		System.out.println(encryptedChars);
		int shiftNumber = convertKey(shift);

		for (int index = 0; index < message.length(); index++) {
			char letter = encryptedChars[index];
			int value = letter - 65;
			value = (value + shiftNumber) % 26;
			value = value + 65;
			encryptedChars[index] = (char)(value);
		}
		return new String(encryptedChars);

	}

	private int convertKey(String key) {
		int shift = key.charAt(0);
		shift = (shift - 65) % 26;
		shift++; // increment in order to produce shift
		return shift;
	}


}
