/**
 * Allows encryption of String with either Caesar or Vigenere cipher.
 */
public class Encryptor {

	public static void main(String[] args) {

		String sampleMessage = "TESTTEST";
		String sampleKey = "A";
		VigenereCipher sample = new VigenereCipher();
		LetterFrequencyAnalysis letterFrequency = new LetterFrequencyAnalysis();

		letterFrequency.analyzeLetterFrequency(sampleMessage, 1);

		System.out.print(sample.encryptMessage(sampleMessage, sampleKey));

	}
}