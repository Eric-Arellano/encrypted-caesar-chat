package apps.networkingutilities;

import java.io.IOException;

interface Connection {

	void listenForMessage() throws IOException;

	void sendMessage() throws IOException;

	void closeConnection() throws IOException;

	enum ConnectionType {
		CLIENT, SERVER;

		@Override
		public String toString() {
			return name().toLowerCase();
		}

		static String getOtherConnectionType(ConnectionType currentType) {
			switch (currentType) {
				case CLIENT:
					return SERVER.toString();
				case SERVER:
					return CLIENT.toString();
				default:
					return null;
			}
		}

	}
}
