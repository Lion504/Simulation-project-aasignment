import java.util.ArrayList;
import java.util.List;

public class EventList {
    private final List<passengerArrival.Event> events;

    public EventList() {
        events = new ArrayList<>();
    }

    public void addEvent(passengerArrival.Event event) {
        events.add(event);
        System.out.println("  → Added to event list: " + event);
    }

    public List<passengerArrival.Event> getAllEvents() {
        return new ArrayList<>(events);
    }

    public int size() {
        return events.size();
    }

    public boolean isEmpty() {
        return events.isEmpty();
    }
}

