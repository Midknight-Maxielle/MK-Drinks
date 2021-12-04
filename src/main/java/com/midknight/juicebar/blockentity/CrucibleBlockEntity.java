package com.midknight.juicebar.blockentity;

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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class CrucibleBlockEntity extends JuiceBlockEntity implements HeatableEntityInterface {

    // ------ |
    // Fields |
    // ------ |

    private final ItemStackHandler handler = createHandler();
    private final LazyOptional<ItemStackHandler> lazyHandler = LazyOptional.of(() -> handler);

    protected int cookProgress;
    protected int cookTimeTotal = 150;
    protected final ContainerData crucibleData;
    protected RecipeType<CrucibleRecipe> crucibleRecipe;

    // ------------ |
    // Constructors |
    // ------------ |

    public CrucibleBlockEntity(BlockPos pos, BlockState state) {
        super(JuiceTiles.CRUCIBLE_TILE.get(), pos, state);
        this.crucibleData = createContainerData();
    }

    public CrucibleBlockEntity(BlockPos pos, BlockState state, RecipeType<CrucibleRecipe> recipeType) {
        super(JuiceTiles.CRUCIBLE_TILE.get(), pos, state);
        this.crucibleRecipe = recipeType;
        this.crucibleData = createContainerData();
    }
    // ---------------- |
    // Compound Methods |
    // ---------------- |

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        cookProgress = compound.getInt("cookProgress");
        cookTimeTotal = compound.getInt("cookTimeTotal");
        handler.deserializeNBT(compound.getCompound("inventory"));
    }

    @Override
    public CompoundTag save(CompoundTag compound) {
        super.save(compound);
        compound.putInt("cookProgress", cookProgress);
        compound.putInt("cookTimeTotal", cookTimeTotal);
        compound.put("inventory", handler.serializeNBT());

        return compound;
    }

    // ----------------- |
    // Capability Method |
    // ----------------- |

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyHandler.cast();
        } else {
            return super.getCapability(cap, side);
        }
    }

    // ------------ |
    // Data Methods |
    // ------------ |

    private ContainerData createContainerData() {
        return new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> CrucibleBlockEntity.this.cookProgress;
                    case 1 -> CrucibleBlockEntity.this.cookTimeTotal;
                    default -> 0;
                };
            }
            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> CrucibleBlockEntity.this.cookProgress = value;
                    case 1 -> CrucibleBlockEntity.this.cookTimeTotal = value;
                }
            }
            @Override
            public int getCount() {
                return 2;
            }
        };
    }

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

    public ContainerData getCrucibleData() {
        return crucibleData;
    }

    // ------------ |
    // Tick Methods |
    // ------------ |

    protected boolean hasInput() {
        return handler.getStackInSlot(0).isEmpty();
    }

    public boolean isHeated() {
        if (level != null) {
            return isHeated(level, worldPosition);
        }
        return false;
    }

    public static void cookTick(Level world, BlockPos pos, BlockState state, CrucibleBlockEntity crucible) {

        // ------ |
        // Fields |
        // ------ |

        ItemStackHandler handler = crucible.handler;
        SimpleContainer inventory = new SimpleContainer(handler.getSlots());
        Optional<CrucibleRecipe> recipe;

        // ------ |
        // Method |
        // ------ |

        for(int i =0; i < handler.getSlots(); i++) { inventory.setItem(i, handler.getStackInSlot(i));}
        if(crucible.hasInput() && crucible.isHeated()) {
            recipe = world.getRecipeManager().getRecipeFor(crucible.crucibleRecipe, new RecipeWrapper(handler), world);
            recipe.ifPresent(Recipe -> {
                ItemStack result = Recipe.getResultItem();
                //crucibleProcess unpack here
                if(crucible.isHeated()) {
                    if(crucible.cookProgress >= crucible.cookTimeTotal) {
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
