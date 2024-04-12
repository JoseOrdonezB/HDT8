import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class WithPriorityQueue {
    public static void main(String[] args) {
        PriorityQueue<Process> processQueue = new PriorityQueue<>(Comparator.comparingInt(Process::getNice));

        // Read processes from the file and add them to the priority queue
        try (Stream<String> lines = Files.lines(Paths.get("procesos.txt"))) {
            lines.map(line -> line.split(","))
                 .filter(processData -> processData.length == 3)
                 .forEach(processData -> {
                     String processName = processData[0];
                     String userName = processData[1];
                     int niceValue = Integer.parseInt(processData[2]);
                     processQueue.add(new Process(processName, userName, niceValue));
                 });
        } catch (IOException e) {
            System.err.println("Error while reading the file: " + e.getMessage());
        }

        // Remove and display processes one by one in priority order
        while (!processQueue.isEmpty()) {
            Process processed = processQueue.poll();
            System.out.println("Process: " + processed.getProcessName() +
                    ", User: " + processed.getUserName() +
                    ", Priority (PR): " + processed.toString());
        }
    }
}
