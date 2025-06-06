package me.hoosiertransfer.hoosiertech.items;

import java.util.UUID;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;

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
                p.sendMessage("无目标设置");
                return;
            }

            LivingEntity entity = p.getWorld().getLivingEntities().stream().filter(e1 -> e1.getUniqueId().equals(target)).findFirst().orElse(null);
            if (entity == null) {
                p.sendMessage("未发现目标");
                return;
            }

            double distance = p.getLocation().distance(entity.getLocation());
            p.sendMessage("目标距离: " + distance);
        };
    }
}
