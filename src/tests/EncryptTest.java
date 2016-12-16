package tests;

import encryptors.CaesarCipher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("encryption test")
class EncryptTest {

	@Nested
	@DisplayName("Caesar")
	class CaesarTest {

		private CaesarCipher caesar;

		@BeforeEach
		void instantiateCipher() {
			caesar = new CaesarCipher();
		}

		@Test
		@DisplayName("no string")
		void noString() {
			assertEquals("", caesar.encryptMessage("", "t"));
		}

		@Test
		@DisplayName("normal encryption")
		void encryptNormalASCII() {
			assertEquals("cdfn", caesar.encryptMessage("zack", "c"));
		}

		@Test
		@DisplayName("special characters & numbers")
		void encryptNumbersSpecialCharacters() {
			// TODO: how is this supposed to encrypt?
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