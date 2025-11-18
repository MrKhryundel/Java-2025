import java.util.*;

/**
 * Головний клас, що виконує обробку тексту та сортування слів за кількістю голосних.
 */
public class TextProcessor {

    public static void main(String[] args) {

        String rawText =
            "Beyond   the mountains\t\tlies a forgotten valley, " +
            "where ancient trees whisper stories to the wind. " +
            "In the heart of this hidden land, travelers discover wonders!";

        // Замінюємо всі повторювані пробіли і табуляції одним пробілом
        rawText = rawText.replaceAll("[ \\t]+", " ");

        System.out.println("=== Original cleaned text ===");
        System.out.println(rawText);

        Text text = parseText(rawText);

        // Збираємо всі слова
        List<Word> allWords = collectWords(text);

        // Сортуємо слова за кількістю голосних
        allWords.sort(Comparator.comparingInt(Word::countVowels));

        System.out.println("\n=== Words sorted by vowel count ===");
        for (Word w : allWords) {
            System.out.println(w + " (" + w.countVowels() + ")");
        }
    }

    /** Парсить текст у структуру Text */
    private static Text parseText(String raw) {
        Text text = new Text();
        String[] sentenceParts = raw.split("(?<=[.!?])");

        for (String s : sentenceParts) {
            Sentence sentence = new Sentence();
            String[] tokens = s.trim().split(" ");

            for (String token : tokens) {
                if (token.isEmpty()) continue;

                if (Character.isAlphabetic(token.charAt(0))) {
                    String wordPart = token.replaceAll("[^A-Za-z]", "");
                    if (!wordPart.isEmpty()) sentence.addWord(new Word(wordPart));
                }

                String punct = token.replaceAll("[A-Za-z]", "");
                for (char c : punct.toCharArray()) sentence.addPunctuation(new Punctuation(c));
            }

            text.addSentence(sentence);
        }
        return text;
    }

    /** Збирає всі слова з тексту */
    private static List<Word> collectWords(Text text) {
        List<Word> words = new ArrayList<>();
        for (Sentence s : text.getSentences()) {
            for (Object obj : s.getElements()) {
                if (obj instanceof Word) words.add((Word) obj);
            }
        }
        return words;
    }
}
