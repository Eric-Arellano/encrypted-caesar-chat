package apps.networkingutilities;

import java.net.InetAddress;
import java.net.UnknownHostException;

class LocalHostNameUtility {

	static String getLocalHostName() {
		String hostName = "";
		try {
			hostName = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException unknownHostException) {
			System.out.println("Host cannot be resolved.");
			System.exit(1);
		}
		return hostName;
	}

	static String getLocalIPAddress() {
		String ipAddress = "";
		try {
			ipAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException unknownHostException) {
			System.out.println("IP address cannot be resolved.");
			System.exit(1);
		}
		return ipAddress;
	}
}
