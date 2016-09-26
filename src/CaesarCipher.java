public class CaesarCipher implements Encryptable {

	public String encryptMessage(String message, String key) {

		char[] encryptedChars = message.toCharArray();
		System.out.println(encryptedChars);
		char keyChar = key.charAt(0);

		// shift down to letters
		for (char element : encryptedChars) {
			element = (char)(element - 65);
		}
		System.out.println(encryptedChars);

		// add key
		for (char element : encryptedChars) {
			element = (char)((element + keyChar) % 26);
		}
		System.out.println(encryptedChars);

		// shift back up
		for (char element : encryptedChars) {
			element = (char)(element + 65);
		}
		System.out.println(encryptedChars);
		
		// return result
		String encryptedMessage = encryptedChars.toString();
		return encryptedMessage;

	}


}
