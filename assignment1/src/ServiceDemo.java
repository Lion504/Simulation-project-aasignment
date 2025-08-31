public class ServiceDemo {
    public static void main(String[] args) {
        System.out.println("=== Service system Flow Demo ===");

        // Create service point
        ServicePoint servicePoint = new ServicePoint();


        // Generate customers
        System.out.println("\n1. Generating customers...");
        CustomerGenerator.setCustomer(servicePoint, 3);

        // Serve customers
        System.out.println("\n2. Starting service...");
        servicePoint.serve();

        // Display statistics
        System.out.println("\n3. Service statistics:");
        ServiceStatistics.calculateStatistic(servicePoint.getSerRecord());

        // Multiple test
        runMultipleTests();
        System.out.println("\n" + "=".repeat(80) + "\n");
    }

    private static void runMultipleTests() {
        System.out.println("\nMultiple Test Runs");

        int numberOfRuns = 3;
        int customersPerRun = 4;

        for (int run = 1; run <= numberOfRuns; run++) {
            System.out.println("\nRun " + run + " time for " + customersPerRun + " customers");
            System.out.println("-".repeat(60));

            ServicePoint servicePoint = new ServicePoint();
            CustomerGenerator.setCustomer(servicePoint, customersPerRun);

            servicePoint.serve();
            ServiceStatistics.calculateStatistic(servicePoint.getSerRecord());
        }
    }
}
