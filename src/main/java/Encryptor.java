import apps.AppChooser;
import apps.Launchable;

/**
 * Allows encryption of String with either Caesar or Vigenere cipher.
 */
public class Encryptor {

	public static void main(String[] args) {
		Launchable app = new AppChooser(args);
		app.launchApp();
	}
}
