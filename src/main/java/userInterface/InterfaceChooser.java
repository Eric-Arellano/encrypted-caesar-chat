package userInterface;

import userInterface.networking.ChatApp;
import userInterface.networking.NetworkingApp;
import userInterface.utilities.InvalidInputException;

public class InterfaceChooser implements Launchable {

	private final String[] args;
	private Launchable app;

	public InterfaceChooser(String[] args) {
		this.args = args;
	}

	public void launchApp() {
		try {
			if (isConsoleApp()) {
				launchConsoleApp();
			} else if (isCommandLineApp()) {
				launchCommandLineApp();
			} else if (isNetworkingApp()) {
				launchNetworkingApp();
			} else if (isChatApp()) {
				launchChatApp();
			} else {
				throw new InvalidInputException("Invalid command line arguments.");
			}
		} catch (InvalidInputException invalidCommandLineException) {
			System.out.println("Invalid command line arguments.");
			System.exit(1);
		}
	}


	// -----------------------------------------------------------------------------------
	// Determine app type
	// -----------------------------------------------------------------------------------

	private boolean isConsoleApp() {
		return args.length == 0;
	}

	private boolean isCommandLineApp() {
		return isCommandLineArgsLength() && isNotChatApp();
	}

	private boolean isCommandLineArgsLength() {
		return args.length == 3;
	}

	private boolean isNetworkingApp() {
		return isNetworkingArgsLength() && isNotChatApp();
	}

	private boolean isNetworkingArgsLength() {
		return args.length == 1 || args.length == 2 || args.length == 4 || args.length == 5;
	}

	private boolean isNotChatApp() {
		return !isChatApp();
	}

	private boolean isChatApp() {
		return isChatArgsLength() && isChatPrefix();
	}

	private boolean isChatArgsLength() {
		return args.length == 2 || args.length == 3;
	}

	private boolean isChatPrefix() {
		String prefix = args[0];
		return prefix.equalsIgnoreCase("chat")
				|| prefix.equalsIgnoreCase("c")
				|| prefix.equalsIgnoreCase("-c");
	}


	// -----------------------------------------------------------------------------------
	// Launch apps
	// -----------------------------------------------------------------------------------

	private void launchConsoleApp() {
		app = new ConsoleApp();
		app.launchApp();
	}

	private void launchCommandLineApp() {
		app = new CommandLineApp(args);
		app.launchApp();
	}

	private void launchNetworkingApp() {
		app = new NetworkingApp(args);
		app.launchApp();
	}

	private void launchChatApp() {
		app = new ChatApp(args);
		app.launchApp();
	}

}
