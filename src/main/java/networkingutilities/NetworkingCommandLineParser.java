package networkingutilities;

import apps.AppChooser;

import static networkingutilities.LocalHostNameUtility.getLocalHostName;

class NetworkingCommandLineParser {

	private final String[] args;
	private final AppChooser chooser;

	NetworkingCommandLineParser(String[] args) {
		this.args = args;
		chooser = new AppChooser(args);
	}

	boolean isClient() {
		return isChatClient() || isNetworkClient();
	}


	private boolean isChatServer() {
		return args.length == 2;
	}

	private boolean isChatClient() {
		return args.length == 3;
	}

	private boolean isNetworkServer() {
		return args.length == 1 || args.length == 4;
	}

	private boolean isNetworkClient() {
		return args.length == 2 || args.length == 5;
	}

	String parseHostName() {
		String hostName;
		if (isLocalClient()) {
			hostName = getLocalHostName();
		} else {
			hostName = getInputtedHostName();
		}
		return hostName;
	}

	private boolean isLocalClient() {
		String HOST_NAME = getInputtedHostName();
		return HOST_NAME.equalsIgnoreCase("local")
				|| HOST_NAME.equalsIgnoreCase("l")
				|| HOST_NAME.equalsIgnoreCase("-l");
	}

	private String getInputtedHostName() {
		if (chooser.isChatApp()) {
			return args[1];
		} else {
			return args[0];
		}
	}

	int parsePortNumber() {
		if (isChatServer()) {
			return Integer.parseInt(args[1]);
		} else if (isChatClient()) {
			return Integer.parseInt(args[2]);
		} else if (isNetworkServer()) {
			return Integer.parseInt(args[0]);
		} else {
			return Integer.parseInt(args[1]);
		}
	}

	boolean includesMessage() {
		return chooser.isNetworkingApp() && (args.length == 4 || args.length == 5);
	}

	String parseAndTranslateInputtedMessage() {
		final int START_MESSAGE_INDEX = parseMessageArgIndex();
		String mode = args[START_MESSAGE_INDEX];
		String message = args[START_MESSAGE_INDEX + 1];
		String key = args[START_MESSAGE_INDEX + 2];
		return inpututilities.CommandLineParser.parseAndTranslateInputtedMessage(mode, message, key);
	}

	private int parseMessageArgIndex() {
		if (isNetworkServer()) {
			return 1;
		} else {
			return 2;
		}
	}
}
