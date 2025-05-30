package me.tacticalmedic.gui;

import me.tacticalmedic.TacticalMedic;
import me.tacticalmedic.model.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

public class MedicalGUI {

    public static Inventory buildGUI(Player viewer, Player target) {
        Inventory gui = Bukkit.createInventory(null, 45, "의료 메뉴 - " + target.getName());
        PlayerMedicalState state = TacticalMedic.getInstance().getState(target.getUniqueId());

        for (BodyPart part : BodyPart.values()) {
            int slot = GUIUtil.mapBodyPartToSlot(part);
            ItemStack item = new ItemStack(Material.PAPER);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§f" + part.getDisplay());

            String bleed = "출혈: " + state.getBleedingStage(part).getDisplay();
            String fracture = "골절: " + (state.isFractured(part) ? "§c있음" : "§7없음");

            meta.setLore(java.util.List.of(bleed, fracture));
            item.setItemMeta(meta);
            gui.setItem(slot, item);
        }

        return gui;
    }
}