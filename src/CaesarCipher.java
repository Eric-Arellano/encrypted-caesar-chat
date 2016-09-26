public class CaesarCipher {

	public String encryptMessage(String message, int shift) {

		char[] encryptedChars = message.toCharArray();
		System.out.println(encryptedChars);

		for (int index = 0; index < message.length(); index++) {
			char letter = encryptedChars[index];
			int value = letter - 65;
//			System.out.print(value + " ");
			value = (value + shift) % 26;
//			System.out.print(value + " ");
			value = value + 65;
//			System.out.print(value + " ");
			encryptedChars[index] = (char)(value);
//			System.out.println();
		}
		return new String(encryptedChars);

	}


}
