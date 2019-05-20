package com.github.travelervihaan.domaincontroller.model;

public class Alert {
    private static boolean active = false;

    public Alert() {
    }

    public static boolean isActive() {
        return active;
    }

    public static void setActive(boolean active) {
        Alert.active = active;
    }
}
