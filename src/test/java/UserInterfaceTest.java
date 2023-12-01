import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class UserInterfaceTest {
    private final Class<?> reflection = Class.forName("UserInterface");
    private final Object reflectionClass = reflection.getConstructor().newInstance();

    UserInterfaceTest() throws
            ClassNotFoundException, InvocationTargetException, InstantiationException,
            IllegalAccessException, NoSuchMethodException {}

    @Test
    void convertDataTest() {
        try {
            Integer[] firstArrayValue;
            Integer[] secondArrayValue;

            Method convertData = reflectionClass.getClass().getDeclaredMethod("convertData", String.class, String.class);
            convertData.setAccessible(true);

            convertData.invoke(reflectionClass, "1 2 3", "1 2 3");

            Field firstArray = reflectionClass.getClass().getDeclaredField("firstArray");
            firstArray.setAccessible(true);
            firstArrayValue = (Integer[]) firstArray.get(reflectionClass);

            Field secondArray = reflectionClass.getClass().getDeclaredField("secondArray");
            secondArray.setAccessible(true);
            secondArrayValue = (Integer[]) firstArray.get(reflectionClass);

            assertArrayEquals(new Integer[]{1, 2, 3}, firstArrayValue);
            assertArrayEquals(new Integer[]{1, 2, 3}, secondArrayValue);

        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @CsvSource({
            "0, Средние значения равны",
            "1, Первый список имеет большее среднее значение",
            "2, Второй список имеет большее среднее значение"
    })
    void showResultTest(int result, String expectedResult) {
        UserInterface userInterface = new UserInterface();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        System.setOut(new PrintStream(outContent));
        userInterface.showResult(result);

        assertEquals(expectedResult, outContent.toString().trim());

        System.setOut(originalOut);
    }
}