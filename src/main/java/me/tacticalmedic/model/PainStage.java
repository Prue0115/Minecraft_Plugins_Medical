package me.tacticalmedic.model;

public enum PainStage {
    NONE("§7없음"),
    LIGHT("§e경미"),
    MODERATE("§6중간"),
    SEVERE("§c심각");

    private final String display;

    PainStage(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }
}