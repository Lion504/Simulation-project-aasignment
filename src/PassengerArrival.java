import java.util.*;

public class PassengerArrival {

    // EventType enum
    enum EventType {
        ARRIVAL, EXIT
    }

    // Simple Event class
    static class Event implements Comparable<Event> {
        String eventName;
        double eventTime;
        EventType eventType;

        public Event(String eventName, double eventTime, EventType eventType) {
            this.eventName = eventName;
            this.eventTime = eventTime;
            this.eventType = eventType;
        }

        @Override
        public int compareTo(Event other) {
            return Double.compare(this.eventTime, other.eventTime);
        }

        @Override
        public String toString() {
            return eventName + " (" + eventType + ") at " + String.format("%.2f", eventTime) + " min";
        }

        public double getEventTime() { return eventTime; }
        public String getEventName() { return eventName; }
        public EventType getEventType() { return eventType; }
    }

    // Simple EventList class
    static class EventList {
        private PriorityQueue<Event> events = new PriorityQueue<>();

        public void addEvent(Event e) {
            events.add(e);
        }

        public Event getNextEvent() {
            return events.poll();
        }

        public boolean isEmpty() {
            return events.isEmpty();
        }

        public List<Event> getEventsOrdered() {
            List<Event> list = new ArrayList<>(events);
            Collections.sort(list);
            return list;
        }

        public int size() {
            return events.size();
        }
    }

    // Singleton AirportClock class
    static class AirportClock {
        private static AirportClock instance = null;
        private double time = 0.0;

        private AirportClock() {}

        public static AirportClock getInstance() {
            if (instance == null) {
                instance = new AirportClock();
            }
            return instance;
        }

        public double getTime() {
            return time;
        }

        public void setTime(double newTime) {
            if (newTime >= time) {
                time = newTime;
            }
        }

        public String getFormattedTime() {
            int hours = (int) (time / 60);
            int minutes = (int) (time % 60);
            return String.format("%02d:%02d", hours, minutes);
        }
    }

    // PassengerArrivalProcess class
    static class PassengerArrivalProcess {
        private EventType eventType;
        private Random randomGenerator;
        private double meanArrivalInterval;
        private String arrivalGate;
        private int passengerCounter;

        public PassengerArrivalProcess(EventType eventType, double meanArrivalInterval, String arrivalGate) {
            this.eventType = eventType;
            this.meanArrivalInterval = meanArrivalInterval;
            this.arrivalGate = arrivalGate;
            this.randomGenerator = new Random();
            this.passengerCounter = 1;
        }

        public void generateNextArrival(EventList eventList) {
            AirportClock clock = AirportClock.getInstance();
            double currentTime = clock.getTime();

            // Generate exponentially distributed inter-arrival time
            double interArrivalTime = generateExponentialTime();
            double arrivalTime = currentTime + interArrivalTime;

            // Create arrival event
            String eventName = "Passenger-" + passengerCounter + " arrives at " + arrivalGate;
            Event arrivalEvent = new Event(eventName, arrivalTime, eventType);

            eventList.addEvent(arrivalEvent);
            clock.setTime(arrivalTime);
            passengerCounter++;
        }

        private double generateExponentialTime() {
            double uniform = randomGenerator.nextDouble();
            return -Math.log(1 - uniform) * meanArrivalInterval;
        }

        public String getArrivalGate() {
            return arrivalGate;
        }

        public double getMeanArrivalInterval() {
            return meanArrivalInterval;
        }
    }

    // Simple Passenger class for queue simulation
    static class Passenger {
        String id;
        double arrivalTime;

        Passenger(String id, double arrivalTime) {
            this.id = id;
            this.arrivalTime = arrivalTime;
        }

        @Override
        public String toString() {
            return id + " (arrived at " + String.format("%.2f", arrivalTime) + " min)";
        }
    }

    // Simple SecurityQueue class
    static class SecurityQueue {
        private Queue<Passenger> queue = new LinkedList<>();

        public void addPassenger(Passenger p) {
            queue.add(p);
        }

        public Passenger removePassenger() {
            return queue.poll();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }

        public int size() {
            return queue.size();
        }
    }

