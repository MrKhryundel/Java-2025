import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Клас, що представляє лицаря, який може мати різні елементи амуніції.
 */
public class Knight {

    /** Список усієї амуніції лицаря */
    private final List<Equipment> equipmentList = new ArrayList<>();

    /** 
     * Конструктор лицаря (пустий список амуніції).
     */
    public Knight() {
        // Порожній конструктор
    }

    /**
     * Додає амуніцію до інвентаря лицаря.
     *
     * @param equipment Об’єкт амуніції
     */
    public void addEquipment(Equipment equipment) {
        if (equipment == null) {
            throw new IllegalArgumentException("Cannot add null equipment!");
        }
        equipmentList.add(equipment);
    }

    /**
     * @return загальна вартість усієї амуніції
     */
    public int getTotalPrice() {
        int sum = 0;
        for (Equipment e : equipmentList) {
            sum += e.getPrice();
        }
        return sum;
    }

    /** Сортує амуніцію за вагою */
    public void sortByWeight() {
        equipmentList.sort(Comparator.comparingInt(Equipment::getWeight));
    }

    /**
     * Пошук елементів амуніції у заданому діапазоні цін.
     *
     * @param min мінімальна ціна
     * @param max максимальна ціна
     * @return список знайдених елементів
     */
    public List<Equipment> findByPriceRange(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Min price cannot be higher than max price!");
        }

        List<Equipment> result = new ArrayList<>();
        for (Equipment e : equipmentList) {
            if (e.getPrice() >= min && e.getPrice() <= max) {
                result.add(e);
            }
        }
        return result;
    }

    /** Виводить усю амуніцію на екран у многострочному форматі */
    public void printEquipment() {
        System.out.println("Knight's equipment:");
        for (Equipment e : equipmentList) {
            System.out.println(e); 
        }
    }
}