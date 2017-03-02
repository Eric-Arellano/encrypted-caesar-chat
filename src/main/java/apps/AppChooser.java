package apps;

import apps.utilities.InvalidInputException;

public class AppChooser implements Launchable {

	private final String[] args;
	private Launchable app;

	public AppChooser(String[] args) {
		this.args = args;
	}

	public void launchApp() {
		try {
			if (isConsoleApp()) {
				app = new ConsoleApp();
			} else if (isCommandLineApp()) {
				app = new CommandLineApp(args);
			} else if (isNetworkingApp()) {
				app = new NetworkingApp(args);
			} else if (isChatApp()) {
				app = new ChatApp(args);
			} else {
				throw new InvalidInputException("Invalid command line arguments.");
			}
			app.launchApp();
		} catch (InvalidInputException invalidCommandLineException) {
			System.out.println("Invalid command line arguments.");
			System.exit(1);
		}
	}

	private boolean isConsoleApp() {
		return args.length == 0;
	}

	private boolean isCommandLineApp() {
		return isCommandLineArgsLength() && isNotChatApp();
	}

	private boolean isCommandLineArgsLength() {
		return args.length == 3;
	}

	public boolean isNetworkingApp() {
		return isNetworkingArgsLength() && isNotChatApp();
	}

	private boolean isNetworkingArgsLength() {
		return args.length == 1 || args.length == 2 || args.length == 4 || args.length == 5;
	}

	private boolean isNotChatApp() {
		return !isChatApp();
	}

	public boolean isChatApp() {
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

}
