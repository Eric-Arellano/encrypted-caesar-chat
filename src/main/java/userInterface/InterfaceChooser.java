package userInterface;

import networking.Client;
import networking.Server;

import static userInterface.UserInputHelper.InvalidInputException;

public class InterfaceChooser {

	public String[] args;

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
		ConsoleApp app = new ConsoleApp();
		app.launchConsoleApp();
	}

	private void launchCommandLineApp() {
		CommandLineApp app = new CommandLineApp(args);
		app.launchCommandLineApp();
	}

	private void launchServer() {
		Server app = new Server(args);
		app.launchConnection();
	}

	private void launchClient() {
		Client app = new Client(args);
		app.launchConnection();
	}

}
