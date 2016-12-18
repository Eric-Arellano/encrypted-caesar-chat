import encryptors.Encryptable;
import org.junit.jupiter.api.*;

import static encryptors.CipherChooser.chooseEncryptionCipher;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("encryption test")
class EncryptTest {

	@Nested
	@DisplayName("Caesar")
	class CaesarTest {

		private Encryptable caesar;

		@BeforeEach
		void instantiateCipher() {
			caesar = chooseEncryptionCipher("s");
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

		@Test
		@DisplayName("uppercase encryption")
		void encryptUppercaseASCII() {
			assertEquals("CDFN", caesar.encryptMessage("ZACK", "C"));
		}

		@Test
		@DisplayName("mixed case encryption")
		void encryptMixedcaseASCII() {
			assertEquals("Cdfn", caesar.encryptMessage("Zack", "c"));
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

		private Encryptable vigenere;

		@BeforeEach
		void instantiateVigenere() {
			vigenere = chooseEncryptionCipher("sample");
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

		@Test
		@DisplayName("uppercase encryption")
		void encryptUppercaseASCII() {
			assertEquals("ACDM", vigenere.encryptMessage("ZACK", "AB"));
		}

		@Test
		@DisplayName("mixed case encryption")
		void encryptMixedcaseASCII() {
			assertEquals("Acdm", vigenere.encryptMessage("Zack", "Ab"));
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