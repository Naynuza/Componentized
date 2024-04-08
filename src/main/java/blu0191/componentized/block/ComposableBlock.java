package blu0191.componentized.block;

import blu0191.componentized.component.IComponent;
import blu0191.componentized.component.TestComponent;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CrafterBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.Merchant;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ComposableBlock extends Block {
    public ArrayList<IComponent> components;
    MapCodec<ComposableBlock> CODEC = createCodec(ComposableBlock::new);
    public ComposableBlock(Settings settings) {
        super(settings);
        components = new ArrayList<>();
    }

    public MapCodec<? extends ComposableBlock> getCodec() {
        return CODEC;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        for (IComponent component : components) {
            component.onUseBlock(this, state, world, pos, player, hand, hit);
        }
        return ActionResult.success(world.isClient);
    }
}
