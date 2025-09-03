import java.util.*;
public class Task2 {
    public static void main(String[] args) {
        ArrayList<Integer> ageGroup = new ArrayList<>();
        Random rand = new Random();
        int generateSize = 1000;

        int[][] ageDistribution = {
            {16, 20}, //16%
            {34, 21}, //18%
            {52, 22}, //18%
            {68, 23}, //16%
            {82, 24}, //14%
            {89, 25}, //7%
            {94, 26}, //5%
            {96, 28}, //2%
            {98, 30}, //2%
            {100, 35} //2%
        };

        for  (int i = 0; i < generateSize; i++) {
            int randomPercent = rand.nextInt(100) + 1;//since 1-100

            int age = percentageToAge (randomPercent, ageDistribution);
            ageGroup.add(age);
        }

        displayAgeResult(ageGroup);
    }

    private  static int percentageToAge (int randomPercent, int[][] ageDistribution) {
        for  (int i = 0; i < ageDistribution.length; i++){
            if (randomPercent < ageDistribution[i][0]){
                return ageDistribution[i][1];
            }
        }
        return 18; // default if not meet
    }

    private static void displayAgeResult(ArrayList<Integer> ageGroup) {
        //count frequencies
        Map<Integer,Integer> ageMap = new HashMap<>();
        for  (int age: ageGroup){
            ageMap.put(age, ageMap.getOrDefault(age, 0) + 1);
        }

        System.out.println("Age Times Percent:");
        System.out.println("-".repeat(50));
        for (Map.Entry<Integer,Integer> entry : ageMap.entrySet()){
            int age = entry.getKey();
            int count = entry.getValue();
            double percentage = (count / (double)ageGroup.size()) * 100;
            System.out.printf("%2d:  %2d    %.1f%% \n", age,  count, percentage);
        }

    }
}
