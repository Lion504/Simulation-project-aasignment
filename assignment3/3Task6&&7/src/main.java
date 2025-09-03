import java.util.List;

public class main {
    public static void main(String[] args) {
        System.out.println("=== ARRIVALPROCESS TEST PROGRAM ===\n");

        // Create EventList and clock
        EventList eventList = new EventList();
        AirportClock clock = AirportClock.getInstance();

        // passenger arrivals with 0.7 probability
        ArrivalProcess passengerArrivals = new ArrivalProcess("PassengerArrival", 8.0, 0.7);

        System.out.println("\n1. GENERATING 10 ARRIVAL EVENTS:");
        System.out.println("Initial clock time: " + clock.getCurrentTime() + " minutes\n");

        // Generate 10 arrival events as required by task
        for (int i = 1; i <= 100; i++) {
            System.out.printf("--- Creating arrival event #%d ---\n", i);
            passengerArrivals.generateNextArrival(eventList);
            System.out.println();
        }

        System.out.println("2. FINAL RESULTS:");
        System.out.printf("Total events created: %d\n", eventList.size());
        System.out.printf("Final clock time: %.2f minutes\n", clock.getCurrentTime());

        System.out.println("\n3. EVENT LIST (order not important as per task):");
        List<passengerArrival.Event> allEvents = eventList.getAllEvents();
        for (int i = 0; i < allEvents.size(); i++) {
            passengerArrival.Event event = allEvents.get(i);
            System.out.printf("  [%d] %s\n", (i+1), event.toString());
        }

        System.out.println("\n4. STATISTICS:");
        System.out.printf("  Event type: %s\n", passengerArrivals.getEventType());
        System.out.printf("  Random generator: %s\n", passengerArrivals.getRandomGenerator());
        System.out.printf("  Base interval: %.2f minutes\n", passengerArrivals.getBaseInterval());

        if (!eventList.isEmpty()) {
            double totalTime = clock.getCurrentTime();
            double avgInterval = totalTime / eventList.size();
            System.out.printf("  Average actual interval: %.2f minutes\n", avgInterval);
        }
    }
}
