import java.util.List;

public class ServiceStatistics {

    public static void calculateStatistic(List<ServiceRecord> records) {
        if (records.isEmpty()) {
            System.out.println("No service records available for statistics");
            return;
        }

        System.out.println("\n=== SERVICE STATISTICS ===");

        long totalServiceTime = 0;
        long totalWaitingTime = 0;
        long totalResponseTime = 0;

        System.out.println("\nDetailed Service Records:");
        System.out.println("ID\tWaiting(ms)\tService(ms)\tResponse(ms)");
        System.out.println("-".repeat(50));

        for (ServiceRecord record : records) {
            double waitingMs = record.getWaitingTime() / 1_000_000.0; // convert nanoTime
            double serviceMs = record.getServiceTime() / 1_000_000.0;
            double responseMs = record.getResponseTime() / 1_000_000.0;

            System.out.printf("%d\t%.3f\t\t%.3f\t\t%.3f%n",
                    record.getCustomerId(), waitingMs, serviceMs, responseMs);

            totalServiceTime += record.getServiceTime();
            totalWaitingTime += record.getWaitingTime();
            totalResponseTime += record.getResponseTime();
        }

        int customerCount = records.size();
        double avgServiceTime = (totalServiceTime / 1_000_000.0) / customerCount;
        double avgWaitingTime = (totalWaitingTime / 1_000_000.0) / customerCount;
        double avgResponseTime = (totalResponseTime / 1_000_000.0) / customerCount;

        System.out.println("\nAVERAGE TIMES:");
        System.out.printf("Average Service Time: %.3f ms%n", avgServiceTime);
        System.out.printf("Average Waiting Time: %.3f ms%n", avgWaitingTime);
        System.out.printf("Average Response Time: %.3f ms%n", avgResponseTime);
        System.out.printf("Total customers served: %d%n", customerCount);
    }
}
