package blu0191.componentized.block;

import blu0191.componentized.component.VirusComponent;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class VirusComposableBlock extends ComposableBlock {
    public VirusComposableBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        getComponents(world, pos).addIfTypeAbsent(VirusComponent.class);
    }
}