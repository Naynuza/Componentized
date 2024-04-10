package blu0191.componentized.component;

import blu0191.componentized.block.ComposableBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class Component {
    private boolean isEnabled = true;

    public String getName() {
        return "Component";
    }

    public void enable() {
        isEnabled = true;
    }

    public void disable() {
        isEnabled = false;
    }

    public boolean enabled() {
        return isEnabled;
    }

    public void readNbt(NbtCompound nbt) {

    }

    public void writeNbt(NbtCompound nbt) {

    }

    public ActionResult onUseBlock(ComposableBlock block, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return ActionResult.success(world.isClient);
    }
}
