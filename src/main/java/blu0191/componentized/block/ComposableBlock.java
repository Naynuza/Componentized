package blu0191.componentized.block;

import blu0191.componentized.component.Component;
import blu0191.componentized.entity.ComposableBlockEntity;
import blu0191.componentized.generic.ExtendsList;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ComposableBlock extends BlockWithEntity {
    MapCodec<ComposableBlock> CODEC = createCodec(ComposableBlock::new);

    public ComposableBlock(Settings settings) {
        super(settings);
    }

    public MapCodec<? extends ComposableBlock> getCodec() {
        return CODEC;
    }

    public ComposableBlockEntity getComposableBlockEntity(World world, BlockPos pos) {
        var blockEntity = world.getBlockEntity(pos);

        if (!(blockEntity instanceof ComposableBlockEntity)) return null;

        return (ComposableBlockEntity) blockEntity;
    }

    public ExtendsList<Component> getComponents(World world, BlockPos pos) {
        return getComposableBlockEntity(world, pos).components;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            var blockEntity = world.getBlockEntity(pos);

            if (!(blockEntity instanceof ComposableBlockEntity)) {
                player.sendMessage(Text.of("The BlockEntity of this ComposableBlock is missing!"));
                return ActionResult.FAIL;
            }

            var components = ((ComposableBlockEntity) blockEntity).components;

            for (Component component : components.stream().filter(Component::enabled).toList()) {
                component.onUseBlock(this, state, world, pos, player, hand, hit);
            }
        }

        return ActionResult.success(world.isClient);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ComposableBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
