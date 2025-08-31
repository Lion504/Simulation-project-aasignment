public class CustomerGenerator {

    public static void setCustomer(ServicePoint serPoint, int customerNums) {
        System.out.println("Generate " + customerNums + " customers");

        for (int i = 0; i < customerNums; i++) {
            Customer customer = new Customer();
            serPoint.addToQueue(customer);
            System.out.println("Queue size: " + serPoint.queueSize());
        }
    }
}
