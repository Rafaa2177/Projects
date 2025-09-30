package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;

/**
 * The type Password generator.
 */
public class PasswordGenerator implements Serializable {
    /**
     * Generate password string.
     *
     * @return the string
     */
    public static String GeneratePassword() {
        StringBuilder passBuilder = new StringBuilder();
        String[] capLetters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        String[] smallLetters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        int randomCapLetter;
        int randomSmallLetter;
        int randomNumber;
        for (int i = 0; i < 7; i++) {
            if (i < 3) {
                randomCapLetter = (int) (Math.random() * 26);
                passBuilder.append(capLetters[randomCapLetter]);
            } else if (i < 5) {
                randomNumber = (int) (Math.random() * 10);
                passBuilder.append(numbers[randomNumber]);
            } else {
                randomSmallLetter = (int) (Math.random() * 26);
                passBuilder.append(smallLetters[randomSmallLetter]);
            }
        }
        return passBuilder.toString();

    }
}

