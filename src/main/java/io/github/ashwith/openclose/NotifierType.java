package io.github.ashwith.openclose;

public enum NotifierType {

    EMAIL("email"),
    SMS("sms");
    private final String value;

    NotifierType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
