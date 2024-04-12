public class Process {
    private String processName;
    private String userName;
    private int nice;

    public Process(String processName, String userName, int nice) {
        this.processName = processName;
        this.userName = userName;
        this.nice = nice;
    }

    // Getters
    public String getProcessName() {
        return processName;
    }

    public String getUserName() {
        return userName;
    }

    public int getNice() {
        return nice;
    }

    // toString() method
    @Override
    public String toString() {
        int priority = 20 + nice;
        return processName + "," + userName + "," + priority + ",PR=" + (100 + priority);
    }
}
