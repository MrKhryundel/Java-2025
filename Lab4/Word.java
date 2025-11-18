import java.util.ArrayList;
import java.util.List;

/**
 * Клас, що представляє слово як набір літер.
 */
public class Word {
    private final List<Letter> letters = new ArrayList<>();

    /**
     * Створює слово зі звичайного рядка.
     * @param word текст слова
     */
    public Word(String word) {
        for (char c : word.toCharArray()) {
            letters.add(new Letter(c));
        }
    }

    /**
     * Підраховує кількість голосних у слові.
     * @return кількість голосних літер
     */
    public int countVowels() {
        String vowels = "eyuioaEYUIOA";
        int count = 0;
        for (Letter letter : letters) {
            if (vowels.indexOf(letter.getCharacter()) != -1) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Letter l : letters) sb.append(l.getCharacter());
        return sb.toString();
    }
}

