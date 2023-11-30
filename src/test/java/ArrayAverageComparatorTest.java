import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;


// хотя тестировать private методы это достаточно странная история, ведь можно протестировать public методы, которые
// используют эти private-методы, но мы тут веников не вяжем, а вяжем тесты, походу знакомясь с понятием рефлексии (Reflection API).
class ArrayAverageComparatorTest {
    private final Class<?> reflection = Class.forName("ArrayAverageComparator");
    private final Object reflectionClass = reflection.getConstructor(int[].class, int[].class).newInstance(new int[]{1, 2, 3}, new int[]{1, 2, 3});

    // ради проброса исключений
    ArrayAverageComparatorTest() throws
            ClassNotFoundException, InvocationTargetException, InstantiationException,
            IllegalAccessException, NoSuchMethodException {}

    @ParameterizedTest
    @DisplayName("Сравнение средних значений")
    @CsvSource({
            "1, 1, 0",
            "2, 1, 1",
            "1, 2, 2"
    })
    void averageComparatorTest(float averageFirst, float averageSecond, int expectedResult) {
        try {
            Method averageComparator = reflectionClass.getClass().getDeclaredMethod("averageComparator", float.class, float.class);
            averageComparator.setAccessible(true);

            assertEquals(expectedResult, averageComparator.invoke(reflectionClass, averageFirst, averageSecond));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Вычисление средних значений")
    void averageCalculatorTest() {
        try {
            Method averageCalculator = reflectionClass.getClass().getDeclaredMethod("averageCalculator", int[].class);
            averageCalculator.setAccessible(true);

            assertEquals((float) 2, averageCalculator.invoke(reflectionClass, new int[]{1, 2, 3}));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Среднее первого массива больше среднего второго")
    void averageComparatorTestFirst() {
        ArrayAverageComparator comparator = new ArrayAverageComparator(new int[]{1, 2, 3}, new int[]{1, 1, 1});
        assertEquals(1, comparator.getCompareResult());
    }

    @Test
    @DisplayName("Среднее второго массива больше среднего первого")
    void averageComparatorTestSecond() {
        ArrayAverageComparator comparator = new ArrayAverageComparator(new int[]{1, 1, 1}, new int[]{1, 2, 3});
        assertEquals(2, comparator.getCompareResult());
    }

    @Test
    @DisplayName("Среднее первого и второго массива равны")
    void averageComparatorTestEquals() {
        ArrayAverageComparator comparator = new ArrayAverageComparator(new int[]{1, 1, 3}, new int[]{1, 1, 3});
        assertEquals(0, comparator.getCompareResult());
    }

    @Test
    @DisplayName("Один из массивов пуст")
    void averageComparatorTestIllegalArg() {
        assertThrows(IllegalArgumentException.class, () ->
                new ArrayAverageComparator(new int[]{1, 1, 3}, new int[]{}));
        assertThrows(IllegalArgumentException.class, () ->
                new ArrayAverageComparator(new int[]{}, new int[]{1, 1, 3}));
    }
}