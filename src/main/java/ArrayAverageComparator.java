import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

// в целом, для данной задачи было бы проще все "служебные" функции сделать private, вызывать
// их в конструкторе. но так будет во-первых не интересно писать тесты, а мы тут для этого
// а во-вторых,
public class ArrayAverageComparator {
    private final int compareResult;

    public ArrayAverageComparator(Integer[] firstArray, Integer[] secondArray) {
        if (firstArray.length == 0 || secondArray.length == 0) {
            throw new IllegalArgumentException("None of arrays can't have zero length");
        }
        float firstAverage = averageCalculator(firstArray);
        float secondAverage = averageCalculator(secondArray);
        compareResult = averageComparator(firstAverage, secondAverage);
    }

    public int getCompareResult() {
        return compareResult;
    }

    private float averageCalculator(Integer[] array) {
        Integer sum = 0;
        for (Integer integer : array) {
            sum += integer;
        }
        return (float) sum / array.length;
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
