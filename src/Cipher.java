/**
 * Created by ericarellano on 9/26/16.
 */
public class Cipher {

	public static void main(String[] args) {

		String sampleMessage = "LAVALOSPLATOS";
		String sampleKey = "J";
		VinereCipher sample = new VinereCipher(sampleMessage, sampleKey);

		System.out.print(sample.encryptMessage());

	}
}
