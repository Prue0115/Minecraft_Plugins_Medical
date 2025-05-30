package me.tacticalmedic.gui;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

public class SubMenuBuilder {

    public static void buildSubMenu(Inventory inv, String partName) {
        // 예: 치료 버튼
        ItemStack bandage = new ItemStack(Material.WHITE_WOOL);
        ItemMeta bMeta = bandage.getItemMeta();
        bMeta.setDisplayName("§a붕대 감기");
        bandage.setItemMeta(bMeta);
        inv.setItem(30, bandage);

        ItemStack splint = new ItemStack(Material.STICK);
        ItemMeta sMeta = splint.getItemMeta();
        sMeta.setDisplayName("§6부목 고정");
        splint.setItemMeta(sMeta);
        inv.setItem(32, splint);
    }
}