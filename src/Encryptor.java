import analysis.LetterFrequencyAnalysis;
import encryptors.CaesarCipher;
import encryptors.VigenereCipher;
/**
 * Allows encryption of String with either Caesar or Vigenere cipher.
 */
public class Encryptor {

	public static void main(String[] args) {

		// ----------------------------------------------------------
		// Sample Encryption/Decryption
		// ----------------------------------------------------------

		String sampleMessage = "TEST";
		String sampleKey = "YETI";
		CaesarCipher caesar = new CaesarCipher();
		VigenereCipher vigenere = new VigenereCipher();

//		String encryptedSample = vigenere.encryptMessage(sampleMessage, sampleKey);
//		System.out.print(encryptedSample);
//		System.out.println();
//		System.out.println(vigenere.decryptMessage(encryptedSample, sampleKey));
//		System.out.println();

		// ----------------------------------------------------------
		// Letter Analysis / breaking key
		// ----------------------------------------------------------

		LetterFrequencyAnalysis letterFrequency = new LetterFrequencyAnalysis();
		String encryptedMessage =
				"XNWISEZKDSQDJLIKTEVZVMIIDTWIHEITWRWKDRLIJMWWIHMSDMJVWALKLOKFBPTVIEAVISWWROVKPCBJDNMWDRQEEUBKDWIISSBYTRMWAEKKDRIESTPVDTPVGFWIDUBGJTNIDMBYTRMWAEKKDRAFIHIKIHMITFTVRTMUHIOEPLKFJLLGPSASPCSKWRWLVHIJTPIIPTMJTTWWROVKPCBTAKYSRCDWALFCEPLCDZVSAVUUOCILIZVQRCJWEANWIKYBALVROVKPCBNXTPKWEXCPTMFCTWNWIKYIHMPLEZVAOIUTDBYTBZLHHMJPNLKWEKFGRMJEOVUXNOJTTWWROVKPCBJDNBYTPTRIEEVGEIIGAVXTDQEUOCIROVTTNBIXCKZGCTVHONKLEVKNSQOIHMFJTMIEAQIDFKZGCTVHIVGJTIESOCKEUBNTRMVFUQMPLMEITWKWEKLGRMEIIVRCEVZVMIGPSAZCGQEDNMUXRMTIIWEIHZFJGPKWEATGAUSAEZRCDBYTIVETRXRXRMHJIDRAEVKIOBYTCCIGEVKULWNXNOZCTPVDPXFHIBVSIZVRTQFC";
		letterFrequency.analyzeLetterFrequency(encryptedMessage, 4);
		System.out.println();
		String testKey = "PAIR";
		System.out.println(vigenere.decryptMessage(encryptedMessage, testKey));
	}
}