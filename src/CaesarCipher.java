public class CaesarCipher implements Encryptable {

	public String encryptMessage(String message, String key) {

		char[] encryptedChars = message.toCharArray();
		System.out.println(encryptedChars);
		int shift = (int)(key.charAt(0));

		for (int index = 0; index < message.length(); index++) {
			char letter = encryptedChars[index];
			letter = (char)(letter - 65);
			letter = (char)((letter + shift) % 26);
			letter = (char)(letter + 65);
			encryptedChars[index] = letter;
		}
		return new String(encryptedChars);

	}


}
