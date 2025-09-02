public class Event implements Comparable<Event> {
    private String eventName;
    private double eventTime;
    private EventType eventType;

    public Event(String eventName, double eventTime) {
        this.eventName = eventName;
        this.eventTime = eventTime;
        this.eventType = eventType;
    }
    public String getEventName() {
        return eventName;
    }
    public double getEventTime() {
        return eventTime;
    }

    public EventType getEventType() {
        return eventType;
    }

    @Override
    public int compareTo(Event other) {
        return Double.compare(this.eventTime, other.eventTime);
    }

    @Override
    public String toString() {
        return "Event{" + "eventName=" + eventName + "("+ eventType +"), at eventTime=" + eventTime + '}';
    }
}
