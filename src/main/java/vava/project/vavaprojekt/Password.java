package vava.project.vavaprojekt;

public class Password {
    private static final int offset = 4;

    public static String getHash(String password) {
        // Source: https://www.baeldung.com/java-caesar-cipher

        StringBuilder result = new StringBuilder();
        for (char character : password.toCharArray()) {
            if (character != ' ') {
                int originalAlphabetPosition = character - 'a';
                int newAlphabetPosition = (originalAlphabetPosition + offset) % 26;
                char newCharacter = (char) ('a' + newAlphabetPosition);
                result.append(newCharacter);
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }
}
