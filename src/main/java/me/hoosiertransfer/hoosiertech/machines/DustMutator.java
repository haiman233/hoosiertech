package me.hoosiertransfer.hoosiertech.machines;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemState;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineProcessHolder;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.machines.MachineProcessor;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.handlers.SimpleBlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.implementation.operations.MiningOperation;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;

public class DustMutator extends SlimefunItem
        implements RecipeDisplayItem, EnergyNetComponent, MachineProcessHolder<MiningOperation> {
    private static final int[] BORDER = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 13, 17, 18, 22,
            26, 27, 35, 36, 39, 40, 44, 45, 46, 47, 48,
            49, 50, 51, 52, 53 };
    private static final int[] INPUT_BORDER = { 10, 11, 12, 19, 21, 28, 30, 37, 38, 39 };
    private static final int[] OUTPUT_BORDER = { 14, 15, 16, 23, 25, 32, 34, 41, 42, 43 };
    private static final int[] INPUT_SLOTS = { 20, 29 };
    private static final int[] OUTPUT_SLOTS = { 24, 33 };

    public static final int DUST_SET_SLOT = 31;

    private final List<ItemStack> dusts = Arrays.asList(
            SlimefunItems.IRON_DUST,
            SlimefunItems.GOLD_DUST,
            SlimefunItems.COPPER_DUST,
            SlimefunItems.TIN_DUST,
            SlimefunItems.ZINC_DUST,
            SlimefunItems.ALUMINUM_DUST,
            SlimefunItems.MAGNESIUM_DUST,
            SlimefunItems.LEAD_DUST,
            SlimefunItems.SILVER_DUST);

    private int energyConsumedPerTick = -1;
    private int energyCapacity = -1;
    private int processingSpeed = -1;

    private final MachineProcessor<MiningOperation> processor = new MachineProcessor<>(this);

    private static final ItemStack NO_ENERGY = new CustomItemStack(Material.RED_STAINED_GLASS_PANE,
            "&c没有足够的能量!");
    private static final ItemStack GENERATING = new CustomItemStack(Material.GREEN_STAINED_GLASS_PANE, "&a正在工作...");
    private static final ItemStack NO_ROOM = new CustomItemStack(Material.ORANGE_STAINED_GLASS_PANE,
            "&6没有足够的空间!");
    private static final ItemStack INVALID_INPUT = new CustomItemStack(Material.RED_STAINED_GLASS_PANE,
            "&c无效输入!");

    private static final ItemStack SET_ITEM = new CustomItemStack(
            Material.LIME_STAINED_GLASS_PANE,
            ChatColor.GREEN + "设置物品",
            ChatColor.GRAY + "将物品拖至此处来设置想要转化的矿粉");

    public DustMutator(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
        addItemHandler(new BlockTicker() {

            @Override
            public void tick(Block b, SlimefunItem sf, Config data) {
                DustMutator.this.tick(b);
            }

            @Override
            public boolean isSynchronized() {
                return false;
            }
        });
        addItemHandler(onBlockBreak());
    }

    @Override
    public MachineProcessor<MiningOperation> getMachineProcessor() {
        return processor;
    }

    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    public int getEnergyConsumption() {
        return energyConsumedPerTick;
    }

    public int getSpeed() {
        return processingSpeed;
    }

    public int getCapacity() {
        return energyCapacity;
    }

    public void register(SlimefunAddon addon) {
        this.addon = addon;

        if (getCapacity() <= 0) {
            warn("The capacity has not been configured correctly. The Item was disabled.");
            warn("Make sure to call '" + getClass().getSimpleName() + "#setEnergyCapacity(...)' before registering!");
        }

        if (getEnergyConsumption() <= 0) {
            warn("The energy consumption has not been configured correctly. The Item was disabled.");
            warn("Make sure to call '" + getClass().getSimpleName()
                    + "#setEnergyConsumption(...)' before registering!");
        }

        if (getSpeed() <= 0) {
            warn("The processing speed has not been configured correctly. The Item was disabled.");
            warn("Make sure to call '" + getClass().getSimpleName() + "#setProcessingSpeed(...)' before registering!");
        }

        if (getCapacity() > 0 && getEnergyConsumption() > 0 && getSpeed() > 0) {
            super.register(addon);
        }
    }

    public final DustMutator setCapacity(int capacity) {
        if (getState() == ItemState.UNREGISTERED) {
            this.energyCapacity = capacity;
            return this;
        } else {
            throw new IllegalStateException("You cannot modify the capacity after the Item was registered.");
        }
    }

    public final DustMutator setProcessingSpeed(int speed) {
        this.processingSpeed = speed;
        return this;
    }

    public final DustMutator setEnergyConsumption(int energyConsumption) {
        // Validate.isTrue(energyConsumption > 0, "The energy consumption must be
        // greater than zero!");
        // Validate.isTrue(energyCapacity > 0, "You must specify the capacity before you
        // can set the consumption amount.");
        // Validate.isTrue(energyConsumption <= energyCapacity, "The energy consumption
        // cannot be higher than the capacity (" + energyCapacity + ')');

        this.energyConsumedPerTick = energyConsumption;
        return this;
    }

    @SuppressWarnings("deprecation")
    protected void constructMenu(BlockMenuPreset preset) {
        for (int i : BORDER) {
            preset.addItem(i, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());
        }

        for (int i : INPUT_BORDER) {
            preset.addItem(i, new CustomItemStack(Material.BLUE_STAINED_GLASS_PANE, " "),
                    ChestMenuUtils.getEmptyClickHandler());
        }

        for (int i : OUTPUT_BORDER) {
            preset.addItem(i, new CustomItemStack(Material.ORANGE_STAINED_GLASS_PANE, " "),
                    ChestMenuUtils.getEmptyClickHandler());
        }
        preset.addItem(DUST_SET_SLOT, SET_ITEM, (p, slot, item, action) -> false);

        preset.addItem(22, new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " "),
                ChestMenuUtils.getEmptyClickHandler());
    }

    private BlockBreakHandler onBlockBreak() {
        return new SimpleBlockBreakHandler() {

            @Override
            public void onBlockBreak(Block b) {
                BlockMenu inv = BlockStorage.getInventory(b);

                if (inv != null) {
                    inv.dropItems(b.getLocation(), INPUT_SLOTS);
                    inv.dropItems(b.getLocation(), OUTPUT_SLOTS);
                }

                processor.endOperation(b);
            }
        };
    }

    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> displayRecipes = new LinkedList<>();
        return displayRecipes;
    }

    @Override
    public void postRegister() {
        new BlockMenuPreset(this.getId(), this.getItemName()) {
            @Override
            public void init() {
                constructMenu(this);
            }

            @Override
            public boolean canOpen(Block block, Player player) {
                return Slimefun.getProtectionManager().hasPermission(player, block.getLocation(),
                        Interaction.INTERACT_BLOCK);
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                return new int[0];
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(DirtyChestMenu menu, ItemTransportFlow flow, ItemStack item) {
                if (flow == ItemTransportFlow.WITHDRAW) {
                    return getOutputSlots();
                }

                int fullSlots = 0;
                List<Integer> slots = new LinkedList<>();

                for (int slot : getInputSlots()) {
                    ItemStack stack = menu.getItemInSlot(slot);
                    if (stack != null && SlimefunUtils.isItemSimilar(stack, item, true, false)) {
                        if (stack.getAmount() >= stack.getMaxStackSize()) {
                            fullSlots++;
                        }

                        slots.add(slot);
                    }
                }

                if (slots.isEmpty()) {
                    return getInputSlots();
                } else if (fullSlots == slots.size()) {
                    // All slots with that item are already full
                    return new int[0];
                } else {
                    Collections.sort(slots, compareSlots(menu));
                    int[] array = new int[slots.size()];

                    for (int i = 0; i < slots.size(); i++) {
                        array[i] = slots.get(i);
                    }

                    return array;
                }
            }

            @Override
            @SuppressWarnings("deprecation")
            public void newInstance(BlockMenu menu, Block block) {
                if (isSet(block)) {
                    menu.replaceExistingItem(DUST_SET_SLOT, getSetDust(block));
                }

                menu.addMenuClickHandler(DUST_SET_SLOT, (p, slot, item, action) -> {
                    final ItemStack itemStack = p.getItemOnCursor().clone();
                    if (itemStack == null || !isDust(itemStack)) {
                        return false;
                    }
                    itemStack.setAmount(1);
                    BlockStorage.addBlockInfo(block, "item", String.valueOf(getDustIndex(itemStack)));
                    menu.replaceExistingItem(DUST_SET_SLOT, itemStack);

                    return false;
                });
            }
        };
    }

    private int getDustIndex(ItemStack dust) {
        for (int i = 0; i < dusts.size(); i++) {
            if (SlimefunUtils.isItemSimilar(dust, dusts.get(i), true, false)) {
                return i;
            }
        }

        return -1;
    }

    private boolean isDust(ItemStack item) {
        for (ItemStack dust : dusts) {
            if (SlimefunUtils.isItemSimilar(item, dust, true, false)) {
                return true;
            }
        }

        return false;
    }

    public int[] getInputSlots() {
        return INPUT_SLOTS;
    }

    public int[] getOutputSlots() {
        return OUTPUT_SLOTS;
    }

    private Comparator<Integer> compareSlots(DirtyChestMenu menu) {
        return Comparator.comparingInt(slot -> menu.getItemInSlot(slot).getAmount());
    }

    protected void tick(Block b) {
        BlockMenu inv = BlockStorage.getInventory(b);
        MiningOperation operation = processor.getOperation(b);

        int totalDustCount = 0;
        Map<Integer, Integer> dustCounts = new HashMap<>();

        // Check all input slots for valid dust items and count them
        for (int slot : getInputSlots()) {
            ItemStack item = inv.getItemInSlot(slot);
            if (item != null && item.getType() != Material.AIR && isDust(item)) {
                totalDustCount += item.getAmount();
                dustCounts.put(slot, item.getAmount());
            } else if (item != null && !isDust(item)) {
                inv.replaceExistingItem(22, INVALID_INPUT);
                processor.endOperation(b);
                return;
            }
        }

        if (totalDustCount < 3) {
            inv.replaceExistingItem(22, INVALID_INPUT);
            processor.endOperation(b);
            return;
        }

        if (operation != null) {
            if (!operation.isFinished()) {
                if (getCharge(b.getLocation()) < getEnergyConsumption()) {
                    inv.replaceExistingItem(22, NO_ENERGY);
                    return;
                }

                removeCharge(b.getLocation(), getEnergyConsumption());
                operation.addProgress(getSpeed());
            } else {
                int remainingDustToConsume = 3;

                // Consume up to three dust items from the slots with dust
                for (Map.Entry<Integer, Integer> entry : dustCounts.entrySet()) {
                    int slot = entry.getKey();
                    int count = entry.getValue();
                    int toConsume = Math.min(remainingDustToConsume, count);

                    inv.consumeItem(slot, toConsume);
                    remainingDustToConsume -= toConsume;

                    if (remainingDustToConsume <= 0) {
                        break;
                    }
                }

                if (remainingDustToConsume > 0) {
                    processor.endOperation(b);
                    return;
                }

                inv.replaceExistingItem(22, GENERATING);
                inv.pushItem(operation.getResult().clone(), OUTPUT_SLOTS);
                processor.endOperation(b);
            }
        } else {
            start(b, inv);
        }
    }

    private ItemStack getSetDust(Block b) {
        if (!BlockStorage.hasBlockInfo(b)) {
            return null;
        }
        String index = BlockStorage.getLocationInfo(b.getLocation(), "item");
        if (index == null) {
            return null;
        }
        return dusts.get(Integer.parseInt(index));
    }

    private boolean isSet(Block b) {
        return getSetDust(b) != null;
    }

    private void start(Block b, BlockMenu inv) {
        if (!isSet(b)) {
            inv.replaceExistingItem(DUST_SET_SLOT, SET_ITEM);
            return;
        }

        int totalDustCount = 0;
        boolean hasDust = false;

        for (int slot : getInputSlots()) {
            ItemStack item = inv.getItemInSlot(slot);
            if (item != null && item.getType() != Material.AIR) {
                if (!isDust(item)) {
                    inv.replaceExistingItem(22, INVALID_INPUT);
                    return;
                }
                totalDustCount += item.getAmount();
                hasDust = true;
            }
        }

        if (!hasDust || totalDustCount < 3) {
            inv.replaceExistingItem(22, INVALID_INPUT);
            return;
        }

        if (!inv.fits(getSetDust(b), OUTPUT_SLOTS)) {
            inv.replaceExistingItem(22, NO_ROOM);
            return;
        }

        inv.replaceExistingItem(22, GENERATING);

        processor.startOperation(b, new MiningOperation(getSetDust(b), 4));
    }

}