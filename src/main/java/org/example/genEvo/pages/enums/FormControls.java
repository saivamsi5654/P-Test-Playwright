package org.example.genEvo.pages.enums;

public enum FormControls {
    TEXT_INPUTS("text-inputs"),
    RADIO_BUTTONS("radios"),
    CHECKBOXES("checkboxes"),
    MULTI_SELECTS("multiselects"),
    TOGGLE_SWITCHES("toggles"),
    TOOLTIP("tooltip"),
    SHOW_HIDE_ELEMENTS("showhide"),
    HIDDEN_ELEMENTS("hidden-ajax");

    private final String label;

    FormControls(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
