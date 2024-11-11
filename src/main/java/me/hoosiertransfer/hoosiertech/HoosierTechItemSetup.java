package me.hoosiertransfer.hoosiertech;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.VanillaItem;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import me.hoosiertransfer.hoosiertech.items.Blowgun;
import me.hoosiertransfer.hoosiertech.items.Tracker;
import me.hoosiertransfer.hoosiertech.machines.CobbleGen;
import me.hoosiertransfer.hoosiertech.machines.ExtremeFreezer;
import me.hoosiertransfer.hoosiertech.machines.UraniumExtractor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.entity.Slime;
import org.bukkit.inventory.ItemStack;

import me.hoosiertransfer.hoosiertech.machines.DustMutator;

public class HoosierTechItemSetup {
    private static final NestedItemGroup hoosiertech = new NestedItemGroup(
            new NamespacedKey(HoosierTech.getInstance(), "hoosiertech"),
            new CustomItemStack(Material.END_CRYSTAL, "&6Hoosier&9Tech"));

    private static final ItemGroup materials = new SubItemGroup(
            new NamespacedKey(HoosierTech.getInstance(), "materials"), hoosiertech,
            new CustomItemStack(Material.NETHERITE_INGOT, "&6Materials"), 1);

    private static final ItemGroup machines = new SubItemGroup(
            new NamespacedKey(HoosierTech.getInstance(), "machines"), hoosiertech,
            new CustomItemStack(Material.FURNACE, "&cMachines"), 2);

    private static final ItemGroup destruction = new SubItemGroup(
            new NamespacedKey(HoosierTech.getInstance(), "destruction"), hoosiertech,
            new CustomItemStack(Material.TNT, "&4Destruction"), 3);

    public static Tracker tracker;

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
                SlimefunItems.TIN_DUST, SlimefunItems.GOLD_DUST, null
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

        new SlimefunItem(materials, HoosierTechItems.LEAD_PLATE, RecipeType.COMPRESSOR, new ItemStack[] {
                new SlimefunItemStack(SlimefunItems.LEAD_INGOT, 8), null, null,
                null, null, null,
                null, null, null
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
        new CobbleGen(machines, HoosierTechItems.COBBLE_GEN, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                null, new ItemStack(Material.DIAMOND_PICKAXE), null,
                new ItemStack(Material.LAVA_BUCKET), SlimefunItems.PROGRAMMABLE_ANDROID_MINER, new ItemStack(Material.WATER_BUCKET),
                SlimefunItems.ELECTRIC_MOTOR, HoosierTechItems.MACHINE_CORE, SlimefunItems.ELECTRIC_MOTOR
        }).setCapacity(360)
                .setEnergyConsumption(48)
                .setProcessingSpeed(1)
                .register(plugin);

        new ExtremeFreezer(machines, HoosierTechItems.EXTREME_FREEZER, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        SlimefunItems.FREEZER, new ItemStack(Material.BLUE_ICE), SlimefunItems.FREEZER,
                        new ItemStack(Material.BLUE_ICE), SlimefunItems.COOLING_UNIT, new ItemStack(Material.BLUE_ICE),
                        SlimefunItems.FREEZER, new ItemStack(Material.BLUE_ICE), SlimefunItems.FREEZER
                }).setCapacity(720)
                .setEnergyConsumption(96)
                .setProcessingSpeed(1)
                .register(plugin);

        new UraniumExtractor(machines, HoosierTechItems.URANIUM_EXTRACTOR, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        SlimefunItems.ELECTRIC_ORE_GRINDER_2, new ItemStack(Material.LAVA_BUCKET), SlimefunItems.ELECTRIC_ORE_GRINDER_2,
                        HoosierTechItems.LEAD_PLATE, HoosierTechItems.MACHINE_CORE, HoosierTechItems.LEAD_PLATE,
                        HoosierTechItems.MACHINE_MOTOR, SlimefunItems.ELECTRIC_DUST_WASHER_3, HoosierTechItems.MACHINE_MOTOR
                }).setCapacity(360)
                .setEnergyConsumption(48)
                .setProcessingSpeed(1)
                .register(plugin);

        new VanillaItem(destruction, new ItemStack(Material.CREEPER_SPAWN_EGG), "CREEPER_SPAWN_EGG", RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        new ItemStack(Material.TNT), new ItemStack(Material.TNT), new ItemStack(Material.TNT),
                        new ItemStack(Material.TNT), new ItemStack(Material.EGG), new ItemStack(Material.TNT),
                        new ItemStack(Material.TNT), new ItemStack(Material.TNT), new ItemStack(Material.TNT)
                }).register(plugin);

//        // TODO: Change the recipes for the blowgun and tracker
//        new Blowgun(destruction, HoosierTechItems.BLOWGUN, RecipeType.ENHANCED_CRAFTING_TABLE,
//                new ItemStack[] {
//                        new ItemStack(Material.STICK), new ItemStack(Material.STRING), new ItemStack(Material.STICK),
//                        new ItemStack(Material.STICK), new ItemStack(Material.FEATHER), new ItemStack(Material.STICK),
//                        new ItemStack(Material.STICK), new ItemStack(Material.STRING), new ItemStack(Material.STICK)
//                }).register(plugin);
//
//        tracker = new Tracker(destruction, HoosierTechItems.TRACKER, RecipeType.ENHANCED_CRAFTING_TABLE,
//                new ItemStack[] {
//                        new ItemStack(Material.COMPASS), new ItemStack(Material.REDSTONE), new ItemStack(Material.COMPASS),
//                        new ItemStack(Material.COMPASS), new ItemStack(Material.REDSTONE), new ItemStack(Material.COMPASS),
//                        new ItemStack(Material.COMPASS), new ItemStack(Material.REDSTONE), new ItemStack(Material.COMPASS)
//                });
//        tracker.register(plugin);
    }
}