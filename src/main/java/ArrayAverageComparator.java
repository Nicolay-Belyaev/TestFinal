import java.util.Arrays;
import java.util.OptionalDouble;

// в целом, для данной задачи было бы проще все "служебные" функции сделать private, вызывать
// их в конструкторе. но так будет во-первых не интересно писать тесты, а мы тут для этого
// а во-вторых,
public class ArrayAverageComparator {
    private final int compareResult;

    public ArrayAverageComparator(int[] firstArray, int[] secondArray) {
        if (firstArray.length == 0 || secondArray.length == 0) {
            throw new IllegalArgumentException("None of array can't have zero length");
        }
        float firstAverage = averageCalculator(firstArray);
        float secondAverage = averageCalculator(secondArray);
        compareResult = averageComparator(firstAverage, secondAverage);
    }

    public int getCompareResult() {
        return compareResult;
    }

    private float averageCalculator(int[] array) {
        return (float) Arrays.stream(array).sum() / array.length;
    }

    private int averageComparator(float averageFirst, float averageSecond) {
        float averageDiff = averageFirst - averageSecond;
        if (averageDiff == 0) {
            return 0;
        } else if (averageDiff > 0) {
            return 1;
        } else {
            return 2;
        }
    }
}
