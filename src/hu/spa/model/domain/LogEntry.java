package hu.spa.model.domain;

public class LogEntry {

    private final int guestId;
    private final Department department;
    private final boolean exit;
    private final LogTime logTime;

    public LogEntry(int guestId, Department department, boolean exit, LogTime logTime) {
        this.guestId = guestId;
        this.department = department;
        this.exit = exit;
        this.logTime = logTime;
    }

    public int getGuestId() {
        return guestId;
    }

    public Department getDepartment() {
        return department;
    }

    public boolean isExit() {
        return exit;
    }

    public LogTime getLogTime() {
        return logTime;
    }
}
