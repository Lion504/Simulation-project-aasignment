public class ServiceDemo {
    public static void main(String[] args) {
        System.out.println("=== Service system Flow Demo ===");

        // Create service point
        ServicePoint servicePoint = new ServicePoint();
        // Generate customers
        CustomerGenerator.setCustomer(servicePoint, 3);
        // Serve customers
        servicePoint.serve();
        // Display statistics
        ServiceStatistics.calculateStatistic(servicePoint.getSerRecord());

        // Multiple test
        runMultipleTests();
    }

    private static void runMultipleTests() {

        int numberOfRuns = 3;
        int customersPerRun = 4;
        System.out.printf("\n%s Time Tests Runs\n", numberOfRuns);
        System.out.println("-".repeat(50));
        for (int run = 1; run <= numberOfRuns; run++) {
            System.out.println("\n" + run + " time for " + customersPerRun + " customers");
            System.out.println("-".repeat(50));

            ServicePoint servicePoint = new ServicePoint();
            CustomerGenerator.setCustomer(servicePoint, customersPerRun);

            servicePoint.serve();
            ServiceStatistics.calculateStatistic(servicePoint.getSerRecord());
        }
    }
}
