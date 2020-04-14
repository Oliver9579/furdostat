package hu.spa.model.service;

import hu.spa.model.domain.Department;
import hu.spa.model.domain.LogEntry;
import hu.spa.model.domain.LogTime;

import java.util.List;
import java.util.stream.Collectors;

public class DataParser {

    public List<LogEntry> parse(List<String> lines) {
        return lines.stream()
                .map(i -> createLogEntry(i))
                .collect(Collectors.toList());

    }

    private LogEntry createLogEntry(String line) {
        String[] items = line.split(" ");
        int guestId = getValue(items[0]);
        Department department = Department.getDepartmentById(getValue(items[1]));
        boolean exit = getValue(items[2]) == 1;
        int hour = getValue(items[3]);
        int minute = getValue(items[4]);
        int second = getValue(items[5]);
        LogTime logTime = new LogTime(hour, minute, second);
        return new LogEntry(guestId, department, exit, logTime);
    }

    private int getValue(String value) {
        return Integer.parseInt(value);
    }
}
