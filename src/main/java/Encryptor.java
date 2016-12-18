import userInterface.InterfaceChooser;

/**
 * Allows encryption of String with either Caesar or Vigenere cipher.
 */
public class Encryptor {

	public static void main(String[] args) {
		InterfaceChooser app = new InterfaceChooser(args);
		app.launchApp();
	}
}
