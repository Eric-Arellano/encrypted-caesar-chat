/**
 * Created by ericarellano on 9/26/16.
 */
public class Cipher {

	public static void main(String[] args) {

		String sampleMessage = "POTUSINTHEHOSUE";
		String sampleKey = "A";
		VigenereCipher sample = new VigenereCipher();

		System.out.print(sample.encryptMessage(sampleMessage, sampleKey));

	}
}
