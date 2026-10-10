package org.example.genEvo.pages.enums;

public enum Modules {
    FORM_CONTROLS("form-controls"),
    DROPDOWNS("dropdowns"),
    FILE_HANDLING("file-handling"),
    TABLES("tables"),
    DIALOGS_INTERACTIONS("interactions"),
    BROWSER_CONTEXT("browser"),
    FRAMES("frames"),
    CALENDER_DATE_PICKER("date-controls"),
    ADVANCED_UI_ELEMENTS("advanced"),
    LABS("browser-internals");

    private final String label;

    Modules(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
