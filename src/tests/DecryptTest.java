package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("decryption test")
class DecryptTest {

	@Nested
	@DisplayName("Caesar")
	class CaesarTest {

		@Test
		@DisplayName("no string")
		void noString() {

		}

		@Test
		@DisplayName("normal decryption")
		void decryptNormalASCII() {

		}

		@Test
		@DisplayName("special characters & numbers")
		void decryptNumbersSpecialCharacters() {

		}

	}

	@Nested
	@DisplayName("Vigenere")
	class VigenereTest {

		@Test
		@DisplayName("no string")
		void noString() {

		}

		@Test
		@DisplayName("normal decryption")
		void decryptNormalASCII() {

		}

		@Test
		@DisplayName("special characters & numbers")
		void decryptNumbersSpecialCharacters() {

		}

		@Test
		@DisplayName("message shorter than key")
		void decryptMessageShorterThanKey() {

		}

	}

}