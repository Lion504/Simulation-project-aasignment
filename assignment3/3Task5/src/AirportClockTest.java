public class AirportClockTest {
    public static void main(String[] args) {
        // Test Singleton behave
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
    }
}