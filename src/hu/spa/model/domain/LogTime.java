package hu.spa.model.domain;

public class LogTime implements Comparable<LogTime> {

    private final int hour;
    private final int minute;
    private final int second;

    public LogTime() {
        this(0);
    }

    public LogTime(int second) {
        this.hour = second / (60 * 60);
        this.minute = second % (60 * 60) / 60;
        this.second = second % (60 * 60) % 60;
    }

    public LogTime(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public boolean isNotNull() {
        return toSecond() > 0;
    }

    public int getHour() {
        return hour;
    }

    private Integer toSecond() {
        return second + minute * 60 + hour * 60 * 60;
    }

    public static LogTime diff(LogTime t1, LogTime t2) {
        return new LogTime(Math.abs(t1.toSecond() - t2.toSecond()));
    }

    public static LogTime add(LogTime t1, LogTime t2) {
        return new LogTime(t1.toSecond() + t2.toSecond());
    }

    @Override
    public String toString() {
        return String.format("%d:%02d:%02d", hour, minute, second);
    }

    @Override
    public int compareTo(LogTime otherLogTime) {
        return this.toSecond().compareTo(otherLogTime.toSecond());
    }
}
