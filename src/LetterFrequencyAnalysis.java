public final class LetterFrequencyAnalysis {

	public void analyzeLetterFrequency(String message, int keyLength) {
		String[] submessages = divideMessage(message, keyLength);

		// display and count letter frequency
		System.out.println(tableTitle);
		for (int arrayIndex = 0; arrayIndex < keyLength; arrayIndex++) {
			LetterMap letterMap = new LetterMap(submessages[arrayIndex]);
			letterMap.countLetterFrequency();
			System.out.println(letterMap.getLetterFrequency());
		}

	}

	private String[] divideMessage(String message, int keyLength) {
		String[] submessages = new String[keyLength];
		final int jump = keyLength - 1;

		if (keyLength < 2) {
			submessages[0] = message;
		} else {
			for (int arrayIndex = 0; arrayIndex < keyLength; arrayIndex++) {
				StringBuilder substring = new StringBuilder();
				int charIndex = arrayIndex;
				while (charIndex < message.length()) {
					String newChar = message.substring(charIndex, charIndex + 1);
					charIndex += jump;
					substring.append(newChar);
				}
				submessages[arrayIndex] = substring.toString();
			}
		}

		return submessages;
	}

	private String tableTitle = "A   B   C   D   E   F   G   H   I   J   K   L   M   N   O   P   Q " +
			"  R   S   T   U   V   W   X   Y   " +
			"Z\n-----------------------------------------------------------------------";

}
