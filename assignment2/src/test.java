public class test {
    public static void main(String[] args) {
        EventList list = new EventList();
        list.addEvent(new Event("a1", 2, EventType.ARRIVAL));
        list.addEvent(new Event("a2", 5, EventType.ARRIVAL));
        list.addEvent(new Event("a1", 7, EventType.EXIT));
        list.addEvent(new Event("a2", 10, EventType.EXIT));

        System.out.println("=== Current Event List ===");
        for (Event e : list.getEventsOrdered()) {
            System.out.println(e);
        }

        System.out.println("=== Served Event List ===");
        while (!list.isEmpty()) {
            Event event = list.getNextEvent();
            System.out.println("nextEvent: " + event);
        }

        System.out.println("=== Current Event List ===");
        for (Event e : list.getEventsOrdered()) {
            System.out.println(e);
        }

    }
}
