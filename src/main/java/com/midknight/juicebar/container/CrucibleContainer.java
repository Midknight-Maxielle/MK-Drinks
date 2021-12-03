package com.midknight.juicebar.container;

import com.midknight.juicebar.registry.JuiceBlocks;
import com.midknight.juicebar.registry.JuiceContainers;
import com.midknight.juicebar.tileentity.CrucibleTile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class CrucibleContainer extends Container {

    // - - - - - // Variable Declaration // - - - - - //

    public final CrucibleTile tileEntity;
    private final PlayerEntity playerEntity;
    private final IItemHandler playerInventory;
    protected final IIntArray crucibleData;

    public CrucibleContainer(int windowId,
                             World world,
                             BlockPos pos,
                             PlayerInventory playerInventory,
                             PlayerEntity player,
                             IIntArray crucibleDataIn,
                             CrucibleTile tileEntity)
    {
        super(JuiceContainers.CRUCIBLE_CONTAINER.get(), windowId);
        this.tileEntity = tileEntity;
        playerEntity = player;
        this.crucibleData = crucibleDataIn;
        this.playerInventory = new InvWrapper(playerInventory);

        this.addDataSlots(crucibleData);
        this.addDataSlot(new IntReferenceHolder() {
            @Override
            public int get() {
                return tileEntity.getCrucibleData().get(1);

            }

            @Override
            public void set(int value) {
                tileEntity.getCrucibleData().set(1,value);
            }
        });

        layoutPlayerInventorySlots(8, 86);

        this.addDataSlot(new IntReferenceHolder() {
            @Override
            public int get() {
                return smeltProgress();
            }

            @Override
            public void set(int value) {
                tileEntity.getCrucibleData().set(0, value);
            }
        });

        // - - - - - // Item Slot Handlers // - - - - - //
        // Adds slots to the Container; Add a new or remove addSlot lines to add or remove slots.


        if(tileEntity != null) {
            tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
                addSlot(new SlotItemHandler(h, 0, 53, 43));
                addSlot(new SlotItemHandler(h, 1, 108, 43));
            });
        }
    }


    @Override @ParametersAreNonnullByDefault
    public boolean stillValid(PlayerEntity player) {
        if(tileEntity.getLevel() != null) {
            return stillValid(IWorldPosCallable.create(tileEntity.getLevel(), tileEntity.getBlockPos()
            ), player, JuiceBlocks.CRUCIBLE.get());
        } else {
            return false;
        }
    }

    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for(int i = 0; i < amount; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horizontalAmount,
                           int dx, int verticalAmount, int dy) {
        for(int j = 0; j < verticalAmount; j++) {
            index = addSlotRange(handler, index, x, y, horizontalAmount, dx);
            y += dy;
        }
        return index;
    }

    private void layoutPlayerInventorySlots(int leftColumn, int topRow) {
        addSlotBox(playerInventory, 9, leftColumn, topRow, 9, 18, 3, 18);
        topRow += 58;
        addSlotRange(playerInventory,0, leftColumn, topRow, 9, 18);
    }

    public int smeltProgress() {
        int timer = this.crucibleData.get(0);
        int total = this.crucibleData.get(1);

        return total != 0 && timer != 0 ? timer * 24 / total : 0;
    }

    public boolean isHeated() {
        return this.tileEntity.isHeated();

    }
    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 2;  // must match TileEntityInventoryBasic.NUMBER_OF_SLOTS

    @Override @Nonnull @ParametersAreNonnullByDefault
    public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + index);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerEntity, sourceStack);
        return copyOfSourceStack;
    }
}
