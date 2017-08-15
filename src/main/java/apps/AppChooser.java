package apps;

public class AppChooser implements Launchable {

	private final String[] args;

	public AppChooser(String[] args) {
		this.args = args;
	}

	public void launchApp() {
		// determine app type
		Launchable app;
		if (isConsoleApp()) {
			app = new ConsoleApp();
		} else if (isCommandLineApp()) {
			app = new CommandLineApp(args);
		} else if (isNetworkingApp()) {
			app = new NetworkingApp(args);
		} else if (isChatApp()) {
			app = new ChatApp(args);
		} else {
			throw new IllegalArgumentException("Invalid command line arguments.");
		}
		// launch app
		app.launchApp();
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
