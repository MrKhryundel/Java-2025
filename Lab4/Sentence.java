import java.util.ArrayList;
import java.util.List;

/**
 * Клас, що представляє речення - набір слів та пунктуації.
 */
public class Sentence {

    private final List<Object> elements = new ArrayList<>();

    /** Додає слово до речення */
    public void addWord(Word word) {
        elements.add(word);
    }

    /** Додає знак пунктуації до речення */
    public void addPunctuation(Punctuation p) {
        elements.add(p);
    }

    public List<Object> getElements() {
        return elements;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Object obj : elements) {
            sb.append(obj.toString());
            if (obj instanceof Word) sb.append(" ");
        }
        return sb.toString().trim();
    }
}
