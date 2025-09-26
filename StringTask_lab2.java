import java.util.*;

public class StringTask_lab2 {

    // Метод для підрахунку кількості голосних у слові
    private static int countVowels(StringBuilder word) {
        if (word == null) {
            return 0;
        }
        // Набір голосних для англійської
        String vowels = "eyuioaEYUIOA"; 
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (vowels.indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        try {
            // Вихідний текст (можна змінити на будь який)
            StringBuilder text = new StringBuilder(
                "Beyond the mountains lies a forgotten valley, where ancient trees whisper stories " +
                "to the wind and rivers carry secrets to the ocean. In the heart of this hidden land, " +
                "curious travelers often discover wonders they never imagined before."
            );


            // Вивід оригінального тексту
            System.out.println("Original text:");
            System.out.println(text.toString());

            // Розбиття тексту на слова (пробіли + знаки пунктуації)
            String[] wordsArray = text.toString().split("[\\s.,!?]+");

            // Конвертація у список типу StringBuilder
            List<StringBuilder> words = new ArrayList<>();
            for (String w : wordsArray) {
                if (!w.isEmpty()) {
                    words.add(new StringBuilder(w));
                }
            }

            // 5. Сортування за кількістю голосних
            words.sort(Comparator.comparingInt(StringTask_lab2::countVowels));

            // Вивід відсортованих слів
            System.out.println("\nWords sorted by vowel count:");
            for (StringBuilder sb : words) {
                System.out.printf("%s (%d)%n", sb, countVowels(sb));
            }

        } catch (Exception e) {
            // Обробка усіх можливих виключень
            System.err.println("An error occurred while processing the text: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
