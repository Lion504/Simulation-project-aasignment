import java.util.Collections;
import java.util.PriorityQueue;
import java.util.ArrayList;

public class EventList {
    private PriorityQueue<Event> events;
    public EventList() {
        events = new PriorityQueue<>();
    }

    public void addEvent(Event newEvent) {
        events.add(newEvent);
    }

    //retrieve and remove next events to process
    public Event getNextEvent() {
        return events.poll();
    }

    public boolean isEmpty() {
        return events.isEmpty();
    }

    public ArrayList<Event> getEventsOrdered() {
        ArrayList<Event> orderedList = new ArrayList<>(events);
        Collections.sort(orderedList);
        return orderedList;
    }
}
