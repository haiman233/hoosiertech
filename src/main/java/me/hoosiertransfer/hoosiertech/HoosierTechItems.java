package me.hoosiertransfer.hoosiertech;

import org.bukkit.Material;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;

public class HoosierTechItems {
        // Materials
        public static final SlimefunItemStack BORON_INGOT = new SlimefunItemStack("HOOSIERTECH_BORON_INGOT",
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTlmMjMzMGFkMjg0NmVjNmM4ZTFjZTAyNTg5ZjA3ZjJmNzU5N2I1YTk3NzU5OWI2ZGI3YzM0NGNhNDRhMTI3NiJ9fX0",
                        "硼锭", "", "&9&o胡希尔科技");
        public static final SlimefunItemStack ELECTROLYTE_INGOT = new SlimefunItemStack("HOOSIERTECH_ELECTROLYTE_INGOT",
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWRkNzAxZDkyYjBjMjMwMmZkOTQwNWY0MzIyODliYzJlMGI0NDYxMDc0M2JjN2QyNTkxZGJhNzdiZjYzZDBmNCJ9fX0",
                        "电解质锭", "", "&9&o胡希尔科技");
        public static final SlimefunItemStack MIXED_INGOT = new SlimefunItemStack("HOOSIERTECH_MIXED_INGOT",
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTQ4NjE3OTUyM2RjZWY2OWY1MTc1NmYxOGJjMGVlNWYzNGIyMTVlNDVlODMxM2I5YTNjOWNiYTdlZWI5MTRlMSJ9fX0",
                        "混合锭", "", "&9&o胡希尔科技");
        public static final SlimefunItemStack BERYLLIUM_INGOT = new SlimefunItemStack("HOOSIERTECH_BERYLLIUM_INGOT",
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTAyZjNkNTVhMjI0NWZkODMyOTJlZGU3MTA2NmFhOTYwMDJiNWU4N2VkZGU0ZTkzYjFhOTYwYTJkODE1ZTA0ZCJ9fX0",
                        "铍锭", "", "&9&o胡希尔科技");
        public static final SlimefunItemStack MACHINE_CORE = new SlimefunItemStack("HOOSIERTECH_MACHINE_CORE1", Material.IRON_BLOCK,
                        "机器核心", "", "&9&o胡希尔科技");
        public static final SlimefunItemStack MIXED_PLATE = new SlimefunItemStack("HOOSIERTECH_MIXED_PLATE", Material.PAPER,
                        "混合板", "", "&9&o胡希尔科技");
        public static final SlimefunItemStack MACHINE_MOTOR = new SlimefunItemStack("HOOSIERTECH_MACHINE_MOTOR1",
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2IwNDMzZjFjMjI3OTYwOGY3YmQyY2VjZWI5OGNkMTc1Y2JhYWRjM2Y2Mjk5YWUzY2NhZTI1N2RjMjJhNTViMiJ9fX0",
                        "&c机器马达", "&7机器组件", "", "&9&o胡希尔科技");

        // Machines
        public static final SlimefunItemStack DUST_MUTATOR = new SlimefunItemStack("HOOSIERTECH_DUST_MUTATOR", Material.IRON_BLOCK,
                        "&8矿粉转化机", "", "&7将矿粉转化为其它种类", "", LoreBuilder.power(24, "/t"),
                        LoreBuilder.power(200, " 可储存"));
        public static final SlimefunItemStack DUST_MUTATOR2 = new SlimefunItemStack("HOOSIERTECH_DUST_MUTATOR2",
                        Material.GOLD_BLOCK,
                        "&8矿粉转化机 II", "", "&7将矿粉转化为其它种类", "",
                        LoreBuilder.power(48, "/t"),
                        LoreBuilder.power(300, " 可储存"));
        public static final SlimefunItemStack COBBLE_GEN = new SlimefunItemStack("HOOSIERTECH_COBBLE_GEN", Material.CHISELED_DEEPSLATE, "&8圆石生成器", "", "&7你对此是怎么想的?", "", LoreBuilder.power(48, "/t"), LoreBuilder.power(360, " 可储存"));

        public static final SlimefunItemStack URANIUM_EXTRACTOR = new SlimefunItemStack("HOOSIERTECH_URANIUM_EXTRACTOR", Material.GREEN_CONCRETE,
                        "&8铀提取机", "", "&7将铀从圆石中提取出来", "", LoreBuilder.power(100, "/t"),
                        LoreBuilder.power(400, " 可储存"));

        public static final SlimefunItemStack EXTREME_FREEZER = new SlimefunItemStack("HOOSIERTECH_EXTREME_FREEZER", Material.BLUE_ICE,
                        "&9超级冰箱", "", "&7快速冷冻物品", "", LoreBuilder.power(96, "/t"),
                        LoreBuilder.power(360, " 可储存"));

        public static final SlimefunItemStack LEAD_PLATE = new SlimefunItemStack("HOOSIERTECH_LEAD_PLATE", Material.PAPER,
                        "铅板", "", "&9&o胡希尔科技");

        public static final SlimefunItemStack BLOWGUN = new SlimefunItemStack("HOOSIERTECH_BLOWGUN", Material.STICK, "气枪", "", "&9&o胡希尔科技");

        public static final SlimefunItemStack TRACKER = new SlimefunItemStack("HOOSIERTECH_TRACKER", Material.COMPASS, "定位器", "", "&9&o胡希尔科技");
}