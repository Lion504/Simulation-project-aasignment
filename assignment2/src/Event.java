public class Event implements Comparable<Event> {
    private String eventName;
    private double eventTime;

    public Event(String eventName, double eventTime) {
        this.eventName = eventName;
        this.eventTime = eventTime;
    }
    public String getEventName() {
        return eventName;
    }
    public double getEventTime() {
        return eventTime;
    }

    @Override
    public int compareTo(Event other) {
        return Double.compare(this.eventTime, other.eventTime);
    }

    @Override
    public String toString() {
        return "Event{" + "eventName=" + eventName + ", at eventTime=" + eventTime + '}';
    }
}
