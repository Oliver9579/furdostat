package hu.spa.model.service;

import hu.spa.model.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GuestLogTransformer {

    public List<GuestLog> transform(List<LogEntry> logEntries) {
        return getGuestIds(logEntries).stream()
                .map(guestId -> new GuestLog(guestId, createDepartmentLogs(getLogEntriesByGuestId(guestId, logEntries))))
                .collect(Collectors.toList());
    }

    private List<LogEntry> getLogEntriesByGuestId(int guestId, List<LogEntry> logEntries) {
        return logEntries.stream()
                .filter(i -> i.getGuestId() == guestId)
                .collect(Collectors.toList());
    }

    private List<Integer> getGuestIds(List<LogEntry> logEntries) {
        return logEntries.stream()
                .map(i -> i.getGuestId())
                .distinct()
                .collect(Collectors.toList());
    }

    private List<DepartmentLog> createDepartmentLogs(List<LogEntry> logEntries) {
        List<DepartmentLog> departmentLogs = new ArrayList<>();
        LogTime arrivalTime = logEntries.get(0).getLogTime();
        LogTime departureTime = logEntries.get(logEntries.size() - 1).getLogTime();
        Department department = logEntries.get(0).getDepartment();
        departmentLogs.add(new DepartmentLog(department, arrivalTime, departureTime));
        for (int i = 1; i < logEntries.size() - 1; i +=2) {
            arrivalTime = logEntries.get(i).getLogTime();
            departureTime = logEntries.get(i + 1).getLogTime();
            department = logEntries.get(i).getDepartment();
            departmentLogs.add(new DepartmentLog(department, arrivalTime, departureTime));
        }
        return departmentLogs;
    }
}
