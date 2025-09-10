public class AirportClockTest {
    public static void main(String[] args) {
        // Test Singleton behavior
        AirportClock clock1 = AirportClock.getInstance();
        AirportClock clock2 = AirportClock.getInstance();

        System.out.println("Testing Singleton:");
        System.out.println("clock1 == clock2: " + (clock1 == clock2));
        System.out.println("Same hash code: " + (clock1.hashCode() == clock2.hashCode()));

        // Test time operations
        System.out.println("\nTesting time operations:");
        System.out.println("Initial time: " + clock1.getFormattedCurrentTime());

        clock1.setCurrentTime(75.5);
        System.out.println("After setting to 75.5 minutes: " + clock1.getFormattedCurrentTime());

        clock2.advanceTime(30);
        System.out.println("After advancing 30 minutes: " + clock2.getFormattedCurrentTime());

        // Verify both references show same time, it should be since Singleton
        System.out.println("clock1 time: " + clock1.getCurrentTime());
        System.out.println("clock2 time: " + clock2.getCurrentTime());

        // Test setting time backwards
        try {
            clock1.setCurrentTime(60);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("After attempting to set to backwards 60 minutes: " + clock1.getFormattedCurrentTime());

        // Test formatting of time
        clock1.setCurrentTime(5.75);
        System.out.println("Test formatting with fractional minutes: " + clock1.getFormattedCurrentTime());
        clock1.setCurrentTime(61);
        System.out.println("Test formatting with an hour and a minute: " + clock1.getFormattedCurrentTime());

        // Test initial state
        // AirportClock newly_created_clock = new AirportClock(); // This line should
        // fail compilation since constructor is private
        // System.out.println("newly_created_clock time: " +
        // newly_created_clock.getCurrentTime());
    }
}