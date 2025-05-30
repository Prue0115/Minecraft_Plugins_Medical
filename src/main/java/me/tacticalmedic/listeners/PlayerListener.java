package me.tacticalmedic.listeners;

import me.tacticalmedic.TacticalMedic;
import me.tacticalmedic.model.PlayerMedicalState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.UUID;

public class PlayerListener implements Listener {

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        TacticalMedic plugin = TacticalMedic.getInstance();

        // 리스폰 시 상태 재생성
        if (!plugin.hasState(uuid)) {
            plugin.createState(uuid);
        }

        PlayerMedicalState state = plugin.getState(uuid);
        if (state != null) {
            // 혈액량 초기화 (기본 5000ml)
            state.setBloodLevel(5000);
            state.setPainLevel(0);
            state.setUnconscious(false);
            state.setHeartRate(80); // 예시: 평상 심박수
        }
    }
}