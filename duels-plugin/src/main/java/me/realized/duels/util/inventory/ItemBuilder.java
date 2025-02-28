package me.realized.duels.util.inventory;

import java.util.Arrays;
import java.util.List;
import me.realized.duels.util.StringUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

public final class ItemBuilder {

    private final ItemStack result;

    private ItemBuilder(final Material type, final int amount, final short durability) {
        this.result = new ItemStack(type, amount, durability);
    }

    private ItemBuilder(final String type, final int amount, final short durability) {
        this(Material.matchMaterial(type), amount, durability);
    }

    private ItemBuilder(final ItemStack item) {
        this.result = item;
    }

    public static ItemBuilder of(final Material type) {
        return of(type, 1);
    }

    public static ItemBuilder of(final Material type, final int amount) {
        return of(type, amount, (short) 0);
    }

    public static ItemBuilder of(final Material type, final int amount, final short durability) {
        return new ItemBuilder(type, amount, durability);
    }

    public static ItemBuilder of(final String type, final int amount, final short durability) {
        return new ItemBuilder(type, amount, durability);
    }

    public static ItemBuilder of(final ItemStack item) {
        return new ItemBuilder(item);
    }

    public ItemBuilder name(final String name) {
        ItemMeta meta = result.getItemMeta();
        meta.setDisplayName(StringUtil.color(name));
        result.setItemMeta(meta);
        return this;
    }

    public ItemBuilder lore(final String... lore) {
        return lore(Arrays.asList(lore));
    }

    public ItemBuilder lore(final List<String> lore) {
        ItemMeta meta = result.getItemMeta();
        meta.setLore(StringUtil.color(lore));
        result.setItemMeta(meta);
        return this;
    }

    public ItemBuilder potion(final PotionType type, final boolean extended, final boolean upgraded) {
        if (result.getType().name().endsWith("POTION")) {
            PotionMeta meta = (PotionMeta) result.getItemMeta();
            meta.setBasePotionData(new PotionData(type, extended, upgraded));
            result.setItemMeta(meta);
        }

        return this;
    }

    public ItemStack build() {
        return result;
    }
}
