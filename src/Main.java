import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        PriorityQueue<Process> processQueue = new PriorityQueue<>(Comparator.comparingInt(Process::getNice));

        try (Stream<String> lines = Files.lines(Paths.get("procesos.txt"))) {
            lines.map(line -> line.split(","))
                    .filter(processData -> processData.length == 3)
                    .map(processData -> new Process(processData[0], processData[1], Integer.parseInt(processData[2])))
                    .forEach(processQueue::add);
        } catch (IOException e) {
            e.printStackTrace();
        }

        processQueue.forEach(process -> System.out.println("Process: " + process.getProcessName() +
                ", User: " + process.getUserName() +
                ", Priority (PR): " + process));
    }
}
