package me.hoosiertransfer.hoosiertech;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.RadioactiveItem;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import me.hoosiertransfer.hoosiertech.machines.DustMutator;

public class HoosierTechItemSetup {
    private static final NestedItemGroup hoosiertech = new NestedItemGroup(
            new NamespacedKey(HoosierTech.getInstance(), "hoosiertech"),
            new CustomItemStack(Material.END_CRYSTAL, "&6Cavern&9Tech")
    );

    private static final ItemGroup materials = new SubItemGroup(
            new NamespacedKey(HoosierTech.getInstance(), "materials"), hoosiertech,
            new CustomItemStack(Material.NETHERITE_INGOT, "&4Materials"), 1
    );

    private static final ItemGroup machines = new SubItemGroup(
            new NamespacedKey(HoosierTech.getInstance(), "machines"), hoosiertech,
            new CustomItemStack(Material.FURNACE, "&cMachines"), 2
    );
    
    public static void setup(HoosierTech plugin) {
        new UnplaceableBlock(materials, HoosierTechItems.BORON_INGOT, RecipeType.SMELTERY, new ItemStack[]{
                SlimefunItems.STEEL_INGOT, SlimefunItems.CARBON, SlimefunItems.IRON_DUST,
                null, null, null,
                null, null, null
        }).register(plugin);
        new UnplaceableBlock(materials, HoosierTechItems.ELECTROLYTE_INGOT, RecipeType.SMELTERY, new ItemStack[]{
                new ItemStack(Material.GOLD_INGOT), SlimefunItems.SILVER_INGOT, SlimefunItems.MAGNESIUM_DUST,
                null, null, null,
                null, null, null
        }).register(plugin);
        new UnplaceableBlock(materials, HoosierTechItems.MIXED_INGOT, RecipeType.SMELTERY, new ItemStack[]{
                SlimefunItems.ALUMINUM_DUST, SlimefunItems.SILVER_DUST, SlimefunItems.ZINC_DUST,
                SlimefunItems.LEAD_DUST, SlimefunItems.IRON_DUST, SlimefunItems.COPPER_DUST,
                SlimefunItems.IRON_DUST, SlimefunItems.TIN_DUST, SlimefunItems.GOLD_DUST
        }).register(plugin);
        new SlimefunItem(materials, HoosierTechItems.MIXED_PLATE, RecipeType.COMPRESSOR, new ItemStack[]{
                new ItemStack(HoosierTechItems.MIXED_INGOT, 8), null, null,
                null, null, null,
                null, null, null
        }).register(plugin);
        new UnplaceableBlock(materials, HoosierTechItems.ELECTROLYTE_INGOT, RecipeType.SMELTERY, new ItemStack[]{
                SlimefunItems.BILLION_INGOT, HoosierTechItems.ELECTROLYTE_INGOT, SlimefunItems.BILLION_INGOT,
                HoosierTechItems.ELECTROLYTE_INGOT, HoosierTechItems.BORON_INGOT, HoosierTechItems.ELECTROLYTE_INGOT,
                SlimefunItems.BILLION_INGOT, HoosierTechItems.ELECTROLYTE_INGOT, SlimefunItems.BILLION_INGOT
        }).register(plugin);

        new DustMutator(machines, HoosierTechItems.DUST_MUTATOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            new ItemStack(Material.COBBLESTONE, 1), null, null,
            null, null, null,
            null, null, null
        }).setCapacity(360)
                .setEnergyConsumption(48)
                .setProcessingSpeed(1)
                .register(plugin);
    }
}