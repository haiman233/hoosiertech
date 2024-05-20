package me.hoosiertransfer.hoosiertech;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import org.bukkit.Material;

public class HoosierTechItems {
        // Materials
        public static final SlimefunItemStack BORON_INGOT = new SlimefunItemStack("BORON_INGOT",
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTlmMjMzMGFkMjg0NmVjNmM4ZTFjZTAyNTg5ZjA3ZjJmNzU5N2I1YTk3NzU5OWI2ZGI3YzM0NGNhNDRhMTI3NiJ9fX0",
                        "Boron Ingot", "", "&9&oHoosiertech");
        public static final SlimefunItemStack ELECTROLYTE_INGOT = new SlimefunItemStack("ELECTROLYTE_INGOT",
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWRkNzAxZDkyYjBjMjMwMmZkOTQwNWY0MzIyODliYzJlMGI0NDYxMDc0M2JjN2QyNTkxZGJhNzdiZjYzZDBmNCJ9fX0",
                        "Electrolyte Ingot", "", "&9&oHoosiertech");
        public static final SlimefunItemStack MIXED_INGOT = new SlimefunItemStack("MIXED_INGOT",
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTQ4NjE3OTUyM2RjZWY2OWY1MTc1NmYxOGJjMGVlNWYzNGIyMTVlNDVlODMxM2I5YTNjOWNiYTdlZWI5MTRlMSJ9fX0",
                        "Mixed Ingot", "", "&9&oHoosiertech");
        public static final SlimefunItemStack BERYLLIUM_INGOT = new SlimefunItemStack("BERYLLIUM_INGOT",
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTAyZjNkNTVhMjI0NWZkODMyOTJlZGU3MTA2NmFhOTYwMDJiNWU4N2VkZGU0ZTkzYjFhOTYwYTJkODE1ZTA0ZCJ9fX0",
                        "Beryllium Ingot", "", "&9&oHoosiertech");
        public static final SlimefunItemStack MACHINE_CORE = new SlimefunItemStack("MACHINE_CORE", Material.IRON_BLOCK,
                        "Machine Core", "", "&9&oHoosiertech");
        public static final SlimefunItemStack MIXED_PLATE = new SlimefunItemStack("MIXED_PLATE", Material.PAPER,
                        "Mixed Plate", "", "&9&oHoosiertech");
        public static final SlimefunItemStack MACHINE_MOTOR = new SlimefunItemStack("MACHINE_MOTOR",
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2IwNDMzZjFjMjI3OTYwOGY3YmQyY2VjZWI5OGNkMTc1Y2JhYWRjM2Y2Mjk5YWUzY2NhZTI1N2RjMjJhNTViMiJ9fX0",
                        "&cMachine Motor", "&7Machine Component", "", "&9&oHoosiertech");

        // Machines
        public static final SlimefunItemStack DUST_MUTATOR = new SlimefunItemStack("DUST_MUTATOR", Material.IRON_BLOCK,
                        "&8Dust Transmutator", "", "&7Converts dusts into new types.", "", LoreBuilder.power(48, "/t"),
                        LoreBuilder.power(360, " Buffer"));
        public static final SlimefunItemStack DUST_MUTATOR2 = new SlimefunItemStack("DUST_MUTATOR2",
                        Material.GOLD_BLOCK,
                        "&8Dust Transmutator II", "", "&7Converts dusts into new types.", "",
                        LoreBuilder.power(96, "/t"),
                        LoreBuilder.power(720, " Buffer"));
}