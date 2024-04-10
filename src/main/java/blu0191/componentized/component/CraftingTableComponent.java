package blu0191.componentized.component;

import blu0191.componentized.block.ComposableBlock;
import blu0191.componentized.handler.CraftingComponentScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CraftingTableComponent extends Component {
    Text TITLE = Text.translatable("container.crafting");
    public CraftingTableComponent() {

    }

    public String getName() { return "CraftingTable"; }

    /*public ActionResult onInteractMob(ComposableEntity entity, PlayerEntity player, Hand hand) {
        //useCraftingTable()
    }*/
    public ActionResult onUseBlock(ComposableBlock block, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        System.out.println("useCraftingTable");
        if (world.isClient) {
            System.out.println("isClient");
            return ActionResult.SUCCESS;
        } else {
            System.out.println("!isClient");
            player.openHandledScreen(createScreenHandlerFactory(world, pos));
            player.incrementStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
            return ActionResult.CONSUME;
        }
    }
    public NamedScreenHandlerFactory createScreenHandlerFactory(World world, BlockPos pos) {
        System.out.println("createScreenHandlerFactory");
        return new SimpleNamedScreenHandlerFactory((syncId, inventory, player) -> {
            return new CraftingComponentScreenHandler(syncId, inventory, ScreenHandlerContext.create(world, pos));
        }, TITLE);
    }
}
