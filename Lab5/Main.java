// C13 = 6 = Визначити ієрархію амуніції лицаря. Екіпірувати лицаря. Порахувати вартість амуніції. Провести сортування амуніції за вагою. Знайти елементи амуніції, що відповідає заданому діапазону цін.

import java.util.List;
import java.util.Scanner;

/**
 * Демонструє роботу системи амуніції лицаря.
 * Додає можливість вибрати діапазон цін для пошуку.
 */
public class Main {

    /**
     * Точка входу в програму.
     *
     * @param args аргументи командного рядка
     */
    public static void main(String[] args) {
        // Створення лицаря
        Knight knight = new Knight();

        // Додавання варіантів шоломів
        knight.addEquipment(new Helmet("Iron Helmet (light)", 900, 50));
        knight.addEquipment(new Helmet("Steel Helmet (medium)", 1400, 120));
        knight.addEquipment(new Helmet("Golden Helmet", 1700, 220));
        knight.addEquipment(new Helmet("Dragonbone Helmet", 1100, 300));

        // Додавання варіантів нагрудної броні
        knight.addEquipment(new ArmorChest("Leather Chestplate", 2500, 80));
        knight.addEquipment(new ArmorChest("Steel Chestplate", 4500, 200));
        knight.addEquipment(new ArmorChest("Mithril Chestplate", 3200, 350));
        knight.addEquipment(new ArmorChest("Dragonbone Armor", 3800, 500));

        // Додавання варіантів щитів
        knight.addEquipment(new Shield("Wooden Shield", 3000, 40));
        knight.addEquipment(new Shield("Steel Shield", 5000, 150));
        knight.addEquipment(new Shield("Tower Shield", 8000, 250));
        knight.addEquipment(new Shield("Dragonbone Shield", 4200, 380));

        // Додавання варіантів мечів
        knight.addEquipment(new Sword("Iron Sword", 1200, 90));
        knight.addEquipment(new Sword("Steel Sword", 1600, 160));
        knight.addEquipment(new Sword("Mithril Blade", 900, 300));
        knight.addEquipment(new Sword("Dragonfang Sword", 1300, 500));

        // Додавання захисту рук
        knight.addEquipment(new ArmProtection("Leather Armguards", 700, 60));
        knight.addEquipment(new ArmProtection("Steel Armguards", 1300, 130));
        knight.addEquipment(new ArmProtection("Mithril Armguards", 900, 210));
        knight.addEquipment(new ArmProtection("Dragonbone Armguards", 1000, 310));

        // Додавання захисту ніг
        knight.addEquipment(new LegProtection("Leather Leggings", 800, 70));
        knight.addEquipment(new LegProtection("Steel Legplates", 1500, 140));
        knight.addEquipment(new LegProtection("Mithril Legplates", 1100, 240));
        knight.addEquipment(new LegProtection("Dragonbone Legplates", 1300, 330));

        // Додавання чобіт
        knight.addEquipment(new Boots("Leather Boots", 600, 40));
        knight.addEquipment(new Boots("Steel Boots", 1000, 90));
        knight.addEquipment(new Boots("Mithril Boots", 800, 180));
        knight.addEquipment(new Boots("Dragonbone Boots", 900, 250));

        // Вивід початкового списку амуніції
        System.out.println("\n=== Initial equipment ===");
        knight.printEquipment();

        // Вивід загальної вартості
        System.out.println("\nTotal cost of equipment: " + knight.getTotalPrice() + " gold");

        // Сортування за вагою та вивід
        System.out.println("\n=== Sorted by weight ===");
        knight.sortByWeight();
        knight.printEquipment();

        // ----------------------------
        // Інтерактивний ввід діапазону цін користувачем
        Scanner scanner = new Scanner(System.in);
        int minPrice = 0;
        int maxPrice = 0;

        try {
            System.out.print("\nEnter minimum price: ");
            minPrice = scanner.nextInt();

            System.out.print("Enter maximum price: ");
            maxPrice = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input. Using default range 100-200.");
            minPrice = 100;
            maxPrice = 200;
            scanner.nextLine(); // очистка буфера
        }

        // Пошук та вивід елементів амуніції у заданому діапазоні
        System.out.println("\n=== Items priced between " + minPrice + " and " + maxPrice + " gold ===");
        try {
            List<Equipment> filtered = knight.findByPriceRange(minPrice, maxPrice);
            if (filtered.isEmpty()) {
                System.out.println("No equipment found in this price range.");
            } else {
                for (Equipment e : filtered) {
                    System.out.println(e);
                }
            }
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        scanner.close();
    }
}

