package com.midknight.owmix.blockentity;

import com.midknight.owmix.block.CrucibleBlock;
import com.midknight.owmix.menu.CrucibleMenu;
import com.midknight.owmix.data.recipes.CrucibleRecipe;
import com.midknight.owmix.registry.RegistryBE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Nameable;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class CrucibleBlockEntity extends JuiceBlockEntity implements MenuProvider, Nameable, HeatableEntityInterface {

    // Fields

    private final Component name = Component.nullToEmpty("Crucible");
    private LazyOptional<IItemHandler> lazyHandler = LazyOptional.empty();
    private final ItemStackHandler itemHandler = new ItemStackHandler(2) {

        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
            return true;
        }

        @Nonnull
        @Override
        public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
            if (!isItemValid(slot, stack)) {
                return stack;
            }
            return super.insertItem(slot, stack, simulate);
        }
    };

    protected final ContainerData data;
    private int cookProgress = 0;
    private int cookTime = 200;

    // Constructor Method

    public CrucibleBlockEntity(BlockPos pos, BlockState state) {
        super(RegistryBE.CRUCIBLE_TILE.get(), pos, state);
        this.lazyHandler = LazyOptional.of(() -> itemHandler);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> CrucibleBlockEntity.this.cookProgress;
                    case 1 -> CrucibleBlockEntity.this.cookTime;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> CrucibleBlockEntity.this.cookProgress = value;
                    case 1 -> CrucibleBlockEntity.this.cookTime = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    // Compound Methods

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyHandler.invalidate();
    }

    @Override
    public void load(@NotNull CompoundTag compTag) {
        super.load(compTag);
        itemHandler.deserializeNBT(compTag.getCompound("inventory"));
        cookProgress = compTag.getInt("crucible.cookProgress");
        cookTime = compTag.getInt("crucible.cookTime");
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putInt("crucible.cookProgress", cookProgress);
        tag.putInt("crucible.cookTime", cookTime);
        super.saveAdditional(tag);
    }

    // Capability Method

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyHandler.cast();
        } else {
            return super.getCapability(cap, side);
        }
    }

    // Tick Methods

    protected boolean hasInput() {
        return !itemHandler.getStackInSlot(0).isEmpty();
    }

    protected boolean hasOutput() {
        return !itemHandler.getStackInSlot(1).isEmpty();
    }

    public boolean getIsHeated() {
        if(this.level == null) {return false;}
        return isHeated(this.level, this.worldPosition);
    }

    public static void cookTick(Level world, BlockPos pos, BlockState state, CrucibleBlockEntity crucible) {

        boolean isHeated = crucible.isHeated(world, pos);


        ItemStackHandler handler = crucible.itemHandler;
        SimpleContainer inventory = new SimpleContainer(handler.getSlots());

        // ------ |
        // Method |
        // ------ |

        for(int i =0; i < handler.getSlots(); i++) { inventory.setItem(i, handler.getStackInSlot(i));}
        if(crucible.level != null) {

            if (crucible.hasInput() && isHeated) {

                Optional<CrucibleRecipe> recipe = crucible.level.getRecipeManager().getRecipeFor(CrucibleRecipe.TYPE, new RecipeWrapper(handler), world);
                recipe.ifPresent(Recipe -> {
                    ItemStack result = Recipe.getResultItem();
                    if ((!crucible.hasOutput() || handler.getStackInSlot(1).getItem() == result.getItem())) {
                        if (crucible.cookProgress == crucible.cookTime) {
                            handler.extractItem(0, 1, false);
                            handler.insertItem(1, result, false);
                            crucible.cookProgress = 0;
                            world.setBlockAndUpdate(crucible.getBlockPos(), crucible.getBlockState().setValue(CrucibleBlock.LIT, false));
                        } else {
                            crucible.cookProgress++;
                            world.setBlockAndUpdate(crucible.getBlockPos(), crucible.getBlockState().setValue(CrucibleBlock.LIT, true));
                        }
                    }
                });
            } else {
                crucible.cookProgress = 0;
                world.setBlockAndUpdate(crucible.getBlockPos(), crucible.getBlockState().setValue(CrucibleBlock.LIT, false));
            }
        }
    }

    // ------------ |
    // Menu Methods |
    // ------------ |

    @Override
    public Component getName() {
        return name;
    }

    @Override
    public Component getDisplayName() {
        return getName();
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInv, Player player) {
        return new CrucibleMenu(id, playerInv, this, data);
    }

    public ContainerData getData() {
        return CrucibleBlockEntity.this.data;
    }
}