    // Main test method
    public static void main(String[] args) {
        System.out.println("=== AIRPORT PASSENGER ARRIVAL SIMULATION ===\n");

        // Initialize components
        EventList eventList = new EventList();
        AirportClock clock = AirportClock.getInstance();
        SecurityQueue securityQueue = new SecurityQueue();

        // Create arrival process (passengers arrive every 3 minutes on average)
        PassengerArrivalProcess arrivalProcess = new PassengerArrivalProcess(
                EventType.ARRIVAL, 3.0, "Terminal 1 Entrance"
        );

        System.out.println("1. GENERATING PASSENGER ARRIVALS");
        System.out.println("Mean arrival interval: " + arrivalProcess.getMeanArrivalInterval() + " minutes");
        System.out.println("Arrival gate: " + arrivalProcess.getArrivalGate());

        // Generate 8 passenger arrival events
        for (int i = 0; i < 800; i++) {
            arrivalProcess.generateNextArrival(eventList);
        }

        System.out.println("Generated " + eventList.size() + " arrival events");
        System.out.println("Final clock time: " + clock.getFormattedTime() +
                " (" + String.format("%.2f", clock.getTime()) + " min)\n");

        System.out.println("2. SCHEDULED EVENTS (chronological order):");
        List<Event> orderedEvents = eventList.getEventsOrdered();
        for (int i = 0; i < orderedEvents.size(); i++) {
            Event e = orderedEvents.get(i);
            System.out.printf("  %d. %s\n", (i+1), e.toString());
        }

        System.out.println("\n3. PROCESSING ARRIVALS (passengers join security queue):");
        while (!eventList.isEmpty()) {
            Event event = eventList.getNextEvent();
            System.out.printf("Time %s: %s\n",
                    AirportClock.getInstance().getFormattedTime(),
                    event.getEventName());

            // Create passenger and add to security queue
            String passengerId = event.getEventName().split(" ")[0]; // Extract "Passenger-X"
            Passenger passenger = new Passenger(passengerId, event.getEventTime());
            securityQueue.addPassenger(passenger);
            System.out.println("  → Added to security queue");

            // Update clock to event time
            clock.setTime(event.getEventTime());
        }

        System.out.printf("\nSecurity queue now has %d passengers\n", securityQueue.size());

        System.out.println("\n4. SIMULATING PROCESSING TIME:");
        clock.setTime(clock.getTime() + 10.0); // Advance 10 minutes
        System.out.println("Advanced clock by 10 minutes to: " + clock.getFormattedTime());

        System.out.println("\n5. PROCESSING SECURITY QUEUE (calculating wait times):");
        List<Double> waitTimes = new ArrayList<>();
        int processedCount = 0;

        while (!securityQueue.isEmpty() && processedCount < 800) {
            Passenger passenger = securityQueue.removePassenger();
            double waitTime = clock.getTime() - passenger.arrivalTime;
            waitTimes.add(waitTime);

            System.out.printf("  Processed %s | Wait time: %.2f minutes\n",
                    passenger.toString(), waitTime);

            // Simulate 2 minutes processing per passenger
            clock.setTime(clock.getTime() + 2.0);
            processedCount++;
        }

        System.out.println("\n6. SIMULATION STATISTICS:");
        if (!waitTimes.isEmpty()) {
            double avgWait = waitTimes.stream().mapToDouble(Double::doubleValue).average().orElse(0);
            double maxWait = waitTimes.stream().mapToDouble(Double::doubleValue).max().orElse(0);
            double minWait = waitTimes.stream().mapToDouble(Double::doubleValue).min().orElse(0);

            System.out.printf("  Passengers processed: %d\n", processedCount);
            System.out.printf("  Average wait time: %.2f minutes\n", avgWait);
            System.out.printf("  Minimum wait time: %.2f minutes\n", minWait);
            System.out.printf("  Maximum wait time: %.2f minutes\n", maxWait);
            System.out.printf("  Final simulation time: %s (%.2f min)\n",
                    clock.getFormattedTime(), clock.getTime());
        }

        System.out.println("\n=== SIMULATION COMPLETE ===");
    }
}
