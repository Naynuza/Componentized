package blu0191.componentized;

import blu0191.componentized.block.TestComposableBlock;
import blu0191.componentized.block.CraftingTableComposableBlock;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class Componentized implements ModInitializer {
    public static final TestComposableBlock TEST_COMPOSABLE_BLOCK = new TestComposableBlock(FabricBlockSettings.create().strength(4.0f));
    public static final CraftingTableComposableBlock CRAFTING_TABLE_COMPOSABLE_BLOCK = new CraftingTableComposableBlock(FabricBlockSettings.create().strength(1.0f));

    @Override
    public void onInitialize() {
        Registry.register(Registries.BLOCK, new Identifier("componentized", "test_composable_block"), TEST_COMPOSABLE_BLOCK);
        Registry.register(Registries.ITEM, new Identifier("componentized", "test_composable_block"), new BlockItem(TEST_COMPOSABLE_BLOCK, new Item.Settings()));
        Registry.register(Registries.BLOCK, new Identifier("componentized", "crafting_table_composable_block"), CRAFTING_TABLE_COMPOSABLE_BLOCK);
        Registry.register(Registries.ITEM, new Identifier("componentized", "crafting_table_composable_block"), new BlockItem(CRAFTING_TABLE_COMPOSABLE_BLOCK, new Item.Settings()));
    }
}
