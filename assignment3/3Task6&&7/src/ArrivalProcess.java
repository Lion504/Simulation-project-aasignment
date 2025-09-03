import eduni.distributions.Bernoulli;

public class ArrivalProcess {

    private final String eventType;                    // Event type property
    private final Bernoulli randomGenerator;           // Random number generator property
    private final double baseArrivalInterval;          // Base time between arrivals
    private final double shortInterval;
    private final double longInterval;  // Intervals based on Bernoulli outcome
    private int eventCounter;

    /**
     * Constructor for passengerArrival
     *
     * @param eventType          The type of events this process generates
     * @param baseInterval       Base interval between arrivals (minutes)
     * @param successProbability Probability for Bernoulli distribution
     */
    public ArrivalProcess(String eventType, double baseInterval, double successProbability) {
        this.eventType = eventType;
        this.baseArrivalInterval = baseInterval;
        this.randomGenerator = new Bernoulli(successProbability);

        // Define intervals based on Bernoulli outcomes
        this.shortInterval = baseInterval * 0.6;  // 60% of base (faster arrivals)
        this.longInterval = baseInterval * 1.4;   // 140% of base (slower arrivals)
        this.eventCounter = 1;

        System.out.printf("passengerArrival created: %s events, %s\n",
                eventType, randomGenerator.toString());
        System.out.printf("  Base interval: %.2f min, Short: %.2f min, Long: %.2f min\n",
                baseInterval, shortInterval, longInterval);
    }

    /**
     * Custom method to add new event to event list (as required by task)
     * Uses random number generator to determine arrival interval
     *
     * @param eventList The event list to add the new event to
     */
    public void generateNextArrival(EventList eventList) {
        AirportClock clock = AirportClock.getInstance();
        double currentTime = clock.getCurrentTime();

        // Use Bernoulli distribution to determine interval
        long bernoulliSample = randomGenerator.sample();
        double arrivalInterval;
        String intervalType;

        if (bernoulliSample == 1) {
            arrivalInterval = shortInterval;
            intervalType = "short";
        } else {
            arrivalInterval = longInterval;
            intervalType = "long";
        }

        double eventTime = currentTime + arrivalInterval;

        // Create new arrival event
        String eventName = eventType + " #" + eventCounter;
        passengerArrival.Event newEvent = new passengerArrival.Event(eventType, eventName, eventTime, eventCounter);

        System.out.printf("Generating arrival: Bernoulli=%d → %s interval (%.2f min)\n",
                bernoulliSample, intervalType, arrivalInterval);

        // Add event to event list (passed as parameter as required)
        eventList.addEvent(newEvent);

        // Move clock to new event time
        clock.setCurrentTime(eventTime);

        eventCounter++;
    }

    // Getter methods
    public String getEventType() {
        return eventType;
    }

    public Bernoulli getRandomGenerator() {
        return randomGenerator;
    }

    public double getBaseInterval() {
        return baseArrivalInterval;
    }
}
