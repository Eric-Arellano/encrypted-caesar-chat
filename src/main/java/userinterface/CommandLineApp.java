package userinterface;

import static userinterface.utilities.CommandLineParser.parseAndTranslateInputtedMessage;

class CommandLineApp implements Launchable {

	private final String[] args;

	CommandLineApp(String[] args) {
		this.args = args;
	}

	public void launchApp() {
		String mode = args[0];
		String message = args[1];
		String key = args[2];
		String translatedMessage = parseAndTranslateInputtedMessage(mode, message, key);
		System.out.println(translatedMessage);
	}

}
