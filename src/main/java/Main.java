public class Main {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        userInterface.getDataFromUser();

        ArrayAverageComparator comparator =
                new ArrayAverageComparator(userInterface.getFirstArray(), 
                                           userInterface.getSecondArray());

        userInterface.showResult(comparator.getCompareResult());


    }
}
