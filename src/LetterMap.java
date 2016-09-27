import java.util.LinkedHashMap;

class LetterMap extends LinkedHashMap<Character, Integer> {

	private String message;

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
		this.put('X', 0);
		this.put('Y', 0);
		this.put('Z', 0);

		this.message = message;
	}

	void countLetterFrequency() {

	}

	String getLetterFrequency() {
		StringBuilder result = new StringBuilder();
		for (char index = 'A'; index <= 'Z'; index++) {
			result.append(index + " ");
		}
		return result.toString();
	}

}
