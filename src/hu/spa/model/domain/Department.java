package hu.spa.model.domain;

import java.util.Arrays;

public enum Department {

    DRESSING_ROOM(0, "Öltöző"),
    SWIMMING_POOL(1, "Uszoda"),
    SAUNAS(2, "Szaunák"),
    SPAS(3, "Gyógyvizes medencék"),
    STRAND(4, "Strand");

    private final int id;
    private final String description;

    Department(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public static Department getDepartmentById(int id) {
        return Arrays.stream(Department.values())
                .filter(i -> i.id == id)
                .findAny()
                .get();
    }

    public String getDescription() {
        return description;
    }
}
