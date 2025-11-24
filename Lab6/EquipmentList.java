import java.util.*;

/**
 * Власна типізована колекція для зберігання Equipment.
 * Реалізує інтерфейс List<Equipment> з внутрішнім масивом та динамічним розширенням.
 */
public class EquipmentList implements List<Equipment> {

    private Equipment[] elements;
    private int size;

    private static final int INITIAL_CAPACITY = 15;

    /**
     * Порожній конструктор. Створює порожній список з початковою ємністю.
     */
    public EquipmentList() {
        elements = new Equipment[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Конструктор з одним елементом.
     * @param equipment початковий елемент списку
     */
    public EquipmentList(Equipment equipment) {
        this();
        if (equipment != null) {
            add(equipment);
        }
    }

    /**
     * Конструктор з колекції.
     * @param collection колекція елементів для додавання
     */
    public EquipmentList(Collection<? extends Equipment> collection) {
        this();
        if (collection != null) {
            addAll(collection);
        }
    }

    /**
     * Перевіряє і при потребі збільшує ємність масиву на 30%.
     */
    private void ensureCapacity() {
        if (size >= elements.length) {
            int newCapacity = elements.length + (elements.length * 30 / 100);
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    /**
     * Перевіряє і при потребі збільшує ємність масиву для додавання кількох елементів.
     * @param additional кількість додаткових елементів
     */
    private void ensureCapacityFor(int additional) {
        while (size + additional > elements.length) {
            int newCapacity = elements.length + (elements.length * 30 / 100);
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    /** Додає елемент в кінець списку */
    @Override
    public boolean add(Equipment e) {
        ensureCapacity();
        elements[size++] = e;
        return true;
    }

    /** Додає елемент на певну позицію */
    @Override
    public void add(int index, Equipment element) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        ensureCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    /** Додає всі елементи з колекції в кінець списку */
    @Override
    public boolean addAll(Collection<? extends Equipment> c) {
        if (c == null) throw new NullPointerException();
        boolean changed = false;
        for (Equipment e : c) {
            add(e);
            changed = true;
        }
        return changed;
    }

    /** Додає всі елементи з колекції на певну позицію */
    @Override
    public boolean addAll(int index, Collection<? extends Equipment> c) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        if (c == null) throw new NullPointerException();
        int numNew = c.size();
        ensureCapacityFor(numNew);
        System.arraycopy(elements, index, elements, index + numNew, size - index);
        int i = index;
        for (Equipment e : c) {
            elements[i++] = e;
        }
        size += numNew;
        return numNew != 0;
    }

    /** Повертає елемент за індексом */
    @Override
    public Equipment get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return elements[index];
    }

    /** Замінює елемент за індексом і повертає старий */
    @Override
    public Equipment set(int index, Equipment element) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Equipment old = elements[index];
        elements[index] = element;
        return old;
    }

    /** Видаляє елемент за індексом і повертає його */
    @Override
    public Equipment remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Equipment removed = elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;
        return removed;
    }

    /** Видаляє перший знайдений елемент, який дорівнює o */
    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], o)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    /** Повертає кількість елементів у списку */
    @Override
    public int size() {
        return size;
    }

    /** Перевіряє, чи список порожній */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /** Очищає список */
    @Override
    public void clear() {
        Arrays.fill(elements, 0, size, null);
        size = 0;
    }

    /** Перевіряє, чи містить список елемент o */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    /** Повертає індекс першого входження елемента */
    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], o)) return i;
        }
        return -1;
    }

    /** Повертає індекс останнього входження елемента */
    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(elements[i], o)) return i;
        }
        return -1;
    }

    /** Перевіряє, чи список містить всі елементи колекції */
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) return false;
        }
        return true;
    }

    /** Видаляє всі елементи, що містяться в колекції c */
    @Override
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        boolean modified = false;
        for (Object o : c) {
            while (remove(o)) {
                modified = true;
            }
        }
        return modified;
    }

    /** Залишає лише елементи, що містяться в колекції c */
    @Override
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);
        boolean modified = false;
        for (int i = size - 1; i >= 0; i--) {
            if (!c.contains(elements[i])) {
                remove(i);
                modified = true;
            }
        }
        return modified;
    }

    /** Повертає ітератор для списку */
    @Override
    public Iterator<Equipment> iterator() {
        return new Iterator<Equipment>() {
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public Equipment next() {
                if (!hasNext()) throw new NoSuchElementException();
                return elements[cursor++];
            }
        };
    }

    /** Повертає listIterator з початку списку */
    @Override
    public ListIterator<Equipment> listIterator() {
        return listIterator(0);
    }

    /** Повертає listIterator з певного індексу */
    @Override
    public ListIterator<Equipment> listIterator(int index) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        return new ListIterator<Equipment>() {
            private int cursor = index;
            private int lastRet = -1;

            @Override
            public boolean hasNext() { return cursor < size; }

            @Override
            public Equipment next() {
                if (!hasNext()) throw new NoSuchElementException();
                lastRet = cursor;
                return elements[cursor++];
            }

            @Override
            public boolean hasPrevious() { return cursor > 0; }

            @Override
            public Equipment previous() {
                if (!hasPrevious()) throw new NoSuchElementException();
                lastRet = --cursor;
                return elements[cursor];
            }

            @Override
            public int nextIndex() { return cursor; }

            @Override
            public int previousIndex() { return cursor - 1; }

            @Override
            public void remove() {
                if (lastRet < 0) throw new IllegalStateException();
                EquipmentList.this.remove(lastRet);
                if (lastRet < cursor) cursor--;
                lastRet = -1;
            }

            @Override
            public void set(Equipment e) {
                if (lastRet < 0) throw new IllegalStateException();
                EquipmentList.this.set(lastRet, e);
            }

            @Override
            public void add(Equipment e) {
                EquipmentList.this.add(cursor++, e);
                lastRet = -1;
            }
        };
    }

    /** Повертає підсписок від fromIndex до toIndex */
    @Override
    public List<Equipment> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex)
            throw new IndexOutOfBoundsException();
        EquipmentList sub = new EquipmentList();
        for (int i = fromIndex; i < toIndex; i++) {
            sub.add(elements[i]);
        }
        return sub;
    }

    /** Повертає масив елементів Object[] */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    /** Повертає масив елементів типу T[] */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            return (T[]) Arrays.copyOf(elements, size, a.getClass());
        System.arraycopy(elements, 0, a, 0, size);
        if (a.length > size) a[size] = null;
        return a;
    }

    /** Сортування елементів за вагою */
    public void sortByWeight() {
        Arrays.sort(elements, 0, size, Comparator.comparingInt(Equipment::getWeight));
    }

    /** Пошук елементів за ціною в діапазоні [min, max] */
    public EquipmentList findByPriceRange(int min, int max) {
        if (min > max) throw new IllegalArgumentException("Min price cannot be higher than max price!");
        EquipmentList result = new EquipmentList();
        for (int i = 0; i < size; i++) {
            if (elements[i].getPrice() >= min && elements[i].getPrice() <= max) {
                result.add(elements[i]);
            }
        }
        return result;
    }

    /** Повертає рядкове представлення списку */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]).append("\n");
        }
        return sb.toString();
    }
}



