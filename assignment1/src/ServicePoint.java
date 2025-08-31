import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

public class ServicePoint {
    private final LinkedList<Customer> queue;
    private final List<ServiceRecord> serviceRecords;

    public ServicePoint() {
        queue = new LinkedList<>();
        serviceRecords = new ArrayList<>();
    }

    public void addToQueue(Customer customer) {
        customer.setStartTime(System.nanoTime()); // Queue entry time
        queue.addFirst(customer);
        System.out.println("Customer ID " + customer.getId() + " added to service queue");
    }

    public Customer removeFromQueue() {
        if (queue.isEmpty()) {
            return null;
        }
        return queue.removeLast(); // FIFO - remove from end
    }

    public void serve() {
        System.out.println("\n=== Starting Service ===");

        while (!queue.isEmpty()) {
            Customer customer = removeFromQueue();
            long queueExitTime = System.nanoTime();

            // Calculate waiting time in queue
            long waitingTime = queueExitTime - customer.getStartTime();

            // Generate random service time (1000-5000 ms)
            int serviceTimeMs = (int)(Math.random() * 4000) + 1000;
            long serviceTimeNs = serviceTimeMs * 1_000_000L;

            System.out.printf("\nServing Customer ID: %d%n", customer.getId());
            System.out.printf("Service time: %d ms%n", serviceTimeMs);

            // Simulate service with Thread.sleep()
            try {
                Thread.sleep(serviceTimeMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Service interrupted!");
            }

            long serviceCompleteTime = System.nanoTime();
            customer.setEndTime(serviceCompleteTime);

            // Calculate times
            long totalResponseTime = serviceCompleteTime - customer.getStartTime();

            // Store service record for statistics
            ServiceRecord record = new ServiceRecord(customer.getId(),
                    waitingTime, serviceTimeNs, totalResponseTime);
            serviceRecords.add(record);

            // Print results
            System.out.printf("Customer ID %d served:%n", customer.getId());
            System.out.printf("- Waiting time: %.3f ms%n", waitingTime / 1_000_000.0);
            System.out.printf("- Service time: %.3f ms%n", serviceTimeNs / 1_000_000.0);
            System.out.printf("- Response time: %.3f ms%n", totalResponseTime / 1_000_000.0);
        }

        System.out.println("\n🆗 Service Complete");
    }

    public List<ServiceRecord> getSerRecord() {
        return serviceRecords;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int queueSize() {
        return queue.size();
    }
}

// Helper class to store service statistics
class ServiceRecord {
    private final int customerId;
    private final long waitingTime;
    private final long serviceTime;
    private final long responseTime;

    public ServiceRecord(int customerId, long waitingTime, long serviceTime, long responseTime) {
        this.customerId = customerId;
        this.waitingTime = waitingTime;
        this.serviceTime = serviceTime;
        this.responseTime = responseTime;
    }

    // Getters
    public int getCustomerId() { return customerId; }
    public long getWaitingTime() { return waitingTime; }
    public long getServiceTime() { return serviceTime; }
    public long getResponseTime() { return responseTime; }
}
