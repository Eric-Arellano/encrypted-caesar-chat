/**
 * Created by ericarellano on 9/26/16.
 */
public class Cipher {

	public static void main(String[] args) {

		String sampleMessage = "LAVALOSPLATOS";
		char sampleKey = 'J';
		CaesarCipher sample = new CaesarCipher(sampleMessage, sampleKey);

		System.out.print(sample.encryptMessage());

	}
}
