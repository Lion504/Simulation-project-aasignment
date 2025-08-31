
public class ServiceSystemTest {

    public static void main(String[] args) {
        System.out.println("=== SERVICE SYSTEM TEST PROGRAM ===");

        // test
        runMultipleTests();
        System.out.println("\n" + "=".repeat(80) + "\n");
    }

    private static void runMultipleTests() {
        System.out.println("\nMultiple Test Runs Compare");

        int numberOfRuns = 3;
        int customersPerRun = 4;

        for (int run = 1; run <= numberOfRuns; run++) {
            System.out.println("\nRUN " + run + " - " + customersPerRun + " customers");
            System.out.println("-".repeat(60));

            ServicePoint servicePoint = new ServicePoint();
            CustomerGenerator.setCustomer(servicePoint, customersPerRun);

            servicePoint.serve();
            ServiceStatistics.calculateStatistic(servicePoint.getSerRecord());
        }
    }

}
