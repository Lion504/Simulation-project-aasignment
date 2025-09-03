import java.util.LinkedList;
import java.util.Queue;

/**
 * ServicePoint class representing airport security checkpoint or check-in counter
 */
public class ServicePoint {
    private String servicePointName;
    private Queue<Customer> customerQueue;
    private boolean busy;
    private Customer currentCustomer;
    private int customersServed;
    private double totalServiceTime;

    public ServicePoint(String servicePointName) {
        this.servicePointName = servicePointName;
        this.customerQueue = new LinkedList<>();
        this.busy = false;
        this.currentCustomer = null;
        this.customersServed = 0;
        this.totalServiceTime = 0.0;
    }

    /**
     * Add customer to the service point queue
     */
    public void addCustomerToQueue(Customer customer) {
        customerQueue.add(customer);
        System.out.printf("  → %s added to %s queue (queue size: %d)\n",
                customer.getCustomerId(), servicePointName, customerQueue.size());
    }

    /**
     * Remove and return next customer from queue (for processing)
     */
    public Customer removeCustomerFromQueue() {
        Customer customer = customerQueue.poll();
        if (customer != null) {
            System.out.printf("  → %s removed from %s queue for processing\n",
                    customer.getCustomerId(), servicePointName);
        }
        return customer;
    }

    /**
     * Check if queue is empty
     */
    public boolean isQueueEmpty() {
        return customerQueue.isEmpty();
    }

    /**
     * Get current queue size
     */
    public int getQueueSize() {
        return customerQueue.size();
    }

    /**
     * Start serving a customer (set as current customer)
     */
    public void startService(Customer customer) {
        this.currentCustomer = customer;
        this.busy = true;
        System.out.printf("  → %s started service at %s\n",
                customer.getCustomerId(), servicePointName);
    }

    /**
     * Complete service and calculate time spent in system
     */
    public double completeService() {
        if (currentCustomer == null) {
            return 0.0;
        }

        AirportClock clock = AirportClock.getInstance();
        double departureTime = clock.getCurrentTime();
        double timeInSystem = departureTime - currentCustomer.getArrivalTime();

        System.out.printf("  → %s completed service at %s (time in system: %.2f min)\n",
                currentCustomer.getCustomerId(), servicePointName, timeInSystem);

        customersServed++;
        totalServiceTime += timeInSystem;

        this.currentCustomer = null;
        this.busy = false;

        return timeInSystem;
    }

    public String getServicePointName() {
        return servicePointName;
    }

    public boolean isBusy() {
        return busy;
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public int getCustomersServed() {
        return customersServed;
    }

    public double getAverageServiceTime() {
        return customersServed > 0 ? totalServiceTime / customersServed : 0.0;
    }

    @Override
    public String toString() {
        return String.format("%s (queue: %d, served: %d, avg time: %.2f min)",
                servicePointName, customerQueue.size(), customersServed, getAverageServiceTime());
    }
}
