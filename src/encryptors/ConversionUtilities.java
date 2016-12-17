package encryptors;

class ConversionUtilities {

    static int convertCharDownFromASCII(char letter, Case CASE) {
        return (int) letter - CASE.asciiShift();
    }

    static int convertCharBackToASCII(char letter, Case CASE) {
        return (int) letter + CASE.asciiShift();
    }

    static int shiftKey(char letter) {
        final int IGNORE_VARIABLE_SHIFT = 0;
        return ShiftCharType.CONVERT_KEY.shiftChar(letter, IGNORE_VARIABLE_SHIFT);
    }

    static int shiftChar(char letter, int shift, ShiftCharType encryptOrDecrypt) {
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
            int shiftChar(char letter, int shift) {
                return Math.floorMod((letter + shift), ALPHABET_SIZE);
            }
        },
        DECRYPT {
            int shiftChar(char letter, int shift) {
                return Math.floorMod((letter - shift), ALPHABET_SIZE);
            }
        },
        CONVERT_KEY {
            int shiftChar(char letter, int shift) {
                return Math.floorMod((letter), ALPHABET_SIZE) + 1;
            }
        };

        private static final int ALPHABET_SIZE = 26;

        abstract int shiftChar(char letter, int shift);
    }
}
