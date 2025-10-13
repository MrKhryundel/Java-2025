// C11 = 6 = Визначити клас спортивний інвентар, який складається як мінімум з 5-и полів.

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Клас, що описує спортивний інвентар.
 */
class SportEquipment {
    /** Назва предмету */
    private String name;
    /** Тип предмету */
    private String type;
    /** Вага предмету в кг */
    private double weight;
    /** Ціна предмету в UAH */
    private double price;
    /** Виробник предмету */
    private String manufacturer;

    /**
     * Конструктор класу SportEquipment
     * @param name назва предмету
     * @param type тип предмету
     * @param weight вага предмету
     * @param price ціна предмету
     * @param manufacturer виробник предмету
     */
    public SportEquipment(String name, String type, double weight, double price, String manufacturer) {
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.price = price;
        this.manufacturer = manufacturer;
    }

    /**
     * Геттер для назви предмету
     * @return назва предмету
     */
    public String getName() { return name; }

    /**
     * Геттер для ціни предмету
     * @return ціна предмету
     */
    public double getPrice() { return price; }

    /**
     * Перевизначення equals для порівняння об'єктів SportEquipment
     * @param obj об'єкт для порівняння
     * @return true, якщо об'єкти ідентичні за всіма полями
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof SportEquipment)) return false;
        SportEquipment other = (SportEquipment) obj;
        return name.equals(other.name) &&
               type.equals(other.type) &&
               weight == other.weight &&
               price == other.price &&
               manufacturer.equals(other.manufacturer);
    }

    /**
     * Перевизначення toString для зручного виводу інформації про об'єкт
     * @return рядок з описом об'єкта
     */
    @Override
    public String toString() {
        return String.format("Name: %s | Type: %s | Weight: %.2f kg | Price: %.2f UAH | Manufacturer: %s",
                name, type, weight, price, manufacturer);
    }
}

/**
 * Головний клас програми для лабораторної роботи №3.
 * Виконує введення, сортування та пошук спортивного інвентарю.
 */
public class ClassTask_lab3 {

    /**
     * Головний метод програми.
     * @param args параметри командного рядка
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<SportEquipment> items = new ArrayList<>();

        System.out.println("=== Program for entering sport equipment ===");

        // --- Введення предметів користувачем ---
        while (true) {
            boolean wantToAdd = askYesNo(scanner, "Do you want to enter a new item? (yes/no): ");
            if (!wantToAdd) break;

            String name = readNonEmptyLine(scanner, "Enter name: ");
            String type = readNonEmptyLine(scanner, "Enter type: ");
            double weight = readDoubleAllowEmpty(scanner, "Enter weight (kg) (or leave empty for 0): ");
            double price = readDoubleAllowEmpty(scanner, "Enter price (UAH) (or leave empty for 0): ");
            String manufacturer = readNonEmptyLine(scanner, "Enter manufacturer: ");

            items.add(new SportEquipment(name, type, weight, price, manufacturer));
            System.out.println("Item added!");
        }

        // --- Сортування списку ---
        Collections.sort(items, new Comparator<SportEquipment>() {
            @Override
            public int compare(SportEquipment a, SportEquipment b) {
                int nameCompare = a.getName().compareToIgnoreCase(b.getName()); // за зростанням
                if (nameCompare != 0) {
                    return nameCompare;
                } else {
                    return Double.compare(b.getPrice(), a.getPrice()); // за спаданням
                }
            }
        });

        // --- Виведення відсортованого списку ---
        System.out.println("\n=== Sorted list of items ===");
        if (items.isEmpty()) {
            System.out.println("No items were entered.");
        } else {
            for (SportEquipment item : items) {
                System.out.println(item);
            }
        }

        // --- Пошук ідентичного об'єкта ---
        boolean searchAnother = askYesNo(scanner, "\nDo you want to search for a specific item? (yes/no): ");
        if (searchAnother) {
            System.out.println("Enter the details of the item to search:");
            String name = readNonEmptyLine(scanner, "Enter name: ");
            String type = readNonEmptyLine(scanner, "Enter type: ");
            double weight = readDoubleAllowEmpty(scanner, "Enter weight (kg) (or leave empty for 0): ");
            double price = readDoubleAllowEmpty(scanner, "Enter price (UAH) (or leave empty for 0): ");
            String manufacturer = readNonEmptyLine(scanner, "Enter manufacturer: ");

            SportEquipment target = new SportEquipment(name, type, weight, price, manufacturer);

            boolean found = false;
            for (SportEquipment item : items) {
                if (item.equals(target)) {
                    System.out.println("\nFound matching item:");
                    System.out.println(item);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("\nNo matching item found.");
            }
        }

        scanner.close();
        System.out.println("\n=== Program finished ===");
    }

    // --- Допоміжні функції з Javadoc ---

    /**
     * Запитує користувача на так/ні відповідь.
     * @param scanner об'єкт Scanner для читання введення
     * @param prompt повідомлення для користувача
     * @return true, якщо користувач відповів 'yes' або 'y', інакше false
     */
    private static boolean askYesNo(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            if (line == null) line = "";
            String v = line.trim().toLowerCase();
            if (v.equals("yes") || v.equals("y")) return true;
            if (v.equals("no") || v.equals("n")) return false;
            System.out.println("Please answer 'yes' or 'no'.");
        }
    }

    /**
     * Змушує користувача ввести непустий рядок.
     * @param scanner об'єкт Scanner для читання введення
     * @param prompt повідомлення для користувача
     * @return введений непустий рядок
     */
    private static String readNonEmptyLine(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            if (line == null) line = "";
            line = line.trim();
            if (!line.isEmpty()) return line;
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    /**
     * Зчитує число з плаваючою комою або повертає 0.0, якщо рядок порожній.
     * @param scanner об'єкт Scanner для читання введення
     * @param prompt повідомлення для користувача
     * @return введене число або 0.0
     */
    private static double readDoubleAllowEmpty(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            if (line == null) line = "";
            line = line.trim();
            if (line.isEmpty()) return 0.0;
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter a valid numeric value (or leave empty).");
            }
        }
    }
}
