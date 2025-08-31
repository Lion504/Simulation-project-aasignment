public class TestCustomer {
    public static void main(String[] args)  {
        // Test Create customers with  ID
        System.out.println("Create customers with auto set ID");
        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        Customer customer3 = new Customer();

        System.out.println("Customer 1 ID: " + customer1.getId());
        System.out.println("Customer 2 ID: " + customer2.getId());
        System.out.println("Customer 3 ID: " + customer3.getId());

        // Test use System.currentTimeMillis()
        System.out.println("\nUsing System.currentTimeMillis()");
        Customer customer4 = new Customer();
        customer4.setStartTime(System.currentTimeMillis());
        customer4.setEndTime(System.currentTimeMillis());

        System.out.println("Customer 4:\n" + customer4);


        // Test Use System.nanoTime()
        System.out.println("\nusing System.nanoTime()");
        Customer customer5 = new Customer();
        customer5.setStartTime(System.nanoTime());
        customer5.setEndTime(System.nanoTime());

        System.out.println("Customer 5:");
        System.out.printf("ID: %s\nStart Time: %s ns\nEnd Time: %s ns\nTime Spent: %s ns\n",
                customer5.getId(),
                customer5.getStartTime(),
                customer5.getEndTime(),
                customer5.getTimeSpent()
        );
        System.out.println("Time Spent: " + (customer5.getTimeSpent() / 1_000_000.0) + " ms");

        //Test Constructor with parameters
        System.out.println("\nTest Constructor with parameters");
        long start = System.currentTimeMillis();
        long end = start + 2000; // Add 2 seconds
        Customer customer6 = new Customer(start, end);

        System.out.println(customer6);
    }
}
