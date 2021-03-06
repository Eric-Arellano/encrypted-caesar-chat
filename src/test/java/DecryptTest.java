import encryptors.Decryptable;
import org.junit.jupiter.api.*;

import static encryptors.CipherChooser.chooseDecryptionCipher;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("decryption test")
class DecryptTest {

	@Nested
	@DisplayName("Caesar")
	class CaesarTest {

		private Decryptable caesar;

		@BeforeEach
		void instantiateCipher() {
			caesar = chooseDecryptionCipher("s");
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

		@Disabled("Add support for spaces in encryption/decryption")
		@Test
		@DisplayName("spaces decryption")
		void decryptSpaces() {
			assertEquals("za ck", caesar.decryptMessage("cd fn", "c"));
		}

		@Disabled("Add support for numbers in encryption/decryption")
		@Test
		@DisplayName("numbers decryption")
		void decryptNumbers() {
			// TODO: how is this supposed to decrypt?
		}

		@Disabled("Add support for special characters in encryption/decryption")
		@Test
		@DisplayName("special characters decryption")
		void decryptNumbersSpecialCharacters() {
			// TODO: how is this supposed to decrypt?
		}

	}

	@Nested
	@DisplayName("Vigenere")
	class VigenereTest {

		private Decryptable vigenere;

		@BeforeEach
		void instantiateVigenere() {
			vigenere = chooseDecryptionCipher("sample");
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

		@Disabled("Add support for spaces in encryption/decryption")
		@Test
		@DisplayName("spaces decryption")
		void decryptSpaces() {
			assertEquals("za ck", vigenere.decryptMessage("ac dm", "ab"));
		}

		@Disabled("Add support for numbers in encryption/decryption")
		@Test
		@DisplayName("numbers decryption")
		void decryptNumbers() {
			// TODO: how is this supposed to decrypt?
		}

		@Disabled("Add support for special characters in encryption/decryption")
		@Test
		@DisplayName("special characters decryption")
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