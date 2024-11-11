package me.hoosiertransfer.hoosiertech.machines;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ExtremeFreezer extends AContainer implements RecipeDisplayItem {
    public ExtremeFreezer(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    protected void registerDefaultRecipes() {
        registerRecipe(2, new ItemStack[] { new ItemStack(Material.WATER_BUCKET, 1) }, new ItemStack[] {
                new SlimefunItemStack(SlimefunItems.REACTOR_COOLANT_CELL, 1),
                new ItemStack(Material.BUCKET, 1)
                });
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.BLUE_ICE);
    }

    @Override
    public String getMachineIdentifier() {
        return "EXTREME_FREEZER";
    }
}