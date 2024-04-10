package blu0191.componentized.block;

import blu0191.componentized.component.CraftingTableComponent;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CraftingTableComposableBlock extends ComposableBlock {
    public CraftingTableComposableBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        getComponents(world, pos).addIfTypeAbsent(CraftingTableComponent.class);
    }
}
