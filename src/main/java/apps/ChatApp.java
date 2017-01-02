package apps;

import apps.networkingutilities.ConnectionInterfacer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static apps.utilities.MessageTranslator.EncryptMode;
import static apps.utilities.MessageTranslator.translateMessage;
import static apps.utilities.UserInputParser.getValidStringInput;

class ChatApp implements Launchable {

	private final ConnectionInterfacer connectionInterfacer;
	private volatile boolean keepRunning;
	private volatile boolean messageReceived;

	ChatApp(String[] args) {
		this.connectionInterfacer = new ConnectionInterfacer(args);
		this.keepRunning = true;
		this.messageReceived = false;
	}

	public void launchApp() {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		Runnable networkListener = createNetworkListener();
		Runnable commandLineInterface = createCommandLineInterface(executor);

		connectionInterfacer.launchConnection();
		executor.submit(networkListener);
		executor.submit(commandLineInterface);
	}

	private synchronized void closeApp() {
		keepRunning = false;
		notifyAll();
		connectionInterfacer.closeConnection();
	}

	private Runnable createNetworkListener() {
		return () -> {
			while (keepRunning) {
				connectionInterfacer.listenForMessage();
				messageReceived = true;
				notifyAll();
			}
		};
	}

	private Runnable createCommandLineInterface(ExecutorService executor) {
		return () -> {
			closeApp();
			// TODO: ask to:
			// 1) send message that will be encrypted upon arrival
			// 2) wait until receiving encrypted message that can then be decrypted locally with key
			// (never send key)
		};
	}


	// -----------------------------------------------------------------------------------
	// Receive and decrypt message
	// -----------------------------------------------------------------------------------

	private synchronized void receiveMessage() {
		if (messageReceived) {
			askToDecryptReceivedMessage();
			String encryptedMessage = requestMessage();
			String key = requestKey();
			decryptMessage(encryptedMessage, key);
			messageReceived = false;
		}
	}

	private void askToDecryptReceivedMessage() {

	}

	private void decryptMessage(String encryptedMessage, String key) {

	}


	// -----------------------------------------------------------------------------------
	// Encrypt and send message
	// -----------------------------------------------------------------------------------

	private void encryptAndSendMessage() {
		String message = requestMessage();
		String key = requestKey();
		String encryptedMessage = translateMessage(EncryptMode.ENCRYPT, message, key);
		sendMessage(encryptedMessage);
	}

	private String requestMessage() {
		promptMessage();
		return listenForMessage();
	}

	private void promptMessage() {
		String prompt = String.format("Please type your decrypted message (use lowercase and/or " +
				"uppercase letters, but no spaces or special characters): ");
		System.out.println(prompt);
	}

	private String listenForMessage() {
		return getValidStringInput();
	}

	private String requestKey() {
		promptKey();
		return listenForKey();
	}

	private void promptKey() {
		String prompt = String.format("Please type your encryption key (use a lowercase letter or " +
				"letters): ");
		System.out.println(prompt);
	}

	private String listenForKey() {
		return getValidStringInput();
	}

	private synchronized void sendMessage(String message) {
		connectionInterfacer.sendMessage();
	}

}
