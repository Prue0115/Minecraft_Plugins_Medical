package me.tacticalmedic.treatment;

import me.tacticalmedic.TacticalMedic;
import me.tacticalmedic.config.ConfigManager;
import me.tacticalmedic.model.BodyPart;
import me.tacticalmedic.model.BleedingStage;
import me.tacticalmedic.model.PlayerMedicalState;
import org.bukkit.entity.Player;

public class MedicalActions {

    public static void applyBandage(Player player, BodyPart part) {
        PlayerMedicalState state = TacticalMedic.getInstance().getState(player.getUniqueId());
        state.setBleedingStage(part, BleedingStage.NONE);
        player.sendMessage("§a[치료] " + part.getDisplay() + " 부위 출혈이 멈췄습니다.");
    }

    public static void applySplint(Player player, BodyPart part) {
        PlayerMedicalState state = TacticalMedic.getInstance().getState(player.getUniqueId());
        if (!state.isFractured(part)) {
            player.sendMessage("§7[치료] 골절이 없는 부위입니다.");
            return;
        }
        state.setFracture(part, false);
        player.sendMessage("§a[치료] " + part.getDisplay() + " 골절을 치료했습니다.");
    }

    public static void applyMorphine(Player player) {
        PlayerMedicalState state = TacticalMedic.getInstance().getState(player.getUniqueId());

        int reduceAmount = ConfigManager.getInt("pain.morphineReduction", 5);
        state.reducePain(reduceAmount);
        player.sendMessage("§d[모르핀] 통증 감소: -" + reduceAmount);
    }

    public static void applyCPR(Player player, Player target) {
        PlayerMedicalState state = TacticalMedic.getInstance().getState(target.getUniqueId());

        if (!state.isUnconscious()) {
            player.sendMessage("§7[CPR] 대상은 의식이 있습니다.");
            return;
        }

        double chance = ConfigManager.getDouble("cpr.successRate", 0.6);
        if (Math.random() < chance) {
            state.setUnconscious(false);
            state.setHeartRate(ConfigManager.getInt("cpr.reviveHeartRate", 65));
            player.sendMessage("§a[CPR 성공] 환자가 의식을 되찾았습니다!");
        } else {
            player.sendMessage("§c[CPR 실패] 다시 시도하세요.");
        }
    }
}