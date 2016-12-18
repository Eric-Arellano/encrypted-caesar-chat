package userInterface;

import userInterface.networking.Client;
import userInterface.networking.Server;

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
			} else if (isServer()) {
				launchServer();
			} else if (isClient()) {
				launchClient();
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

	private boolean isServer() {
		return args.length == 4;
	}

	private boolean isClient() {
		return args.length == 5;
	}

	private void launchConsoleApp() {
		app = new ConsoleApp();
		app.launchApp();
	}

	private void launchCommandLineApp() {
		app = new CommandLineApp(args);
		app.launchApp();
	}

	private void launchServer() {
		app = new Server(args);
		app.launchApp();
	}

	private void launchClient() {
		app = new Client(args);
		app.launchApp();
	}

}
