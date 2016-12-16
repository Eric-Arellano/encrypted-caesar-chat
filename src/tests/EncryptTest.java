package tests;

import encryptors.CaesarCipher;
import encryptors.VigenereCipher;
import org.junit.jupiter.api.*;

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
		@DisplayName("normal lowercase encryption")
		void encryptNormalASCII() {
			assertEquals("cdfn", caesar.encryptMessage("zack", "c"));
		}

		@Disabled("Add support for uppercase encryption/decryption")
		@Test
		@DisplayName("uppercase encryption")
		void encryptUppercaseASCII() {
			assertEquals("CDFN", caesar.encryptMessage("ZACK", "C"));
		}

		@Disabled("Add support for special characters, spaces, & numbers in encryption/decryption")
		@Test
		@DisplayName("special characters & numbers")
		void encryptNumbersSpecialCharacters() {
			// TODO: how is this supposed to encrypt?
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
			assertEquals("", vigenere.encryptMessage("", "ab"));
		}

		@Test
		@DisplayName("normal lowercase encryption")
		void encryptNormalASCII() {
			assertEquals("acdm", vigenere.encryptMessage("zack", "ab"));
		}

		@Disabled("Add support for uppercase encryption/decryption")
		@Test
		@DisplayName("uppercase encryption")
		void encryptUppercaseASCII() {
			assertEquals("ACDM", vigenere.encryptMessage("ZACK", "AB"));
		}

		@Disabled("Add support for special characters, spaces, & numbers in encryption/decryption")
		@Test
		@DisplayName("special characters & numbers")
		void encryptNumbersSpecialCharacters() {
			// TODO: how is this supposed to encrypt?
		}

		@Test
		@DisplayName("message shorter than key")
		void encryptMessageShorterThanKey() {
			assertEquals("acfo", vigenere.encryptMessage("zack", "abcdefg"));
		}

	}

}