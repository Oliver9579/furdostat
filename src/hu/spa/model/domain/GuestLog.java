package hu.spa.model.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GuestLog implements  Comparable<GuestLog> {

    private final int guestId;
    private final List<DepartmentLog> departmentLogs;

    public GuestLog(int guestId, List<DepartmentLog> departmentLogs) {
        this.guestId = guestId;
        this.departmentLogs = departmentLogs;
    }

    public int getGuestId() {
        return guestId;
    }

    public boolean isSingleDepartmentVisit() {
        return departmentLogs.size() < 3;
    }

    public boolean isArrivalHour(int from, int to) {
        int hour = getArrivalTime().getHour();
        return from <= hour && hour < to;
    }

    public boolean hasStayTime(Department department) {
        return getStayTime(department).isNotNull();
    }

    public LogTime getArrivalTime() {
        return departmentLogs.stream()
                .filter(i -> i.getDepartment() == Department.DRESSING_ROOM)
                .map(i -> i.getArrivalTime())
                .findAny()
                .get();
    }

    public Map<Department, Boolean> getDepartmentVisitMap() {
        return Arrays.stream(Department.values())
                .collect(Collectors.toMap(i -> i, i -> hasStayTime(i)));
    }

    public LogTime getStayTime() {
        return getStayTimes(Department.DRESSING_ROOM).get(0);
    }

    public LogTime getStayTime(Department department) {
        return getStayTimes(department).stream()
                .reduce(new LogTime(), LogTime::add);


    }

        public List<LogTime> getStayTimes(Department department) {
        return departmentLogs.stream()
                .filter(i -> i.getDepartment() == department)
                .map(i -> i.getStayTime())
                .collect(Collectors.toList());
    }

    @Override
    public int compareTo(GuestLog otherGuestLog) {
        return this.getStayTime().compareTo(otherGuestLog.getStayTime());
    }
}
