import java.util.ArrayList;
import java.util.List;

/**
 * Клас, що представляє текст - набір речень.
 */
public class Text {
    private final List<Sentence> sentences = new ArrayList<>();

    /** Додає речення до тексту */
    public void addSentence(Sentence s) {
        sentences.add(s);
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Sentence s : sentences) {
            sb.append(s.toString()).append(" ");
        }
        return sb.toString().trim();
    }
}

