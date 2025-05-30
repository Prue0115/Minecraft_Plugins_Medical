package me.tacticalmedic.gui;

import me.tacticalmedic.model.BodyPart;

public class GUIUtil {

    public static int mapBodyPartToSlot(BodyPart part) {
        return switch (part) {
            case HEAD -> 13;
            case TORSO -> 22;
            case ARM_LEFT -> 12;
            case ARM_RIGHT -> 14;
            case LEG_LEFT -> 21;
            case LEG_RIGHT -> 23;
        };
    }
}
