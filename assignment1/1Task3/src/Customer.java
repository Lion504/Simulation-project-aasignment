public class Customer {
    private static int idCount = 1;
    private final int id;
    private long startTime;
    private long endTime;

    // Constructor initial
    public Customer() {
        this.id = idCount++;
        this.startTime = 0;
        this.endTime = 0;
    }

    public Customer(long startTime, long endTime) {
        this.id = idCount++;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters
    public int getId() {
        return id;
    }
    public long getStartTime() {
        return startTime;
    }
    public long getEndTime() {
        return endTime;
    }

    //setters
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    // calculate time spent
    public long getTimeSpent() {
        return endTime - startTime;
    }

    @Override
    public String toString() {
        return "Id=" + id
                + "\nstartTime=" + startTime
                + "ms\nendTime=" + endTime
                + "ms\ntimeSpent=" + getTimeSpent() + " ms";
    }
}
