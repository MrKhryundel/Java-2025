/**
 * Клас нагрудної броні лицаря, наслідує Equipment.
 */
public class ArmorChest extends Equipment {

    /**
     * Конструктор для створення нагрудної броні.
     *
     * @param name Назва броні
     * @param weight Вага броні
     * @param price Ціна броні
     */
    public ArmorChest(String name, int weight, int price) {
        super(name, weight, price);
    }
}

