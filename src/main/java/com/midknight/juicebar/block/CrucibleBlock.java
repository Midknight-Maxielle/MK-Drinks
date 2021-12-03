package com.midknight.juicebar.block;

import com.midknight.juicebar.block.state.CrucibleLift;
import com.midknight.juicebar.container.CrucibleContainer;
import com.midknight.juicebar.tileentity.CrucibleTile;
import com.midknight.juicebar.registry.JuiceTiles;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
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
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.fmllegacy.network.NetworkHooks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class CrucibleBlock extends BaseEntityBlock {

    public static final EnumProperty<CrucibleLift> LIFT = EnumProperty.create("lift", CrucibleLift.class);
    public static final BooleanProperty LIT = BooleanProperty.create("lit");
    public static final DirectionProperty HORIZONTAL_FACING = HorizontalDirectionalBlock.FACING;
    protected static final VoxelShape SHAPE = box(3.0D, 0.0D, 3.0D, 13.0D, 12.0D, 13.0D);
    protected static final VoxelShape SHAPE_LIFTED =
            Shapes.or(SHAPE, Block.box(0.0D, -1.0D, 0.0D, 16.0D, 0.0D, 16.0D));

    public CrucibleBlock(Properties builder) {

        super(builder);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(HORIZONTAL_FACING, Direction.NORTH)
                .setValue(LIFT, CrucibleLift.NONE)
                .setValue(LIT, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING, LIFT, LIT);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        Level world = context.getLevel();
        BlockState state = this.defaultBlockState().setValue(HORIZONTAL_FACING, context.getHorizontalDirection());

        return state.setValue(LIFT, getLiftState(world, pos));
    }

    private CrucibleLift getLiftState(LevelAccessor world, BlockPos pos) {
        if (world.getBlockState(pos.below()).getBlock().getTags().contains(BlockTags.CAMPFIRES)) {
            return CrucibleLift.LIFTED;
        }
        return CrucibleLift.NONE;
    }

    @Override
    @Nonnull
    @ParametersAreNonnullByDefault
    public InteractionResult use(BlockState state, Level world, BlockPos pos,
                                 Player player, InteractionHand handIn, BlockHitResult hit) {

        if (!world.isClientSide()) {
            BlockEntity tileEntity = world.getBlockEntity(pos);
            if (tileEntity instanceof CrucibleTile) {
                MenuProvider containerProvider = createContainerProvider(world, pos);
                NetworkHooks.openGui(((ServerPlayer) player), containerProvider, tileEntity.getBlockPos());
            } else {
                throw new IllegalStateException("Container provider missing!");
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    @Nonnull
    @ParametersAreNonnullByDefault
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    @Nonnull
    @ParametersAreNonnullByDefault
    public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        if (state.getValue(LIFT) == CrucibleLift.LIFTED) {
            return SHAPE_LIFTED;
        }
        return SHAPE;
    }

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
            public AbstractContainerMenu createMenu(int i, Inventory playerInventory, Player playerEntity) {

                CrucibleTile crucibleTile = (CrucibleTile) world.getBlockEntity(pos);
                ContainerData crucibleData = null;
                if (crucibleTile != null) {
                    crucibleData = crucibleTile.getCrucibleData();
                }
                return new CrucibleContainer(i, world, pos, playerInventory, playerEntity, crucibleData, crucibleTile);
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
        return JuiceTiles.CRUCIBLE_TILE.get().create(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> entityType) {

        return createTickerHelper(entityType, JuiceTiles.CRUCIBLE_TILE.get(), CrucibleTile::tick);
    }
}

