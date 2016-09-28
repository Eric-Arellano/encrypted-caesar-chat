import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedHashMap;

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

	private final String tableTitle = "A   B   C   D   E   F   G   H   I   J   K   L   M   N   O   " +
			"P   Q " +
			"  R   S   T   U   V   W   X   Y   " +
			"Z\n-----------------------------------------------------------------------";


	private class LetterMap extends LinkedHashMap<Character, Integer> {

		private final String message;

		LetterMap(String message) {
			super(26);
			this.put('A', 0);
			this.put('B', 0);
			this.put('C', 0);
			this.put('D', 0);
			this.put('E', 0);
			this.put('F', 0);
			this.put('G', 0);
			this.put('H', 0);
			this.put('I', 0);
			this.put('J', 0);
			this.put('K', 0);
			this.put('L', 0);
			this.put('M', 0);
			this.put('N', 0);
			this.put('O', 0);
			this.put('P', 0);
			this.put('Q', 0);
			this.put('R', 0);
			this.put('S', 0);
			this.put('T', 0);
			this.put('U', 0);
			this.put('V', 0);
			this.put('W', 0);
			this.put('X', 0);
			this.put('Y', 0);
			this.put('Z', 0);

			this.message = message;
		}

		void countLetterFrequency() {
			try (StringReader reader = new StringReader(this.message)) {
				boolean moreText = true;
				while (moreText) {
					int value = reader.read();
					incrementCount((char) value);
					if (value == -1) {
						moreText = false;
					}
				}
			} catch (IOException exception) {
				System.out.println("Issue analyzing letters.");
			}
		}

		private void incrementCount(char letter) {
			if (letter >= 'A' && letter <= 'Z') {
				this.put(letter, this.get(letter) + 1);
			}
		}

		String getLetterFrequency() {
			StringBuilder result = new StringBuilder();
			for (char index = 'A'; index <= 'Z'; index++) {
				int count = this.get(index);
				result.append(count).append("  ");
			}
			return result.toString();
		}

	}
}
