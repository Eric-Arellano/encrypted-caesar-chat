public class CaesarCipher implements Encryptable {

	public String encryptMessage(String message, String shift) {

		char[] encryptedChars = message.toCharArray();
		System.out.println(encryptedChars);
		int shiftNumber = convertKey(shift);

		for (int index = 0; index < message.length(); index++) {
			char letter = encryptedChars[index];
			int value = letter - 65;
//			System.out.print(value + " ");
			value = (value + shiftNumber) % 26;
//			System.out.print(value + " ");
			value = value + 65;
//			System.out.print(value + " ");
			encryptedChars[index] = (char)(value);
//			System.out.println();
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
