package networkingutilities;

import java.net.InetAddress;
import java.net.UnknownHostException;

class LocalHostNameUtility {

	static String getLocalHostName() {
		String hostName;
		try {
			hostName = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException unknownHostException) {
			throw new IllegalArgumentException("Cannot resolve host name.");
		}
		return hostName;
	}

	static String getLocalIPAddress() {
		String ipAddress;
		try {
			ipAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException unknownHostException) {
			throw new IllegalArgumentException("Cannot resolve IP address.");
		}
		return ipAddress;
	}
}
