package tests;

import encryptors.CaesarCipher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("decryption test")
class DecryptTest {

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
			assertEquals("", caesar.decryptMessage("", "t"));
		}

		@Test
		@DisplayName("normal lowercase decryption")
		void decryptNormalASCII() {
			assertEquals("zack", caesar.decryptMessage("cdfn", "c"));
		}

        @Test
        @DisplayName("uppercase decryption")
        void decryptUppercaseASCII() {
            assertEquals("ZACK", caesar.decryptMessage("CDFN", "c"));
        }

		@Test
		@DisplayName("special characters & numbers")
		void decryptNumbersSpecialCharacters() {
			// TODO: how is this supposed to decrypt?
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