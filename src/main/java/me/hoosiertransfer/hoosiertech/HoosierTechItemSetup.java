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
            new CustomItemStack(Material.END_CRYSTAL, "&6Hoosier&9Tech"));

    private static final ItemGroup materials = new SubItemGroup(
            new NamespacedKey(HoosierTech.getInstance(), "materials"), hoosiertech,
            new CustomItemStack(Material.NETHERITE_INGOT, "&4Materials"), 1);

    private static final ItemGroup machines = new SubItemGroup(
            new NamespacedKey(HoosierTech.getInstance(), "machines"), hoosiertech,
            new CustomItemStack(Material.FURNACE, "&cMachines"), 2);

    public static void setup(HoosierTech plugin) {
        new UnplaceableBlock(materials, HoosierTechItems.BORON_INGOT, RecipeType.SMELTERY, new ItemStack[] {
                SlimefunItems.STEEL_INGOT, SlimefunItems.CARBON, SlimefunItems.IRON_DUST,
                null, null, null,
                null, null, null
        }).register(plugin);
        new UnplaceableBlock(materials, HoosierTechItems.ELECTROLYTE_INGOT, RecipeType.SMELTERY,
                new ItemStack[] {
                        new ItemStack(Material.GOLD_INGOT), SlimefunItems.SILVER_INGOT,
                        SlimefunItems.MAGNESIUM_DUST,
                        null, null, null,
                        null, null, null
                }).register(plugin);
        new UnplaceableBlock(materials, HoosierTechItems.BERYLLIUM_INGOT, RecipeType.SMELTERY, new ItemStack[] {
                SlimefunItems.ALUMINUM_BRASS_INGOT, SlimefunItems.ZINC_DUST, new ItemStack(Material.IRON_INGOT),
                new ItemStack(Material.VINE, 6), null, null,
                null, null, null
        }).register(plugin);
        new UnplaceableBlock(materials, HoosierTechItems.MIXED_INGOT, RecipeType.SMELTERY, new ItemStack[] {
                SlimefunItems.ALUMINUM_DUST, SlimefunItems.SILVER_DUST, SlimefunItems.ZINC_DUST,
                SlimefunItems.LEAD_DUST, SlimefunItems.IRON_DUST, SlimefunItems.COPPER_DUST,
                SlimefunItems.IRON_DUST, SlimefunItems.TIN_DUST, SlimefunItems.GOLD_DUST
        }).register(plugin);
        new SlimefunItem(materials, HoosierTechItems.MIXED_PLATE, RecipeType.COMPRESSOR, new ItemStack[] {
                new SlimefunItemStack(HoosierTechItems.MIXED_INGOT, 8), null, null,
                null, null, null,
                null, null, null
        }).register(plugin);
        new UnplaceableBlock(materials, HoosierTechItems.MACHINE_CORE, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        SlimefunItems.BILLON_INGOT, HoosierTechItems.ELECTROLYTE_INGOT,
                        SlimefunItems.BILLON_INGOT,
                        HoosierTechItems.ELECTROLYTE_INGOT, HoosierTechItems.BORON_INGOT,
                        HoosierTechItems.ELECTROLYTE_INGOT,
                        SlimefunItems.BILLON_INGOT, HoosierTechItems.ELECTROLYTE_INGOT,
                        SlimefunItems.BILLON_INGOT
                }).register(plugin);
        new UnplaceableBlock(materials, HoosierTechItems.MACHINE_MOTOR, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        null, SlimefunItems.ELECTRO_MAGNET, null,
                        SlimefunItems.DURALUMIN_INGOT, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.DURALUMIN_INGOT,
                        SlimefunItems.DURALUMIN_INGOT, new ItemStack(Material.REDSTONE_TORCH),
                        SlimefunItems.DURALUMIN_INGOT
                }).register(plugin);
        new DustMutator(machines, HoosierTechItems.DUST_MUTATOR, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        HoosierTechItems.MIXED_PLATE, SlimefunItems.ELECTRIC_DUST_WASHER, HoosierTechItems.MIXED_PLATE,
                        SlimefunItems.EARTH_RUNE, HoosierTechItems.MACHINE_CORE, SlimefunItems.EARTH_RUNE,
                        HoosierTechItems.MIXED_PLATE, HoosierTechItems.MACHINE_MOTOR, HoosierTechItems.MIXED_PLATE
                }).setCapacity(360)
                .setEnergyConsumption(48)
                .setProcessingSpeed(1)
                .register(plugin);
        new DustMutator(machines, HoosierTechItems.DUST_MUTATOR2, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        HoosierTechItems.BERYLLIUM_INGOT, HoosierTechItems.MACHINE_MOTOR,
                        HoosierTechItems.BERYLLIUM_INGOT,
                        SlimefunItems.FIRE_RUNE, HoosierTechItems.DUST_MUTATOR, SlimefunItems.FIRE_RUNE,
                        HoosierTechItems.BERYLLIUM_INGOT, HoosierTechItems.MACHINE_MOTOR,
                        HoosierTechItems.BERYLLIUM_INGOT
                }).setCapacity(580)
                .setEnergyConsumption(136)
                .setProcessingSpeed(4)
                .register(plugin);
    }
}