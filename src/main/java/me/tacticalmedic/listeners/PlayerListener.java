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

        if (!plugin.hasState(uuid)) {
            plugin.createState(uuid);
        }

        PlayerMedicalState state = plugin.getState(uuid);
        state.setBloodLevel(5000);
        state.setPainLevel(0);
        state.clearAllBleeding();
        state.clearAllFractures();
    }

}
