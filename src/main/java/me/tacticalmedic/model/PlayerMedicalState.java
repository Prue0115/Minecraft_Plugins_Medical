package me.tacticalmedic.model;

import java.util.*;

public class PlayerMedicalState {

    private final UUID playerUUID;

    private int bloodLevel;
    private int painLevel;
    private int heartRate;
    private boolean unconscious;

    private final Map<BodyPart, Integer> bleedingMap = new EnumMap<>(BodyPart.class);
    private final Map<BodyPart, BleedingStage> bleedingStageMap = new EnumMap<>(BodyPart.class);
    private final Set<BodyPart> fracturedParts = new HashSet<>();

    // 🔧 생성자
    public PlayerMedicalState(UUID playerUUID) {
        this.playerUUID = playerUUID;
        this.bloodLevel = 5000;
        this.painLevel = 0;
        this.heartRate = 80;
        this.unconscious = false;
    }

    // ✅ UUID
    public UUID getUuid() {
        return playerUUID;
    }

    // ✅ 혈액
    public void setBloodLevel(int bloodLevel) {
        this.bloodLevel = Math.max(0, bloodLevel);
    }

    public int getBloodLevel() {
        return bloodLevel;
    }

    public void loseBlood(int amount) {
        this.bloodLevel -= amount;
        if (this.bloodLevel < 0) this.bloodLevel = 0;
    }

    // ✅ 통증
    public void setPainLevel(int painLevel) {
        this.painLevel = Math.max(0, painLevel);
    }

    public int getPainLevel() {
        return painLevel;
    }

    public void addPain(int amount) {
        this.painLevel += amount;
        if (this.painLevel < 0) this.painLevel = 0;
    }

    public void reducePain(int amount) {
        this.painLevel -= amount;
        if (this.painLevel < 0) this.painLevel = 0;
    }

    public PainStage getPainStage() {
        if (painLevel >= 80) return PainStage.SEVERE;
        if (painLevel >= 30) return PainStage.LIGHT;
        return PainStage.NONE;
    }

    // ✅ 출혈
    public Map<BodyPart, Integer> getBleedingMap() {
        return bleedingMap;
    }

    public Map<BodyPart, BleedingStage> getBleedingStageMap() {
        return bleedingStageMap;
    }

    public BleedingStage getBleedingStage(BodyPart part) {
        return bleedingStageMap.getOrDefault(part, BleedingStage.NONE);
    }

    public void setBleedingStage(BodyPart part, BleedingStage stage) {
        bleedingStageMap.put(part, stage);
    }

    public void clearAllBleeding() {
        bleedingMap.clear();
        bleedingStageMap.clear();
    }

    // ✅ 골절
    public boolean isFractured(BodyPart part) {
        return fracturedParts.contains(part);
    }

    public void setFracture(BodyPart part, boolean fractured) {
        if (fractured) fracturedParts.add(part);
        else fracturedParts.remove(part);
    }

    public void clearAllFractures() {
        fracturedParts.clear();
    }

    public Set<BodyPart> getFracturedParts() {
        return fracturedParts;
    }

    // ✅ 의식
    public boolean isUnconscious() {
        return unconscious;
    }

    public void setUnconscious(boolean unconscious) {
        this.unconscious = unconscious;
    }

    // ✅ 심박수
    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }
}