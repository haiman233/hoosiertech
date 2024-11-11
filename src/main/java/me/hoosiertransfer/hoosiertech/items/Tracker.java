package me.hoosiertransfer.hoosiertech.items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class Tracker extends SlimefunItem {
    private UUID target;
    public Tracker(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        addItemHandler(getItemUseHandler());
    }

    public void setTarget(UUID target) {
        this.target = target;
    }

    public UUID getTarget() {
        return target;
    }

    public ItemUseHandler getItemUseHandler() {
        return e -> {
            // send distance to target
            Player p = e.getPlayer();
            if (target == null) {
                p.sendMessage("No target set");
                return;
            }

            LivingEntity entity = p.getWorld().getLivingEntities().stream().filter(e1 -> e1.getUniqueId().equals(target)).findFirst().orElse(null);
            if (entity == null) {
                p.sendMessage("Target not found");
                return;
            }

            double distance = p.getLocation().distance(entity.getLocation());
            p.sendMessage("Distance to target: " + distance);
        };
    }
}
