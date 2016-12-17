package encryptors;

class ConversionUtilities {

    static char convertCharDownFromASCII(char letter, Case CASE) {
        return (char) (letter - CASE.asciiShift());
    }

    static char convertCharBackToASCII(char letter, Case CASE) {
        return (char) (letter + CASE.asciiShift());
    }

    static char shiftKey(char letter) {
        final int IGNORE_VARIABLE_SHIFT = 0;
        return ShiftCharType.CONVERT_KEY.shiftChar(letter, IGNORE_VARIABLE_SHIFT);
    }

    static char shiftChar(char letter, int shift, ShiftCharType encryptOrDecrypt) {
        return encryptOrDecrypt.shiftChar(letter, shift);
    }

    enum Case {
        UPPERCASE(65), LOWERCASE(97);

        private final int asciiShiftValue;

        Case(int asciiShiftValue) {
            this.asciiShiftValue = asciiShiftValue;
        }

        public int asciiShift() {
            return asciiShiftValue;
        }
    }

    enum ShiftCharType {
        ENCRYPT {
            char shiftChar(char letter, int shift) {
                return (char) Math.floorMod((letter + shift), ALPHABET_SIZE);
            }
        },
        DECRYPT {
            char shiftChar(char letter, int shift) {
                return (char) Math.floorMod((letter - shift), ALPHABET_SIZE);
            }
        },
        CONVERT_KEY {
            char shiftChar(char letter, int shift) {
                return (char) (Math.floorMod((letter), ALPHABET_SIZE) + 1);
            }
        };

        private static final int ALPHABET_SIZE = 26;

        abstract char shiftChar(char letter, int shift);
    }
}
