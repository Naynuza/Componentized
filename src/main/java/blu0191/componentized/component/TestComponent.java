package blu0191.componentized.component;

import blu0191.componentized.block.ComposableBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class TestComponent implements IComponent {
    Text text;
    public TestComponent() {
        this.text = Text.literal("No coords found");
    }
    Text getText() {
        return this.text;
    }
    public void setText(Text text) {
        this.text = text;
    }
    public ActionResult onUseBlock(ComposableBlock block, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        } else {
            //arbitrarily launch the rust WLO server or something
            return ActionResult.CONSUME;
        }
    }
    public void onPlacedBlock(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        this.text = Text.of("Placed at X:" + pos.getX() + " Y:" + pos.getY() + " Z:" + pos.getZ());
    }
}
