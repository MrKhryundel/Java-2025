/**
 * Клас, що представляє одну літеру тексту.
 */
public class Letter {
    /** Символ літери */
    private final char character;

    /**
     * Створює об’єкт літери.
     * @param character літера
     */
    public Letter(char character) {
        this.character = character;
    }

    /** @return символ літери */
    public char getCharacter() {
        return character;
    }

    @Override
    public String toString() {
        return Character.toString(character);
    }
}
