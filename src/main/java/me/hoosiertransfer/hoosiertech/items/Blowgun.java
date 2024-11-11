package me.hoosiertransfer.hoosiertech.items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.DamageableItem;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.hoosiertransfer.hoosiertech.HoosierTech;
import me.hoosiertransfer.hoosiertech.HoosierTechItemSetup;
import org.bukkit.FluidCollisionMode;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.awt.*;

public class Blowgun extends SlimefunItem {
    public static final NamespacedKey LAST_SHOT = new NamespacedKey(HoosierTech.getInstance(), "last_shot");


    public Blowgun(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        addItemHandler(getItemUseHandler());
    }

    public ItemUseHandler getItemUseHandler() {
        return e -> {
            e.cancel();
            System.out.println("Blowgun used");
            Player p = e.getPlayer();
            ItemStack blowgun = e.getItem();
            // check if they can use it
            if (p.getPersistentDataContainer().has(LAST_SHOT, PersistentDataType.LONG)) {
                long lastShot = p.getPersistentDataContainer().get(LAST_SHOT, PersistentDataType.LONG);
                if (System.currentTimeMillis() - lastShot < 1000) {
                    return;
                }
            }
            p.getPersistentDataContainer().set(LAST_SHOT, PersistentDataType.LONG, System.currentTimeMillis());

            shoot(p, blowgun);
        };
    }

    public void shoot(Player p, ItemStack blowgun) {
        // check if they can shoot

        RayTraceResult rayTraceResult = p.getWorld().rayTrace(p.getEyeLocation(), p.getLocation().getDirection(), 20, FluidCollisionMode.ALWAYS, true, 0.1, entity -> !entity.equals(p));
        if (rayTraceResult == null || rayTraceResult.getHitEntity() == null || !(rayTraceResult.getHitEntity() instanceof LivingEntity)) {
            return;
        }
        System.out.println("Hit entity: " + rayTraceResult.getHitEntity().getName());

        LivingEntity target = (LivingEntity) rayTraceResult.getHitEntity();
        target.damage(1, p);

        if (SlimefunUtils.containsSimilarItem(p.getInventory(), HoosierTechItemSetup.tracker.getItem(), true)) {
            
        }
    }
}
