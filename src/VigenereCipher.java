public class VigenereCipher implements Encryptable {

	public String encryptMessage(String message, String key) {
		return "";
	}

	private int[] convertKey(String key) {
		char[] keys = key.toCharArray();
		int[] shifts = new int[keys.length];
		for (int index = 0; index < keys.length; index++) {
			shifts[index] = keys[index];
			shifts[index] = (shifts[index] - 65) % 26;
			shifts[index]++;
		}
		return shifts;
	}

}