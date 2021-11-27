package com.midknight.mkdrinks.block;

import com.midknight.mkdrinks.block.state.CrucibleLift;
import com.midknight.mkdrinks.container.CrucibleContainer;
import com.midknight.mkdrinks.tileentity.CrucibleTile;
import com.midknight.mkdrinks.tileentity.DrinkTiles;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.BlockTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;

public class CrucibleBlock extends HorizontalBlock {

    public static final EnumProperty<CrucibleLift> LIFT = EnumProperty.create("lift",CrucibleLift.class);
    public static final BooleanProperty LIT = BooleanProperty.create("lit");
    public static final DirectionProperty HORIZONTAL_FACING = HorizontalBlock.HORIZONTAL_FACING;
    protected static final VoxelShape SHAPE = makeCuboidShape(3.0D,0.0D,3.0D,13.0D,12.0D,13.0D);
    protected static final VoxelShape SHAPE_LIFTED =
            VoxelShapes.or(SHAPE, Block.makeCuboidShape(0.0D,-1.0D,0.0D,16.0D,0.0D,16.0D));

    public CrucibleBlock(Properties builder) {

        super(builder);
        this.setDefaultState(this.stateContainer.getBaseState()
                .with(HORIZONTAL_FACING,Direction.NORTH)
                .with(LIFT,CrucibleLift.NONE)
                .with(LIT,false));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING, LIFT, LIT);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos pos = context.getPos();
        World world = context.getWorld();
        BlockState state = this.getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing());

        return state.with(LIFT, getLiftState(world, pos));
    }

    private CrucibleLift getLiftState(IWorld world, BlockPos pos) {
        if (world.getBlockState(pos.down()).getBlock().isIn(BlockTags.CAMPFIRES)) {
            return CrucibleLift.LIFTED;
        }
        return CrucibleLift.NONE;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos,
                                             PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {

        if(!world.isRemote()) {
            TileEntity tileEntity = world.getTileEntity(pos);
            if(tileEntity instanceof CrucibleTile) {
                INamedContainerProvider containerProvider = createContainerProvider(world, pos);
                NetworkHooks.openGui(((ServerPlayerEntity)player), containerProvider, tileEntity.getPos());
            } else { throw new IllegalStateException("Container provider missing!"); }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if(state.get(LIFT) == CrucibleLift.LIFTED) {
            return SHAPE_LIFTED;
        }
        return SHAPE;
    }

    private INamedContainerProvider createContainerProvider(World world, BlockPos pos) {
        return new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName() {
                return new TranslationTextComponent("screen.mkdrinks.crucible");
            }
            @Nullable
            @Override
            public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
                CrucibleTile crucibleTile = (CrucibleTile) world.getTileEntity(pos);
                IIntArray crucibleData = crucibleTile.getCrucibleData();
                return new CrucibleContainer(i, world, pos, playerInventory, playerEntity, crucibleData, crucibleTile);
            }
        };
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return DrinkTiles.CRUCIBLE_TILE.get().create();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip.mkdrinks.crucible_shift"));
        } else {
            tooltip.add(new TranslationTextComponent("tooltip.mkdrinks.crucible"));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
