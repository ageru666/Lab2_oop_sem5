package check_sort;

import Components.Beer;

import java.lang.reflect.Field;
import java.util.Comparator;

public class BeerComparator implements Comparator<Beer> {

    private String fieldName;

    public BeerComparator(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public int compare(Beer beer1, Beer beer2) {
        try {
            Field field = Beer.class.getDeclaredField(fieldName);
            field.setAccessible(true);

            Comparable value1 = (Comparable) field.get(beer1);
            Comparable value2 = (Comparable) field.get(beer2);

            // Сортування значень
            return value1.compareTo(value2);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Помилка при сортуванні", e);
        }
    }
}
