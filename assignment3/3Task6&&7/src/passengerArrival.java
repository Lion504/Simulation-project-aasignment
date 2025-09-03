class passengerArrival {

    static class Event {
        private String eventType;
        private String eventName;
        private double eventTime;
        private int eventId;

        public Event(String eventType, String eventName, double eventTime, int eventId) {
            this.eventType = eventType;
            this.eventName = eventName;
            this.eventTime = eventTime;
            this.eventId = eventId;
        }

        public String getEventType() {
            return eventType;
        }

        public String getEventName() {
            return eventName;
        }

        public double getEventTime() {
            return eventTime;
        }

        public int getEventId() {
            return eventId;
        }

        @Override
        public String toString() {
            return String.format("Event #%d: %s (%s) at time %.2f",
                    eventId, eventName, eventType, eventTime);
        }
    }

}
