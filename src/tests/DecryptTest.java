package tests;

import encryptors.CaesarCipher;
import encryptors.VigenereCipher;
import org.junit.jupiter.api.*;

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
			assertEquals("ZACK", caesar.decryptMessage("CDFN", "C"));
		}

		@Test
		@DisplayName("mixed case decryption")
		void decryptMixedcaseASCII() {
			assertEquals("Zack", caesar.decryptMessage("Cdfn", "c"));
		}

		@Disabled("Add support for special characters, spaces, & numbers in encryption/decryption")
		@Test
		@DisplayName("special characters & numbers")
		void decryptNumbersSpecialCharacters() {
			// TODO: how is this supposed to decrypt?
		}

	}

	@Nested
	@DisplayName("Vigenere")
	class VigenereTest {

		private VigenereCipher vigenere;

		@BeforeEach
		void instantiateVigenere() {
			vigenere = new VigenereCipher();
		}

		@Test
		@DisplayName("no string")
		void noString() {
			assertEquals("", vigenere.decryptMessage("", "ab"));
		}

		@Test
		@DisplayName("normal lowercase decryption")
		void decryptNormalASCII() {
			assertEquals("zack", vigenere.decryptMessage("acdm", "ab"));
		}

		@Test
		@DisplayName("uppercase decryption")
		void decryptUppercaseASCII() {
			assertEquals("ZACK", vigenere.decryptMessage("ACDM", "AB"));
		}

		@Test
		@DisplayName("mixed case decryption")
		void decryptMixedcaseASCII() {
			assertEquals("Zack", vigenere.decryptMessage("Acdm", "Ab"));
		}

		@Disabled("Add support for special characters, spaces, & numbers in encryption/decryption")
		@Test
		@DisplayName("special characters & numbers")
		void decryptNumbersSpecialCharacters() {
			// TODO: how is this supposed to decrypt?
		}

		@Test
		@DisplayName("message shorter than key")
		void decryptMessageShorterThanKey() {
			assertEquals("zack", vigenere.decryptMessage("acfo", "abcdefg"));
		}

	}

}