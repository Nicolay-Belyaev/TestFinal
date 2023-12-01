public class Main {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        userInterface.getDataFromUser();

        ArrayAverageComparator comporator = 
                new ArrayAverageComparator(userInterface.getFirstArray(), 
                                           userInterface.getSecondArray());

        userInterface.showResult(comporator.getCompareResult());


    }
}
