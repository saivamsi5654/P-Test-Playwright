package org.example.orange.pages.navigation;

public enum MainMenuItem {
    ADMIN("Admin"),
    PIM("Pim"),
    LEAVE("Leave"),
    TIME("Time"),
    RECRUITMENT("Recruitment"),
    MY_INFO("My info"),
    PERFORMANCE("Performance"),
    DASHBOARD("Dashboard"),
    DIRECTORY("Directory"),
    MAINTENANCE("Maintenance"),
    CLAIM("Claim"),
    BUZZ("Buzz");

    private final String label;

    MainMenuItem(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }

    public static MainMenuItem formLabel(String label) {
        for(MainMenuItem item: values()) {
            if(item.label.equalsIgnoreCase(label)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unsupported Menu Item " + label);
    }
}
