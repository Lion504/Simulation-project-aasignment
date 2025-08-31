public class CustomerGenerator {

    public static void setCustomer(ServicePoint servicePoint, int numberOfCustomers) {
        System.out.println("Generating " + numberOfCustomers + " customers");

        for (int i = 0; i < numberOfCustomers; i++) {
            Customer customer = new Customer();
            servicePoint.addToQueue(customer);

            // Small delay between customer arrivals (optional)
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Generated " + numberOfCustomers + " customers");
        System.out.println("Queue size: " + servicePoint.queueSize());
    }
}
