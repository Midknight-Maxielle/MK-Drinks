package com.midknight.owmix.block;

import com.midknight.owmix.block.state.CrucibleLift;
import com.midknight.owmix.menu.CrucibleMenu;
import com.midknight.owmix.blockentity.CrucibleBlockEntity;
import com.midknight.owmix.registry.RegistryBE;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class CrucibleBlock extends BaseEntityBlock {

    // ------ |
    // Fields |
    // ------ |

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty LIT = BooleanProperty.create("lit");
    public static final EnumProperty<CrucibleLift> CRUCIBLE_LIFT = EnumProperty.create("lift", CrucibleLift.class);

    protected static final VoxelShape SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 12.0D, 13.0D);
    protected static final VoxelShape LIFTED_SHAPE = Shapes.or(SHAPE, Block.box(0.0D, -1.0D, 0.0D, 16.0D, 0.0D, 16.0D));

    // ------------------- |
    // Constructor Methods |
    // ------------------- |

    public CrucibleBlock() {
        super(Properties.of(Material.METAL).strength(0.5F, 4.0F).sound(SoundType.LANTERN));
        this.registerDefaultState(this.stateDefinition.any()
            .setValue(FACING, Direction.NORTH)
            .setValue(CRUCIBLE_LIFT, CrucibleLift.NONE)
            .setValue(LIT, false));
    }

    // ---------- |
    // Use Method |
    // ---------- |

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos position, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide()) {
            BlockEntity blockEntity = world.getBlockEntity(position);
            if (blockEntity instanceof CrucibleBlockEntity) {
                CrucibleBlockEntity crucibleEntity = (CrucibleBlockEntity) blockEntity;
                NetworkHooks.openGui((ServerPlayer) player, crucibleEntity, blockEntity.getBlockPos());
            } else {
                throw new IllegalStateException("Menu provider missing!");
            }
        } else {
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.SUCCESS;
    }

    // ------------- |
    // Shape Methods |
    // ------------- |

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos position, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        if (state.getValue(CRUCIBLE_LIFT) == CrucibleLift.LIFTED) {
            return LIFTED_SHAPE;
        } else {
            return SHAPE;
        }
    }

    // ------------- |
    // State Methods |
    // ------------- |

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        Level world = context.getLevel();
        BlockState state = this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());

        return state.setValue(CRUCIBLE_LIFT, getLiftState(world, pos));
    }

    private boolean isSource(Block block) {
        if((block == Blocks.LAVA) ||
                (block == Blocks.CAMPFIRE) ||
                (block == Blocks.SOUL_CAMPFIRE) ||
                (block == Blocks.FIRE)) {
            return true;
        } else {
            return false;
        }
    }

    private CrucibleLift getLiftState(LevelAccessor world, BlockPos pos) {
        if(isSource(world.getBlockState(pos.below()).getBlock())) {
            return CrucibleLift.LIFTED;
        }
        return CrucibleLift.NONE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, CRUCIBLE_LIFT, LIT);
    }

    // Create Methods |

    private MenuProvider createContainerProvider(Level world, BlockPos pos) {
        return new MenuProvider() {

            @Override
            @Nonnull
            public Component getDisplayName() {
                return new TranslatableComponent("screen.juicebar.crucible");
            }

            @Override
            @Nonnull
            @ParametersAreNonnullByDefault
            public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player playerEntity) {

                CrucibleBlockEntity crucibleBlockEntity = (CrucibleBlockEntity) world.getBlockEntity(pos);
                if (crucibleBlockEntity != null) {
                    ContainerData crucibleData = crucibleBlockEntity.getData();
                    return new CrucibleMenu(id, playerInventory, crucibleBlockEntity, crucibleData);
                }
                throw new IllegalStateException("Menu cannot be created - crucibleBlockEntity = null!");
            }
        };
    }

    @Override
    @ParametersAreNonnullByDefault
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter worldIn, List<Component> tooltip, TooltipFlag flagIn) {

        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslatableComponent("tooltip.juicebar.crucible_shift"));
        } else {
            tooltip.add(new TranslatableComponent("tooltip.juicebar.crucible"));
        }
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return RegistryBE.CRUCIBLE_TILE.get().create(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> entityType) {

        return createTickerHelper(entityType, RegistryBE.CRUCIBLE_TILE.get(), CrucibleBlockEntity::cookTick);
    }
}

