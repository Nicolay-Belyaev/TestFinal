public class Main {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        try {
            userInterface.getDataFromUser();
        } catch (NumberFormatException e) {
            System.out.println("Похоже, вы ошиблись при вводе. Пожалуйста, вводите целые числа через пробел.");
            userInterface.getDataFromUser();
        }


        ArrayAverageComparator comparator = new ArrayAverageComparator(userInterface.getFirstArray(),
                                                                       userInterface.getSecondArray());

        userInterface.showResult(comparator.getCompareResult());

    }
}
