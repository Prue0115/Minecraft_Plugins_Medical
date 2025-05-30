package me.tacticalmedic.treatment;

import me.tacticalmedic.config.ConfigManager;
import me.tacticalmedic.model.PainStage;
import me.tacticalmedic.model.PlayerMedicalState;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PainSystem {

    public static void applyPainEffects(Player player, PlayerMedicalState state) {
        PainStage stage = state.getPainStage();

        String basePath = "effects.pain.";

        switch (stage) {
            case LIGHT -> {
                int duration = ConfigManager.getInt(basePath + "light.duration", 100);
                int slow = ConfigManager.getInt(basePath + "light.slow", 0);
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, duration, slow, true, false));
            }

            case MODERATE -> {
                int duration = ConfigManager.getInt(basePath + "moderate.duration", 120);
                int slow = ConfigManager.getInt(basePath + "moderate.slow", 1);
                boolean confusion = ConfigManager.getBoolean(basePath + "moderate.confusion", true);

                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, duration, slow, true, false));
                if (confusion) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 0, true, false));
                }
            }

            case SEVERE -> {
                int duration = ConfigManager.getInt(basePath + "severe.duration", 160);
                int slow = ConfigManager.getInt(basePath + "severe.slow", 2);
                boolean confusion = ConfigManager.getBoolean(basePath + "severe.confusion", true);
                boolean darkness = ConfigManager.getBoolean(basePath + "severe.darkness", true);

                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, duration, slow, true, false));
                if (confusion) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 1, true, false));
                }
                if (darkness) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 80, 0, true, false));
                }
            }
        }
    }
}
