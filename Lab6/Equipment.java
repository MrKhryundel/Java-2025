/**
 * Абстрактний батьківський клас, що представляє елемент амуніції лицаря.
 * Кожен елемент амуніції має назву, вагу та ціну.
 */
public abstract class Equipment {

    /** Назва елемента амуніції */
    protected String name;

    /** Вага амуніції у грамах */
    protected int weight;

    /** Ціна амуніції у золотих */
    protected int price;

    /**
     * Конструктор для створення елемента амуніції.
     *
     * @param name   Назва амуніції
     * @param weight Вага амуніції
     * @param price  Ціна амуніції
     */
    public Equipment(String name, int weight, int price) {
        this.name = name;
        this.weight = weight;
        this.price = price;
    }

    /** @return вага амуніції */
    public int getWeight() {
        return weight;
    }

    /** @return ціна амуніції */
    public int getPrice() {
        return price;
    }

    /** @return назва амуніції */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "- " + name + "\n  Weight: " + weight + " g\n  Price: " + price + " gold";
    }
}

