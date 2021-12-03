package com.midknight.juicebar.tileentity;

import com.midknight.juicebar.block.CrucibleBlock;
import com.midknight.juicebar.data.recipes.CrucibleRecipe;
import com.midknight.juicebar.registry.JuiceTiles;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class CrucibleTile extends BlockEntity implements IHeatableTile {

    // Fields
    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    protected RecipeType<CrucibleRecipe> recipeType;
    protected final ContainerData crucibleData;
    protected boolean processActive;
    protected int processElapsed;
    protected int processTotal;


    // Constructors

    public CrucibleTile(BlockPos pos, BlockState state) {
        super(JuiceTiles.CRUCIBLE_TILE.get(), pos, state);
        this.crucibleData = createIntArray();
    }

    public CrucibleTile(BlockPos pos, BlockState state, RecipeType<CrucibleRecipe> recipeType) {
        super(JuiceTiles.CRUCIBLE_TILE.get(), pos, state);
        this.crucibleData = createIntArray();
        this.recipeType = recipeType;
    }

    //NBT Methods


    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        processElapsed = nbt.getInt("ProcessElapsed");
        processTotal = nbt.getInt("ProcessTotal");
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
    }

    @Override
    public CompoundTag save(CompoundTag nbt) {
        super.save(nbt);
        nbt.putInt("ProcessElapsed", processElapsed);
        nbt.putInt("ProcessTotal", processTotal);
        nbt.put("inv", itemHandler.serializeNBT());

        return nbt;
    }

    // Capability & Handling Method
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        return super.getCapability(cap, side);
    }

    // Data Array Management Method
    public ContainerData getCrucibleData() {
        return crucibleData;
    }

    // Tick Method
    public static void tick(Level level, BlockPos pos, BlockState state, CrucibleTile crucibleTile) {
        ItemStackHandler itemHandler = crucibleTile.itemHandler;
        if(level != null && !level.isClientSide()) {
            SimpleContainer inv = new SimpleContainer(itemHandler.getSlots());

            for(int i = 0; i < itemHandler.getSlots(); i++) {
                inv.setItem(i, itemHandler.getStackInSlot(i));
            }
            if (hasInput() && isHeated()) {
                Optional<CrucibleRecipe> recipe = level.getRecipeManager().getRecipeFor(
                        this.recipeType,
                        new RecipeWrapper(itemHandler), level);

                recipe.ifPresent(iRecipe -> {
                    crucibleTile.processActive = true;
                    ItemStack result = iRecipe.getResultItem();
                    crucibleProcess(result, crucibleTile.isHeated(level, worldPosition));
                });
            } else {
                processElapsed = 0;
                processActive = false;
                level.setBlockAndUpdate(
                        crucibleTile.getBlockPos(),
                        crucibleTile.getBlockState().setValue(CrucibleBlock.LIT, false));
            }
        }
    }

    // Processing Methods

    private static void crucibleProcess(ItemStack result, boolean heated) {
        processTotal = 200;

        if(this.level != null) {
            if (heated && processActive) {
                if (processElapsed == processTotal) {
                    itemHandler.extractItem(0, 1, false);
                    itemHandler.insertItem(1, result, false);
                    processElapsed = 0;
                    processActive = false;
                    this.level.setBlockAndUpdate(this.getBlockPos(), this.getBlockState().setValue(CrucibleBlock.LIT, false));
                } else {
                    this.level.setBlockAndUpdate(this.getBlockPos(), this.getBlockState().setValue(CrucibleBlock.LIT, true));
                    ++processElapsed;
                }
            } else {
                processElapsed = 0;
                processActive = false;
            }
        }
    }

    // I/O Methods
    private static boolean hasInput() {
        return !(itemHandler.getStackInSlot(0).isEmpty());
    }

    // Heat Method
    public static boolean isHeated() {
        if(level == null) {
            return false;
        } else {
            return isHeated(level, worldPosition);
        }
    }

    // Data Management Array Init Method
    private ContainerData createIntArray() {
        return new ContainerData() {
            @Override
            public int get(int index) {
                switch (index) {
                    case 0:
                        return CrucibleTile.this.processElapsed;
                    case 1:
                        return CrucibleTile.this.processTotal;
                    default:
                        return 0;
                }
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0:
                        CrucibleTile.this.processElapsed = value;
                        break;
                    case 1:
                        CrucibleTile.this.processTotal = value;
                        break;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    // ItemHandler init Method
    private ItemStackHandler createHandler() {
        return new ItemStackHandler(2) {

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
    }
}
