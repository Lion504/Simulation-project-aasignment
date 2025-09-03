import java.util.List;
import java.util.ArrayList;

public class newMain {
    public static void main(String[] args) {
        System.out.println("=== AIRPORT SIMULATION TEST SUITE - TASK 7 ===\n");

        // Create components
        EventList eventList = new EventList();
        AirportClock clock = AirportClock.getInstance();
        ServicePoint securityCheckpoint = new ServicePoint("Security Checkpoint Alpha");

        // Create passenger arrival process with 0.7 probability
        ArrivalProcess passengerArrivals = new ArrivalProcess("PassengerArrival", 8.0, 0.7);

        System.out.println("1. GENERATING 10 ARRIVAL EVENTS:");
        System.out.println("Initial clock time: " + clock.getCurrentTime() + " minutes\n");

        for (int i = 1; i <= 100; i++) {
            System.out.printf("--- Creating arrival event #%d ---\n", i);
            passengerArrivals.generateNextArrival(eventList);
            System.out.println();
        }

        System.out.println("2. CLOCK STATUS AFTER EVENT GENERATION:");
        System.out.printf("Total events created: %d\n", eventList.size());
        System.out.printf("Clock displays time of last event: %.2f minutes (%s)\n",
                clock.getCurrentTime(), clock.getFormattedCurrentTime());

        System.out.println("\n3. PROCESSING ALL EVENTS SEQUENTIALLY (creating customers):");
        System.out.println("Note: Clock is NOT moved during this phase\n");

        List<Customer> allCustomers = new ArrayList<>();
        List<passengerArrival.Event> allEvents = eventList.getAllEvents();
        String[] destinations = {"New York", "London", "Tokyo", "Paris", "Dubai", "Berlin", "Sydney"};
        String[] passengerTypes = {"Economy", "Business", "First Class", "VIP"};

        for (int i = 0; i < allEvents.size(); i++) {
            passengerArrival.Event event = allEvents.get(i);
            System.out.printf("Processing event [%d]: %s\n", (i+1), event.toString());

            // Create customer with event time as arrival time (as required by task)
            String customerId = "PAX-" + String.format("%03d", event.getEventId());
            Customer customer = new Customer(customerId, event.getEventTime());

            // Add some airport realism
            customer.setFlightDestination(destinations[i % destinations.length]);
            customer.setPassengerType(passengerTypes[i % passengerTypes.length]);

            // Add customer to service point queue
            securityCheckpoint.addCustomerToQueue(customer);
            allCustomers.add(customer);

            System.out.printf("  → Created customer: %s\n", customer.toString());
            System.out.println();
        }

        System.out.println("4. ADVANCING CLOCK BY 5 TIME UNITS:");
        double timeBeforeAdvance = clock.getCurrentTime();
        clock.advanceTime(5.0);
        System.out.printf("Clock moved from %.2f to %.2f minutes (+5.0 minutes)\n",
                timeBeforeAdvance, clock.getCurrentTime());
        System.out.printf("Current time: %s\n", clock.getFormattedCurrentTime());

        System.out.println("\n5. CLEARING SERVICE POINT - CALCULATING TIME IN SYSTEM:");
        System.out.printf("Processing customers from %s...\n\n", securityCheckpoint.getServicePointName());

        List<Double> timeInSystemResults = new ArrayList<>();
        int processedCount = 0;

        while (!securityCheckpoint.isQueueEmpty()) {
            Customer customer = securityCheckpoint.removeCustomerFromQueue();
            if (customer != null) {
                processedCount++;

                // Start service
                securityCheckpoint.startService(customer);

                // Complete service and get time spent in system
                double timeInSystem = securityCheckpoint.completeService();
                timeInSystemResults.add(timeInSystem);

                System.out.printf("Customer %d processed: %s spent %.2f minutes in system\n",
                        processedCount, customer.getCustomerId(), timeInSystem);
                System.out.println();

                // Simulate some processing time (advance clock by 1 minute per customer)
                clock.advanceTime(1.0);
            }
        }

        System.out.println("6. FINAL AIRPORT SIMULATION STATISTICS:");
        System.out.printf("Service Point: %s\n", securityCheckpoint.toString());
        System.out.printf("Total customers processed: %d\n", processedCount);

        if (!timeInSystemResults.isEmpty()) {
            double avgTimeInSystem = timeInSystemResults.stream().mapToDouble(Double::doubleValue).average().orElse(0);
            double minTimeInSystem = timeInSystemResults.stream().mapToDouble(Double::doubleValue).min().orElse(0);
            double maxTimeInSystem = timeInSystemResults.stream().mapToDouble(Double::doubleValue).max().orElse(0);

            System.out.printf("Average time in system: %.2f minutes\n", avgTimeInSystem);
            System.out.printf("Minimum time in system: %.2f minutes\n", minTimeInSystem);
            System.out.printf("Maximum time in system: %.2f minutes\n", maxTimeInSystem);
        }

        System.out.printf("Final simulation time: %.2f minutes (%s)\n",
                clock.getCurrentTime(), clock.getFormattedCurrentTime());

    }
}
