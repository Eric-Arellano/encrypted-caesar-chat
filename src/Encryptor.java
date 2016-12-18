import userInterface.CommandLineApp;
import userInterface.ConsoleApp;

/**
 * Allows encryption of String with either Caesar or Vigenere cipher.
 */
public class Encryptor {

	public static void main(String[] args) {

		if (args.length > 0) {
			CommandLineApp app = new CommandLineApp(args);
			app.launchCommandLineApp();
		} else {
			ConsoleApp app = new ConsoleApp();
			app.launchConsoleApp();
		}

	}
}
