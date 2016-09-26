/**
 * Created by ericarellano on 9/26/16.
 */
public class Cipher {

	public static void main(String[] args) {

		String sampleMessage = "POTUSINTHEHOSUE";
		int sampleKey = 1;
		CaesarCipher sample = new CaesarCipher();

		System.out.print(sample.encryptMessage(sampleMessage, sampleKey));

	}
}
