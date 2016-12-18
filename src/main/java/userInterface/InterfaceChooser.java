package userInterface;

import userInterface.networking.NetworkingApp;

import static userInterface.UserInputHelper.InvalidInputException;

public class InterfaceChooser implements Launchable {

	private String[] args;
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
			} else {
				throw new InvalidInputException("Invalid command line arguments.");
			}
		} catch (InvalidInputException invalidCommandLineException) {
			System.out.println("Invalid command line arguments.");
			System.exit(1);
		}

	}

	private boolean isConsoleApp() {
		return args.length == 0;
	}

	private boolean isCommandLineApp() {
		return args.length == 3;
	}

	private boolean isNetworkingApp() {
		return args.length == 1 || args.length == 5;
	}

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

}
