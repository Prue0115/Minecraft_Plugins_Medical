package me.tacticalmedic.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InteractionListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player player)) return;

        String title = e.getView().getTitle(); // ✅ getTitle() FIXED
        if (!title.contains("의료 메뉴")) return;

        e.setCancelled(true);
        player.sendMessage("§7[의료] GUI 클릭 처리 예정");
    }
}
