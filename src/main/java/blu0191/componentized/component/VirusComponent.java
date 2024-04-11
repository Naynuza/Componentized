package blu0191.componentized.component;

import blu0191.componentized.Componentized;
import blu0191.componentized.block.ComposableBlock;
import blu0191.componentized.entity.ComposableBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class VirusComponent extends Component {
    public String getName() { return "Virus"; }

    public ActionResult onUseBlock(ComposableBlock block, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        } else {
            world.addFireworkParticle(pos.getX(), pos.getY(), pos.getZ(), 0D, 0D, 0D, null);
            spread(world, pos.north());
            spread(world, pos.east());
            spread(world, pos.south());
            spread(world, pos.west());
            return ActionResult.CONSUME;
        }
    }

    public void spread(World world, BlockPos pos) {
        world.breakBlock(pos, true);
        world.setBlockState(pos, Componentized.VIRUS_COMPOSABLE_BLOCK.getDefaultState());

        var be = (ComposableBlockEntity) world.getBlockEntity(pos);
        be.components.addIfTypeAbsent(VirusComponent.class);
    }
}
