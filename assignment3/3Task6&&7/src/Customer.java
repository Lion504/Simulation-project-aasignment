/**
 * Customer class representing passengers in airport simulation
 */
public class Customer {
    private String customerId;
    private double arrivalTime;
    private String flightDestination;
    private String passengerType;

    public Customer(String customerId, double arrivalTime) {
        this.customerId = customerId;
        this.arrivalTime = arrivalTime;
        this.flightDestination = "Unknown"; // Default destination
        this.passengerType = "Regular";     // Default type
    }

    public Customer(String customerId, double arrivalTime, String flightDestination, String passengerType) {
        this.customerId = customerId;
        this.arrivalTime = arrivalTime;
        this.flightDestination = flightDestination;
        this.passengerType = passengerType;
    }

    public String getCustomerId() {
        return customerId;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public String getFlightDestination() {
        return flightDestination;
    }

    public String getPassengerType() {
        return passengerType;
    }

    public void setFlightDestination(String flightDestination) {
        this.flightDestination = flightDestination;
    }

    public void setPassengerType(String passengerType) {
        this.passengerType = passengerType;
    }

    @Override
    public String toString() {
        return String.format("Customer %s (arrived: %.2f min, dest: %s, type: %s)",
                customerId, arrivalTime, flightDestination, passengerType);
    }
}
