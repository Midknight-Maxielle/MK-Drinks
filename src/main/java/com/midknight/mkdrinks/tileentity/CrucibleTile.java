package com.midknight.mkdrinks.tileentity;

import com.midknight.mkdrinks.block.CrucibleBlock;
import com.midknight.mkdrinks.data.recipes.CrucibleRecipe;
import com.midknight.mkdrinks.data.recipes.DrinksRecipes;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class CrucibleTile extends TileEntity implements ITickableTileEntity, IHeatableTile {

    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    protected final IIntArray crucibleData;
    protected final IRecipeType<CrucibleRecipe> recipeType;

    // // // // CONSTRUCTOR // // // //

    public CrucibleTile(TileEntityType<?> tileEntity, IRecipeType<CrucibleRecipe> recipeType) {
        super(tileEntity);
        this.recipeType = recipeType;
        this.crucibleData = createIntArray();
    }

    public CrucibleTile() {
    this(MKTileEntities.CRUCIBLE_TILE.get(), CrucibleRecipe.TYPE);
    }

    // // // // NBT PKT MANAGEMENT // // // //

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        processElapsed = nbt.getInt("ProcessElapsed");
        processTotal = nbt.getInt("ProcessTotal");
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        super.write(nbt);
        nbt.putInt("ProcessElapsed", processElapsed);
        nbt.putInt("ProcessTotal", processTotal);
        nbt.put("inv", itemHandler.serializeNBT());

        return nbt;
    }

    // // // // CAPABILITY & HANDLING // // // //

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }

        return super.getCapability(cap, side);
    }

    // // // // DATA ARRAY MANAGEMENT // // // //

    public IIntArray getCrucibleData() { return crucibleData; }

    // // // // TICKING FUNCTIONALITY // // // //
    @Override
    public void tick() {

        if(world != null && !world.isRemote()) {

            Inventory inv = new Inventory(itemHandler.getSlots());
            for(int i = 0; i < itemHandler.getSlots(); i++) {
                inv.setInventorySlotContents(i, itemHandler.getStackInSlot(i));
            }

            if (hasInput() && isHeated()) {

                Optional<CrucibleRecipe> recipe = Optional.ofNullable(this.world.getRecipeManager().getRecipe(
                        this.recipeType,
                        new RecipeWrapper(itemHandler),
                        world).orElse(null));

                recipe.ifPresent(iRecipe -> {
                    processActive = true;
                    ItemStack result = iRecipe.getRecipeOutput();
                    crucibleProcess(result, this.isHeated(world, pos));
                });
            } else {
                processElapsed = 0;
                processActive = false;
                this.world.setBlockState(this.getPos(),this.getBlockState().with(CrucibleBlock.LIT, false));
            }

        } else {
            return;
        }
    }

    // - - - - - // Machine Processes // - - - - - //

    protected boolean processActive;

    protected int processElapsed;
    protected int processTotal;

    private void crucibleProcess(ItemStack result, boolean heated) {

        processTotal = 200;

        if(heated && processActive) {
            if(processElapsed == processTotal) {

                itemHandler.extractItem(0,1,false);
                itemHandler.insertItem(1,result,false);

                processElapsed = 0;
                processActive = false;

                this.world.setBlockState(this.getPos(),this.getBlockState().with(CrucibleBlock.LIT, false));

            } else {

                this.world.setBlockState(this.getPos(),this.getBlockState().with(CrucibleBlock.LIT, true));

                ++processElapsed;
            }
        } else {
            processElapsed = 0;
            processActive = false;

        }
    }

    // - - - - - // I/O Management // - - - - - //

    private boolean hasInput() {
        if(!(itemHandler.getStackInSlot(0).isEmpty())) {
            return true;
        }
        return false;
    }

    // - - - - - // Heat Management // - - - - - //

    public boolean isHeated() {
        if(world == null) {
            return false;
        } else {
            return this.isHeated(world, pos);
        }
    };

    // - - - - - // IIntArray Creation // - - - - - //

    private IIntArray createIntArray() {
        return new IIntArray() {
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
            public int size() {
                return 2;
            }
        };
    }

    // - - - - - // create Methods // - - - - - //

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(2) {

            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
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
