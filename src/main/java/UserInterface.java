import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Integer[] firstArray;
    private Integer[] secondArray;

    public Integer[] getFirstArray() {
        return firstArray;
    }

    public Integer[] getSecondArray() {
        return secondArray;
    }

    public void getDataFromUser() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите первый массив (несколько целых чисел через пробел)");
        String firstArray = in.nextLine();
        System.out.println("Введите второй массив (несколько целых чисел через пробел)");
        String secondArray = in.nextLine();
        in.close();
        convertData(firstArray, secondArray);
    }

    public void showResult(int result) {
        switch (result) {
            case 0:
                System.out.println("Средние значения равны");
                break;
            case 1:
                System.out.println("Первый список имеет большее среднее значение");
                break;
            case 2:
                System.out.println("Второй список имеет большее среднее значение");
                break;
        }
    }


    private void convertData(String firstArrayAsStr, String secondArrayAsStr) {
        String[] firstArraySplited = firstArrayAsStr.split(" ");
        String[] secondArraySplited = secondArrayAsStr.split(" ");

        List<Integer> firstArrayList = new ArrayList<>();
        List<Integer> secondArrayList = new ArrayList<>();

        for (String s : firstArraySplited) {firstArrayList.add(Integer.parseInt(s));}
        for (String s: secondArraySplited) {secondArrayList.add(Integer.parseInt(s));}

        firstArray = firstArrayList.toArray(new Integer[0]);
        secondArray = secondArrayList.toArray(new Integer[0]);
    }
}
