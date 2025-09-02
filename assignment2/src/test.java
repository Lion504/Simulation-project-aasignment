public class test {
    public static void main(String[] args) {
        EventList list = new EventList();
        list.addEvent(new Event("a1", 5));
        list.addEvent(new Event("a2", 2));
        list.addEvent(new Event("a3", 7));
        list.addEvent(new Event("a4", 1));

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
