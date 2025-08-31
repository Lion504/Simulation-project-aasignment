import java.util.LinkedList;
import java.util.Scanner;

public class CustomerQueueManager {
    private final LinkedList<Customer> queue;

    public CustomerQueueManager() {
        queue = new LinkedList<>();
    }

    public void enqueue(Customer customer) {
        customer.setStartTime(System.nanoTime()); // Set queue entry time
        queue.addFirst(customer); // Add to front (FIFO)
        System.out.println("✓ Customer ID " + customer.getId() + " added to queue");
    }

    public Customer dequeue() {
        if (queue.isEmpty()) {
            return null;
        }
        Customer customer = queue.removeLast(); // Remove from end (FIFO)
        customer.setEndTime(System.nanoTime()); // Set queue exit time
        return customer;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }

    public static void main(String[] args) {
        CustomerQueueManager queueManager = new CustomerQueueManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Customer Queue Management System ===");

        while (true) {
            System.out.println("\nChoose action:");
            System.out.println("1. Enqueue customer");
            System.out.println("2. Dequeue customer");
            System.out.println("3. Check queue status");
            System.out.println("4. Exit");
            System.out.print("Enter choice (1-4): ");

            try {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        Customer newCustomer = new Customer();
                        queueManager.enqueue(newCustomer);
                        System.out.println("Queue size: " + queueManager.size());
                        break;

                    case 2:
                        if (queueManager.isEmpty()) {
                            System.out.println("Queue is empty!");
                        } else {
                            Customer served = queueManager.dequeue();
                            long timeInQueue = served.getTimeSpent();
                            System.out.println("Dequeued Customer ID: " + served.getId());
                            System.out.println("Time spent in queue: " + timeInQueue + " ns");
                            System.out.printf("Time spent in queue: %.3f ms%n",
                                    timeInQueue / 1_000_000.0);
                            System.out.println("Remaining in queue: " + queueManager.size());
                        }
                        break;

                    case 3:
                        System.out.println("Queue size: " + queueManager.size());
                        break;

                    case 4:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Invalid input!");
                scanner.nextLine();
            }
        }
    }
}
