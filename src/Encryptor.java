import userInterface.CommandLineApp;

/**
 * Allows encryption of String with either Caesar or Vigenere cipher.
 */
public class Encryptor {

	public static void main(String[] args) {

		// Console
//		ConsoleApp app = new ConsoleApp();
//		app.launchConsoleApp();

		// Command Line
		CommandLineApp app = new CommandLineApp(args);
		app.launchCommandLineApp();

	}
}
