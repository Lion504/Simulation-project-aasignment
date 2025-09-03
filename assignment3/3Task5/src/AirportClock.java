public class AirportClock {
    private static AirportClock instance = null;
    private double currentTime; // minutes based

    // prevents external instantiation
    private AirportClock() {
        this.currentTime = 0.0;
    }

    //getter
    public static synchronized AirportClock getInstance() {
        if (instance == null) {
            instance = new AirportClock();
        }
        return instance;
    }

    public double getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(double newTime) {
        if (newTime >= currentTime) {
            this.currentTime = newTime;
        } else {
            System.out.println("⚠️ clock backwards!");
        }
    }

    public void advanceTime(double mins) {
        this.currentTime += mins;
    }

    public String getFormattedCurrentTime() {
        int hours = (int) (currentTime / 60);
        int minutes = (int) (currentTime % 60);
        return String.format("%02d:%02d", hours, minutes);
    }
}



