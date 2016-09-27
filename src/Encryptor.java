/**
 * Allows encryption of String with either Caesar or Vigenere cipher.
 */
public class Encryptor {

	public static void main(String[] args) {

		String sampleMessage = "TESTTEST";
		String sampleKey = "AB";
		VigenereCipher sample = new VigenereCipher();

		System.out.print(sample.encryptMessage(sampleMessage, sampleKey));

	}
}