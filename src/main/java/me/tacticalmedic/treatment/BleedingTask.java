package me.tacticalmedic.treatment;

import me.tacticalmedic.TacticalMedic;
import me.tacticalmedic.config.ConfigManager;
import me.tacticalmedic.model.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class BleedingTask extends BukkitRunnable {

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            UUID uuid = player.getUniqueId();

            if (!TacticalMedic.getInstance().hasState(uuid)) {
                TacticalMedic.getInstance().createState(uuid);
            }

            PlayerMedicalState state = TacticalMedic.getInstance().getState(uuid);
            if (state == null) return;

            int totalLoss = 0;

            for (BodyPart part : BodyPart.values()) {
                BleedingStage stage = state.getBleedingStage(part);
                totalLoss += stage.getLossPerTick();
            }

            state.loseBlood(totalLoss);

            if (totalLoss > 0) {
                player.sendMessage("§c[출혈] -" + totalLoss + "ml (현재 혈액: " + state.getBloodLevel() + "ml)");
            }

            if (state.getBloodLevel() <= 0) {
                player.setHealth(0);
                player.sendMessage("§4[사망] 과다출혈로 사망했습니다.");
            }
        }
    }}

