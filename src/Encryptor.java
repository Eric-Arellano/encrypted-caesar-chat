/**
 * Allows encryption of String with either Caesar or Vigenere cipher.
 */
public class Encryptor {

	public static void main(String[] args) {

		// ----------------------------------------------------------
		// Sample Encryption/Decryption
		// ----------------------------------------------------------

		String sampleMessage = "TESTTEST";
		String sampleKey = "A";
		VigenereCipher sample = new VigenereCipher();

		String encryptedSample = sample.encryptMessage(sampleMessage, sampleKey);
		System.out.print(encryptedSample);
		System.out.println();
		System.out.println(sample.decryptMessage(encryptedSample, sampleKey));
		System.out.println();

		// ----------------------------------------------------------
		// Letter Analysis / breaking key
		// ----------------------------------------------------------

		LetterFrequencyAnalysis letterFrequency = new LetterFrequencyAnalysis();
		String encryptedMessage = sample.encryptMessage("TESTTESTTESTTESTS", "A");
		letterFrequency.analyzeLetterFrequency(encryptedMessage, 1);
		String testKey = "A";
		System.out.println(sample.decryptMessage(encryptedMessage, testKey));

	}
}