import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;


// хотя тестировать private методы это достаточно странная история, ведь можно протестировать public методы, которые
// используют эти private-методы, но мы тут веников не вяжем и знакомимся с понятием рефлексии (Reflection API).
class ArrayAverageComparatorTest {
    private ArrayAverageComparator arrayAverageComparator;

    @ParameterizedTest
    @CsvSource({
            "1, 1, 0",
            "2, 1, 1",
            "1, 2, 2"
    })
    void averageComparatorTest(float averageFirst, float averageSecond, int expectedResult) {
        try {
            Class<?> reflection = Class.forName("ArrayAverageComparator");
            Object reflectionClass = reflection.getConstructor(int[].class, int[].class).newInstance(new int[]{1, 2, 3}, new int[]{1, 2, 3});
            Method averageComparator = reflectionClass.getClass().getDeclaredMethod("averageComparator", float.class, float.class);
            averageComparator.setAccessible(true);
            assertEquals(expectedResult, averageComparator.invoke(reflectionClass, averageFirst, averageSecond));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException |
                 ClassNotFoundException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Test
    void averageCalculatorTest() {
        try {
            Class<?> reflection = Class.forName("ArrayAverageComparator");
            Object reflectionClass = reflection.getConstructor(int[].class, int[].class).newInstance(new int[]{1, 2, 3}, new int[]{1, 2, 3});
            Method averageCalculator = reflectionClass.getClass().getDeclaredMethod("averageCalculator", int[].class);
            averageCalculator.setAccessible(true);

            assertEquals((float) 2, averageCalculator.invoke(reflectionClass, (Object) new int[]{1, 2, 3}));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException |
                 ClassNotFoundException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}