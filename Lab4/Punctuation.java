/**
 * Клас, що представляє знак пунктуації.
 */
public class Punctuation {
    /** Символ пунктуації */
    private final char mark;

    /**
     * Створює об’єкт пунктуації.
     * @param mark символ пунктуації
     */
    public Punctuation(char mark) {
        this.mark = mark;
    }

    public char getMark() {
        return mark;
    }

    @Override
    public String toString() {
        return Character.toString(mark);
    }
}

