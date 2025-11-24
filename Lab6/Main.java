// C2 = 0
// C3 = 0
import java.util.Scanner;

/**
 * Демонструє роботу власної колекції EquipmentList.
 * Показує додавання елементів, сортування за вагою та пошук за ціною.
 */
public class Main {

    /** Точка входу в програму */
    public static void main(String[] args) {

        // Створення порожньої колекції
        EquipmentList myList = new EquipmentList();

        // Додавання елементів
        myList.add(new Helmet("Iron Helmet (light)", 900, 50));
        myList.add(new Helmet("Steel Helmet (medium)", 1400, 120));
        myList.add(new ArmorChest("Leather Chestplate", 2500, 80));
        myList.add(new ArmorChest("Steel Chestplate", 4500, 200));
        myList.add(new Sword("Steel Sword", 1600, 160));
        myList.add(new Boots("Mithril Boots", 800, 180));

        // Вивід початкового списку
        System.out.println("=== Initial Equipment ===");
        System.out.println(myList);

        // Сортування за вагою
        myList.sortByWeight();
        System.out.println("=== Sorted by weight ===");
        System.out.println(myList);

        // Ввід діапазону цін
        Scanner scanner = new Scanner(System.in);
        int min = 0, max = 0;

        try {
            System.out.print("Enter minimum price: ");
            min = scanner.nextInt();
            System.out.print("Enter maximum price: ");
            max = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input. Using default range 50-200.");
            min = 50;
            max = 200;
            scanner.nextLine();
        }

        // Пошук за ціною
        EquipmentList filtered = myList.findByPriceRange(min, max);
        System.out.println("=== Items priced between " + min + " and " + max + " ===");
        System.out.println(filtered);

        scanner.close();
    }
}




