package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("encryption test")
class EncryptTest {

	@Nested
	@DisplayName("Caesar")
	class CaesarTest {

		@Test
		@DisplayName("no string")
		void noString() {

		}

		@Test
		@DisplayName("normal encryption")
		void encryptNormalASCII() {

		}

		@Test
		@DisplayName("special characters & numbers")
		void encryptNumbersSpecialCharacters() {

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
		@DisplayName("normal encryption")
		void encryptNormalASCII() {

		}

		@Test
		@DisplayName("special characters & numbers")
		void encryptNumbersSpecialCharacters() {

		}

		@Test
		@DisplayName("message shorter than key")
		void encryptMessageShorterThanKey() {

		}

	}

}