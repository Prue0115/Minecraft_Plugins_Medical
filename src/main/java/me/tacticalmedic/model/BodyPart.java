package me.tacticalmedic.model;

public enum BodyPart {
    HEAD("머리"),
    TORSO("몸통"),
    ARM_LEFT("왼팔"),
    ARM_RIGHT("오른팔"),
    LEG_LEFT("왼다리"),
    LEG_RIGHT("오른다리");

    private final String display;

    BodyPart(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }
}