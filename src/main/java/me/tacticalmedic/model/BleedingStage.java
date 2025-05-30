package me.tacticalmedic.model;

public enum BleedingStage {
    NONE("§7없음", 0),
    LIGHT("§e경미", 5),
    MODERATE("§6중간", 10),
    SEVERE("§c심각", 20);

    private final String display;
    private final int lossPerTick;

    BleedingStage(String display, int lossPerTick) {
        this.display = display;
        this.lossPerTick = lossPerTick;
    }

    public String getDisplay() {
        return display;
    }

    public int getLossPerTick() {
        return lossPerTick;
    }
}